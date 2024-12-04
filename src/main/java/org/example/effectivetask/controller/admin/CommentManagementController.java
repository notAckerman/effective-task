package org.example.effectivetask.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.comment.CommentManagementResponse;
import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.dto.task.TaskManagementResponse;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.comment.CommentManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment-management/")
@RequiredArgsConstructor
public class CommentManagementController {

    private final CommentManagementService managementService;

    @GetMapping("/tasks/{id}/comments")
    @Operation(
            summary = "Get comments for a task",
            description = "Retrieve a paginated list of comments for a specific task by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved comments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<Page<CommentManagementResponse>> getComments(@PathVariable("id") Long id, @PageableDefault(page = 0, size = 10, sort = "id,asc") Pageable pageable) {
        return new SuccessResponse<>("success", managementService.getComments(id, pageable));
    }

    @PostMapping("/tasks/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Post a comment for a task",
            description = "Create a new comment for a specific task",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created comment", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<CommentManagementResponse> postComment(@PathVariable("id") Long id, @RequestBody @Valid CommentRequest request, @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", managementService.postComment(id, request, user));
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a comment",
            description = "Delete a specific comment by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted comment", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public void deleteComment(@PathVariable("id") Long id) {
        managementService.deleteComment(id);
    }
}
