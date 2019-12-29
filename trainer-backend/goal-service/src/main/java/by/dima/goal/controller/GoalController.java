package by.dima.goal.controller;

import by.dima.common.utils.UserInfoExtractor;
import by.dima.goal.model.Goal;
import by.dima.goal.service.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/goal")
public class GoalController {
    private GoalService goalService;
    private UserInfoExtractor infoExtractor;

    public GoalController(GoalService goalService, UserInfoExtractor infoExtractor) {
        this.goalService = goalService;
        this.infoExtractor = infoExtractor;
    }

    @GetMapping
    public Collection<Goal> getAll(Authentication authentication) {
        return goalService.getAll(infoExtractor.getUserId(authentication));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Goal createCustom(@RequestBody Goal goal, Authentication authentication) {
        return goalService.createCustom(goal, infoExtractor.getUserId(authentication));
    }

    @DeleteMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer goalId, Authentication authentication) {
        Goal goal = goalService.find(goalId);
        if (goal != null && infoExtractor.getUserId(authentication).equals(goal.getUserId())) {
            goalService.delete(goal);
        }
    }
}
