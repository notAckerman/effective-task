package org.example.effectivetask.service.comment;

import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.dto.comment.CommentResponse;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис для работы с комментариями пользователей.
 */
public interface UserCommentService {

    /**
     * Получает список комментариев для задачи с заданным ID.
     *
     * @param taskId ID задачи.
     * @param pageable Параметры пагинации.
     * @return Страница комментариев для задачи.
     */
    Page<CommentResponse> getComments(Long taskId, Pageable pageable);

    /**
     * Добавляет новый комментарий к задаче.
     *
     * @param taskId ID задачи.
     * @param request Запрос с текстом комментария.
     * @param user Пользователь, добавляющий комментарий.
     * @return Добавленный комментарий.
     */
    CommentResponse addComment(Long taskId, CommentRequest request, User user);
}
