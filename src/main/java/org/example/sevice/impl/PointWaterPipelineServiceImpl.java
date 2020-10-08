package org.example.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.PointWaterPipelineDAO;
import org.example.model.PointWaterPipeline;
import org.example.sevice.PointWaterPipelineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class PointWaterPipelineServiceImpl implements PointWaterPipelineService {

    private final PointWaterPipelineDAO pointWaterPipelineDAO;
    private final ReadingAndParsingCsv readingAndParsingCsv;

    @Override
    public void save(String filePath) {
        readingAndParsingCsv.getPointWaterPipeline(filePath).forEach(pointWaterPipelineDAO::save);
    }

    @Override
    public PointWaterPipeline findById(Integer id) {
        Optional<PointWaterPipeline> pointWaterPipeline = ofNullable(pointWaterPipelineDAO.findById(id))
                .orElseThrow(() -> new RuntimeException());
        return pointWaterPipeline.get();
    }

    @Override
    public List<PointWaterPipeline> findAll() {
        return pointWaterPipelineDAO.findAll();
    }

    @Override
    public void delete(PointWaterPipeline pointWaterPipeline) {
        pointWaterPipelineDAO.delete(pointWaterPipeline);
    }
}
