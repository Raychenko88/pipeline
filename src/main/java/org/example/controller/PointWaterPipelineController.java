package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Profile("jsp")
@RequiredArgsConstructor
@RequestMapping(value = "point")
public class PointWaterPipelineController {

    private final PointWaterPipelineService pointWaterPipelineService;

    @PutMapping
    public String save(@RequestBody String filePath) {
        pointWaterPipelineService.save(filePath);
        return null;
    }


//    @GetMapping(path = "/{id}")
//    public ResponseEntity<PointWaterPipeline> findById(@PathVariable Integer id) {
//        try {
//            return new ResponseEntity<>(pointWaterPipelineService.findById(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List> findAll() {
//        return new ResponseEntity<>(pointWaterPipelineService.findAll(), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        try {
//            pointWaterPipelineService.delete(pointWaterPipelineService.findById(id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
