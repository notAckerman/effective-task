package org.example.effectivetask.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.status.UpdateStatusRequest;
import org.example.effectivetask.model.dto.task.TaskRequest;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.task.AssigneeTaskService;
import org.example.effectivetask.service.task.UserTaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final UserTaskService taskService;
    private final AssigneeTaskService assigneeTaskService;

    @GetMapping
    @Operation(
            summary = "Get all tasks",
            description = "Retrieve a list of all tasks with pagination and sorting",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)))
            }
    )
    public SuccessResponse<Page<TaskResponse>> getTasks(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return new SuccessResponse<>("success", taskService.getTasks(pageable));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a task by ID",
            description = "Retrieve details of a specific task by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskResponse> getTask(@PathVariable Long id) {
        return new SuccessResponse<>("success", taskService.getTask(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new task",
            description = "Create a new task with the provided details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskResponse> postTask(@RequestBody @Valid TaskRequest request, @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", taskService.createTask(request, user));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a task",
            description = "Delete a task by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public void deleteTask(@PathVariable Long id, @AuthenticationPrincipal User user) {
        taskService.deleteTask(id, user);
    }

    @PostMapping("/{id}/assign")
    @Operation(
            summary = "Assign a task to a user",
            description = "Assign a task to the currently authenticated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully assigned task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskResponse> assignTask(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", assigneeTaskService.assigneeTask(id, user));
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Update task status",
            description = "Update the status of a specific task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated task status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid status provided", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskResponse> updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateStatusRequest request, @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", assigneeTaskService.updateStatus(id, request.getStatus(), user));
    }
}
