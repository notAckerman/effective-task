package org.example.effectivetask.service.task;

import org.example.effectivetask.model.dto.task.TaskRequest;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис для работы с назначением задач.
 */
public interface AssigneeTaskService {

    /**
     * Получает список задач, назначенных пользователю.
     *
     * @param username Имя пользователя, для которого нужно получить задачи.
     * @param pageable Параметры пагинации.
     * @return Страница с задачами, назначенными пользователю.
     */
    Page<TaskResponse> getAssigneeTasks(String username, Pageable pageable);

    /**
     * Назначает задачу конкретному пользователю.
     *
     * @param id ID задачи.
     * @param user Пользователь, которому назначается задача.
     * @return Ответ с данными назначенной задачи.
     */
    TaskResponse assigneeTask(Long id, User user);

    /**
     * Обновляет статус задачи для конкретного пользователя.
     *
     * @param id ID задачи.
     * @param status Новый статус задачи.
     * @param user Пользователь, который обновляет статус задачи.
     * @return Ответ с обновленными данными задачи.
     */
    TaskResponse updateStatus(Long id, Long status, User user);
}
