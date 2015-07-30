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
initialise("C:\\AMR\\LargeTrainingSet.txt")

conceptStrings                                    //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.5 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [5.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.6 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.8 sec].
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
conceptStrings                                    //> res5: Set[String] = Set(incident, submit-01, rate, amazon.com, command-02, r
                                                  //| espond-01, Amateur, scenario, close-01, defend-01, Province, hail-02, automa
                                                  //| tic, secretary, Gorbunov, II, Golden, altitude, workshop, Nations, relate-01
                                                  //| , Football, lead-01, Blatter, engage-01, Electricity, down, Electric, expert
                                                  //| -41, strangle-01, dangerous, contend-01, Ahmed, measure, panel, economy, rai
                                                  //| d-01, outlaw, trouble, Musharraf, diram, facilitate-01, convert-01, percenta
                                                  //| ge, number, Sepp, rule-03, contrast-01, headline, demonstrate-01, chief, for
                                                  //| , organization, jury, ruble, Lumpur, mention-01, Fajr, People's, politics, d
                                                  //| istance-quantity, Kober, Telecommunications, program, enable-01, network, Al
                                                  //| fa, business, Air, say-01, prevent-01, interrogative, Tajik, school, see-01,
                                                  //|  false, soldier, Australian, art, fourth, Francis, function-01, community, b
                                                  //| udget-01, method, officer, post, test, Zvezda, further, defense, Ichiro, pat
                                                  //| rol-01, ABC, people, electric, Iraq, gram, series, newspaper, address-01, an
                                                  //| y, behave-01, Federation, pose-01, portion, website, gain-02, city, on-time,
                                                  //|  module, Gafur, sell-01, significance, body, stage, Zarb-e-Taiba, kill-01, a
                                                  //| cross, Academy, come-01, plant, percentage-entity, young, Department, area, 
                                                  //| remain-01, Bangkok, name, huge, this, issue-01, state-01, work-01, fall-05, 
                                                  //| strict, death, al-Rashid, imply-01, instead, in, move, www.francetv.fr, entr
                                                  //| ench-01, Commission, drive-02, consist-01, show-01, brothel, women, critic, 
                                                  //| win-01, regional, traffic-00, Jordan, reliable, Patent, reason-01, scientist
                                                  //| , vote-01, include-91, define-01, result-01, black-out, priority, substance,
                                                  //|  defraud-01, channel, addict-01, Ozawa, fair, point, fire-arm, Chang-Sung, c
                                                  //| ultural, Belyaninov, crisis, source, smuggle-01, taste-01, task, be-from-91,
                                                  //|  Philippines, insist-01, Telegraph, hysteria, Northern, Kennett, cut-01, tra
                                                  //| de-01, tie, go-08, too, South, Russian, force, president, MiG-29, plane, Alg
                                                  //| eria, applaud-01, group-01, militia, foreigner, name-01, defame-01, seminar,
                                                  //|  effort, fund-01, subsidiary, F-15K, likely, intend-01, Supreme, evaluate-01
                                                  //| , location, market, approximate, hard, Control, remove-01, purchase-01, sele
                                                  //| ct-01, leak-01, Company, parliament, soon, Yugoslavia, differ-02, War, cheap
                                                  //| , consult-01, Asahi, less, date-entity, year-on-year, private, system, weeke
                                                  //| nd, relation, accordingly, raise-02, society, partner-01, electroc
                                                  //| Output exceeds cutoff limit.
relationStrings                                   //> res6: Set[String] = Set(quant-of, duration, quant, polarity, name, ARG0-of, 
                                                  //| manner, source, ARG1-of, prep-for, time-of, consist-of, ARG4, location, path
                                                  //| , accompanier, mod, extent, prep-against, source-of, degree, subset-of, prep
                                                  //| -under, ARG1, domain, concession, decade, op11, location-of, subevent-of, ye
                                                  //| ar, direction, op10, medium, age, ARG2, opN, purpose, condition, prep-instea
                                                  //| d-of, weekday, mode, beneficiary, ARG3-of, poss, frequency, topic, manner-of
                                                  //| , unit, time, ARG2-of, part, ARG3, compared-to, part-of, condition-of, examp
                                                  //| le, instrument-of, ROOT, destination, value, ARG0, prep-with, day, instrumen
                                                  //| t, ord, poss-of, month)
