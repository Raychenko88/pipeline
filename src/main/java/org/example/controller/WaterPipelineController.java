package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.model.WaterPipeline;
import org.example.sevice.WaterPipelineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "Water-pipeline")
public class WaterPipelineController {

    private final WaterPipelineService waterPipelineService;

    public ResponseEntity<String> save(@RequestBody String filePath) {
        try {
            waterPipelineService.save(filePath);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<WaterPipeline> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(waterPipelineService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping
    public ResponseEntity<List> findAll() {
        return new ResponseEntity<>(waterPipelineService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            waterPipelineService.delete(waterPipelineService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
