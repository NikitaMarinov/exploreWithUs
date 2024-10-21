package com.excursions.exploreWithUs.ExploreWithUs.service;

import com.excursions.exploreWithUs.ExploreWithUs.dto.ExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.dto.NoIdExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.exception.ResourceNotFoundException;
import com.excursions.exploreWithUs.ExploreWithUs.mapper.ExcursionMapper;
import com.excursions.exploreWithUs.ExploreWithUs.model.Excursion;
import com.excursions.exploreWithUs.ExploreWithUs.repository.ExcursionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExcursionServiceTest {

    @Mock
    private ExcursionRepository excursionRepository;
    @Mock
    private ExcursionMapper excursionMapper;

    @InjectMocks
    private ExcursionService excursionService;

    @Test
    void testGetAllExcursions() {
        Pageable pageable = mock(Pageable.class);
        Excursion excursion = new Excursion();
        ExcursionDto excursionDto = new ExcursionDto();

        Page<Excursion> excursionPage = new PageImpl<>(Collections.singletonList(excursion), pageable, 1);

        when(excursionRepository.findAll(pageable)).thenReturn(excursionPage);
        when(excursionMapper.excursionToExcursionDto(excursion)).thenReturn(excursionDto);

        List<ExcursionDto> result = excursionService.gelAllExcursions(pageable);

        assertEquals(1, result.size());
        assertEquals(excursionDto, result.get(0));
        verify(excursionRepository).findAll(pageable);
    }

    @Test
    void testGetExcursionById() {
        Long id = 1L;
        Excursion excursion = new Excursion();
        ExcursionDto excursionDto = new ExcursionDto();
        when(excursionRepository.findById(id)).thenReturn(Optional.of(excursion));
        when(excursionMapper.excursionToExcursionDto(excursion)).thenReturn(excursionDto);

        ExcursionDto result = excursionService.getExcursionById(id);

        assertEquals(excursionDto, result);
        verify(excursionRepository).findById(id);
    }

    @Test
    void testGetExcursionByIdNotFound() {
        Long id = 1L;
        when(excursionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> excursionService.getExcursionById(id));
        verify(excursionRepository).findById(id);
    }

    @Test
    void testCreateExcursion() {
        NoIdExcursionDto noIdExcursionDto = new NoIdExcursionDto();
        Excursion excursion = new Excursion();
        Excursion savedExcursion = new Excursion();
        ExcursionDto excursionDto = new ExcursionDto();
        when(excursionMapper.noIdExcursionDtoToExcursion(noIdExcursionDto)).thenReturn(excursion);
        when(excursionRepository.save(excursion)).thenReturn(savedExcursion);
        when(excursionMapper.excursionToExcursionDto(savedExcursion)).thenReturn(excursionDto);

        ExcursionDto result = excursionService.createExcursion(noIdExcursionDto);

        assertEquals(excursionDto, result);
        verify(excursionMapper).noIdExcursionDtoToExcursion(noIdExcursionDto);
        verify(excursionRepository).save(excursion);
    }

    @Test
    void testDeleteExcursion() {
        Long id = 1L;
        Excursion excursion = new Excursion();
        when(excursionRepository.findById(id)).thenReturn(Optional.of(excursion));

        excursionService.deleteExcursion(id);

        verify(excursionRepository).deleteById(id);
    }

    @Test
    void testDeleteExcursionNotFound() {
        Long id = 1L;
        when(excursionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> excursionService.deleteExcursion(id));
        verify(excursionRepository).findById(id);
    }

    @Test
    void testUpdateExcursion() {
        Long id = 1L;
        NoIdExcursionDto noIdExcursionDto = new NoIdExcursionDto();
        Excursion excursion = new Excursion();
        Excursion savedExcursion = new Excursion();
        ExcursionDto excursionDto = new ExcursionDto();
        when(excursionRepository.findById(id)).thenReturn(Optional.of(excursion));
        when(excursionMapper.noIdExcursionDtoToExcursion(noIdExcursionDto)).thenReturn(excursion);
        when(excursionRepository.save(excursion)).thenReturn(savedExcursion);
        when(excursionMapper.excursionToExcursionDto(savedExcursion)).thenReturn(excursionDto);

        ExcursionDto result = excursionService.updateExcursion(id, noIdExcursionDto);

        assertEquals(excursionDto, result);
        verify(excursionRepository).findById(id);
        verify(excursionRepository).save(excursion);
    }

    @Test
    void testUpdateExcursionNotFound() {
        Long id = 1L;
        NoIdExcursionDto noIdExcursionDto = new NoIdExcursionDto();
        when(excursionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> excursionService.updateExcursion(id, noIdExcursionDto));
        verify(excursionRepository).findById(id);
    }

}
