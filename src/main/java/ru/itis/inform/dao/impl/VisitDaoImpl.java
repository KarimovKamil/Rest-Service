package ru.itis.inform.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.inform.dao.VisitDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class VisitDaoImpl implements VisitDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_VERIFY_USER_ID =
            "SELECT CASE WHEN EXISTS" +
                    "(SELECT 1 FROM rs_user WHERE user_id = ?) " +
                    "THEN TRUE ELSE FALSE END;";
    private static final String SQL_VERIFY_PAGE_ID =
            "SELECT CASE WHEN EXISTS" +
                    "(SELECT 1 FROM site_page WHERE page_id = ?) " +
                    "THEN TRUE ELSE FALSE END;";
    private static final String SQL_VISIT_INSERT =
            "INSERT INTO visit (user_id, page_id, visit_date) " +
                    "VALUES (:userId, :pageId, :date);";
    private static final String SQL_PERIOD_UNIQUE_USERS =
            "SELECT count(user_id) FROM (SELECT DISTINCT user_id FROM visit " +
                    "WHERE visit_date >= :startDate AND visit_date < :endDate) us;";
    private static final String SQL_PERIOD_TOTAL_VISITS =
            "SELECT count(visit_id) FROM visit " +
                    "WHERE visit_date >= :startDate AND visit_date < :endDate;";
    private static final String SQL_REGULAR_USERS =
            "SELECT count(user_id) FROM " +
                    "(SELECT count(visit_id) AS uc, user_id FROM visit " +
                    "GROUP BY(user_id)) c WHERE uc >= 10;";

    @Override
    public boolean verifyUserId(long userId) {
        return jdbcTemplate.queryForObject(SQL_VERIFY_USER_ID, boolean.class, userId);
    }

    @Override
    public boolean verifyPageId(long pageId) {
        return jdbcTemplate.queryForObject(SQL_VERIFY_PAGE_ID, boolean.class, pageId);
    }

    @Override
    public void addVisit(long userId, long pageId, Timestamp date) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("pageId", pageId);
        params.put("date", date);
        namedParameterJdbcTemplate.update(SQL_VISIT_INSERT, params);
    }

    @Override
    public int getPeriodVisits(Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return namedParameterJdbcTemplate.queryForObject(SQL_PERIOD_TOTAL_VISITS, params, int.class);
    }

    @Override
    public int getPeriodUniqueUsers(Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return namedParameterJdbcTemplate.queryForObject(SQL_PERIOD_UNIQUE_USERS, params, int.class);
    }

    @Override
    public int getPeriodRegularUsers(Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return namedParameterJdbcTemplate.queryForObject(SQL_REGULAR_USERS, params, int.class);
    }
}
