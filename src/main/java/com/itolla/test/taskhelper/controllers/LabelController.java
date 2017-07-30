package com.itolla.test.taskhelper.controllers;

import com.itolla.test.taskhelper.Services.LabelService;
import com.itolla.test.taskhelper.models.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Label> getLabels(){
        return labelService.getAllLabels();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Label getLabel(@PathVariable("id") Long id){
        return labelService.getLabelById(id);
    }
}
