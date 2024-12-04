package org.example.effectivetask.service.comment;

import org.example.effectivetask.model.dto.comment.CommentManagementResponse;
import org.example.effectivetask.model.dto.comment.CommentRequest;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис для управления комментариями в контексте администрирования.
 */
public interface CommentManagementService {

    /**
     * Получает список комментариев для задачи с заданным ID.
     *
     * @param id ID задачи.
     * @param pageable Параметры пагинации.
     * @return Страница комментариев для задачи.
     */
    Page<CommentManagementResponse> getComments(Long id, Pageable pageable);

    /**
     * Добавляет новый комментарий для задачи.
     *
     * @param id ID задачи.
     * @param request Запрос с текстом комментария.
     * @param user Пользователь, который добавляет комментарий.
     * @return Добавленный комментарий.
     */
    CommentManagementResponse postComment(Long id, CommentRequest request, User user);

    /**
     * Удаляет комментарий по заданному ID.
     *
     * @param id ID комментария, который нужно удалить.
     */
    void deleteComment(Long id);
}
