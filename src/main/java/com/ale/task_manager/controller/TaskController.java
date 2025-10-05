/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ale.task_manager.customresponses.SuccessResponse;
import com.ale.task_manager.model.dto.TaskRequest;
import com.ale.task_manager.model.dto.TaskResponse;
import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;
import com.ale.task_manager.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




/**
 *
 * @author night
 */
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<SuccessResponse<Page<TaskResponse>>> getAllTasks(
        @RequestParam(required=false) TaskStatus status, 
        @RequestParam(required=false) TaskPriority priority,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size) {
        return ResponseEntity.ok(new SuccessResponse<>(true, "", taskService.getAllTasks(status, priority, page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<TaskResponse>> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(new SuccessResponse<>(true, "", taskService.getTaskById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<TaskResponse>> updateTask(@Valid @PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(new SuccessResponse<>(true, "Tarea actualizada correctamente", taskService.updateTask(id, taskRequest)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<TaskResponse>> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(new SuccessResponse<>(true, taskService.deleteTask(id), null));
    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<TaskResponse>> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(new SuccessResponse<>(true, "Tarea creada correctamente",taskService.createTask(taskRequest)));
    }
    
}
