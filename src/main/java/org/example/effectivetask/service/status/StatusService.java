package org.example.effectivetask.service.status;

import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.enums.TaskStatus;

/**
 * Сервис для работы со статусами.
 */
public interface StatusService {

    /**
     * Получает статус по переданному значению типа TaskStatus.
     *
     * @param status Статус задачи.
     * @return Статус задачи.
     */
    Status getStatus(TaskStatus status);

    /**
     * Получает статус по заданному ID.
     *
     * @param id ID статуса.
     * @return Статус с указанным ID.
     */
    Status getStatus(Long id);
}