relationMaster                                    //> res7: scala.collection.immutable.Map[Int,String] = Map(0 -> UNKNOWN, 5 -> na
                                                  //| me, 10 -> prep-for, 56 -> condition-of, 42 -> mode, 24 -> ARG1, 37 -> opN, 2
                                                  //| 5 -> domain, 52 -> part, 14 -> location, 20 -> source-of, 46 -> frequency, 5
                                                  //| 7 -> example, 29 -> location-of, 61 -> value, 1 -> quant-of, 6 -> ARG0-of, 6
                                                  //| 0 -> destination, 28 -> op11, 38 -> purpose, 21 -> degree, 33 -> op10, 65 ->
                                                  //|  instrument, 9 -> ARG1-of, 53 -> ARG3, 13 -> ARG4, 41 -> weekday, 2 -> durat
                                                  //| ion, 32 -> direction, 34 -> medium, 45 -> poss, 64 -> day, 17 -> mod, 22 -> 
                                                  //| subset-of, 44 -> ARG3-of, 59 -> ROOT, 27 -> decade, 12 -> consist-of, 54 -> 
                                                  //| compared-to, 49 -> unit, 7 -> manner, 39 -> condition, 66 -> ord, 3 -> quant
                                                  //| , 35 -> age, 48 -> manner-of, 63 -> prep-with, 18 -> extent, 50 -> time, 67 
                                                  //| -> poss-of, 16 -> accompanier, 31 -> year, 11 -> time-of, 43 -> beneficiary,
                                                  //|  40 -> prep-instead-of, 26 -> concession, 55 -> part-of, 23 -> prep-under, 8
                                                  //|  -> source, 58 -> instrument-of, 36 -> ARG2, 30 -> subevent-of, 51 -> ARG2-o
                                                  //| f, 19 -> prep-against, 4 -> polarity, 47 -> topic, 15 -> path, 68 -> month, 
                                                  //| 62 -> ARG0)
insertableConcepts                                //> res8: Map[String,Set[String]] = Map(serious -> Set(possible), comply -> Set(
                                                  //| capable-41), michael -> Set(), simon -> Set(), launch -> Set(possible), tali
                                                  //| ban -> Set(), enable -> Set(possible), economy -> Set(and), cruelty -> Set(c
                                                  //| ounter-01), fifa -> Set(), kang -> Set(), trouble -> Set(possible), diram ->
                                                  //|  Set(monetary-quantity), yunus -> Set(), number -> Set(product-of), chief ->
                                                  //|  Set(have-org-role-91, make-01), for -> Set(), organization -> Set(have-org-
                                                  //| role-91, publication, publish-01), ruble -> Set(monetary-quantity), politics
                                                  //|  -> Set(and), program -> Set(prove-01), network -> Set(website), business ->
                                                  //|  Set(and), announce -> Set(act-02), banned -> Set(thing), produce -> Set(pos
                                                  //| sible), soldier -> Set(have-org-role-91), june -> Set(date-entity), consult 
                                                  //| -> Set(possible), raid -> Set(cause-01), further -> Set(prove-01), defense -
                                                  //| > Set(), coexist -> Set(possible), shimbun -> Set(), electric -> Set(company
                                                  //| ), turkmenistan -> Set(), gram -> Set(mass-quantity), newspaper -> Set(and),
                                                  //|  any -> Set(country, aircraft-type), lead -> Set(possible), asahi -> Set(), 
                                                  //| mig- -> Set(), website -> Set(url-entity), vladimir -> Set(), city -> Set(ba
                                                  //| se-01, have-org-role-91, be-located-at-91), riyadh -> Set(), qari -> Set(), 
                                                  //| southern -> Set(country), allegation -> Set(cause-01), court -> Set(), name 
                                                  //| -> Set(organization, network, newspaper, city, state, political-party, event
                                                  //| , book, thing, religious-group, country, continent, county, company, languag
                                                  //| e, peninsula, world-region, university, spaceship, person, aircraft-type, cr
                                                  //| iminal-organization, island, facility, publication, game, military, ethnic-g
                                                  //| roup, government-organization), afghan -> Set(), this -> Set(before, thing),
                                                  //|  laos -> Set(), in -> Set(country, publication, person), bernard -> Set(), b
                                                  //| ros -> Set(), critic -> Set(person), strengthen -> Set(thing), bangkok -> Se
                                                  //| t(), have -> Set(organization, country, need-01, person, possible), scientis
                                                  //| t -> Set(have-org-role-91), delay -> Set(recommend-01), francis -> Set(), po
                                                  //| int -> Set(range-01), communication -> Set(have-org-role-91), crisis -> Set(
                                                  //| cause-01), northern -> Set(), uncover -> Set(possible), execute -> Set(possi
                                                  //| ble), be-from-91 -> Set(country), smh -> Set(), require -> Set(prepare-02), 
                                                  //| warner -> Set(), force -> Set(), president -> Set(have-org-role-91), interve
                                                  //| ne -> Set(recommend-01), india -> Set(), broadcaster -> Set(person
                                                  //| Output exceeds cutoff limit.
