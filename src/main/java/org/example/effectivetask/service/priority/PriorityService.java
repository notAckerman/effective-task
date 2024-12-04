package org.example.effectivetask.service.priority;

import org.example.effectivetask.model.entity.Priority;
import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.enums.TaskPriority;
import org.example.effectivetask.model.enums.TaskStatus;

/**
 * Сервис для работы с приоритетами задач.
 */
public interface PriorityService {

    /**
     * Получает приоритет по заданному ID.
     *
     * @param id ID приоритета.
     * @return Приоритет с указанным ID.
     */
    Priority getPriority(Long id);
}
