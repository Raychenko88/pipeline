package org.example.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.PointWaterPipeline;
import org.example.model.WaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.example.sevice.WaterPipelineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteAndParsingCsv {
    private final PointWaterPipelineService pointWaterPipelineService;
    private final WaterPipelineService waterPipelineService;

    String[] findResult(){
        List<Integer> listLengths = new ArrayList<>();
        List<PointWaterPipeline> listPointWaterPipelines = pointWaterPipelineService.findAll();

        for (PointWaterPipeline pointWaterPipeline : listPointWaterPipelines){
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
                                pointWaterPipeline.setTrueFalse("TRUE");
                                listWaterPipelines.remove(listWaterPipelines.get(i));
                            }else {
                                pointWaterPipeline.setTrueFalse("FALSE");
                            }
                        }
                    }
                }
            }
            pointWaterPipeline.setResult(minLength(listLengths));
        }
        return null;
    }

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
