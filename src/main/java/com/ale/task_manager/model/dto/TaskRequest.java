/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.model.dto;

import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author night
 */

@RequiredArgsConstructor
@Getter
public class TaskRequest {
    
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final TaskPriority priority;

}
