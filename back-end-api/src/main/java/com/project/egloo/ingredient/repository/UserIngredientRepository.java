package com.project.egloo.ingredient.repository;

import com.project.egloo.ingredient.domain.UserIngredient;
import com.project.egloo.ingredient.domain.UserIngredientIDclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, UserIngredientIDclass> {
    List<UserIngredient> findByUserId(UUID userId);
}
