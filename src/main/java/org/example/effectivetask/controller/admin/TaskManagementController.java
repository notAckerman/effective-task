package org.example.effectivetask.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.task.TaskManagementRequest;
import org.example.effectivetask.model.dto.task.TaskManagementResponse;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.task.TaskManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task-management/tasks")
@RequiredArgsConstructor
public class TaskManagementController {

    private final TaskManagementService managementService;

    @GetMapping
    @Operation(
            summary = "Get tasks",
            description = "Retrieve a paginated list of tasks",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)))
            }
    )
    public SuccessResponse<Page<TaskManagementResponse>> getTasks(@PageableDefault(page = 0, size = 20, sort = "id,asc") Pageable pageable) {
        return new SuccessResponse<>("success", managementService.getTasks(pageable));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a task by ID",
            description = "Retrieve a task by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskManagementResponse> getTask(@PathVariable Long id) {
        return new SuccessResponse<>("success", managementService.getTask(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new task",
            description = "Create a new task with the provided details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid task data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskManagementResponse> createTask(@RequestBody @Valid TaskManagementRequest request, @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", managementService.createTask(request, user));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a task",
            description = "Update an existing task by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid task data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<TaskManagementResponse> updateTask(@PathVariable Long id, @RequestBody @Valid TaskManagementRequest request) {
        return new SuccessResponse<>("success", managementService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a task",
            description = "Delete a task by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted task"),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public void deleteTask(@PathVariable Long id) {
        managementService.deleteTask(id);
    }
}
