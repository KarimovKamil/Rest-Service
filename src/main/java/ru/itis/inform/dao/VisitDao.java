package ru.itis.inform.dao;

import java.util.Date;
import java.sql.Timestamp;

public interface VisitDao {

    boolean verifyUserId(long userId);

    boolean verifyPageId(long pageId);

    void addVisit(long userId, long pageId, Timestamp date);

    int getPeriodVisits(Date startDate, Date endDate);

    int getPeriodUniqueUsers(Date startDate, Date endDate);

    int getPeriodRegularUsers(Date startDate, Date endDate);
}
