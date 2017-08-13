package com.itolla.test.taskhelper.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason= "No such Project")
public class ProjectNotFoundException extends RuntimeException {
}
