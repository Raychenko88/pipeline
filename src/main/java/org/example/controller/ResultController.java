package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.sevice.impl.WriteAndParsingCsv;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "result")
public class ResultController {

    private final WriteAndParsingCsv writeAndParsingCsv;

    @GetMapping
    public String getResult(Model model) {
        try {
            writeAndParsingCsv.writeToCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> points = new ArrayList<>();
        for (PointWaterPipeline p : writeAndParsingCsv.findResult()){
            points.add(p.toString());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : points){
            stringBuilder.append(s);
        }
        model.addAttribute("message", stringBuilder.toString());
        return "water-pipeline";
    }
}
