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
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteAndParsingCsv {
    private final PointWaterPipelineService pointWaterPipelineService;
    private final WaterPipelineService waterPipelineService;


    public void writeToCsv() throws IOException {
        List<PointWaterPipeline> list = findResult();
        String[] record = new String[list.size() + 1];
        String csv =
                System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "src" +
                        System.getProperty("file.separator") +
                        "files" +
                        System.getProperty("file.separator") +
                        "result.csv";
        File file = new File(csv);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter, '\n', CSVWriter.NO_ESCAPE_CHARACTER
                , CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        record[0] = "ROUTE EXISTS;MIN LENGTH";
        for (int i = 1; i < list.size() + 1; i++) {
            record[i] = list.get(i - 1).toString();
        }
        writer.writeNext(record);
        writer.close();
        fileWriter.close();
        for (WaterPipeline w : waterPipelineService.findAll()) {
            waterPipelineService.delete(w);
        }
        for (PointWaterPipeline p : pointWaterPipelineService.findAll()) {
            pointWaterPipelineService.delete(p);
        }
    }

    List<PointWaterPipeline> findResult() {
        List<PointWaterPipeline> listPointWaterPipelines = pointWaterPipelineService.findAll();

        for (PointWaterPipeline pointWaterPipeline : listPointWaterPipelines) {
            List<Integer> listLengths = new ArrayList<>();
            List<WaterPipeline> listWaterPipelines = waterPipelineService.findAll();
            int x = pointWaterPipeline.getX();
            int y = pointWaterPipeline.getY();

            for (WaterPipeline waterPipeline : listWaterPipelines) {
                if (waterPipeline.getX().equals(x)) {
                    if (waterPipeline.getY().equals(y)) {
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
                if (pointWaterPipeline.getResult() != null) {
                    if (pointWaterPipeline.getResult() > minLength(listLengths)) {
                        pointWaterPipeline.setResult(minLength(listLengths));
                    }
                } else {
                    pointWaterPipeline.setResult(minLength(listLengths));
                }
            }
        }
        return listPointWaterPipelines;
    }

    public List<String> getResult() {
        List<String> points = new ArrayList<>();
        points.add("ROUTE EXISTS;MIN LENGTH");
        for (int i = 1; i < findResult().size() + 1; i++) {
            points.add(findResult().get(i - 1).toString());
        }
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
