package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.example.sevice.impl.WriteAndParsingCsv;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "point")
public class PointWaterPipelineController {

    private final PointWaterPipelineService pointWaterPipelineService;
    private final WriteAndParsingCsv writeAndParsingCsv;

    @PostMapping
    public String save(@RequestParam String filePath) {
        pointWaterPipelineService.save(filePath);
        return "result";
    }

//    @GetMapping
//    public String getResult(Model model) {
//        try {
//            writeAndParsingCsv.writeToCsv();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<String> points = new ArrayList<>();
//        for (PointWaterPipeline p : writeAndParsingCsv.findResult()){
//            points.add(p.toString());
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : points){
//            stringBuilder.append(s);
//        }
//        model.addAttribute("message", stringBuilder);
////        model.addAllAttributes(points);
//        return "result";
//    }


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
//    public List<String> findAll() {
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
