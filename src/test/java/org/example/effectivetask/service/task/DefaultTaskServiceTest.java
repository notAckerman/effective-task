package org.example.effectivetask.service.task;

import org.example.effectivetask.model.entity.Task;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.repository.TaskRepository;
import org.example.effectivetask.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DefaultTaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private DefaultTaskService taskService;

    private Task task;
    private User user;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");

        user = new User();
        user.setName("Test User");

        pageable = mock(Pageable.class);
    }

    @Test
    void getTasks() {
        Page<Task> tasksPage = new PageImpl<>(List.of(task));
        when(taskRepository.findAll(pageable)).thenReturn(tasksPage);

        Page<Task> result = taskService.getTasks(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Test Task", result.getContent().get(0).getDescription());
    }

    @Test
    void getUserTasks() {
        Page<Task> tasksPage = new PageImpl<>(List.of(task));
        when(taskRepository.findByAuthor(user, pageable)).thenReturn(tasksPage);

        Page<Task> result = taskService.getUserTasks(user, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Test Task", result.getContent().get(0).getDescription());
    }

    @Test
    void getAssignedTasks() {
        Page<Task> tasksPage = new PageImpl<>(List.of(task));
        when(taskRepository.findByAssignee(user, pageable)).thenReturn(tasksPage);

        Page<Task> result = taskService.getAssignedTasks(user, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Test Task", result.getContent().get(0).getDescription());
    }

    @Test
    void getTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTask(1L);

        assertNotNull(result);
        assertEquals("Test Task", result.getDescription());
    }

    @Test
    void getTaskThrowsNotFoundException() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.getTask(1L));
    }

    @Test
    void createTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals("Test Task", result.getDescription());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void deleteTaskByObject() {
        taskService.deleteTask(task);

        verify(taskRepository, times(1)).delete(task);
    }
}
