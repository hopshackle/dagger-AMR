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
                                                  //| im.crf.ser.gz ... done [5.5 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.0 sec].
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
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(incident, "Saudi", submit
                                                  //| -01, rate, command-02, "Ichiro", respond-01, scenario, close-01, defend-01, 
                                                  //| hail-02, automatic, "Gulf", secretary, "Zafar", altitude, workshop, relate-0
                                                  //| 1, lead-01, engage-01, down, "IRNA", "Electric", expert-41, strangle-01, dan
                                                  //| gerous, "Su-32", contend-01, measure, panel, economy, raid-01, outlaw, "Jais
                                                  //| h-e-Mohammad", trouble, diram, facilitate-01, convert-01, percentage, "Bahra
                                                  //| in", number, rule-03, contrast-01, headline, "Hindustan", demonstrate-01, "S
                                                  //| aint", chief, organization, jury, ruble, "Shaun", mention-01, "Meeting", pol
                                                  //| itics, distance-quantity, "American", "Cole", program, "bin", enable-01, "Ko
                                                  //| ber", network, business, say-01, "FIFA", prevent-01, interrogative, school, 
                                                  //| see-01, false, soldier, art, fourth, function-01, community, budget-01, meth
                                                  //| od, officer, post, test, further, defense, "Chong", "North", patrol-01, peop
                                                  //| le, electric, "Rafale", "Ozawa", "Kamchatenergo", gram, series, newspaper, a
                                                  //| ddress-01, any, behave-01, pose-01, portion, "Quintao", website, gain-02, ci
                                                  //| ty, on-time, module, sell-01, significance, body, "Lao", stage, kill-01, acr
                                                  //| oss, come-01, plant, percentage-entity, young, area, remain-01, name, huge, 
                                                  //| "Brian", this, issue-01, state-01, work-01, "Board", fall-05, strict, death,
                                                  //|  imply-01, instead, move, entrench-01, "The", drive-02, consist-01, "Stalin"
                                                  //| , show-01, brothel, women, "Control", critic, win-01, regional, "al-Rashid",
                                                  //|  traffic-00, reliable, "Ministers'", reason-01, scientist, vote-01, "East", 
                                                  //| include-91, define-01, result-01, black-out, priority, "Pakistan", substance
                                                  //| , defraud-01, channel, addict-01, "Arab", fair, point, fire-arm, "Wales", cu
                                                  //| ltural, crisis, source, smuggle-01, taste-01, task, be-from-91, insist-01, h
                                                  //| ysteria, cut-01, trade-01, tie, go-08, "Asia", too, "Computer", force, presi
                                                  //| dent, plane, "Mohanty", "Cambodia", applaud-01, group-01, militia, foreigner
                                                  //| , name-01, defame-01, seminar, "Academy", effort, fund-01, subsidiary, "Kabu
                                                  //| l", likely, intend-01, "Cooperation", "Daily", evaluate-01, location, market
                                                  //| , approximate, hard, remove-01, purchase-01, select-01, "Morocco", leak-01, 
                                                  //| "Warner", "Sudan", parliament, "Aviation", soon, differ-02, "II", "Burnell",
                                                  //|  "SA", cheap, "Musharraf", "MiG-21-93", consult-01, less, "Yugoslavia", date
                                                  //| -entity, year-on-year, "Committee", private, "Nations", "Belyanino
                                                  //| Output exceeds cutoff limit.
ImportConcepts.conceptsPerLemma foreach println   //> (incident,Set(1, 710, 1592, 488, 64, 230, 1293, 1098, 1546))
                                                  //| (serious,Set(1596, 683, 1243, 1724, 802, 1136, 1377, 85, 1636, 65, 493, 1592
                                                  //| , 391, 230, 271, 387, 1218, 258, 119, 1308, 1513, 536, 283, 803))
                                                  //| (comply,Set(1322, 1200, 1013, 1343, 1592, 124, 1720, 779, 1727, 856, 49, 134
                                                  //| 9, 434, 103, 609, 1044, 295, 1218, 119, 536, 1578, 967, 1480, 178, 491))
                                                  //| (rate,Set(1750, 115, 683, 1269, 511, 1305, 715, 153, 1391, 417, 184, 372, 96
                                                  //| 9, 106, 147, 1377, 89, 312, 1696, 381, 1676, 815, 852, 842, 224, 1592, 1082,
                                                  //|  124, 1060, 1627, 205, 1563, 1054, 1747, 1007, 1675, 187, 377, 7, 868, 1694,
                                                  //|  473, 1025, 872, 1225, 993, 502, 1218, 1353, 43, 1654, 1481, 258, 186, 75, 1
                                                  //| 19, 1294, 383, 1097, 4, 294, 94, 796, 992, 131, 501, 122, 491, 1449))
                                                  //| (Amateur,Set(730, 185, 1682, 1622, 1521, 1580, 65, 1573, 1592, 753, 997, 173
                                                  //| 3, 49, 181, 900, 1509, 1049, 1218, 1654, 119, 151, 950, 1363, 992))
                                                  //| (scenario,Set(555, 275, 710, 1592, 403, 1118, 124, 654, 1599, 541, 662, 888,
                                                  //|  118, 462, 868, 1218, 1654, 1126, 1674, 8, 119, 58, 283, 1653))
                                                  //| (combate,Set(760, 909, 893, 683, 1735, 325, 1136, 1570, 197, 1592, 1567, 230
                                                  //| , 616, 278, 780, 1364, 1025, 1218, 43, 1654, 517, 568, 119, 383, 283, 575, 1
                                                  //| 78))
                                                  //| (unity,Set(683, 597, 1601, 1592, 1540, 105, 1637, 1188, 119))
                                                  //| (VN,Set(683, 866, 1592, 188, 1654, 119))
                                                  //| (Province,Set(683, 1705, 153, 1222, 829, 1619, 1592, 1534, 1567, 124, 1231, 
                                                  //| 1246, 22, 1389, 1175, 494, 1594, 823, 1218, 612, 465, 119, 146, 354))
                                                  //| (automatic,Set(523, 715, 78, 1502, 165, 852, 1592, 578, 1017, 124, 1599, 205
                                                  //| , 1563, 298, 423, 12, 230, 868, 1516, 1605, 1218, 258, 119, 432))
                                                  //| (secretary,Set(1105, 1618, 42, 1645, 14, 321, 1592, 1007, 1699, 1218, 119, 9
                                                  //| 0))
                                                  //| (Gorbunov,Set(683, 1205, 1233, 1592, 1118, 1029, 278, 1218, 119, 447, 283, 4
                                                  //| 59))
                                                  //| (II,Set(1295, 1554, 683, 1243, 14, 870, 1377, 221, 1238, 1592, 1006, 476, 96
                                                  //| , 1167, 1616, 979, 836, 48, 409, 1230, 1218, 571, 1649, 119, 283))
                                                  //| (Golden,Set(683, 1366, 1518, 1119, 1592, 1329, 1131, 653, 1654, 119, 1737))
                                                  //| 
                                                  //| (altitude,Set(683, 1570, 1592, 980, 610, 1333, 382, 1675, 1556, 16, 359, 158
                                                  //| 5, 903, 771, 258, 119, 294, 358, 696))
                                                  //| (workshop,Set(683, 1486, 1671, 153, 24, 37, 1323, 196, 638, 325, 216, 1465, 
                                                  //| 1418, 623, 1183, 1215, 877, 1447, 920, 1519, 1355, 297, 1619, 65, 435, 1592,
                                                  //|  1017, 1644, 679, 1297, 1451, 1060, 2, 508, 1071, 17, 819, 611, 1239, 919, 1
                                                  //| 408, 230, 811, 103, 213, 251, 1293,
                                                  //| Output exceeds cutoff limit.
val t = (ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2)
                                                  //> t  : List[(String, Int)] = List((the,1498), (be,1222), (of,1191), (and,1190)
                                                  //| , (to,1162), (a,1113), (in,1096), (that,1071), (state,832), ('s,808), (have,
                                                  //| 724), (for,636), (on,556), (by,520), (with,437), (government,434), (from,378
                                                  //| ), (will,363), (it,336), (official,310), (drug,310), (Korea,300), (say,298),
                                                  //|  (at,289), (Afghanistan,281), (South,265), (would,251), (year,247), (as,246)
                                                  //| , (nuclear,239), (against,236), (not,232), (they,229), (its,229), (program,2
                                                  //| 23), (also,220), (russian,216), (include,216), (United,216), (group,210), (c
                                                  //| ould,208), (international,207), (military,192), (heroin,190), (make,189), (N
                                                  //| orth,184), (States,183), (other,182), (trafficking,181), (country,179), (wea
                                                  //| pon,177), (security,175), (terrorism,175), (which,172), (Defense,169), (he,1
                                                  //| 67), (power,167), (Asia,164), (newspaper,163), (report,161), (Russia,160), (
                                                  //| over,160), (Japan,159), (attack,159), (January,158), (than,157), (there,157)
                                                  //| , (ban,156), (or,154), (this,153), (but,152), (Boeing,152), (opium,149), (bo
                                                  //| rder,148), (trade,146), (more,146), (cooperation,144), (Tajikistan,142), (Oz
                                                  //| awa,140), (between,138), (end,137), (take,136), (plutonium,136), (afghan,135
                                                  //| ), (system,135), (fighter,135), (rights,135), (first,134), (plan,134), (kore
                                                  //| an,133), (action,133), (because,132), (property,132), (intellectual,132), (n
                                                  //| ation,130), (U.S.,130), (dollar,129), (company,129), (daily,128), (April,128
                                                  //| ), (many,127), (one,127), (if,126), (dog,126), (space,126), (February,125), 
                                                  //| (Mohanty,125), (N.,125), (R.,125), (force,124), (Southeast,124), (any,123), 
                                                  //| (Aeronautics,123), (Hindustan,123), (Limited,123), (under,123), (meet,123), 
                                                  //| (technology,122), (Myanmar,121), (service,120), (Asian,120), (launch,119), (
                                                  //| Nations,117), (International,117), (rule,117), (who,117), (Rakhimov,117), (u
                                                  //| se,117), (million,117), (all,116), (statement,116), (defense,114), (when,113
                                                  //| ), (ASEAN,112), (publication,112), (recent,111), (call,111), (support,110), 
                                                  //| (no,110), (people,109), (islamic,109), (last,109), (need,109), (after,109), 
                                                  //| (should,109), (war,108), (jet,108), (percent,108), (world,107), (work,107), 
                                                  //| (terrorist,107), (firm,107), (during,107), (become,106), (poppy,106), (some,
                                                  //| 106), (center,105), (Islam,105), (Rafale,105), (Laden,105), (bin,105), (day,
                                                  //| 105), (IAEA,104), (cost,104), (Ministry,103), (european,103), (transfer,103)
                                                  //| , (tell,103), (announce,102), (police,102), (infrastructure,102), 
                                                  //| Output exceeds cutoff limit.
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
                                                  //> (the,68)
                                                  //| (be,64)
                                                  //| (and,64)
                                                  //| (of,61)
                                                  //| (in,60)
                                                  //| (a,60)
                                                  //| (to,59)
                                                  //| (that,56)
                                                  //| ('s,55)
                                                  //| (state,51)
                                                  //| (for,48)
                                                  //| (have,48)
                                                  //| (on,47)
                                                  //| (Korea,46)
                                                  //| (nuclear,45)
                                                  //| (by,45)
                                                  //| (Afghanistan,43)
                                                  //| (South,42)
                                                  //| (will,42)
                                                  //| (with,42)
                                                  //| (say,42)
                                                  //| (year,41)
                                                  //| (at,41)
                                                  //| (government,41)
                                                  //| (from,40)
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
                                                  //| (it,34)
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
                                                  //| (or,27
                                                  //| Output exceeds cutoff limit.
}