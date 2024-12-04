package org.example.effectivetask.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.service.task.AssigneeTaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assignees")
@RequiredArgsConstructor
public class AssigneeController {

    private final AssigneeTaskService taskService;

    @GetMapping("/{username}/tasks")
    @Operation(
            summary = "Get tasks assigned to a user",
            description = "Fetches a list of tasks that are assigned to the specified user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<Page<TaskResponse>> getAssignees(Pageable pageable, @PathVariable String username) {
        return new SuccessResponse<>("success", taskService.getAssigneeTasks(username, pageable));
    }
}
