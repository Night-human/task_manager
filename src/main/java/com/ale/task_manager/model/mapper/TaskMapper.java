/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.ale.task_manager.model.mapper;

import com.ale.task_manager.model.dto.TaskResponse;
import com.ale.task_manager.model.entity.Task;

import lombok.NoArgsConstructor;

/**
 *
 * @author night
 */
@NoArgsConstructor
public class TaskMapper {
    
    public static TaskResponse mapTaskToTaskResponse(Task task) {
        return new TaskResponse(task);
    }
}
