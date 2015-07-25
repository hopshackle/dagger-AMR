package amr

object conceptLoading {

import ImportConcepts._
val numbers = "[0-9]".r                           //> numbers  : scala.util.matching.Regex = [0-9]
val alpha = "[a-zA-Z]".r                          //> alpha  : scala.util.matching.Regex = [a-zA-Z]
val quote = """"""".r                             //> quote  : scala.util.matching.Regex = "
numbers replaceAllIn("006294fg230=_", "")         //> res0: String = fg=_
alpha replaceAllIn("006294fg230=_", "")           //> res1: String = 006294230=_
quote findFirstIn(""" "NATO" """)                 //> res2: Option[String] = Some(")
quote findFirstIn("006294fg230=_")                //> res3: Option[String] = None
initialise("C:\\AMR\\MediumTrainingSet.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [2.9 sec].
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "for"
                                                  //| WARNING: Found duplicate match for concept "Arab"
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept factor
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept internet
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept website
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept "South"
                                                  //| WARNING: Found duplicate match for concept "FIFA"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept dog
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept include-91
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept group
                                                  //| WARNING: Found duplicate match for concept 5
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept "Pakistan"
                                                  //| WARNING: Found duplicate match for concept ban-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found 
                                                  //| Output exceeds cutoff limit.
conceptStrings                                    //> res5: scala.collection.immutable.Set[String] = Set(incident, command-02, res
                                                  //| pond-01, scenario, close-01, defend-01, hail-02, workshop, relate-01, lead-0
                                                  //| 1, engage-01, down, expert-41, strangle-01, dangerous, contend-01, measure, 
                                                  //| panel, economy, raid-01, trouble, facilitate-01, convert-01, number, rule-03
                                                  //| , contrast-01, headline, demonstrate-01, chief, organization, jury, ruble, m
                                                  //| ention-01, politics, distance-quantity, program, network, business, say-01, 
                                                  //| prevent-01, interrogative, false, soldier, art, function-01, budget-01, offi
                                                  //| cer, post, further, defense, patrol-01, electric, newspaper, any, behave-01,
                                                  //|  portion, website, gain-02, city, on-time, module, sell-01, significance, bo
                                                  //| dy, kill-01, across, come-01, percentage-entity, remain-01, name, huge, this
                                                  //| , issue-01, state-01, work-01, strict, death, imply-01, move, entrench-01, d
                                                  //| rive-02, consist-01, show-01, women, win-01, regional, traffic-00, reason-01
                                                  //| , scientist, vote-01, include-91, define-01, result-01, black-out, defraud-0
                                                  //| 1, channel, point, fire-arm, cultural, crisis, source, smuggle-01, taste-01,
                                                  //|  insist-01, cut-01, trade-01, go-08, force, president, applaud-01, militia, 
                                                  //| foreigner, name-01, defame-01, seminar, fund-01, subsidiary, likely, locatio
                                                  //| n, market, approximate, hard, remove-01, parliament, soon, differ-02, less, 
                                                  //| date-entity, private, system, weekend, accordingly, society, partner-01, ele
                                                  //| ctrocution, elder, economic, hope-01, relevant-41, libel-01, research, cause
                                                  //| -01, history, apply-02, disappear-01, late, caution-01, populate-01, rural, 
                                                  //| border, desperate, perform-01, back-01, have-org-role-91, hang-01, monopoly,
                                                  //|  state, make-01, nation, terror, accuse-01, coordinate-01, lab, et-cetera, c
                                                  //| larify-01, tender, circulate-01, so-far, report-01, conflict-01, improve-01,
                                                  //|  future, weapons, security, dissident, role, slaughter-01, link-01, create-0
                                                  //| 1, standby, threaten-01, reach-01, rhetoric, lead-05, event, ordinal-entity,
                                                  //|  regime, another, police, border-01, democratic, radio, cultivate-01, recent
                                                  //| , debate-01, partly, image, project, center, hold-04, update-01, world, comb
                                                  //| at-01, contain-01, tool, corrupt-01, die-01, long-time, narcotics, last-01, 
                                                  //| hostage, extend-01, demand-01, population, war, notification, so, include-01
                                                  //| , survey-01, vast, weapons-grade, hear-01, act-01, all, mass-quantity, equip
                                                  //| -01, barrier, bias-01, fail-01, journal, book, infrastructure, res
                                                  //| Output exceeds cutoff limit.
relationStrings                                   //> res6: scala.collection.immutable.Set[String] = Set(quant-of, duration, quant
                                                  //| , polarity, name, ARG0-of, manner, source, ARG1-of, prep-for, consist-of, AR
                                                  //| G4, location, path, accompanier, mod, extent, prep-against, source-of, degre
                                                  //| e, prep-under, ARG1, domain, concession, op11, location-of, subevent-of, yea
                                                  //| r, direction, op10, medium, age, ARG2, opN, purpose, condition, weekday, mod
                                                  //| e, beneficiary, poss, frequency, topic, unit, time, ARG2-of, part, ARG3, com
                                                  //| pared-to, part-of, instrument-of, ROOT, destination, value, ARG0, prep-with,
                                                  //|  day, instrument, ord, poss-of, month)
relationMaster                                    //> res7: scala.collection.immutable.Map[Int,String] = Map(0 -> UNKNOWN, 5 -> na
                                                  //| me, 10 -> prep-for, 56 -> day, 42 -> topic, 24 -> concession, 37 -> weekday,
                                                  //|  25 -> op11, 52 -> destination, 14 -> path, 20 -> degree, 46 -> part, 57 -> 
                                                  //| instrument, 29 -> direction, 1 -> quant-of, 6 -> ARG0-of, 60 -> month, 28 ->
                                                  //|  year, 38 -> mode, 21 -> prep-under, 33 -> ARG2, 9 -> ARG1-of, 53 -> value, 
                                                  //| 13 -> location, 41 -> frequency, 2 -> duration, 32 -> age, 34 -> opN, 45 -> 
                                                  //| ARG2-of, 17 -> extent, 22 -> ARG1, 44 -> time, 59 -> poss-of, 27 -> subevent
                                                  //| -of, 12 -> ARG4, 54 -> ARG0, 49 -> part-of, 7 -> manner, 39 -> beneficiary, 
                                                  //| 3 -> quant, 35 -> purpose, 48 -> compared-to, 18 -> prep-against, 50 -> inst
                                                  //| rument-of, 16 -> mod, 31 -> medium, 11 -> consist-of, 43 -> unit, 40 -> poss
                                                  //| , 26 -> location-of, 55 -> prep-with, 23 -> domain, 8 -> source, 58 -> ord, 
                                                  //| 36 -> condition, 30 -> op10, 51 -> ROOT, 19 -> source-of, 4 -> polarity, 47 
                                                  //| -> ARG3, 15 -> accompanier)
insertableConcepts                                //> res8: Set[String] = Set(relate-01, lead-01, organization, network, say-01, n
                                                  //| ewspaper, website, city, name, women, smuggle-01, president, fund-01, locati
                                                  //| on, date-entity, relevant-41, cause-01, populate-01, back-01, have-org-role-
                                                  //| 91, state, event, ordinal-entity, include-01, mass-quantity, bias-01, book, 
                                                  //| temporal-quantity, trafficking, before, capable-41, counter-01, thing, run-0
                                                  //| 1, religious-group, monetary-quantity, men, take-01, country, prepare-02, th
                                                  //| at, continent, realize-01, now, -, county, eat-01, company, bomb-01, schedul
                                                  //| e-01, rate-entity-91, entitle-01, recommend-01, language, url-entity, penins
                                                  //| ula, obligate-01, world-region, host-01, attack-01, university, spaceship, n
                                                  //| eed-01, person, resemble-01, publish-01, extremism, currency, endanger-01, a
                                                  //| t-least, fly-01, criminal-organization, act-02, pay-01, turn-02, oppose-01, 
                                                  //| relative-position, multiple, base-01, exemplify-01, facility, publication, g
                                                  //| ame, lose-02, military, ethnic-group, cut-02, possible, and, govern-01, righ
                                                  //| t, government-organization)
}