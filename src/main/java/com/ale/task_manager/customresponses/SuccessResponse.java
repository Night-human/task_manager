/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.customresponses;

/**
 *
 * @author Night
 */

public record SuccessResponse<T> (
      boolean success,
      String message,
      T content){}
