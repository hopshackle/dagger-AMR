package amr.classic

import dagger.core._
import amr.Sentence
import amr.ImportConcepts._

class ClassicExpert extends HeuristicPolicy[Sentence, ClassicAction, ClassicTransitionState] {

  val debug = false

  override def chooseTransition(data: Sentence, state: ClassicTransitionState): ClassicAction = {
    state.phase match {
      case 1 =>
        if (data.positionToAMR.contains(state.nodesToProcess.head))
          NextNode(conceptForCurrentNode(data, state))
        else
          DeleteNode
      case 2 =>
        // AssignToFragment iff:
        // Leaf node in AMR && Parent AMR is unassigned to token && has a sibling that has its own fragment already
        // && that sibling is also a leaf node
        // *** Removed constraint on fragments needing to be leaf nodes. No longer true
        val amrKey = data.positionToAMR.getOrElse(state.nodesToProcess.head, "UNK")
        val amrParents = data.amr.get.parentsOf(amrKey)
        val unmappedParents = amrParents filterNot data.AMRToPosition.contains
        val mappedChildrenOfUnmappedParents = unmappedParents flatMap data.amr.get.childrenOf filter data.AMRToPosition.contains
        val eligibleFragments = mappedChildrenOfUnmappedParents map data.AMRToPosition filter state.fragments.contains
        if (eligibleFragments.nonEmpty)
          AssignToFragment(eligibleFragments(0))
        else
          CreateFragment
      case 3 =>

        def getCompositeNode(amrKey: String, acc: String): String = {
          // we find the node, plus all unmapped nodes further up the tree iff there is a single line of ascent
          // in other words, we stop once we hit a node that has other children, or which has more than one parent
          val amr = data.amr.get
          val parents = amr.parentsOf(amrKey)
          val unmappedParents = parents filterNot data.AMRToPosition.contains filterNot state.amrMap.contains
          val concept = amr.nodes(amrKey)
          val newAcc = if (acc == "") amr.nodes(amrKey) else amr.nodes(amrKey) + ":" + acc
          if (parents.isEmpty || parents.size > 1 || unmappedParents.isEmpty)
            newAcc
          else {
            val relation = amr.arcs((unmappedParents(0), amrKey))
            getCompositeNode(unmappedParents(0), relation + ":" + newAcc)
          }
        }
        /*
         * Possibilities are:
         *  All the nodes in the fragment have the same parent, and this is unmapped
         *      Ideal situation. AddParent with the correct composite node.
         *  All the nodes in the fragment have the same parent, and it is mapped
         *      Ideal if we have just one node - we use NoParent.
         *      Non-ideal for more than one node. Not much we can do. Insert a name node, and hope for the best.
         *  The nodes in the fragment do not have a common parent, but only one of them is unmapped
         *      Non-ideal, but straightforward. Insert a composite node in line with the sole unmapped parent.
         *  The nodes in the fragment do not have a common parent, and more than one is unmapped
         *      Non-ideal. Pick the first unmapped parent, and insert the relevant composite node
         *  The nodes in the fragment do not have a common parent, and all of them are mapped
         *      Non-ideal. Not much we can do. Insert a name node, and hope for the best.
         */
        val fragmentNodes = state.fragments.getOrElse(state.nodesToProcess.head, List.empty[Int])
        val fragmentAMR = fragmentNodes map { data.positionToAMR.getOrElse(_, "U") }
        val fragmentParents = fragmentAMR flatMap data.amr.get.parentsOf distinct
        val unmappedFragmentParents = fragmentParents filterNot data.AMRToPosition.contains
        val fragmentNodesAllLeaves = fragmentAMR forall data.amr.get.isLeafNode
        (fragmentParents.size, unmappedFragmentParents.size, fragmentNodesAllLeaves) match {
          case (_, 1, _) => AddParent(getCompositeNode(unmappedFragmentParents(0), ""))
    //      case (_, 1, false) => InsertNode(getCompositeNode(unmappedFragmentParents(0), ""))
          case (1, 0, _) => NoParent
          case (_, 0, _) => AddParent("and")
    //      case (_, 0, false) => InsertNode("and")
          case (_, _, _) => AddParent(getCompositeNode(unmappedFragmentParents(0), ""))
   //       case (_, _, false) => InsertNode(getCompositeNode(unmappedFragmentParents(0), ""))
        }
      case 4 =>
        val allNodeMappings = data.positionToAMR.toList ++ state.amrMap.toList.map(i => (i._2, i._1))
        val from = allNodeMappings filter (i => i._1 == state.nodePair._1) map (_._2)
        val to = allNodeMappings filter (i => i._1 == state.nodePair._2) map (_._2)
        /*
         * allNodeMappings contains all node -> AMRKey mappings
         * from then contains those that map to the first of the nodePair, and to contains those that map to the second
         * 
         * allAMRNodes then contains all these mappings, and we look for ALL arcs between them....aha....we actually
         * only want arcs that start at one and go to the other!
         */
        if (from.isEmpty || to.isEmpty)
          NextEdge(0)
        else {
          val allRelations = for {
            f <- from
            t <- to
            direction <- List(1, 2)
            key = if (direction == 1) (f, t) else (t, f)
            if data.amr.get.arcs contains key
          } yield (key._1, key._2, data.amr.get.arcs(key))
          if (allRelations.isEmpty)
            NextEdge(0)
          else {
            if ((from contains allRelations(0)._1) && (to contains allRelations(0)._2))
              NextEdge(relationIndex(allRelations(0)._3))
            else
              NextEdge(-relationIndex(allRelations(0)._3))

          }
        }
    }
  }

  def conceptForCurrentNode(data: Sentence, state: ClassicTransitionState): Int = {
    val conceptKey = data.positionToAMR.getOrElse(state.nodesToProcess.head, "NULL")
    val concept = quote.replaceAllIn(data.amr.get.nodes.getOrElse(conceptKey, "NULL"), "")
    if (debug) {
      println("Concept: " + conceptKey + " -> " + concept)
      println("Index: " + conceptIndex(concept))
    }
    if (!ClassicTransitionSystem.preferKnown) {
      if (concept == state.currentGraph.nodeLemmas(state.nodesToProcess.head))
        -1
      else if (concept == state.currentGraph.nodeLemmas(state.nodesToProcess.head) + "-01")
        -2
      else if (concept == state.currentGraph.nodes(state.nodesToProcess.head))
        0
      else
        conceptIndex(concept)
    } else {
      conceptIndex(concept)
    }
  }
}