conceptsPerLemma                                  //> res9: Map[String,Set[Int]] = Map(incident -> Set(1), serious -> Set(1294), c
                                                  //| omply -> Set(1729), rate -> Set(810, 3), michael -> Set(1517), scenario -> S
                                                  //| et(8), combate -> Set(377), simon -> Set(1507), automatic -> Set(13), secret
                                                  //| ary -> Set(14), altitude -> Set(18), workshop -> Set(19), significantly -> S
                                                  //| et(1217), launch -> Set(736), taliban -> Set(1478), dangerous -> Set(31), en
                                                  //| able -> Set(65), measure -> Set(34), panel -> Set(35), researcher -> Set(986
                                                  //| ), economy -> Set(36), cruelty -> Set(813), fifa -> Set(426), kang -> Set(14
                                                  //| 46), outlaw -> Set(38), copyright -> Set(1689), register -> Set(370), troubl
                                                  //| e -> Set(39), diram -> Set(41), compete -> Set(1525), percentage -> Set(44),
                                                  //|  read -> Set(1414), yunus -> Set(663), number -> Set(45, 1253), headline -> 
                                                  //| Set(49), failure -> Set(427), chief -> Set(51), preparedness -> Set(714), fo
                                                  //| r -> Set(52), organization -> Set(53), drive -> Set(146), jury -> Set(54), r
                                                  //| uble -> Set(55), politics -> Set(60), program -> Set(64), organized -> Set(1
                                                  //| 289), network -> Set(66), find -> Set(1329, 922), neighbor -> Set(780), busi
                                                  //| ness -> Set(68), announce -> Set(1197), contend -> Set(32), support -> Set(1
                                                  //| 057), banned -> Set(1139), produce -> Set(1311), school -> Set(74), false ->
                                                  //|  Set(76), soldier -> Set(77), art -> Set(79), fourth -> Set(80), satisfy -> 
                                                  //| Set(1299), consult -> Set(232), raid -> Set(37), community -> Set(83), const
                                                  //| ruction -> Set(575), method -> Set(85), officer -> Set(86), test -> Set(88),
                                                  //|  further -> Set(90), defense -> Set(1433, 91), coexist -> Set(1331), harvest
                                                  //|  -> Set(802), shimbun -> Set(578), proceeds -> Set(1381), equip -> Set(421),
                                                  //|  people -> Set(95, 59), electric -> Set(28, 96), paint -> Set(1417), reach -
                                                  //| > Set(336), begin -> Set(1280), turkmenistan -> Set(1685), gram -> Set(98), 
                                                  //| series -> Set(99), newspaper -> Set(100), largely -> Set(1368), any -> Set(1
                                                  //| 02), lead -> Set(1058, 643), asahi -> Set(233), portion -> Set(106), mig- ->
                                                  //|  Set(1581), website -> Set(107), vladimir -> Set(1082), win -> Set(152), cit
                                                  //| y -> Set(109), review -> Set(726), circulation -> Set(301), module -> Set(11
                                                  //| 1), production -> Set(1311), fail -> Set(427), riyadh -> Set(859), body -> S
                                                  //| et(115), qari -> Set(787), revival -> Set(1330), stage -> Set(1660, 116), ap
                                                  //| prove -> Set(1270), southern -> Set(1237), across -> Set(119), plant -> Set(
                                                  //| 122), allegation -> Set(1694), court -> Set(1186), young -> Set(12
                                                  //| Output exceeds cutoff limit.
