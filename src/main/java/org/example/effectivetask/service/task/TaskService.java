package org.example.effectivetask.service.task;

import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис для работы с задачами.
 */
public interface TaskService {

    /**
     * Получает список всех задач с учетом пагинации.
     *
     * @param pageable Параметры пагинации.
     * @return Страница с задачами.
     */
    Page<Task> getTasks(Pageable pageable);

    /**
     * Получает задачи, назначенные пользователю, с учетом пагинации.
     *
     * @param user     Пользователь.
     * @param pageable Параметры пагинации.
     * @return Страница с задачами, назначенными пользователю.
     */
    Page<Task> getUserTasks(User user, Pageable pageable);

    Page<Task> getAssignedTasks(User user, Pageable pageable);

    /**
     * Получает задачу по ID.
     *
     * @param id ID задачи.
     * @return Задача с указанным ID.
     */
    Task getTask(Long id);

    /**
     * Создает новую задачу.
     *
     * @param task Задача для создания.
     * @return Созданная задача.
     */
    Task createTask(Task task);

    /**
     * Удаляет задачу.
     *
     * @param task Задача для удаления.
     */
    void deleteTask(Task task);

    /**
     * Удаляет задачу по ID.
     *
     * @param id ID задачи для удаления.
     */
    void deleteTask(Long id);
}
