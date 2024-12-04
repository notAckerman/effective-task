package org.example.effectivetask.service.task;

import org.example.effectivetask.model.dto.task.TaskRequest;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис для работы с задачами пользователя.
 */
public interface UserTaskService {

    /**
     * Получает список всех задач с учетом пагинации.
     *
     * @param pageable Параметры пагинации.
     * @return Страница с задачами.
     */
    Page<TaskResponse> getTasks(Pageable pageable);

    /**
     * Получает задачи пользователя с заданным именем пользователя с учетом пагинации.
     *
     * @param username Имя пользователя.
     * @param pageable Параметры пагинации.
     * @return Страница с задачами пользователя.
     */
    Page<TaskResponse> getUserTasks(String username, Pageable pageable);

    /**
     * Получает задачу по ID.
     *
     * @param id ID задачи.
     * @return Ответ с данными задачи.
     */
    TaskResponse getTask(Long id);

    /**
     * Создает новую задачу для пользователя.
     *
     * @param request Данные задачи для создания.
     * @param user Пользователь, который создает задачу.
     * @return Ответ с данными созданной задачи.
     */
    TaskResponse createTask(TaskRequest request, User user);

    /**
     * Удаляет задачу пользователя по ID.
     *
     * @param id ID задачи для удаления.
     * @param user Пользователь, который удаляет задачу.
     */
    void deleteTask(Long id, User user);
}
