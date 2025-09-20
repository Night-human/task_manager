/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.model.dto;

import java.time.LocalDateTime;

import com.ale.task_manager.model.entity.Task;
import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;

import lombok.Getter;

/**
 *
 * @author night
 */
@Getter
public class TaskResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final TaskPriority priority;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
}
