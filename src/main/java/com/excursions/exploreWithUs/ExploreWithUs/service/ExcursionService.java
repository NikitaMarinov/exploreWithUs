package com.excursions.exploreWithUs.ExploreWithUs.service;

import com.excursions.exploreWithUs.ExploreWithUs.dto.ExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.dto.NoIdExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.exception.ResourceNotFoundException;
import com.excursions.exploreWithUs.ExploreWithUs.mapper.ExcursionMapper;
import com.excursions.exploreWithUs.ExploreWithUs.model.Excursion;
import com.excursions.exploreWithUs.ExploreWithUs.repository.ExcursionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcursionService {

    private final ExcursionRepository excursionRepository;
    private final ExcursionMapper excursionMapper;

    public List<ExcursionDto> gelAllExcursions(Pageable pageable) {
        return excursionRepository.findAll(pageable)
                .stream()
                .map(excursionMapper::excursionToExcursionDto)
                .collect(Collectors.toList());
    }

    public ExcursionDto getExcursionById(Long id) {
        Excursion excursion = excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursion  with id " + id + " not found!"));

        return excursionMapper.excursionToExcursionDto(excursion);
    }


    public ExcursionDto createExcursion(NoIdExcursionDto noIdExcursionDto) {
        Excursion excursion = excursionMapper.noIdExcursionDtoToExcursion(noIdExcursionDto);
        Excursion savedExcursion = excursionRepository.save(excursion);

        return excursionMapper.excursionToExcursionDto(savedExcursion);
    }

    public void deleteExcursion(Long id) {
        excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursion  with id " + id + " not found!"));

        excursionRepository.deleteById(id);
    }

    public ExcursionDto updateExcursion(Long id, NoIdExcursionDto noIdExcursionDto) {
        excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursion  with id " + id + " not found!"));

        Excursion excursion = excursionMapper.noIdExcursionDtoToExcursion(noIdExcursionDto);
        excursion.setId(id);
        return excursionMapper.excursionToExcursionDto(excursionRepository.save(excursion));
    }

}
