package amr

object conceptLoading {

import ImportConcepts._
loadConcepts("C:\\AMR\\testAMRfull.txt")          //> res0: <error> = Set("James", incident, serious, "Saudi", submit-01, "Taiwan"
                                                  //| , spokeswoman, "Finland", voice-01, devote-01, respond-01, assault-01, accid
                                                  //| ent, "Michoacan", close-01, defend-01, assertive, antenna, eye, "IPPR", "Ura
                                                  //| l", minority, automatic, "Gulf", consider-01, secretary, altitude, 150000000
                                                  //| 0000, "Strait", "Stephane", relate-01, sunday, "Martinez", lead-01, "London"
                                                  //| , engage-01, down, 388, 17.8, recruit-01, side, expert-41, conventional, "Du
                                                  //| ncan", surveil-01, "Long", measure, health, secure-01, panel, quality, econo
                                                  //| my, futile, come-04, raid-01, interministerial, pass-07, 153, realize-02, ca
                                                  //| librate-01, jurisdiction, convert-01, 2003, secret, number, mass, 93, "Asia-
                                                  //| Pacific", "Familia", contrast-01, asymmetrical, behind, 30000000, demonstrat
                                                  //| e-01, mother, chief, 1950, for, organization, mention-01, vessel, discrimina
                                                  //| te-01, politics, distance-quantity, slice-01, disarm-01, 147, program, netwo
                                                  //| rk, originate-01, business, say-01, funeral, prevent-01, interrogative, "Hum
                                                  //| an", gunman, "Felix", see-01, false, 2020, membership, geostationary, questi
                                                  //| on, monitor, chip, ship-01, function-01, community, budget-01, officer, "Top
                                                  //| ol-M", test, subassembly, further, relative, "Taipei", defense, "North", 12,
                                                  //|  replace-01, unleash-01, 3450, patrol-01, outer, "NC", "Hadoux", average-01,
                                                  //|  paint, trend, injure-01, reach, "Verhagen", sophistication, series, newspap
                                                  //| er, due, address-01, any, resist-01, w
                                                  //| Output exceeds cutoff limit.
 loadRelations("C:\\AMR\\testAMRfull.txt")        //> res1: <error> = Set(prep-to, quant-of, duration, quant, polarity, name, prep
                                                  //| -among, ARG0-of, manner, source, ARG1-of, prep-for, time-of, consist-of, pre
                                                  //| p-in, ARG4, location, path, degree-of, accompanier, mod, prep-from, prep-as,
                                                  //|  extent, prep-against, snt2, degree, prep-under, ARG1, ARG4-of, extent-of, d
                                                  //| omain, concession, concession-of, decade, location-of, century, prep-without
                                                  //| , subevent-of, year, direction, medium, season, age, ARG2, dayperiod, opN, p
                                                  //| urpose, condition, medium-of, duration-of, weekday, mode, beneficiary, snt1,
                                                  //|  ARG3-of, poss, frequency, topic, manner-of, unit, time, ARG2-of, ARG3, comp
                                                  //| ared-to, part-of, path-of, example, instrument-of, prep-on, prep-into, ROOT,
                                                  //|  destination, value, ARG0, prep-with, day, example-of, prep-on-behalf-of, in
                                                  //| strument, ord, poss-of, month)

}