package org.example.effectivetask.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.service.task.UserTaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserTaskService taskService;

    @GetMapping("/{username}/tasks")
    @Operation(
            summary = "Get tasks assigned to a user",
            description = "Retrieve all tasks assigned to a specific user by their username",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<Page<TaskResponse>> getTasks(@PageableDefault(page = 0, size = 10, sort = "id,asc") Pageable pageable, @PathVariable String username) {
        return new SuccessResponse<>("success", taskService.getUserTasks(username, pageable));
    }
}
