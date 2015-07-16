package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\LargeTrainingSet.txt")

ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.9 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.7 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.4 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [5.1 sec].
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
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (state,624)
                                                  //| (have,554)
                                                  //| (government,326)
                                                  //| (will,294)
                                                  //| (drug,263)
                                                  //| (Korea,256)
                                                  //| (Afghanistan,237)
                                                  //| (say,231)
                                                  //| (official,226)
                                                  //| (at,224)
                                                  //| (South,217)
                                                  //| (would,204)
                                                  //| (year,204)
                                                  //| (they,202)
                                                  //| (nuclear,192)
                                                  //| (as,182)
                                                  //| (its,180)
                                                  //| (not,177)
                                                  //| (could,177)
                                                  //| (program,176)
                                                  //| (against,176)
                                                  //| (group,175)
                                                  //| (include,173)
                                                  //| (United,172)
                                                  //| (russian,167)
                                                  //| (heroin,166)
                                                  //| (also,162)
                                                  //| (military,162)
                                                  //| (North,161)
                                                  //| (international,157)
                                                  //| (weapon,150)
                                                  //| (trafficking,148)
                                                  //| (States,147)
                                                  //| (power,145)
                                                  //| (make,143)
                                                  //| (security,140)
                                                  //| (Japan,138)
                                                  //| (terrorism,138)
                                                  //| (opium,136)
                                                  //| (than,134)
                                                  //| (ban,133)
                                                  //| (country,133)
                                                  //| (other,132)
                                                  //| (report,131)
                                                  //| (this,130)
                                                  //| (there,129)
                                                  //| (newspaper,128)
                                                  //| (Russia,128)
                                                  //| (or,126)
                                                  //| (which,126)
                                                  //| (Ozawa,125)
                                                  //| (but,124)
                                                  //| (Boeing,124)
                                                  //| (border,123)
                                                  //| (more,121)
                                                  //| (Asia,119)
                                                  //| (cooperation,119)
                                                  //| (January,118)
                                                  //| (afghan,118)
                                                  //| (over,118)
                                                  //| (he,116)
                                                  //| (end,116)
                                                  //| (Tajikistan,116)
                                                  //| (because,114)
                                                  //| (system,113)
                                                  //| (korean,113)
                                                  //| (trade,113)
                                                  //| (rights,112)
                                                  //| (plutonium,112)
                                                  //| (dog,111)
                                                  //| (attack,111)
                                                  //| (take,110)
                                                  //| (dollar,109)
                                                  //| (space,108)
                                                  //| (property,108)
                                                  //| (Defense,108)
                                                  //| (many,108)
                                                  //| (one,108)
                                                  //| (intellectual,108)
                                                  //| (U.S.,107)
                                                  //| (between,107)
                                                  //| (if,106)
                                                  //| (plan,104)
                                                  //| (launch,103)
                                                  //| (any,102)
                                                  //| (use,102)
                                                  //| (force,101)
                                                  //| (Mohanty,101)
                                                  //| (N.,101)
                                                  //| (technology,101)
                                                  //| (fighter,101)
                                                  //| (R.,101)
                                                  //| (action,100)
                                                  //| (nation,99)
                                                  //| (Aeronautics,99)
                                                  //| (Hindustan,99)
                                                  //| (Limited,99)
                                                  //| (all,98)
                                                  //| (daily,98)
                                                  //| (under,98)
                                                  //| (terrorist,97)
                                                  //| (million,97)
                                                  //| (recent,96)
                                                  //| (publication,96)
                                                  //| (poppy,96)
                                                  //| (first,95)
                                                  //| (when,95)
                                                  //| (February,94)
                                                  //| (after,94)
                                                  //| (meet,94)
                                                  //| (Southeast,93)
                                                  //| (IAEA,92)
                                                  //| (world,92)
                                                  //| (islamic,92)
                                                  //| (Myanmar,92)
                                                  //| (service,92)
                                                  //| (company,92)
                                                  //| (support,91)
                                                  //| (defense,91)
                                                  //| (statement,91)
                                                  //| (April,91)
                                                  //| (center,89)
                                                  //| (who,89)
                                                  //| (percent,89)
                                                  //| (Nations,88)
                                                  //| (International,88)
                                                  //| (some,88)
                                                  //| (last,87)
                                                  //| (become,87)
                                                  //| (Asian,87)
                                                  //| (people,86)
                                                  //| (rule,86)
                                                  //| (work,86)
                                                  //| (issue,86)
                                                  //| (pakistani,86)
                                                  //| (ASEAN,85)
                                                  //| (need,85)
                                                  //| (Rakhimov,85)
                                                  //| (jet,84)
                                                  //| (cost,84)
                                                  //| (religious,84)
                                                  //| (police,83)
                                                  //| (China,83)
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
                                                  //| (have,48)
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
                                                  //| (ann
                                                  //| Output exceeds cutoff limit.|
}