package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.sevice.WaterPipelineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "water-pipeline")
public class WaterPipelineController {

    private final WaterPipelineService waterPipelineService;

    @PostMapping
    public String save(@RequestParam String filePath) {
        waterPipelineService.save(filePath);
        return "point";
    }


//    @GetMapping(path = "/{id}")
//    public ResponseEntity<WaterPipeline> findById(@PathVariable Integer id) {
//        try {
//            return new ResponseEntity<>(waterPipelineService.findById(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List> findAll() {
//        return new ResponseEntity<>(waterPipelineService.findAll(), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity delete(@PathVariable Integer id) {
//        try {
//            waterPipelineService.delete(waterPipelineService.findById(id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