edgesPerLemma                                     //> res10: Map[String,Set[Int]] = Map(relate-01-IN -> Set(9), zarya-OUT -> Set()
                                                  //| , standoff-IN -> Set(54), ago-OUT -> Set(), drug-trafficking-IN -> Set(17), 
                                                  //| reduce-IN -> Set(), hospitalize-IN -> Set(), standoff-OUT -> Set(9, 50, 17),
                                                  //|  ease-IN -> Set(24), riyadh-based-OUT -> Set(), dushanbe-IN -> Set(), scenar
                                                  //| io-IN -> Set(24), taliban-backed-IN -> Set(), strong-OUT -> Set(), populate-
                                                  //| 01-OUT -> Set(36), confirmation-IN -> Set(24), vorobyov-OUT -> Set(), announ
                                                  //| cement-OUT -> Set(), spark-IN -> Set(48), interior-OUT -> Set(), accessible-
                                                  //| OUT -> Set(14, 24), arrest-IN -> Set(24), figure-IN -> Set(24), generally-OU
                                                  //| T -> Set(), ruble-OUT -> Set(), issue-IN -> Set(9, 36, 24), read-IN -> Set(3
                                                  //| 6, 24), budget-OUT -> Set(62), detain-OUT -> Set(62, 36, 24), rule-03-IN -> 
                                                  //| Set(), sell-01-IN -> Set(24), brazilian-IN -> Set(), commission-asean-OUT ->
                                                  //|  Set(), project-IN -> Set(24, 38, 36, 55), newspaper-IN -> Set(62, 36, 24), 
                                                  //| rupee-IN -> Set(49), kazakhstan-IN -> Set(), news-OUT -> Set(), instead-OUT 
                                                  //| -> Set(), campaigner-OUT -> Set(24), argue-OUT -> Set(62, 24), politics-IN -
                                                  //| > Set(), wake-OUT -> Set(9), of-OUT -> Set(5, 24, 53, 12, 49, 3, 36), delive
                                                  //| r-IN -> Set(25, 36, 24), by-OUT -> Set(), reliability-IN -> Set(62), monday-
                                                  //| IN -> Set(41), embassy-IN -> Set(62, 24), without-IN -> Set(), shimbun-IN ->
                                                  //|  Set(), entrench-OUT -> Set(21, 36, 24), free-IN -> Set(17), rakhmonov-IN ->
                                                  //|  Set(), send-IN -> Set(), begin-OUT -> Set(24, 50, 62, 36), aware-IN -> Set(
                                                  //| ), relation-IN -> Set(36, 47), apply-IN -> Set(), method-OUT -> Set(55, 38),
                                                  //|  multiple-OUT -> Set(), brazil-OUT -> Set(), ##-OUT -> Set(6, 9, 64, 49, 3, 
                                                  //| 31), cyberattack-OUT -> Set(), study-OUT -> Set(62, 24), month-OUT -> Set(17
                                                  //| ), looming-OUT -> Set(), fernando-IN -> Set(), gryzlov-OUT -> Set(), control
                                                  //| -OUT -> Set(24, 4, 62, 7), raw-OUT -> Set(), religious-IN -> Set(17), france
                                                  //| -IN -> Set(), wwwfrancetvfr-OUT -> Set(), reporter-IN -> Set(6), expectancy-
                                                  //| OUT -> Set(3, 17), armed-OUT -> Set(), game-IN -> Set(24, 36, 30, 54), deepl
                                                  //| y-OUT -> Set(), drug-IN -> Set(24, 17, 12, 36), grand-IN -> Set(), long-term
                                                  //| -IN -> Set(2), kind-OUT -> Set(9, 3), provider-IN -> Set(6), serious-IN -> S
                                                  //| et(25), use-01-OUT -> Set(24), mark-IN -> Set(24), counter-IN -> Set(24, 38)
                                                  //| , also-IN -> Set(17, 24), time-IN -> Set(17), lose-IN -> Set(), sale-IN -> S
                                                  //| et(), controversial-IN -> Set(17), strictly-IN -> Set(7), confisca
                                                  //| Output exceeds cutoff limit.
}