package ru.itis.inform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.inform.dao.VisitDao;
import ru.itis.inform.model.PeriodStatistic;
import ru.itis.inform.model.TodayVisits;
import ru.itis.inform.service.VisitService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    VisitDao visitDao;

    @Override
    public TodayVisits addVisit(long userId, long pageId) {
        if (visitDao.verifyPageId(pageId) && visitDao.verifyUserId(userId)) {
            Timestamp date = new Timestamp(new Date().getTime());
            visitDao.addVisit(userId, pageId, date);
            Date startDay = getStartOfDate();
            Date endDay = getEndDay(startDay);
            int uniqueUsers = visitDao.getPeriodUniqueUsers(startDay, endDay);
            int totalVisits = visitDao.getPeriodVisits(startDay, endDay);
            return new TodayVisits(totalVisits, uniqueUsers);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public PeriodStatistic getPeriodStatistic(Date startDate, Date endDate) {
        if (null != startDate && null != endDate) {
            int totalVisits = visitDao.getPeriodVisits(startDate, endDate);
            int uniqueUsers = visitDao.getPeriodUniqueUsers(startDate, endDate);
            int regularUsers = visitDao.getPeriodRegularUsers(startDate, endDate);
            PeriodStatistic periodStatistic = new PeriodStatistic(totalVisits, uniqueUsers, regularUsers);
            return periodStatistic;
        } else {
            return new PeriodStatistic(0, 0, 0);
        }
    }

    private Date getEndDay(Date startDay) {
        return new Date(startDay.getTime() + TimeUnit.HOURS.toMillis(24));
    }

    private Date getStartOfDate() {
        Date startDay = new Date(System.currentTimeMillis());
        startDay.setHours(0);
        startDay.setMinutes(0);
        startDay.setSeconds(0);
        return startDay;
    }
}
