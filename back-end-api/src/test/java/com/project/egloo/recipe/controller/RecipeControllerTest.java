package com.project.egloo.recipe.controller;

import com.project.egloo.recipe.domain.Difficulty;
import com.project.egloo.recipe.dto.BasicCategoryDTO;
import com.project.egloo.recipe.dto.CreateRecipeDTO;
import com.project.egloo.recipe.dto.RecipeDTO;
import com.project.egloo.recipe.service.RecipeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Disabled
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    public void createRecipeTest() throws Exception {
        // given
        CreateRecipeDTO createRecipeInfo = new CreateRecipeDTO(
            "국",
            "소고기 미역국 레시피",
            "소고기 미역국 레시피 입니다.",
            Difficulty.MIDDLE,
            "https://image.com/recipe1.jpg",
            6000
        );

        RecipeDTO createdRecipe = new RecipeDTO(
            1L,
            new BasicCategoryDTO(1L, "국", OffsetDateTime.now()),
            "소고기 미역국",
            "소고기 미역국 입니다.",
            Difficulty.MIDDLE,
            0.0,
            "https://image.com/1.jpg",
            6000,
            0,
            0,
            OffsetDateTime.now(),
            null
        );

        // when
        when(recipeService.createRecipe(createRecipeInfo)).thenReturn(createdRecipe);

        // then
        mockMvc.perform(
                post("/api/v1/recipes")
                    .content("{\"category\":\"국\",\"name\":\"소고기 미역국\",\"summary\":\"소고기 미역국 입니다.\",\"difficulty\":\"MIDDLE\",\"image\":\"https://image.com/1.jpg\",\"price\":\"6000\"}")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("소고기 미역국")))
            .andExpect(jsonPath("$.summary", is("소고기 미역국 입니다.")))
            .andExpect(jsonPath("$.difficulty", is("MIDDLE")))
            .andExpect(jsonPath("$.image", is("https://image.com/1.jpg")))
            .andExpect(jsonPath("$.price", is(6000)))
            .andDo(print());
    }

}