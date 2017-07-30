package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.models.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(Long id);
    List<Label> getAllLabels();
}
