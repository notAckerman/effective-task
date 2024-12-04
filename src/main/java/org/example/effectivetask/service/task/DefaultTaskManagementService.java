package org.example.effectivetask.service.task;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.task.TaskManagementRequest;
import org.example.effectivetask.model.dto.task.TaskManagementResponse;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.priority.PriorityService;
import org.example.effectivetask.service.status.StatusService;
import org.example.effectivetask.service.user.UserService;
import org.example.effectivetask.util.CustomModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.effectivetask.util.CustomModelMapper.*;

@Service
@RequiredArgsConstructor
public class DefaultTaskManagementService implements TaskManagementService {

    private final TaskService taskService;
    private final UserService userService;
    private final StatusService statusService;
    private final PriorityService priorityService;

    @Override
    public Page<TaskManagementResponse> getTasks(Pageable pageable) {
        return taskService.getTasks(pageable).map((entity) -> toDto(entity, TaskManagementResponse.class));
    }

    @Override
    public TaskManagementResponse getTask(Long id) {
        return toDto(taskService.getTask(id), TaskManagementResponse.class);
    }

    @Override
    public TaskManagementResponse createTask(TaskManagementRequest request, User user) {
        Task task = extractTask(request);
        task.setAuthor(user);
        return toDto(taskService.createTask(task), TaskManagementResponse.class);
    }

    @Override
    public TaskManagementResponse updateTask(Long id, TaskManagementRequest request) {
        Task task = taskService.getTask(id);
        Task extractedTask = extractTask(request);

        task.setPriority(extractedTask.getPriority());
        task.setDescription(extractedTask.getDescription());
        task.setStatus(extractedTask.getStatus());
        task.setAssignee(extractedTask.getAssignee());

        return toDto(taskService.createTask(task), TaskManagementResponse.class);
    }

    @Override
    public void deleteTask(Long id) {
        taskService.deleteTask(id);
    }

    private Task extractTask(TaskManagementRequest request) {
        return Task.builder()
                .description(request.getDescription())
                .status(statusService.getStatus(request.getStatus()))
                .priority(priorityService.getPriority(request.getPriority()))
                .assignee(userService.getUser(request.getAssignee()))
                .build();
    }
}
