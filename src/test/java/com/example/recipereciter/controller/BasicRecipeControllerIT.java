package com.example.recipereciter.controller;

import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.example.recipereciter.TestHelper.*;
import static com.example.recipereciter.TestHelper.mockFirstRecipe;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasicRecipeController.class)
class BasicRecipeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @InjectMocks
    BasicRecipeController basicRecipeController;

    @Test
    void shouldReturnAllRecipes() throws Exception {
        // arrange
        when(recipeService.getAllRecipes()).thenReturn(mockRecipeList());

        // act -> assert
        String expected = getFile("allRecipesResponse");
        mockMvc.perform(get("/recipes")).andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void shouldReturnOneRecipe() throws Exception {
        // arrange
        when(recipeService.getRecipe(1)).thenReturn(mockFirstRecipe());
        String expected = mapToString(mockGetFirstRecipeResponse());
        System.out.println(expected);

        // act -> assert
        mockMvc.perform(get("/recipes/1"))
                .andExpect(content().json(expected))
                .andExpect(status().isOk());
    }

    @Test
    void addNewRecipe() throws Exception {
        // arrange
        when(recipeService.addRecipe(mockNewRecipe())).thenReturn(mockNewRecipe());
        String payload = mapToString(mockNewRecipe());
        String expected = getFile("addNewRecipeResponse");

        // act -> assert
        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(payload))
                .andExpect(content().json(expected))
                .andExpect(status().isOk());
    }

    @Test
    void editRecipe() throws Exception {
        // arrange
        when(recipeService.editRecipe(1, mockNewRecipe())).thenReturn(mockEditedRecipe());
        String expected = getFile("editFirstRecipeResponse");

        // act -> assert
        String payload = mapToString(mockNewRecipe());
        mockMvc.perform(patch("/recipes/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(payload))
                .andExpect(content().json(expected))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRecipe() throws Exception {
        // arrange
        when(recipeService.deleteRecipe(1)).thenReturn(mockRecipe("firstRecipe"));
        String expected = getFile("deletedResponse");

        // act -> assert
        mockMvc.perform(delete("/recipes/1"))
                .andExpect(content().json(expected))
                .andExpect(status().isOk());
    }

}
