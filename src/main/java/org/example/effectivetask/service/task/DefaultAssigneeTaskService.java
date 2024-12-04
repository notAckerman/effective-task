package org.example.effectivetask.service.task;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.service.status.StatusService;
import org.example.effectivetask.service.user.UserService;
import org.example.effectivetask.util.exception.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.example.effectivetask.util.CustomModelMapper.toDto;

@Service
@RequiredArgsConstructor
public class DefaultAssigneeTaskService implements AssigneeTaskService {

    private final TaskService taskService;
    private final UserService userService;
    private final StatusService statusService;

    @Override
    public Page<TaskResponse> getAssigneeTasks(String username, Pageable pageable) {
        User user = userService.getUser(username);
        return taskService.getAssignedTasks(user, pageable).map(task -> toDto(task, TaskResponse.class));
    }

    @Override
    public TaskResponse assigneeTask(Long id, User user) {
        Task task = taskService.getTask(id);
        if (Objects.nonNull(task.getAssignee()) && !Objects.equals(task.getAssignee(), user)) {
            throw new AccessDeniedException("You do not have permission to assign task");
        }
        task.setAssignee(user);
        return toDto(taskService.createTask(task), TaskResponse.class);
    }

    @Override
    public TaskResponse updateStatus(Long id, Long status, User user) {
        Task task = taskService.getTask(id);
        if (!Objects.equals(task.getAssignee(), user)) {
            throw new AccessDeniedException("You do not have permission to update this task");
        }
        task.setStatus(statusService.getStatus(status));
        return toDto(taskService.createTask(task), TaskResponse.class);
    }
}
