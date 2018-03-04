package ru.itis.inform.service;

import ru.itis.inform.model.PeriodStatistic;
import ru.itis.inform.model.TodayVisits;

import java.util.Date;

public interface VisitService {

    TodayVisits addVisit(long userId, long pageId);

    PeriodStatistic getPeriodStatistic(Date startDate, Date endDate);
}
