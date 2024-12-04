package org.example.effectivetask.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.dto.comment.CommentResponse;
import org.example.effectivetask.model.entity.Comment;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.task.TaskService;
import org.example.effectivetask.util.CustomModelMapper;
import org.example.effectivetask.util.exception.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.example.effectivetask.util.CustomModelMapper.*;

@Service
@RequiredArgsConstructor
public class DefaultUserCommentService implements UserCommentService {

    private final CommentService commentService;
    private final TaskService taskService;

    @Override
    public Page<CommentResponse> getComments(Long taskId, Pageable pageable) {
        return commentService.getComments(taskId, pageable).map((entity) -> toDto(entity, CommentResponse.class));
    }

    @Override
    public CommentResponse addComment(Long taskId, CommentRequest request, User user) {
        Task task = taskService.getTask(taskId);
        if (!Objects.equals(task.getAssignee(), user)) {
            throw new AccessDeniedException("You do not have permission to comment on this task.");
        }
        Comment comment = Comment.builder()
                .author(user)
                .text(request.getText())
                .build();
        return toDto(commentService.addComment(task, comment), CommentResponse.class);
    }


}
