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
initialise("C:\\AMR\\testAMR.txt")

conceptStrings                                    //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept together
                                                  //| WARNING: Found duplicate match for concept "NATO"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept foreign
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept border-01
                                                  //| WARNING: Found duplicate match for concept "Afghanistan"
                                                  //| WARNING: Found duplicate match for concept "Russian"
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
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include
                                                  //| Output exceeds cutoff limit.
conceptStrings                                    //> res5: Set[String] = Set(incident, serious, submit-01, spokeswoman, voice-01,
                                                  //|  devote-01, respond-01, assault-01, accident, close-01, defend-01, assertive
                                                  //| , antenna, eye, minority, automatic, consider-01, secretary, altitude, relat
                                                  //| e-01, sunday, lead-01, engage-01, down, recruit-01, side, expert-41, convent
                                                  //| ional, surveil-01, measure, health, secure-01, panel, quality, economy, futi
                                                  //| le, come-04, raid-01, interministerial, pass-07, realize-02, calibrate-01, j
                                                  //| urisdiction, convert-01, secret, number, mass, contrast-01, asymmetrical, be
                                                  //| hind, demonstrate-01, mother, chief, for, organization, mention-01, vessel, 
                                                  //| discriminate-01, politics, distance-quantity, slice-01, disarm-01, program, 
                                                  //| network, originate-01, business, say-01, funeral, prevent-01, interrogative,
                                                  //|  gunman, see-01, false, membership, geostationary, question, monitor, chip, 
                                                  //| ship-01, function-01, community, budget-01, officer, test, subassembly, furt
                                                  //| her, relative, defense, replace-01, unleash-01, patrol-01, outer, average-01
                                                  //| , paint, trend, injure-01, reach, sophistication, series, newspaper, due, ad
                                                  //| dress-01, any, resist-01, website, gain-02, city, list-01, sell-01, insight,
                                                  //|  body, stage, kill-01, prefer-01, across, tragedy, come-01, plant, bomblet, 
                                                  //| spaceflight, court, percentage-entity, young, reprisal, powder, area, remain
                                                  //| -01, name, huge, this, issue-01, ease-01, state-01, work-01, door, strict, c
                                                  //| emetery, decal, refute-01, fraction, drive-02, show-01, amount-01, diesel-el
                                                  //| ectric, impress-01, win-01, quantity, plate, denominator, appear-01, wrestle
                                                  //| -01, professional, anonymity, traffic-00, tighten-01, cargo, scientist, vote
                                                  //| -01, include-91, off, as-of, commander-in-chief, fingerprint, develop-01, re
                                                  //| sult-01, once, passenger, point-01, channel, grip-01, fierce, determine-01, 
                                                  //| market-01, communication, crisis, smuggle-01, admit-01, combat, label-01, ta
                                                  //| sk, bankroll-01, watch-01, reconnaissance, trade-01,
                                                  //| Output exceeds cutoff limit.
relationStrings                                   //> WARNING: Found duplicate match for concept attack-01
                                                  //| WARNING: Found duplicate match for concept cyber
                                                  //| WARNING: Found duplicate match for concept together
                                                  //| WARNING: Found duplicate match for concept "NATO"
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept minister
                                                  //| WARNING: Found duplicate match for concept foreign
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept border-01
                                                  //| WARNING: Found duplicate match for concept "Afghanistan"
                                                  //| WARNING: Found duplicate match for concept "Russian"
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
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept state-01
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept missile
                                                  //| WARNING: Found duplicate match for concept image
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept include-01
                                                  //| WARNING: Found duplicate match for concept and
                                                  //| WARNING: Found duplicate match for concept include
                                                  //| Output exceeds cutoff limit.-

}