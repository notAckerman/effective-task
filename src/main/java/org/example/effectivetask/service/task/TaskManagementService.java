package org.example.effectivetask.service.task;

import org.example.effectivetask.model.dto.task.TaskManagementRequest;
import org.example.effectivetask.model.dto.task.TaskManagementResponse;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Сервис для управления задачами.
 */
public interface TaskManagementService {

    /**
     * Получает список задач с учетом пагинации.
     *
     * @param pageable Параметры пагинации.
     * @return Страница с задачами.
     */
    Page<TaskManagementResponse> getTasks(Pageable pageable);

    /**
     * Получает задачу по ID.
     *
     * @param id ID задачи.
     * @return Ответ с данными задачи.
     */
    TaskManagementResponse getTask(Long id);

    /**
     * Создает новую задачу.
     *
     * @param request Данные задачи для создания.
     * @param user Пользователь, создающий задачу.
     * @return Ответ с данными созданной задачи.
     */
    TaskManagementResponse createTask(TaskManagementRequest request, User user);

    /**
     * Обновляет задачу по ID.
     *
     * @param id ID задачи для обновления.
     * @param request Обновленные данные задачи.
     * @return Ответ с обновленными данными задачи.
     */
    TaskManagementResponse updateTask(Long id, TaskManagementRequest request);

    /**
     * Удаляет задачу по ID.
     *
     * @param id ID задачи для удаления.
     */
    void deleteTask(Long id);
}

