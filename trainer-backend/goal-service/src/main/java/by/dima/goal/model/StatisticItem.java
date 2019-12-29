package by.dima.goal.model;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class StatisticItem {
    private String name;
    private Map<String, Collection<Double>> value;
}
