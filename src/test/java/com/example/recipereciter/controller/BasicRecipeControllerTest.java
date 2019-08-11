package com.example.recipereciter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BasicRecipeControllerTest {

    @Autowired
    private BasicRecipeController basicRecipeController;

    @Test
    void helloWorldCheck() {
        assertEquals("", basicRecipeController.helloWorld());
    }
}