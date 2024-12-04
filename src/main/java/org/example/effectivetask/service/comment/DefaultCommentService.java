package org.example.effectivetask.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.entity.Comment;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.repository.CommentRepository;
import org.example.effectivetask.service.task.TaskService;
import org.example.effectivetask.util.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Page<Comment> getComments(Long taskId, Pageable pageable) {
        return commentRepository.findByTaskId(taskId, pageable);
    }

    @Override
    public Comment addComment(Task task, Comment comment) {
        comment.setTask(task);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
}
