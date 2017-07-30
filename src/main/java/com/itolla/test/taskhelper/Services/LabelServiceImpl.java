package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.Repositories.LabelRepository;
import com.itolla.test.taskhelper.models.Label;
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
}
