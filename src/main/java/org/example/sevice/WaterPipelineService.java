package org.example.sevice;

import org.example.model.WaterPipeline;

import java.util.List;

public interface WaterPipelineService {

    void save(String filePath);

    WaterPipeline findById(Integer id);

    List<WaterPipeline> findAll();

    void delete(WaterPipeline waterPipeline);
}
