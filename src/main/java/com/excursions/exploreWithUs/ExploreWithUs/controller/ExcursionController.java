package com.excursions.exploreWithUs.ExploreWithUs.controller;

import com.excursions.exploreWithUs.ExploreWithUs.dto.ErrorResponse;
import com.excursions.exploreWithUs.ExploreWithUs.dto.ExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.dto.NoIdExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.service.ExcursionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_204;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_404;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_500;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_DESCRIPTION;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_ID_PARAM;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_DELETE_SUMMERY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_ALL_200;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_ALL_500;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_ALL_SUMMERY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_200;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_404;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_500;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_DESCRIPTION;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_ID_PARAM;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_GET_BY_ID_SUMMARY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_201;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_400;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_500;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_DESCRIPTION;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_REQUEST_BODY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_POST_CREATE_SUMMERY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_200;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_400;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_404;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_500;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_DESCRIPTION;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_ID_PARAM;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_REQUEST_BODY;
import static com.excursions.exploreWithUs.ExploreWithUs.constants.ExcursionConstants.EXCURSION_PUT_UPDATE_SUMMERY;

@RestController
@RequestMapping("/excursions")
@RequiredArgsConstructor
public class ExcursionController {

    private final ExcursionService excursionService;

    @Operation(summary = EXCURSION_GET_ALL_SUMMERY,
            description = "Retrieve all excursions with pagination support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = EXCURSION_GET_ALL_200,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcursionDto.class))),
            @ApiResponse(responseCode = "500",
                    description = EXCURSION_GET_ALL_500,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ExcursionDto>> getAllExcursions(
            @ParameterObject Pageable pageable) {

        List<ExcursionDto> excursions = excursionService.gelAllExcursions(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(excursions);
    }

    @Operation(summary = EXCURSION_GET_BY_ID_SUMMARY,
            description = EXCURSION_GET_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = EXCURSION_GET_BY_ID_200,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcursionDto.class))),
            @ApiResponse(responseCode = "404",
                    description = EXCURSION_GET_BY_ID_404,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = EXCURSION_GET_BY_ID_500,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExcursionDto> getExcursionById(
            @Parameter(description = EXCURSION_GET_BY_ID_ID_PARAM, example = "1") @PathVariable("id") Long id) {

        ExcursionDto excursionDto = excursionService.getExcursionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(excursionDto);
    }

    @Operation(summary = EXCURSION_POST_CREATE_SUMMERY, description = EXCURSION_POST_CREATE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = EXCURSION_POST_CREATE_201,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcursionDto.class))),
            @ApiResponse(responseCode = "400",
                    description = EXCURSION_POST_CREATE_400,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = EXCURSION_POST_CREATE_500,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ExcursionDto> createExcursion(
            @Parameter(description = EXCURSION_POST_CREATE_REQUEST_BODY) @Valid @RequestBody NoIdExcursionDto noIdExcursionDto) {

        ExcursionDto createdExcursion = excursionService.createExcursion(noIdExcursionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExcursion);
    }

    @Operation(summary = EXCURSION_PUT_UPDATE_SUMMERY, description = EXCURSION_PUT_UPDATE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = EXCURSION_PUT_UPDATE_200,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcursionDto.class))),
            @ApiResponse(responseCode = "400",
                    description = EXCURSION_PUT_UPDATE_400,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = EXCURSION_PUT_UPDATE_404,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = EXCURSION_PUT_UPDATE_500,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExcursionDto> updateExcursion(
            @Parameter(description = EXCURSION_PUT_UPDATE_ID_PARAM) @PathVariable("id") Long id,
            @Parameter(description = EXCURSION_PUT_UPDATE_REQUEST_BODY) @RequestBody @Valid NoIdExcursionDto noIdExcursionDto) {

        ExcursionDto excursionDto = excursionService.updateExcursion(id, noIdExcursionDto);
        return ResponseEntity.status(HttpStatus.OK).body(excursionDto);
    }

    @Operation(summary = EXCURSION_DELETE_SUMMERY,
            description = EXCURSION_DELETE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = EXCURSION_DELETE_204),
            @ApiResponse(responseCode = "404",
                    description = EXCURSION_DELETE_404,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = EXCURSION_DELETE_500,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcursion(
            @Parameter(description = EXCURSION_DELETE_ID_PARAM) @PathVariable("id") Long id) {

        excursionService.deleteExcursion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

