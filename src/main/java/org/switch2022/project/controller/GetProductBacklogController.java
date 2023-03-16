package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.dto.assembler.UserStoryDtoListAssembler;
import org.switch2022.project.model.ProductBacklog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class GetProductBacklogController acts as an intermediary between the
 * user interface (UI) and the business logic underlying the "US018 - As PO/SM/Team Member, I
 * want to consult the product backlog, i.e. to get the list of user stories sorted by priority."
 */

public class GetProductBacklogController {
    private final Company company;

    /**
     * Constructor
     */

    public GetProductBacklogController(Company company) {
        this.company = company;
    }

    /**
     * This method should return a list of User Stories Dto extracted from a given Product
     * Backlog.
     *
     * @param projectDto to extract its projectCode.
     * @return a list of User Stories Dto.
     */

    public List<UserStoryDto> getProductBacklog(ProjectDto projectDto) {
        List<UserStoryDto> userStoryDtoList = new ArrayList<>();

        String projectCode = projectDto.getProjectCode();

        Optional<ProductBacklog> productBacklogOptional =
                company.getProductBacklog(projectCode);

        if (productBacklogOptional.isPresent()) {
            ProductBacklog productBacklog = productBacklogOptional.get();
            List<UserStoryDto> userStoriesDto =
                    UserStoryDtoListAssembler.backlogToDto(productBacklog);
            userStoryDtoList.addAll(userStoriesDto);
        }
        return userStoryDtoList;
    }
}
