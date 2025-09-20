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

import com.ale.task_manager.model.dto.TaskRequest;
import com.ale.task_manager.model.dto.TaskResponse;
import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;
import com.ale.task_manager.service.TaskService;

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
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
        @RequestParam(required=false) TaskStatus status, 
        @RequestParam(required=false) TaskPriority priority,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size) {
        return ResponseEntity.ok(taskService.getAllTasks(status, priority, page, size));
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }
    
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }
    
}
