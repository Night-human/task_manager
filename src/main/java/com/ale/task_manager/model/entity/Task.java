/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.model.entity;

import java.time.LocalDateTime;

import com.ale.task_manager.model.dto.TaskRequest;
import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author night
 */
@Entity
@Getter
@Table(name="task")
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateFromTaskRequest(TaskRequest taskRequest) {
        this.title = taskRequest.getTitle();
        this.description = taskRequest.getDescription();
        this.status = taskRequest.getStatus();
        this.priority = taskRequest.getPriority();        
    }
}
