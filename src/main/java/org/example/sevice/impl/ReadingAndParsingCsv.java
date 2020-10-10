package org.example.sevice.impl;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.model.WaterPipeline;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingAndParsingCsv {

    List<WaterPipeline> getWaterPipeline(String filePath){
        List<WaterPipeline> waterPipelines = new ArrayList<>();
        for (String str : readingAndParsing(filePath)){
            List<String> list = new ArrayList<>();
            for (String value : str.split(";")) {
                list.add(value);
            }
            WaterPipeline waterPipeline = WaterPipeline.builder()
                    .x(Integer.valueOf(list.get(0)))
                    .y(Integer.valueOf(list.get(1)))
                    .length(Integer.valueOf(list.get(2)))
                    .build();
            waterPipelines.add(waterPipeline);
        }
        return waterPipelines;
    }

    List<PointWaterPipeline> getPointWaterPipeline(String filePath){
        List<PointWaterPipeline> pointWaterPipelines = new ArrayList<>();
        for (String str : readingAndParsing(filePath)){
            List<String> list = new ArrayList<>();
            for (String value : str.split(";")) {
                list.add(value);
            }
            PointWaterPipeline pointWaterPipeline = PointWaterPipeline.builder()
                    .x(Integer.valueOf(list.get(0)))
                    .y(Integer.valueOf(list.get(1)))
                    .build();
            pointWaterPipelines.add(pointWaterPipeline);
        }
        return pointWaterPipelines;
    }


    List<String> readingAndParsing(String filePath){
        List<String> list = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (checkFileName(file).equals("csv")) {
                CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()));
                String[] nextLine;
                reader.readNext();
                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine != null) {
                        for (String retval : nextLine) {
                            list.add(retval);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private String checkFileName(File file) throws Exception {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        else throw new Exception("This is not a csv file");
    }
}
