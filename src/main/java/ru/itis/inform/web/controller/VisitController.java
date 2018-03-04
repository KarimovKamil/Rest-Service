package ru.itis.inform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.inform.model.PeriodStatistic;
import ru.itis.inform.model.TodayVisits;
import ru.itis.inform.service.VisitService;

import java.sql.Date;

@Controller
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    VisitService visitService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public TodayVisits addVisit(@RequestParam(value = "userId") long userId,
                                @RequestParam(value = "pageId") long pageId) {
        TodayVisits todayVisits = visitService.addVisit(userId, pageId);
        return todayVisits;
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PeriodStatistic getDayStatistic(@RequestParam(value = "startDate") Date startDate,
                                           @RequestParam(value = "endDate") Date endDate) {
        PeriodStatistic periodStatistic = visitService.getPeriodStatistic(startDate, endDate);
        return periodStatistic;
    }

}
