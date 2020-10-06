package org.example.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.WaterPipelineDAO;
import org.example.model.WaterPipeline;
import org.example.sevice.WaterPipelineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class WaterPipelineServiceImpl implements WaterPipelineService {

    private final WaterPipelineDAO waterPipelineDAO;
    private final ReadingAndParsingCsv readingAndParsingCsv;

    @Override
    public void save(String filePath) {
        readingAndParsingCsv.getWaterPipeline(filePath).forEach(waterPipelineDAO::save);
    }

    @Override
    public WaterPipeline findById(Integer id) {
        Optional<WaterPipeline> waterPipeline = ofNullable(waterPipelineDAO.findById(id))
                .orElseThrow(() -> new RuntimeException());
        return waterPipeline.get();
    }

    @Override
    public List<WaterPipeline> findAll() {
        return waterPipelineDAO.findAll();
    }

    @Override
    public void delete(WaterPipeline waterPipeline) {
        waterPipelineDAO.delete(waterPipeline);
    }
}
