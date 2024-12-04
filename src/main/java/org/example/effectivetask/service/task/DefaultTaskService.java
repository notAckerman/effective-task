package org.example.effectivetask.service.task;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.repository.TaskRepository;
import org.example.effectivetask.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    private TaskService self;

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "tasks", key = "#user.name")
    public Page<Task> getUserTasks(User user, Pageable pageable) {
        return taskRepository.findByAuthor(user, pageable);
    }

    @Override
    @Cacheable(value = "tasks", key = "#user.name + '_assigned'")
    public Page<Task> getAssignedTasks(User user, Pageable pageable) {
        return taskRepository.findByAssignee(user, pageable);
    }

    @Override
    @Cacheable(value = "task", key = "#id")
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Override
    @CacheEvict(cacheNames = {"tasks", "task"}, allEntries = true)
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @CacheEvict(cacheNames = {"tasks", "task"}, key = "#id")
    public void deleteTask(Long id) {
        Task task = self.getTask(id);
        taskRepository.delete(task);
    }

    @Override
    @CacheEvict(cacheNames = {"tasks", "task"}, key = "#task.id")
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
