package org.example.effectivetask.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.SuccessResponse;
import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.dto.comment.CommentResponse;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.comment.UserCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final UserCommentService commentService;

    @GetMapping
    @Operation(
            summary = "Get comments for a task",
            description = "Retrieve all comments for a given task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved comments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)))
            }
    )
    public SuccessResponse<Page<CommentResponse>> getComments(
            @PathVariable("taskId") Long id,
            @PageableDefault(sort = "id,asc")
            Pageable pageable) {
        Page<CommentResponse> comments = commentService.getComments(id, pageable);
        return new SuccessResponse<>("success", comments);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Post a comment to a task",
            description = "Add a new comment to the task",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created comment", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
            }
    )
    public SuccessResponse<CommentResponse> postComment(
            @PathVariable("taskId") Long taskId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal User user) {
        return new SuccessResponse<>("success", commentService.addComment(taskId, request, user));
    }
}
