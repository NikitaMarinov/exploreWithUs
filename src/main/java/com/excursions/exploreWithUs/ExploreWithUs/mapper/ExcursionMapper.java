package com.excursions.exploreWithUs.ExploreWithUs.mapper;

import com.excursions.exploreWithUs.ExploreWithUs.dto.ExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.dto.NoIdExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.model.Excursion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {
    ExcursionMapper INSTANCE = Mappers.getMapper(ExcursionMapper.class);

    ExcursionDto excursionToExcursionDto(Excursion excursion);

    Excursion excursionDtoToExcursion(ExcursionDto excursionDto);

    List<ExcursionDto> excursionsToExcursionDtos(List<Excursion> excursions);

    List<Excursion> excursionDtosToExcursions(List<ExcursionDto> excursionDtos);

    NoIdExcursionDto excursionToNoIdExcursionDto(Excursion excursion);

    Excursion noIdExcursionDtoToExcursion(NoIdExcursionDto noIdExcursionDto);

    List<NoIdExcursionDto> excursionsToNoIdExcursionDtos(List<Excursion> excursions);
    List<Excursion> noIdExcursionDtosToExcursion(List<NoIdExcursionDto> noIdExcursionDtos);
}
