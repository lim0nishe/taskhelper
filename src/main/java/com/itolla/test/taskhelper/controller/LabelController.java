package com.itolla.test.taskhelper.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.util.LabelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Label> getLabels(){
        return labelRepository.findAll();
    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.GET)
    public Label getLabel(@PathVariable("labelId") Long labelId, @PathVariable("projectId") Long projectId){
        return labelRepository.findOne(labelId);
    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateLabel(@PathVariable("labelId") Long labelId, @PathVariable("projectId") Long projectId,
                                      @RequestBody String jsonString){
        Label label = labelRepository.findOne(labelId);
        if (label == null) throw new LabelNotFoundException();
        try {
            // deserialize json into map
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

            if (jsonMap.containsKey("title"))
                label.setTitle(jsonMap.get("title").toString());

            List<Long> issueIdList = (List<Long>)jsonMap.get("issues");

            // avoid NullPointer
            if (issueIdList != null){
                label.removeAllIssues();
                for (Long issueId : issueIdList){
                    label.addIssue(issueRepository.findOne(issueId));
                }
            }

            labelRepository.save(label);
            return ResponseEntity.ok("Label successfully updated");
        }
        catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Something wrong with json map", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{projectId}/{labelId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLabel(@PathVariable("projectId") Long projectId, @PathVariable("labelId") Long labelId){
        Label label = labelRepository.findOne(labelId);
        if (label == null) throw new LabelNotFoundException();

        label.setProject(null);
        label.removeAllIssues();
        labelRepository.delete(label);
        return ResponseEntity.ok("Label successfully deleted");
    }
}
