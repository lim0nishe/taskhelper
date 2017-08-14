package com.itolla.test.taskhelper.controller;

import com.itolla.test.taskhelper.dto.LabelDto;
import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.repository.ProjectRepository;
import com.itolla.test.taskhelper.util.JsonResponse;
import com.itolla.test.taskhelper.util.LabelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<LabelDto> getLabels(){
        List<Label> labels = labelRepository.findAll();
        return labels.stream().map(label -> modelMapper.map(label, LabelDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.GET)
    public LabelDto getLabel(@PathVariable("labelId") Long labelId, @PathVariable("projectId") Long projectId){
        return modelMapper.map(labelRepository.findOne(labelId), LabelDto.class);
    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.PUT)
    public LabelDto updateLabel(@PathVariable("labelId") Long labelId, @PathVariable("projectId") Long projectId,
                                      @RequestBody LabelDto labelDto){
        Label label = labelRepository.findOne(labelId);
        if (label == null) throw new LabelNotFoundException();

        Label updatedLabel = modelMapper.map(labelDto, Label.class);
        updatedLabel.setId(labelId);
        updatedLabel.setProject(projectRepository.findOne(projectId));

        labelRepository.save(updatedLabel);
        return modelMapper.map(updatedLabel, LabelDto.class);

    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.DELETE)
    public JsonResponse deleteLabel(@PathVariable("projectId") Long projectId, @PathVariable("labelId") Long labelId){
        Label label = labelRepository.findOne(labelId);
        if (label == null) throw new LabelNotFoundException();

        labelRepository.delete(label);
        return new JsonResponse("200", "Label successfully deleted");
    }
}
