package com.example.app.service;


import com.example.app.entity.Employee;
import com.example.app.entity.Task;
import com.example.app.entity.TaskStatus;

public interface NotificationService {
    void notifyTaskStatusChanged(Task task, TaskStatus oldStatus, TaskStatus newStatus);
    void notifyTaskAssigned(Task task, Employee previousEmployee, Employee newEmployee);
    void notifyTaskUpdate(int taskId);
}
