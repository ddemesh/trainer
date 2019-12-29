package by.dima.goal.service.impl;

import by.dima.goal.model.Progress;
import by.dima.goal.model.StatisticItem;
import by.dima.goal.repository.GoalRepository;
import by.dima.goal.service.GoalService;
import by.dima.goal.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class StatisticServiceImpl implements StatisticService {

    private GoalRepository goalService;

    public StatisticServiceImpl(GoalRepository goalService) {
        this.goalService = goalService;
    }

    @Override
    public List<StatisticItem> getAll(Integer userId) {
        Integer currentMonth = new Date().getMonth();
        Integer currentDay = new Date().getDate();

        return goalService.findAllByUserId(userId).parallelStream()
                .filter(goal -> goal.getTargetDate() == null && goal.getGoal() == null)
                .map(goal -> {
                    StatisticItem item = new StatisticItem();
                    item.setName(goal.getBodyAttribute().getName());
                    Map<String, Collection<Double>> map = new HashMap<>();
                    if (goal.getProgress().size() > 0) {
                        map.put("week", fillGaps(goal.getProgress()
                                .parallelStream()
                                .filter(progress -> {
                                    if (progress.getDate() == null) return false;
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(progress.getDate());
                                    calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                                    return calendar.getTime().compareTo(new Date()) >= 0;
                                })
                                .collect(groupingBy(progress -> progress.getDate().getDay(),
                                        averagingDouble(Progress::getValue))), 1, 7).values());
                        map.put("month", fillGaps(goal.getProgress()
                                .parallelStream().filter(progress -> {
                                    if (progress.getDate() == null) return false;
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(progress.getDate());
                                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                                    return calendar.getTime().compareTo(new Date()) >= 0;
                                })
                                .collect(groupingBy(progress -> progress.getDate().getDate(),
                                        averagingDouble(Progress::getValue))), 1, currentDay).values());
                        map.put("season", fillGaps(goal.getProgress()
                                .parallelStream().filter(progress -> {
                                    if (progress.getDate() == null) return false;
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(progress.getDate());
                                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3);
                                    return calendar.getTime().compareTo(new Date()) >= 0;
                                })
                                .collect(groupingBy(progress -> progress.getDate().getMonth(),
                                        averagingDouble(Progress::getValue))), currentMonth - 2, currentMonth).values());
                        map.put("halfyear", fillGaps(goal.getProgress()
                                .parallelStream().filter(progress -> {
                                    if (progress.getDate() == null) return false;
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(progress.getDate());
                                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 6);
                                    return calendar.getTime().compareTo(new Date()) >= 0;
                                })
                                .collect(groupingBy(progress -> progress.getDate().getMonth(),
                                        averagingDouble(Progress::getValue))),currentMonth - 5, currentMonth).values());
                        map.put("year", fillGaps(goal.getProgress()
                                .parallelStream().filter(progress -> {
                                    if (progress.getDate() == null) return false;
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(progress.getDate());
                                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 12);
                                    return calendar.getTime().compareTo(new Date()) >= 0;
                                })
                                .collect(groupingBy(progress -> progress.getDate().getMonth(),
                                        averagingDouble(Progress::getValue))), currentMonth - 11, currentMonth).values());
                    }
                    item.setValue(map);
                    return item;
                })
                .collect(toList());
    }

    private Map<Integer, Double> fillGaps(Map<Integer, Double> data, int from, int to) {
        for (int i = from; i <= to; i++) {
            data.putIfAbsent(i, 0.0);
        }
        return data;
    }
}
