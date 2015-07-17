package amr

object ActionsPerLemma {

ImportConcepts.initialise("C:\\AMR\\trainingSet150.txt")

ImportConcepts.conceptStrings                     //> Adding annotator tokenize
                                                  //| Adding annotator ssplit
                                                  //| Adding annotator parse
                                                  //| Loading parser from serialized file edu/stanford/nlp/models/lexparser/englis
                                                  //| hPCFG.ser.gz ... done [1.1 sec].
                                                  //| Adding annotator lemma
                                                  //| Adding annotator ner
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.all.3class.dists
                                                  //| im.crf.ser.gz ... done [4.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.muc.7class.dists
                                                  //| im.crf.ser.gz ... done [2.2 sec].
                                                  //| Loading classifier from edu/stanford/nlp/models/ner/english.conll.4class.dis
                                                  //| tsim.crf.ser.gz ... done [3.8 sec].
                                                  //| WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept together
                                                  //| WARNING: Found duplicate match for concept -
                                                  //| WARNING: Found duplicate match for concept "NATO"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept foreign
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept border-01
                                                  //| WARNING: Found duplicate match for concept "Afghanistan"
                                                  //| WARNING: Found duplicate match for concept "Tajikistan"
                                                  //| WARNING: Found duplicate match for concept "Russian"
                                                  //| WARNING: Found duplicate match for concept "Federation"
                                                  //| WARNING: Found duplicate match for concept force
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept progress-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept plan-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept design-01
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept early
                                                  //| WARNING: Found duplicate match for concept submarine
                                                  //| res0: scala.collection.immutable.Set[String] = Set(respond-01, defend-01, as
                                                  //| sertive, altitude, relate-01, health, contrast-01, chief, distance-quantity,
                                                  //|  program, network, say-01, false, officer, defense, series, any, city, acros
                                                  //| s, remain-01, name, this, state-01, impress-01, quantity, tighten-01, i
                                                  //| Output exceeds cutoff limit.
ImportConcepts.conceptStrings                     //> res1: scala.collection.immutable.Set[String] = Set(respond-01, defend-01, as
                                                  //| sertive, altitude, relate-01, health, contrast-01, chief, distance-quantity,
                                                  //|  program, network, say-01, false, officer, defense, series, any, city, acros
                                                  //| s, remain-01, name, this, state-01, impress-01, quantity, tighten-01, includ
                                                  //| e-91, commander-in-chief, fingerprint, point-01, fierce, task, watch-01, for
                                                  //| ce, president, slow, fund-01, needle, intend-01, market, hard, progress-01, 
                                                  //| select-01, date-entity, system, real, commission-01, assure-01, boast-01, ca
                                                  //| use-01, history, late, border, have-org-role-91, make-01, hit-01, nation, dr
                                                  //| op-01, land, so-far, future, security, essential, threaten-01, rhetoric, eve
                                                  //| nt, ordinal-entity, sea, police, border-01, recent, image, project, center, 
                                                  //| good, world, die-01, bolster-01, scale, war, dispute-01, smoke-01, illness, 
                                                  //| equip-01, northeast, fail-01, participate-01, precedent, get-01, seize-01, a
                                                  //| buse-01, commander, dense, temporal-quantity, warn-01, observe-01, display-0
                                                  //| 1, capable-41, convoy, infect-01, sovereignty, examine-01, immediate, corpor
                                                  //| ate, site, dollar, decrease-01, join-05, develop-02, silo, missile, counter-
                                                  //| 01, cross-02, warhead, minister, everybody, receive-01, decoy, build-01, pre
                                                  //| cede-01, design-01, thing, speak-01, vow-01, modernize-01, regular, year, sw
                                                  //| ear-06, provide-01, strike-01, monetary-quantity, clear-04, specialize-01, c
                                                  //| ertain, conduct-01, agree-01, country, computer, such, avoid-01, launch-01, 
                                                  //| last, opium, joint, terrorism, square, out, neighbor-01, deny-01, pine, scie
                                                  //| nce, base, key, arrest-01, -, approximately, modern, technology, web, raise-
                                                  //| 01, treaty, small, around, salary, rate-entity-91, repair-01, most, collapse
                                                  //| -01, hardware, more-than, week, version, propose-01, civilian, arm-01, trans
                                                  //| it-01, legal, buy-01, estimate-01, join-02, portray-01, low, difficult, truc
                                                  //| k, step-01, backbone, research-01, gun, test-01, also, enemy, ex, sign-01, g
                                                  //| eneral, control-01, globe, motivate-01, mind, oil, prospective, navy, space,
                                                  //|  after, mount-02, have-part-91, cooperate-01, capital, make-02, obligate-01,
                                                  //|  have-03, rare, international, open-01, consider-02, block-01, disease, warf
                                                  //| are, cyber, penetrate-01, scene, supply-01, train-01, increase-01, world-reg
                                                  //| ion, official, even, conceal-01, mentor-01, recency, revenue, new, attack-01
                                                  //| , few, foreign, strategy, proceed-01, speed-01, good-42, early, an
                                                  //| Output exceeds cutoff limit.
ImportConcepts.conceptsPerLemma.getOrElse("university", List()) foreach (x => println(ImportConcepts.concept(x)))
(ImportConcepts.conceptsPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (a,339)
                                                  //| (the,297)
                                                  //| (and,248)
                                                  //| (in,221)
                                                  //| (of,216)
                                                  //| (to,196)
                                                  //| (be,175)
                                                  //| ('s,155)
                                                  //| (missile,151)
                                                  //| (that,121)
                                                  //| (have,103)
                                                  //| (state,98)
                                                  //| (make,87)
                                                  //| (for,79)
                                                  //| (on,79)
                                                  //| (military,67)
                                                  //| (cyber,65)
                                                  //| (nuclear,62)
                                                  //| (at,62)
                                                  //| (year,61)
                                                  //| (threat,60)
                                                  //| (from,58)
                                                  //| (it,57)
                                                  //| (as,57)
                                                  //| (country,57)
                                                  //| (government,56)
                                                  //| (with,55)
                                                  //| (border,54)
                                                  //| (will,53)
                                                  //| (by,52)
                                                  //| (defense,51)
                                                  //| (base,49)
                                                  //| (new,49)
                                                  //| (Federation,47)
                                                  //| (Russian,46)
                                                  //| (drug,45)
                                                  //| (attack,44)
                                                  //| (funding,41)
                                                  //| (',40)
                                                  //| (promise,40)
                                                  //| (such,39)
                                                  //| (force,38)
                                                  //| (announce,37)
                                                  //| (after,37)
                                                  //| (heroin,37)
                                                  //| (chief,36)
                                                  //| (President,36)
                                                  //| (necessary,36)
                                                  //| (submarine,33)
                                                  //| (research,32)
                                                  //| (center,32)
                                                  //| (official,32)
                                                  //| (teikovo,31)
                                                  //| (commander,31)
                                                  //| (take,31)
                                                  //| (fund,31)
                                                  //| (part,31)
                                                  //| (parade,31)
                                                  //| (design,30)
                                                  //| (than,30)
                                                  //| (security,30)
                                                  //| (or,30)
                                                  //| (provide,30)
                                                  //| (Strategic,30)
                                                  //| (not,30)
                                                  //| (opium,29)
                                                  //| (narcotic,29)
                                                  //| (continue,29)
                                                  //| (since,29)
                                                  //| (mile,28)
                                                  //| (difficult,28)
                                                  //| (terrorism,27)
                                                  //| (target,27)
                                                  //| (ballistic,26)
                                                  //| (topol-m,26)
                                                  //| (test,25)
                                                  //| (equip,25)
                                                  //| (series,25)
                                                  //| (fail,25)
                                                  //| (this,25)
                                                  //| (deployment,25)
                                                  //| (bulava,25)
                                                  //| (commission,25)
                                                  //| (but,25)
                                                  //| (agreement,25)
                                                  //| (prospects,25)
                                                  //| (uncertain,25)
                                                  //| (global,25)
                                                  //| (debut,25)
                                                  //| (deploy,25)
                                                  //| (counter,25)
                                                  //| (nuclear-armed,25)
                                                  //| (armed,25)
                                                  //| (develop,25)
                                                  //| (about,25)
                                                  //| (tour,25)
                                                  //| (may,25)
                                                  //| (other,25)
                                                  //| (Minister,24)
                                                  //| (sign,24)
                                                  //| (conference,24)
                                                  //| (news,24)
                                                  //| (more,24)
                                                  //| (network,23)
                                                  //| (convoy,23)
                                                  //| (arrest,23)
                                                  //| (regularly,23)
                                                  //| (computer,23)
                                                  //| (large-scale,23)
                                                  //| (seizure,23)
                                                  //| (cross,23)
                                                  //| (weapon,23)
                                                  //| (neighboring,23)
                                                  //| (illegal,23)
                                                  //| (ally,23)
                                                  //| (altitude,22)
                                                  //| (significantly,22)
                                                  //| (launch,22)
                                                  //| (officer,22)
                                                  //| (hard,22)
                                                  //| (system,22)
                                                  //| (detect,22)
                                                  //| (earlier,22)
                                                  //| (warning,22)
                                                  //| (lower,22)
                                                  //| (drop,22)
                                                  //| (enemy,22)
                                                  //| (solomonov,22)
                                                  //| (early,22)
                                                  //| (which,22)
                                                  //| (kilometer,22)
                                                  //| (engine,22)
                                                  //| (neighbor,21)
                                                  //| (due,21)
                                                  //| (quantity,21)
                                                  //| (needle,21)
                                                  //| (history,21)
                                                  //| (abuse,21)
                                                  //| (would,21)
                                                  //| (police,21)
                                                  //| (tighten,21)
                                                  //| (illness,21)
                                                  //| (dollar,21)
                                                  //| (estimate,21)
                                                  //| (minister,21)
                                                  //| (they,21)
                                                  //| (each,21)
                                                  //| (l
                                                  //| Output exceeds cutoff limit.
ImportConcepts.edgesPerLemma foreach println      //> (penetrate,Set(5, 24, 37, 14, 35, 18, 23, 4))
                                                  //| (assertive,Set(5, 24, 37, 14, 39, 35, 18, 11, 26, 4))
                                                  //| (altitude,Set(5, 10, 24, 37, 14, 33, 2, 17, 35, 18, 26, 30, 4))
                                                  //| (significantly,Set(5, 10, 24, 37, 14, 33, 2, 17, 35, 18, 26, 30, 4))
                                                  //| (launch,Set(5, 10, 24, 37, 14, 33, 2, 17, 35, 18, 26, 30, 4))
                                                  //| (Square,Set(42, 24, 37, 14, 38, 21, 41, 7, 35, 18, 11, 26, 36, 30, 4))
                                                  //| (health,Set(35, 24, 5))
                                                  //| (afghan-sourced,Set(5, 24, 37, 14, 29, 2, 27, 7, 35, 18, 40, 23, 8, 4))
                                                  //| (chief,Set(5, 42, 24, 37, 25, 14, 6, 38, 21, 35, 18, 26, 23, 8, 30, 4, 15))
                                                  //| 
                                                  //| (for,Set(5, 10, 42, 24, 37, 25, 14, 29, 1, 6, 38, 21, 33, 9, 41, 2, 34, 17, 
                                                  //| 7, 35, 18, 11, 26, 23, 8, 36, 30, 4, 15))
                                                  //| (program,Set(5, 42, 24, 37, 14, 38, 21, 35, 18, 26, 23, 30, 4))
                                                  //| (network,Set(5, 24, 37, 14, 21, 35, 18, 26, 23, 8, 30, 4))
                                                  //| (neighbor,Set(24, 37, 14, 6, 35, 18, 31, 11, 8, 30, 4))
                                                  //| (announce,Set(5, 24, 37, 14, 29, 6, 2, 17, 3, 35, 18, 11, 23, 8, 30, 4))
                                                  //| (false,Set(5, 24, 37, 14, 34, 17, 35, 18, 23, 30, 4))
                                                  //| (officer,Set(5, 24, 37, 14, 35, 18, 11, 26, 23, 8, 19, 4))
                                                  //| (test,Set(24, 37, 25, 14, 3, 35, 18, 23, 8, 30, 19, 4))
                                                  //| (defense,Set(5, 24, 37, 14, 6, 28, 17, 35, 18, 31, 11, 23, 8, 30, 4))
                                                  //| (equip,Set(24, 37, 25, 14, 3, 35, 18, 23, 8, 30, 19, 4))
                                                  //| (series,Set(24, 37, 25, 14, 3, 35, 18, 23, 8, 30, 19, 4))
                                                  //| (due,Set(5, 24, 37, 14, 29, 2, 27, 7, 35, 18, 40, 23, 8, 4))
                                                  //| (mentor,Set(24, 37, 35, 18, 4))
                                                  //| (any,Set(5, 24, 37, 14, 35, 18, 23, 4))
                                                  //| (Federation,Set(5, 42, 24, 37, 25, 14, 29, 1, 6, 38, 21, 2, 35, 18, 26, 23, 
                                                  //| 8, 30, 4, 15))
                                                  //| (fail,Set(24, 37, 25, 14, 3, 35, 18, 23, 8, 30, 19, 4))
                                                  //| (Mattis,Set(5, 24, 37, 14, 32, 35, 18, 23, 4))
                                                  //| (Poland,Set(24, 37, 14, 6, 35, 18, 31, 11, 8, 30, 4))
                                                  //| (across,Set(24, 37, 14, 1, 12, 35, 18, 30, 19, 4))
                                                  //| (afghan,Set(5, 24, 37, 14, 13, 35, 18, 11, 23, 30, 4))
                                                  //| (this,Set(24, 37, 25, 14, 3, 35, 18, 23, 8, 30, 19, 4))
                                                  //| (in,Set(5, 42, 24, 37, 25, 14, 20, 29, 1, 6, 28, 38, 21, 13, 41, 2, 32, 34, 
                                                  //| 17, 22, 12, 7, 3, 35, 18, 31, 11, 26, 23, 8, 36, 30, 19, 4, 15))
                                                  //| (quantity,Set(24, 37, 14, 29, 2, 17, 35, 18, 11, 23, 8, 30, 4))
                                                  //| (strengthen,Set(5, 24, 37, 14, 39, 35, 18, 11, 26, 4))
                                                  //| (design,Set(5, 10, 24, 37, 14, 33, 2, 17, 35, 18, 26, 23, 30, 4))
                                                  //| (RU,Set(4, 24, 35))
                                                  //| (have,Set(5, 42, 24, 37, 25, 14, 29, 6, 28, 38, 21, 2, 17, 3, 35, 18, 16, 26
                                                  //| , 23, 8, 30, 19, 4))
                                                  //| (fingerprint,Set(5, 24, 14, 20, 17, 3, 35, 
                                                  //| Output exceeds cutoff limit.
ImportConcepts.relationStrings                    //> res2: scala.collection.immutable.Set[String] = Set(duration, quant, polarity
                                                  //| , name, ARG0-of, manner, source, ARG1-of, prep-for, ARG4, location, path, ac
                                                  //| companier, mod, prep-as, prep-against, degree, ARG1, domain, location-of, ye
                                                  //| ar, direction, ARG2, opN, purpose, poss, frequency, topic, unit, time, ARG2-
                                                  //| of, ARG3, compared-to, part-of, ROOT, value, ARG0, day, example-of, instrume
                                                  //| nt, ord, month)
(ImportConcepts.edgesPerLemma map { case (lemma, set) => (lemma, set.size)}).toList.sortWith((a, b) => a._2 > b._2) foreach println
                                                  //> (the,39)
                                                  //| (in,35)
                                                  //| (and,35)
                                                  //| (to,34)
                                                  //| (of,34)
                                                  //| ('s,33)
                                                  //| (a,32)
                                                  //| (missile,31)
                                                  //| (for,29)
                                                  //| (that,29)
                                                  //| (Russia,28)
                                                  //| (state,27)
                                                  //| (be,27)
                                                  //| (Medvedev,26)
                                                  //| (Dmitry,26)
                                                  //| (country,25)
                                                  //| (make,25)
                                                  //| (on,24)
                                                  //| (from,24)
                                                  //| (have,23)
                                                  //| (Afghanistan,23)
                                                  //| (NATO,23)
                                                  //| (Russian,22)
                                                  //| (base,22)
                                                  //| (May,22)
                                                  //| (cyber,22)
                                                  //| (will,22)
                                                  //| (with,22)
                                                  //| (threat,22)
                                                  //| (as,21)
                                                  //| (nuclear,21)
                                                  //| (government,21)
                                                  //| (funding,21)
                                                  //| (military,21)
                                                  //| (Federation,20)
                                                  //| (necessary,20)
                                                  //| (it,20)
                                                  //| (year,20)
                                                  //| (first,20)
                                                  //| (promise,20)
                                                  //| (since,20)
                                                  //| (teikovo,19)
                                                  //| (border,19)
                                                  //| (Moscow,19)
                                                  //| (China,19)
                                                  //| (India,19)
                                                  //| (at,19)
                                                  //| (Vladimir,19)
                                                  //| (by,19)
                                                  //| (Putin,19)
                                                  //| (drug,19)
                                                  //| (Yuri,19)
                                                  //| (research,18)
                                                  //| (center,18)
                                                  //| (commander,18)
                                                  //| (provide,18)
                                                  //| (take,18)
                                                  //| (part,18)
                                                  //| (parade,18)
                                                  //| (chief,17)
                                                  //| (force,17)
                                                  //| (agreement,17)
                                                  //| (President,17)
                                                  //| (such,17)
                                                  //| (global,17)
                                                  //| (debut,17)
                                                  //| (counter,17)
                                                  //| (heroin,17)
                                                  //| (armed,17)
                                                  //| (tour,17)
                                                  //| (announce,16)
                                                  //| (than,16)
                                                  //| (opium,16)
                                                  //| (sign,16)
                                                  //| (',16)
                                                  //| (U.S.,16)
                                                  //| (soviet,16)
                                                  //| (attack,16)
                                                  //| (ally,16)
                                                  //| (Square,15)
                                                  //| (defense,15)
                                                  //| (Lavrov,15)
                                                  //| (security,15)
                                                  //| (Red,15)
                                                  //| (or,15)
                                                  //| (Day,15)
                                                  //| (Missiles,15)
                                                  //| (hardware,15)
                                                  //| (mile,15)
                                                  //| (solomonov,15)
                                                  //| (time,15)
                                                  //| (Victory,15)
                                                  //| (when,15)
                                                  //| (display,15)
                                                  //| (collapse,15)
                                                  //| (afghan-sourced,14)
                                                  //| (due,14)
                                                  //| (design,14)
                                                  //| (needle,14)
                                                  //| (abuse,14)
                                                  //| (illness,14)
                                                  //| (Russians,14)
                                                  //| (each,14)
                                                  //| (Strategic,14)
                                                  //| (infected,14)
                                                  //| (die,14)
                                                  //| (after,14)
                                                  //| (new,14)
                                                  //| (aids-related,14)
                                                  //| (not,14)
                                                  //| (thousand,14)
                                                  //| (target,14)
                                                  //| (fund,14)
                                                  //| (continue,14)
                                                  //| (altitude,13)
                                                  //| (significantly,13)
                                                  //| (launch,13)
                                                  //| (program,13)
                                                  //| (quantity,13)
                                                  //| (task,13)
                                                  //| (hard,13)
                                                  //| (system,13)
                                                  //| (history,13)
                                                  //| (sure,13)
                                                  //| (rhetoric,13)
                                                  //| (police,13)
                                                  //| (image,13)
                                                  //| (detect,13)
                                                  //| (so,13)
                                                  //| (convoy,13)
                                                  //| (dollar,13)
                                                  //| (estimate,13)
                                                  //| (receive,13)
                                                  //| (arrest,13)
                                                  //| (earlier,13)
                                                  //| (swear,13)
                                                  //| (regularly,13)
                                                  //| (topol-m,13)
                                                  //| (warning,13)
                                                  //| (lower,13)
                                                  //| (they,13)
                                                  //| (far,13)
                                                  //| (last,13)
                                                  //| (avoid,13)
                                                  //| (Missile,13)
                                                  //| (euro,13)
                                                  //| (drop,13)
                                                  //| (portray,13)
                                                  //| (approximately,13)
                                                  //| (modern,13)
                                                  //| 
                                                  //| Output exceeds cutoff limit.
}