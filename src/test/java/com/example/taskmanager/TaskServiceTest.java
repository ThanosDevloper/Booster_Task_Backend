package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        assertNotNull(tasks);
        assertEquals(4, tasks.size());
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setName("New Unit Test Task");
        task.setDescription("Unit test description");

        Task created = taskService.createTask(task);
        assertNotNull(created.getId());
        assertEquals("New Unit Test Task", created.getName());
        assertEquals(TaskStatus.PENDING, created.getStatus());
        assertNotNull(created.getCreatedAt());

        assertEquals(5, taskService.getAllTasks().size());
    }

    @Test
    void testUpdateTaskStatus() {
        Task task = new Task();
        task.setName("Task to Update");
        Task created = taskService.createTask(task);
        Long id = created.getId();

        Optional<Task> updated = taskService.updateTaskStatus(id, TaskStatus.COMPLETED);
        assertTrue(updated.isPresent());
        assertEquals(TaskStatus.COMPLETED, updated.get().getStatus());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setName("Task to Delete");
        Task created = taskService.createTask(task);
        Long id = created.getId();

        int initialSize = taskService.getAllTasks().size();
        boolean deleted = taskService.deleteTask(id);
        assertTrue(deleted);
        assertEquals(initialSize - 1, taskService.getAllTasks().size());

        boolean deletedAgain = taskService.deleteTask(999L);
        assertFalse(deletedAgain);
    }
}
