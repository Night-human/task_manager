/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.customresponses;

import lombok.Getter;

/**
 *
 * @author Night
 */
@Getter
public class SuccessResponse<T> {
    private final boolean success;
    private final String message;
    private final T content;

    public SuccessResponse(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }
}
