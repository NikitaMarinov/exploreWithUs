package com.excursions.exploreWithUs.ExploreWithUs.controller;

import com.excursions.exploreWithUs.ExploreWithUs.dto.NoIdExcursionDto;
import com.excursions.exploreWithUs.ExploreWithUs.model.Excursion;
import com.excursions.exploreWithUs.ExploreWithUs.repository.ExcursionRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;

@Import(TestContainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcursionControllerTest {
    @Autowired
    ExcursionRepository excursionRepository;
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    @LocalServerPort
    Integer port;


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        excursionRepository.save(new Excursion(null, "Excursion 1", "Description 1", 100.0, LocalDateTime.now()));

    }


    static {
        mySQLContainer.start();
    }

    @Test
    void shouldGetAllExcursions() {
        RestAssured.given()
                .when()
                .get("/excursions")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void shouldGetExcursionById() {
        Long id = 1L;
        RestAssured.given()
                .when()
                .get("/excursions/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id.intValue()));
    }

    @Test
    void shouldCreateExcursion() {
        var noIdExcursionDto = new NoIdExcursionDto("Excursion Name", "Description", 100.0);
        RestAssured.given()
                .contentType("application/json")
                .body(noIdExcursionDto)
                .when()
                .post("/excursions")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("Excursion Name"))
                .body("description", equalTo("Description"))
                .body("price", equalTo(100.0f));;
    }

    @Test
    void shouldUpdateExcursion() {
        Long id = 1L;
        var noIdExcursionDto = new NoIdExcursionDto("Updated Name", "Updated Description", 150.0);

        RestAssured.given()
                .contentType("application/json")
                .body(noIdExcursionDto)
                .when()
                .put("/excursions/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("Updated Name"));
    }


    @Test
    void shouldDeleteExcursion() {
        Long id = 2L;
        RestAssured.given()
                .when()
                .delete("/excursions/" + id)
                .then()
                .log().all()
                .statusCode(204);
    }

}
