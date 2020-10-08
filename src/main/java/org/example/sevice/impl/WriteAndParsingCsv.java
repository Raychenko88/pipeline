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
        String csv = "result.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        for (int i = 0; i < list.size(); i++){
            record[i] = list.get(i).toString();
        }
        writer.writeNext(record);
        writer.close();
    }

    public List<PointWaterPipeline> findResult(){
        List<PointWaterPipeline> listPointWaterPipelines = pointWaterPipelineService.findAll();

        for (PointWaterPipeline pointWaterPipeline : listPointWaterPipelines){
            List<Integer> listLengths = new ArrayList<>();
            List<WaterPipeline> listWaterPipelines = waterPipelineService.findAll();
            int x = pointWaterPipeline.getX();
            int y = pointWaterPipeline.getY();
            int length;
            for (WaterPipeline waterPipeline :listWaterPipelines){
                if (waterPipeline.getX().equals(x)){
                    for (int i = 0; i < listWaterPipelines.size(); i++){
                        if (listWaterPipelines.get(i).getY().equals(waterPipeline.getY())
                                && !listWaterPipelines.get(i).getId().equals(waterPipeline.getId())){
                            if (listWaterPipelines.get(i).getY().equals(y)){
                                listLengths.add(waterPipeline.getLength() + listWaterPipelines.get(i).getLength());
                                pointWaterPipeline.setTrueFalse("TRUE;");
                                listWaterPipelines.remove(listWaterPipelines.get(i));
                            }else {
                                pointWaterPipeline.setTrueFalse("FALSE;");
                            }
                        }
                    }
                }
            }
            if (!listLengths.isEmpty()){
                pointWaterPipeline.setResult(minLength(listLengths));
            }
        }
        return listPointWaterPipelines;
    }

//    public void getResult(){
//        List<PointWaterPipeline> list= findResult();
//        for (int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i).toString());
//        }
//    }

    Integer minLength(List<Integer> list){
        int min = list.get(0);
        for (Integer i : list){
            if (i < min){
                min = i;
            }
        }
        return min;
    }
}
