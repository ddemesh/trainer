package by.dima.goal.service.impl;

import by.dima.goal.model.Goal;
import by.dima.goal.model.Progress;
import by.dima.goal.service.GoalService;
import by.dima.goal.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProgressServiceImpl implements ProgressService {
    private GoalService goalService;

    public ProgressServiceImpl(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    public void add(Progress progress, Integer goalId) {
        Goal goal = goalService.find(goalId);

        add(progress, goal);
    }

    @Override
    public void add(Progress progress, Goal goal) {
        Set<Progress> progressSet = goal.getProgress();
        if (progressSet == null) {
            progressSet = new HashSet<>();
            goal.setProgress(progressSet);
        }
        progress.setId(null);
        progress.setDate(new Date());
        progressSet.add(progress);

        goalService.update(goal);
    }
}
