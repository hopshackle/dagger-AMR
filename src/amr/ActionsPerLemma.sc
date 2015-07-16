package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\LargeTrainingSet.txt")

ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.4 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [3.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.0 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [4.2 sec].
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
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(incident, submit-01, rate
                                                  //| , command-02, respond-01, scenario, close-01, defend-01, hail-02, automatic,
                                                  //|  secretary, altitude, workshop, relate-01, lead-01, engage-01, down, expert-
                                                  //| 41, strangle-01, dangerous, contend-01, measure, panel, economy, raid-01, ou
                                                  //| tlaw, trouble, diram, facilitate-01, convert-01, percentage, number, rule-03
                                                  //| , contrast-01, headline, demonstrate-01, chief, organization, jury, ruble, m
                                                  //| ention-01, politics, distance-quantity, program, enable-01, network, busines
                                                  //| s, say-01, prevent-01, interrogative, school, see-01, false, soldier, art, f
                                                  //| ourth, function-01, community, budget-01, method, officer, post, test, furth
                                                  //| er, defense, patrol-01, people, electric, gram, series, newspaper, address-0
                                                  //| 1, any, behave-01, pose-01, portion, website, gain-02, city, on-time, module
                                                  //| , sell-01, significance, body, stage, kill-01, across, come-01, plant, perce
                                                  //| ntage-entity, young, area, remain-01, name, huge, this, issue-01, state-01, 
                                                  //| work-01, fall-05, strict, death, imply-01, instead, move, entrench-01, drive
                                                  //| -02, consist-01, show-01, brothel, women, critic, win-01, regional, traffic-
                                                  //| 00, reliable, reason-01, scientist, vote-01, include-91, define-01, result-0
                                                  //| 1, black-out, priority, substance, defraud-01, channel, addict-01, fair, poi
                                                  //| nt, fire-arm, cultural, crisis, source, smuggle-01, taste-01, task, be-from-
                                                  //| 91, insist-01, hysteria, cut-01, trade-01, tie, go-08, too, force, president
                                                  //| , plane, applaud-01, group-01, militia, foreigner, name-01, defame-01, semin
                                                  //| ar, effort, fund-01, subsidiary, likely, intend-01, evaluate-01, location, m
                                                  //| arket, approximate, hard, remove-01, purchase-01, select-01, leak-01, parlia
                                                  //| ment, soon, differ-02, cheap, consult-01, less, date-entity, year-on-year, p
                                                  //| rivate, system, weekend, relation, accordingly, raise-02, society, partner-0
                                                  //| 1, electrocution, elder, process-01, economic, hope-01, relevant-41, libel-0
                                                  //| 1, research, cause-01, history, initial, apply-02, disappear-01, late, field
                                                  //| , caution-01, populate-01, anonymous, standoff, backlash, advise-01, rural, 
                                                  //| border, desperate, perform-01, back-01, have-org-role-91, hang-01, negotiate
                                                  //| -01, monopoly, export-01, state, challenge-01, make-01, nation, agreement, t
                                                  //| error, revise-01, accuse-01, coordinate-01, lab, et-cetera, clarify-01, draw
                                                  //| -03, tender, circulate-01, so-far, report-01, conflict-01, improve
                                                  //| Output exceeds cutoff limit.
ImportConcepts.conceptsPerLemma foreach println   //> (incident,Set(1, 547, 1202, 176, 1011, 1240, 860, 47, 369))
                                                  //| (serious,Set(1072, 1274, 1174, 889, 212, 176, 291, 204, 1344, 374, 194, 48, 
                                                  //| 971, 951, 1023, 1240, 1244, 410, 294, 94, 528))
                                                  //| (comply,Set(468, 142, 814, 372, 220, 38, 1054, 750, 787, 98, 663, 1340, 1049
                                                  //| , 327, 951, 1240, 410, 79, 1346, 94))
                                                  //| (rate,Set(5, 120, 1305, 797, 670, 782, 288, 1359, 651, 1287, 372, 751, 1147,
                                                  //|  1319, 57, 1362, 1020, 1072, 116, 233, 1306, 381, 847, 1012, 1121, 356, 1082
                                                  //| , 96, 173, 674, 34, 148, 161, 772, 286, 71, 382, 1058, 219, 98, 827, 3, 194,
                                                  //|  282, 951, 1218, 314, 612, 859, 1240, 991, 627, 1321, 104, 771, 82, 146, 94,
                                                  //|  528, 90, 659))
                                                  //| (Amateur,Set(1287, 147, 38, 697, 819, 563, 48, 580, 951, 1240, 771, 119, 117
                                                  //| 2, 94))
                                                  //| (scenario,Set(670, 1287, 504, 1247, 93, 6, 512, 874, 547, 212, 1286, 880, 98
                                                  //| , 303, 1304, 414, 951, 1240, 688, 94))
                                                  //| (combate,Set(142, 797, 288, 1287, 587, 692, 1351, 243, 435, 889, 156, 212, 3
                                                  //| 4, 176, 704, 599, 441, 951, 1221, 1240, 94, 395, 528))
                                                  //| (unity,Set(460, 81, 1240, 929, 94, 528))
                                                  //| (VN,Set(94, 528, 1240, 1287))
                                                  //| (Province,Set(115, 120, 1191, 348, 1242, 974, 266, 375, 641, 919, 98, 951, 1
                                                  //| 221, 1240, 961, 635, 955, 94, 1261, 528))
                                                  //| (automatic,Set(10, 670, 1247, 325, 443, 60, 161, 176, 98, 1177, 130, 223, 12
                                                  //| 50, 194, 791, 951, 1218, 1240, 319, 400, 1165, 94, 659))
                                                  //| (secretary,Set(1260, 782, 33, 240, 951, 11, 1240, 94))
                                                  //| (Gorbunov,Set(942, 874, 212, 951, 336, 1240, 801, 94, 528))
                                                  //| (II,Set(308, 37, 1013, 1072, 1210, 212, 647, 73, 1258, 672, 1282, 971, 951, 
                                                  //| 11, 1240, 781, 960, 94, 528))
                                                  //| (Golden,Set(885, 1287, 1353, 1240, 94, 528))
                                                  //| (altitude,Set(1305, 1041, 270, 761, 1234, 593, 537, 12, 219, 271, 194, 1240,
                                                  //|  699, 1212, 287, 94, 528, 469))
                                                  //| (workshop,Set(760, 1179, 120, 715, 1254, 1287, 14, 949, 157, 189, 1100, 29, 
                                                  //| 623, 1151, 348, 1119, 243, 248, 810, 1123, 388, 837, 13, 926, 176, 772, 1256
                                                  //| , 413, 713, 1092, 1011, 599, 631, 827, 1034, 490, 426, 1134, 1018, 48, 525, 
                                                  //| 712, 18, 791, 951, 336, 1240, 478, 214, 967, 79, 828, 94, 1261, 528, 222))
                                                  //| (Nations,Set(645, 468, 730, 1205, 878, 814, 885, 1359, 301, 1287, 587, 902, 
                                                  //| 969, 924, 253, 348, 353, 766, 147, 61, 956, 1351, 243, 233, 942, 381, 220, 1
                                                  //| 274, 38, 729, 467, 229, 435, 889, 842, 1089, 156, 1121, 212, 96, 654, 569, 2
                                                  //| 66, 1167, 750, 819, 176, 44, 713, 1239, 1092, 947, 1349, 98, 599, 609, 636, 
                                                  //| 1245, 455, 426, 1208, 1340, 194, 1049, 48, 18, 951, 808, 72,
                                                  //| Output exceeds cutoff limit.
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (state,623)
                                                  //| (government,325)
                                                  //| (will,293)
                                                  //| (drug,262)
                                                  //| (Korea,255)
                                                  //| (Afghanistan,236)
                                                  //| (say,230)
                                                  //| (official,225)
                                                  //| (at,223)
                                                  //| (South,216)
                                                  //| (would,203)
                                                  //| (year,203)
                                                  //| (they,201)
                                                  //| (nuclear,191)
                                                  //| (as,181)
                                                  //| (its,179)
                                                  //| (not,176)
                                                  //| (could,176)
                                                  //| (program,175)
                                                  //| (against,175)
                                                  //| (group,174)
                                                  //| (include,172)
                                                  //| (United,171)
                                                  //| (russian,166)
                                                  //| (heroin,165)
                                                  //| (also,161)
                                                  //| (military,161)
                                                  //| (North,160)
                                                  //| (international,156)
                                                  //| (weapon,149)
                                                  //| (trafficking,147)
                                                  //| (States,146)
                                                  //| (power,144)
                                                  //| (make,142)
                                                  //| (security,139)
                                                  //| (Japan,137)
                                                  //| (terrorism,137)
                                                  //| (opium,135)
                                                  //| (than,133)
                                                  //| (ban,132)
                                                  //| (country,132)
                                                  //| (other,131)
                                                  //| (report,130)
                                                  //| (this,129)
                                                  //| (there,128)
                                                  //| (newspaper,127)
                                                  //| (Russia,127)
                                                  //| (or,125)
                                                  //| (which,125)
                                                  //| (Ozawa,124)
                                                  //| (but,123)
                                                  //| (Boeing,123)
                                                  //| (border,122)
                                                  //| (more,120)
                                                  //| (Asia,118)
                                                  //| (cooperation,118)
                                                  //| (January,117)
                                                  //| (afghan,117)
                                                  //| (over,117)
                                                  //| (he,115)
                                                  //| (end,115)
                                                  //| (Tajikistan,115)
                                                  //| (because,113)
                                                  //| (system,112)
                                                  //| (korean,112)
                                                  //| (trade,112)
                                                  //| (rights,111)
                                                  //| (plutonium,111)
                                                  //| (dog,110)
                                                  //| (attack,110)
                                                  //| (take,109)
                                                  //| (dollar,108)
                                                  //| (space,107)
                                                  //| (property,107)
                                                  //| (Defense,107)
                                                  //| (many,107)
                                                  //| (one,107)
                                                  //| (intellectual,107)
                                                  //| (U.S.,106)
                                                  //| (between,106)
                                                  //| (if,105)
                                                  //| (plan,103)
                                                  //| (launch,102)
                                                  //| (any,101)
                                                  //| (use,101)
                                                  //| (force,100)
                                                  //| (Mohanty,100)
                                                  //| (N.,100)
                                                  //| (technology,100)
                                                  //| (fighter,100)
                                                  //| (R.,100)
                                                  //| (action,99)
                                                  //| (nation,98)
                                                  //| (Aeronautics,98)
                                                  //| (Hindustan,98)
                                                  //| (Limited,98)
                                                  //| (all,97)
                                                  //| (daily,97)
                                                  //| (under,97)
                                                  //| (terrorist,96)
                                                  //| (million,96)
                                                  //| (recent,95)
                                                  //| (publication,95)
                                                  //| (poppy,95)
                                                  //| (first,94)
                                                  //| (when,94)
                                                  //| (February,93)
                                                  //| (after,93)
                                                  //| (meet,93)
                                                  //| (Southeast,92)
                                                  //| (IAEA,91)
                                                  //| (world,91)
                                                  //| (islamic,91)
                                                  //| (Myanmar,91)
                                                  //| (service,91)
                                                  //| (company,91)
                                                  //| (support,90)
                                                  //| (defense,90)
                                                  //| (statement,90)
                                                  //| (April,90)
                                                  //| (center,88)
                                                  //| (who,88)
                                                  //| (percent,88)
                                                  //| (Nations,87)
                                                  //| (International,87)
                                                  //| (some,87)
                                                  //| (last,86)
                                                  //| (become,86)
                                                  //| (Asian,86)
                                                  //| (people,85)
                                                  //| (rule,85)
                                                  //| (work,85)
                                                  //| (issue,85)
                                                  //| (pakistani,85)
                                                  //| (ASEAN,84)
                                                  //| (need,84)
                                                  //| (Rakhimov,84)
                                                  //| (jet,83)
                                                  //| (cost,83)
                                                  //| (religious,83)
                                                  //| (police,82)
                                                  //| (China,82)
                                                  //| (transfer,82)
ImportConcepts.edgesPerLemma foreach println      //> (incident,Set(24, 37, 17, 59, 3, 50, 31, 62))
                                                  //| (serious,Set(5, 24, 37, 25, 6, 9, 17, 59, 54, 50, 31, 36, 62))
                                                  //| (comply,Set(5, 24, 37, 14, 6, 9, 17, 59, 55, 36, 47, 62))
                                                  //| (rate,Set(5, 24, 37, 25, 61, 6, 21, 9, 53, 17, 59, 39, 50, 36, 47, 62))
                                                  //| (Amateur,Set(5, 24, 37, 25, 38, 45, 17, 59, 62))
                                                  //| (scenario,Set(5, 24, 37, 6, 53, 17, 59, 3, 36, 62))
                                                  //| (combate,Set(5, 24, 37, 14, 6, 64, 17, 59, 50, 31, 36, 47, 68, 62))
                                                  //| (unity,Set(5, 24, 37, 59, 55, 62))
                                                  //| (VN,Set(5, 59, 37))
                                                  //| (Province,Set(5, 24, 37, 14, 6, 34, 17, 59, 49, 3, 50, 55, 51, 62))
                                                  //| (automatic,Set(5, 24, 37, 6, 9, 17, 59, 3, 50, 31, 43, 55, 36, 68, 62))
                                                  //| (secretary,Set(5, 37, 25, 6, 45, 17, 59))
                                                  //| (Gorbunov,Set(5, 24, 37, 6, 17, 59, 8, 36, 62))
                                                  //| (II,Set(5, 24, 37, 6, 45, 17, 59, 50, 36, 4, 47, 62))
                                                  //| (Golden,Set(5, 24, 37, 17, 59, 36))
                                                  //| (altitude,Set(5, 24, 37, 14, 21, 9, 45, 17, 59, 36, 51, 62))
                                                  //| (workshop,Set(5, 24, 37, 14, 20, 28, 38, 33, 9, 2, 34, 45, 64, 17, 59, 12, 4
                                                  //| 9, 3, 50, 67, 31, 43, 55, 36, 68, 62))
                                                  //| (Nations,Set(5, 24, 37, 25, 14, 6, 38, 21, 9, 2, 45, 64, 17, 59, 12, 49, 7, 
                                                  //| 3, 48, 50, 31, 36, 47, 68, 62))
                                                  //| (significantly,Set(5, 24, 37, 14, 21, 45, 17, 59, 36, 62))
                                                  //| (Football,Set(5, 24, 37, 6, 45, 17, 59, 36, 4, 47, 62))
                                                  //| (launch,Set(5, 10, 24, 37, 25, 52, 14, 6, 38, 9, 41, 2, 45, 64, 17, 59, 12, 
                                                  //| 49, 39, 3, 50, 31, 55, 8, 36, 51, 4, 68, 62))
                                                  //| (Blatter,Set(5, 24, 37, 6, 38, 45, 17, 59, 50, 43, 36, 68, 62))
                                                  //| (Electricity,Set(5, 24, 37, 6, 9, 17, 59, 62))
                                                  //| (down,Set(5, 24, 37, 6, 9, 17, 59, 50, 36, 47, 62))
                                                  //| (Electric,Set(5, 24, 37, 6, 21, 9, 45, 64, 17, 59, 7, 3, 50, 36, 68, 62))
                                                  //| (dangerous,Set(5, 24, 37, 14, 21, 45, 17, 59, 3, 36, 51, 47, 62))
                                                  //| (enable,Set(5, 24, 37, 25, 6, 45, 17, 59, 36, 62))
                                                  //| (Ahmed,Set(5, 24, 37, 25, 14, 46, 6, 9, 53, 45, 64, 17, 59, 49, 3, 50, 31, 2
                                                  //| 3, 8, 36, 47, 68, 62))
                                                  //| (measure,Set(5, 24, 37, 6, 38, 21, 9, 2, 45, 17, 59, 7, 50, 43, 55, 36, 68, 
                                                  //| 62))
                                                  //| (researcher,Set(5, 24, 37, 14, 6, 9, 53, 17, 59, 50, 31, 36, 4, 68, 62))
                                                  //| (economy,Set(5, 24, 37, 25, 14, 53, 17, 59, 62))
                                                  //| (cruelty,Set(5, 24, 37, 6, 38, 45, 17, 59, 50, 43, 36, 68, 62))
                                                  //| (kang,Set(5, 24, 37, 25, 6, 38, 45, 17, 59, 62))
                                                  //| (outlaw,Set(5, 24, 37, 25, 57, 9, 17, 59, 3, 36, 62))
                                                  //| (copyright,Set(5, 24, 37, 14, 46, 38, 21, 17, 59, 55, 36, 47, 62))
                                                  //| (register,Set(5, 24, 37, 14, 9, 59, 54, 7, 3, 50, 31, 36))
                                                  //| (trouble,Set(5, 24, 37, 25, 6, 9, 45, 
                                                  //| Output exceeds cutoff limit.
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(quant-of, duration, quant
                                                  //| , polarity, name, ARG0-of, manner, source, ARG1-of, prep-for, time-of, consi
                                                  //| st-of, ARG4, location, path, accompanier, mod, extent, prep-against, source-
                                                  //| of, degree, subset-of, prep-under, ARG1, domain, concession, decade, op11, l
                                                  //| ocation-of, subevent-of, year, direction, op10, medium, age, ARG2, opN, purp
                                                  //| ose, condition, prep-instead-of, weekday, mode, beneficiary, ARG3-of, poss, 
                                                  //| frequency, topic, manner-of, unit, time, ARG2-of, part, ARG3, compared-to, p
                                                  //| art-of, condition-of, example, instrument-of, ROOT, destination, value, ARG0
                                                  //| , prep-with, day, instrument, ord, poss-of, month)
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (state,51)
                                                  //| (Korea,46)
                                                  //| (nuclear,45)
                                                  //| (Afghanistan,43)
                                                  //| (South,42)
                                                  //| (will,42)
                                                  //| (say,42)
                                                  //| (year,41)
                                                  //| (at,41)
                                                  //| (government,41)
                                                  //| (drug,39)
                                                  //| (would,36)
                                                  //| (dog,36)
                                                  //| (they,36)
                                                  //| (official,36)
                                                  //| (include,36)
                                                  //| (report,36)
                                                  //| (heroin,36)
                                                  //| (weapon,36)
                                                  //| (United,36)
                                                  //| (North,36)
                                                  //| (January,35)
                                                  //| (country,35)
                                                  //| (its,35)
                                                  //| (group,35)
                                                  //| (ban,34)
                                                  //| (russian,34)
                                                  //| (power,34)
                                                  //| (more,34)
                                                  //| (States,34)
                                                  //| (border,33)
                                                  //| (as,33)
                                                  //| (opium,33)
                                                  //| (make,33)
                                                  //| (end,33)
                                                  //| (which,33)
                                                  //| (rights,33)
                                                  //| (Defense,33)
                                                  //| (program,32)
                                                  //| (newspaper,32)
                                                  //| (korean,32)
                                                  //| (trafficking,32)
                                                  //| (become,32)
                                                  //| (property,32)
                                                  //| (Tajikistan,32)
                                                  //| (during,32)
                                                  //| (plan,32)
                                                  //| (intellectual,32)
                                                  //| (this,31)
                                                  //| (than,31)
                                                  //| (arrest,31)
                                                  //| (terrorism,31)
                                                  //| (also,31)
                                                  //| (international,31)
                                                  //| (he,31)
                                                  //| (not,31)
                                                  //| (Boeing,31)
                                                  //| (attack,31)
                                                  //| (April,31)
                                                  //| (against,31)
                                                  //| (military,31)
                                                  //| (support,30)
                                                  //| (point,30)
                                                  //| (security,30)
                                                  //| (Japan,30)
                                                  //| (recent,30)
                                                  //| (Russia,30)
                                                  //| (meat,30)
                                                  //| (there,30)
                                                  //| (over,30)
                                                  //| (Asia,30)
                                                  //| (could,30)
                                                  //| (between,30)
                                                  //| (publication,30)
                                                  //| (launch,29)
                                                  //| (February,29)
                                                  //| (Ozawa,29)
                                                  //| (Ministry,29)
                                                  //| (police,29)
                                                  //| (document,29)
                                                  //| (european,29)
                                                  //| (because,29)
                                                  //| (trade,29)
                                                  //| (service,29)
                                                  //| (expert,29)
                                                  //| (technology,29)
                                                  //| (Space,29)
                                                  //| (transfer,29)
                                                  //| (space,29)
                                                  //| (Forces,29)
                                                  //| (many,29)
                                                  //| (day,29)
                                                  //| (plutonium,29)
                                                  //| (center,28)
                                                  //| (cut,28)
                                                  //| (Aeronautics,28)
                                                  //| (islamic,28)
                                                  //| (build,28)
                                                  //| (daily,28)
                                                  //| (Hindustan,28)
                                                  //| (Limited,28)
                                                  //| (May,28)
                                                  //| (fighter,28)
                                                  //| (indian,28)
                                                  //| (first,28)
                                                  //| (under,28)
                                                  //| (percent,28)
                                                  //| (meet,28)
                                                  //| (Pakistan,28)
                                                  //| (million,28)
                                                  //| (firm,28)
                                                  //| (past,28)
                                                  //| (action,28)
                                                  //| (other,28)
                                                  //| (one,28)
                                                  //| (defense,27)
                                                  //| (due,27)
                                                  //| (production,27)
                                                  //| (afghan,27)
                                                  //| (source,27)
                                                  //| (Russian,27)
                                                  //| (force,27)
                                                  //| (market,27)
                                                  //| (system,27)
                                                  //| (nation,27)
                                                  //| (ASEAN,27)
                                                  //| (war,27)
                                                  //| (all,27)
                                                  //| (Southeast,27)
                                                  //| (ministry,27)
                                                  //| (trafficker,27)
                                                  //| (Iran,27)
                                                  //| (energy,27)
                                                  //| (ton,27)
                                                  //| (or,27)
                                                  //| (statement,27)
                                                  //| (last,27)
                                                  //| (issue,27)
                                                  //| (take,27)
                                                  //| (U.S.,27)
                                                  //| (pakistani,27)
                                                  //| (cooperation,27)
                                                  //| (kill,27)
                                                  //| (major,27)
                                                  //| (increase,27)
                                                  //| (workshop,26)
                                                  //| (announce,26)
                                                  //| 
                                                  //| Output exceeds cutoff limit.
}