package com.itolla.test.taskhelper.service;

import com.itolla.test.taskhelper.model.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(Long id);
    List<Label> getAllLabels();
    Label save(Label label);
    Label update(Label label);
}
