package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "point")
public class PointWaterPipelineController {

    private final PointWaterPipelineService pointWaterPipelineService;


            public ResponseEntity<String> save(@RequestBody String filePath) {
                try {
                    pointWaterPipelineService.save(filePath);
                    return new ResponseEntity<String>(HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }


            @GetMapping(path = "/{id}")
            public ResponseEntity<PointWaterPipeline> findById(@PathVariable Integer id) {
                try {
                    return new ResponseEntity<>(pointWaterPipelineService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping
    public ResponseEntity<List> findAll() {
        return new ResponseEntity<>(pointWaterPipelineService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            pointWaterPipelineService.delete(pointWaterPipelineService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
