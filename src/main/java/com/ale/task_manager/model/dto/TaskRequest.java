/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.model.dto;

import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author night
 */

@RequiredArgsConstructor
@Getter
public class TaskRequest {
    
    @NotBlank(message="La tarea debe tener un titulo")
    private final String title;
    private final String description;
    @NotNull(message="La tarea debe tener un estado")
    private final TaskStatus status;
    @NotNull(message="La tarea debe tener una prioridad")
    private final TaskPriority priority;

}
