package com.project.egloo.recipe.repository;

import com.project.egloo.recipe.domain.Cooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingRepository extends JpaRepository<Cooking, Long> {
}
