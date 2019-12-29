package by.dima.goal.controller;

import by.dima.goal.model.Progress;
import by.dima.goal.service.ProgressService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgressController {
    private ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/goal/{goalId}/progress")
    public void add(@RequestBody Progress progress, @PathVariable Integer goalId) {
        progressService.add(progress, goalId);
    }
}
