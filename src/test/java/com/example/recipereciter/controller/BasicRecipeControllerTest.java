package com.example.recipereciter.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicRecipeControllerTest {

    private BasicRecipeController basicRecipeController = new BasicRecipeController();

    @Test
    void helloWorldCheck() {
        assertEquals("Hello World", basicRecipeController.helloWorld());
    }
}