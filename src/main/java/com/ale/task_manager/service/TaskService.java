/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ale.task_manager.exceptionhandler.custom_exceptions.CustomExceptionsMessages;
import com.ale.task_manager.exceptionhandler.custom_exceptions.TaskNotFoundException;
import com.ale.task_manager.model.dto.TaskRequest;
import com.ale.task_manager.model.dto.TaskResponse;
import com.ale.task_manager.model.entity.Task;
import com.ale.task_manager.model.enum_properties.TaskPriority;
import com.ale.task_manager.model.enum_properties.TaskStatus;
import com.ale.task_manager.model.mapper.TaskMapper;
import com.ale.task_manager.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author night
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Page<TaskResponse> getAllTasks(
            TaskStatus taskStatus,
            TaskPriority taskPriority,
            int pageNumber,
            int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").ascending());
        Page<Task> taskList;
        List<TaskResponse> taskResponseList;

        if (taskStatus != null && taskPriority != null) {
            taskList = taskRepository.findByStatusAndPriority(taskStatus, taskPriority, pageable);
        } else if (taskStatus != null) {
            taskList = taskRepository.findByStatus(taskStatus, pageable);
        } else if (taskPriority != null) {
            taskList = taskRepository.findByPriority(taskPriority, pageable);
        } else {
            taskList = taskRepository.findAll(pageable);
        }

        taskResponseList = taskList.getContent().stream().map(TaskMapper::mapTaskToTaskResponse).toList();

        return new PageImpl<>(taskResponseList, taskList.getPageable(), taskList.getTotalElements());
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(String.format(CustomExceptionsMessages.taskNotFoundException, id)));
        existingTask.updateFromTaskRequest(taskRequest);

        return TaskMapper.mapTaskToTaskResponse(taskRepository.save(existingTask));
    }

    public String deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);

            return String.format("Elemento con id %d fue eliminado", id);
        } else {
            throw new TaskNotFoundException(String.format(CustomExceptionsMessages.taskNotFoundException, id));
        }

    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(String.format(CustomExceptionsMessages.taskNotFoundException, id)));

        return TaskMapper.mapTaskToTaskResponse(task);
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.updateFromTaskRequest(taskRequest);

        return TaskMapper.mapTaskToTaskResponse(taskRepository.save(task));
    }

}
