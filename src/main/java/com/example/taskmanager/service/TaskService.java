package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final List<Task> tasks = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TaskService() {
        createTask(new Task(null, "Configure Angular Project", "Set up standard Angular skeleton and configure routing", TaskStatus.COMPLETED, LocalDateTime.now().minusDays(2)));
        createTask(new Task(null, "Implement REST APIs", "Develop Spring Boot CRUD endpoints with validation and error handling", TaskStatus.COMPLETED, LocalDateTime.now().minusDays(1)));
        createTask(new Task(null, "Design Beautiful Frontend UI", "Build responsive design with modern dashboard statistics and validation", TaskStatus.PENDING, LocalDateTime.now()));
        createTask(new Task(null, "Configure PWA Support", "Enable service worker for offline viewing of tasks and cached assets", TaskStatus.PENDING, LocalDateTime.now()));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.PENDING);
        }
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }
        tasks.add(task);
        return task;
    }

    public Optional<Task> updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> existingTask = getTaskById(id);
        existingTask.ifPresent(task -> task.setStatus(status));
        return existingTask;
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }
}
