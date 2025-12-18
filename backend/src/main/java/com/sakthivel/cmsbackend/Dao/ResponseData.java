package com.sakthivel.cmsbackend.Dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ResponseData<T> {

    private T data;
    private boolean success;
    private String message;
}