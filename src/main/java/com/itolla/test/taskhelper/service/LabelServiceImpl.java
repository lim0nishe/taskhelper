package com.itolla.test.taskhelper.service;

import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("labelService")
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelRepository labelRepository;

    @Override
    public Label getLabelById(Long id) {
        return labelRepository.findOne(id);
    }

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label update(Label label) {
        return labelRepository.save(label);
    }
}