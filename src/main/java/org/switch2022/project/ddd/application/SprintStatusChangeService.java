package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;

/**
 * Class ChangeSprintStatusService allows to change the status of a sprint.
 */
@Service
public class SprintStatusChangeService {
    @Qualifier("sprint_jpa")
    @Autowired
    private ISprintRepository sprintRepository;

    @Qualifier("us_jpa")
    @Autowired
    private IUsRepository userStoryRepository;
}
