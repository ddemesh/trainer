package by.dima.goal.service;

import by.dima.goal.model.BodyAttribute;
import by.dima.goal.model.Progress;

public interface BodyAttributeService {
    Iterable<BodyAttribute> getAll();
    BodyAttribute get(Integer attributeId);
    void update(BodyAttribute bodyAttribute, Progress progress, Integer userId);
}
