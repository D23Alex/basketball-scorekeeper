package com.d23alex.vtbstat;

import java.util.*;
import java.util.stream.Collectors;

public class LeagueSchedule {
    public static final Map<Integer, Date> seasonStart = Map.of(
            2021, new GregorianCalendar(2021, 9, 1).getTime(),
            2022, new GregorianCalendar(2022, 9, 1).getTime(),
            2023, new GregorianCalendar(2023, 9, 1).getTime()
    );

    public static final Map<Integer, Date> seasonEnd = Map.of(
            2021, new GregorianCalendar(2022, 6, 1).getTime(),
            2022, new GregorianCalendar(2023, 6, 1).getTime(),
            2023, new GregorianCalendar(2024, 6, 1).getTime()
    );

    // Сезоны, хотя бы частично попадающие во временной промежуток
    public static Set<Integer> seasonsInRange(Date from, Date to) {
        return seasonEnd.keySet().stream().filter(season -> seasonStart.get(season).before(to)
                && seasonEnd.get(season).after(from)).collect(Collectors.toSet());
    }
}
