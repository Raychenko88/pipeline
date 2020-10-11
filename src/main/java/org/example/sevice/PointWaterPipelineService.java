package org.example.sevice;

import org.example.model.PointWaterPipeline;

import java.util.List;

public interface PointWaterPipelineService {

    void save(String filePath);

    PointWaterPipeline findById(Integer id);

    List<PointWaterPipeline> findAll();

    void delete(PointWaterPipeline pointWaterPipeline);
}
