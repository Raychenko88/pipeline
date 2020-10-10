package org.example.sevice.impl;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.model.WaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.example.sevice.WaterPipelineService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteAndParsingCsv {
    private final PointWaterPipelineService pointWaterPipelineService;
    private final WaterPipelineService waterPipelineService;


    public void writeToCsv() throws IOException {
        List<PointWaterPipeline> list = findResult();
        String[] record = new String[list.size()];
        String csv =
                System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "files" +
                        System.getProperty("file.separator") +
                        "result.csv";
        File file = new File(csv);
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter);
        record[0] = "ROUTE EXISTS;MIN LENGTH" + "/n";
        for (int i = 1; i < list.size(); i++) {
            record[i] = list.get(i).toString() + "/n";
        }
        writer.writeNext(record);
        writer.close();
    }

    List<PointWaterPipeline> findResult() {
        List<PointWaterPipeline> listPointWaterPipelines = pointWaterPipelineService.findAll();

        for (PointWaterPipeline pointWaterPipeline : listPointWaterPipelines) {
            List<Integer> listLengths = new ArrayList<>();
            List<WaterPipeline> listWaterPipelines = waterPipelineService.findAll();
            int x = pointWaterPipeline.getX();
            int y = pointWaterPipeline.getY();

            for (WaterPipeline waterPipeline : listWaterPipelines){
                if (waterPipeline.getX().equals(x)){
                    if (waterPipeline.getY().equals(y)){
                        listLengths.add(waterPipeline.getLength());
                        pointWaterPipeline.setTrueFalse("TRUE;");
                        pointWaterPipeline.setResult(waterPipeline.getLength());
                    }
                }
            }
            for (WaterPipeline waterPipeline : listWaterPipelines) {
                if (waterPipeline.getX().equals(x)) {
                    for (int i = 0; i < listWaterPipelines.size(); i++) {
                        if (listWaterPipelines.get(i).getX().equals(waterPipeline.getY())) {
                            if (listWaterPipelines.get(i).getY().equals(y)) {
                                listLengths.add(waterPipeline.getLength() + listWaterPipelines.get(i).getLength());
                                pointWaterPipeline.setTrueFalse("TRUE;");
                            } else {
                                pointWaterPipeline.setTrueFalse("FALSE;");
                            }
                        }
                    }
                }
            }
            if (!listLengths.isEmpty()) {
                if (pointWaterPipeline.getResult() != null){
                    if (pointWaterPipeline.getResult() > minLength(listLengths)){
                        pointWaterPipeline.setResult(minLength(listLengths));
                    }
                }else {
                    pointWaterPipeline.setResult(minLength(listLengths));
                }
            }
        }
        return listPointWaterPipelines;
    }

    public List<String> getResult() {
        List<String> points = new ArrayList<>();
        for (PointWaterPipeline p : findResult()) {
            points.add(p.toString());
        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : points) {
//            stringBuilder.append(s + "\n");
//        }
//        return stringBuilder.toString();
        return points;
    }

    Integer minLength(List<Integer> list) {
        int min = list.get(0);
        for (Integer i : list) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}
