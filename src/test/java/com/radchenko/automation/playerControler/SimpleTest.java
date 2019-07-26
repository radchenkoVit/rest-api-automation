package com.radchenko.automation.playerControler;

import com.radchenko.automation.model.request.PlayerDto;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SimpleTest {

    private static String playerJsonAsString = "{" +
            "        \"id\": null," +
            "        \"fullName\": \"Petr Čech2\"," +
            "        \"position\": \"GK\"," +
            "        \"teamName\": null" +
            "    }";

    @Test
    public void validatePlayerAdded() {
        RestAssured
                .given().log().all()
                .baseUri("http://localhost:8088/v1/player")
                .body(playerJsonAsString)
                .contentType("application/json")
                .post()
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void validatePlayerAddedViaObject() {
        PlayerDto playerToCreate = generatePlayer();

        PlayerDto createdPlayer = RestAssured
                .given().log().all()
                .baseUri("http://localhost:8088/v1/player")
                .body(playerToCreate)
                .contentType("application/json")
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .extract().body().as(PlayerDto.class);

        Assert.assertNotNull(createdPlayer.getId());
    }


    private static PlayerDto generatePlayer() {
        return PlayerDto.builder()
                .id(null)
                .fullName(RandomStringUtils.randomAlphabetic(10))
                .position("GK")
                .teamName(null)
                .build();
    }

}
