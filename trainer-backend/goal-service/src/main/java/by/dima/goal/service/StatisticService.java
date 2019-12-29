package by.dima.goal.service;

import by.dima.goal.model.StatisticItem;

import java.util.List;

public interface StatisticService {
    List<StatisticItem> getAll(Integer userId);
}
