package org.example.effectivetask.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.comment.CommentManagementResponse;
import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.entity.Comment;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.task.TaskService;
import org.example.effectivetask.util.CustomModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCommentManagementService implements CommentManagementService {

    private final CommentService commentService;
    private final TaskService taskService;

    @Override
    public Page<CommentManagementResponse> getComments(Long id, Pageable pageable) {
        return commentService.getComments(id, pageable).map((entity) -> CustomModelMapper.toDto(entity, CommentManagementResponse.class));
    }

    @Override
    public CommentManagementResponse postComment(Long id, CommentRequest request, User user) {
        Task task = taskService.getTask(id);
        Comment comment = Comment.builder()
                .author(user)
                .text(request.getText())
                .build();
        return CustomModelMapper.toDto(commentService.addComment(task, comment), CommentManagementResponse.class);
    }

    @Override
    public void deleteComment(Long id) {
        commentService.deleteComment(id);
    }
}
