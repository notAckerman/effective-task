package org.example.effectivetask.service.task;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.task.TaskRequest;
import org.example.effectivetask.model.dto.task.TaskResponse;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.model.enums.TaskStatus;
import org.example.effectivetask.repository.TaskRepository;
import org.example.effectivetask.service.priority.PriorityService;
import org.example.effectivetask.service.status.StatusService;
import org.example.effectivetask.service.user.UserService;
import org.example.effectivetask.util.exception.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.example.effectivetask.util.CustomModelMapper.*;

@Service
@RequiredArgsConstructor
public class DefaultUserTaskService implements UserTaskService {

    private final TaskService taskService;
    private final StatusService statusService;
    private final PriorityService priorityService;
    private final UserService userService;

    @Override
    public Page<TaskResponse> getTasks(Pageable pageable) {
        return taskService.getTasks(pageable).map((task) -> toDto(task, TaskResponse.class));
    }

    @Override
    public Page<TaskResponse> getUserTasks(String username, Pageable pageable) {
        User user = userService.getUser(username);
        return taskService.getUserTasks(user, pageable).map((task) -> toDto(task, TaskResponse.class));
    }

    @Override
    public TaskResponse getTask(Long id) {
        return toDto(taskService.getTask(id), TaskResponse.class);
    }

    @Override
    public TaskResponse createTask(TaskRequest request, User user) {
        return toDto(taskService.createTask(getDefaultTask(request, user)), TaskResponse.class);
    }

    @Override
    public void deleteTask(Long id, User user) {
        Task task = taskService.getTask(id);
        if (!Objects.equals(task.getAuthor(), user)) {
            throw new AccessDeniedException("You do not have permission to delete this task.");
        }
        taskService.deleteTask(task);
    }

    private Task getDefaultTask(TaskRequest request, User user) {
        return Task.builder()
                .description(request.getDescription())
                .author(user)
                .priority(priorityService.getPriority(request.getPriority()))
                .status(statusService.getStatus(TaskStatus.PENDING))
                .build();
    }

}
