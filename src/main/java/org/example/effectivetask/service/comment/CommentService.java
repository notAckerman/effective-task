package org.example.effectivetask.service.comment;

import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.dto.comment.CommentResponse;
import org.example.effectivetask.model.entity.Comment;
import org.example.effectivetask.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис для работы с комментариями.
 */
public interface CommentService {

    /**
     * Получает список комментариев для задачи с заданным ID.
     *
     * @param taskId ID задачи.
     * @param pageable Параметры пагинации.
     * @return Страница комментариев для задачи.
     */
    Page<Comment> getComments(Long taskId, Pageable pageable);

    /**
     * Добавляет новый комментарий к задаче.
     *
     * @param task Задача, к которой добавляется комментарий.
     * @param comment Комментарий, который нужно добавить.
     * @return Добавленный комментарий.
     */
    Comment addComment(Task task, Comment comment);

    /**
     * Удаляет комментарий по заданному ID.
     *
     * @param id ID комментария, который нужно удалить.
     */
    void deleteComment(Long id);
}
