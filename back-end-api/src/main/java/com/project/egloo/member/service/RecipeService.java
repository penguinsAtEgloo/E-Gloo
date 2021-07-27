package com.project.egloo.member.service;

import com.project.egloo.common.StatusCode;
import com.project.egloo.member.domain.IngredientRecipeMapping;
import com.project.egloo.member.repository.IngredientRecipeMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private IngredientRecipeMappingRepository ingredientRecipeMappingRepository;

    public HashMap getRecipeByIngredients(ArrayList lis) {
        ArrayList intersection = new ArrayList();
        for(int i=0; i < lis.size(); i++){
            List mappingList =  ingredientRecipeMappingRepository.findByIngredient_id(Long.parseLong((String) lis.get(i)));
            List recipeNames = new ArrayList();
            for(int j=0; j < mappingList.size(); j++){
                IngredientRecipeMapping map = (IngredientRecipeMapping) mappingList.get(j);
                recipeNames.add(map.getRecipe().getName());
            }
            intersection.add(recipeNames);
        }
        return  response(intersection(intersection));
    }

    public HashSet intersection(ArrayList inputArrays)
    {
        HashSet intersectionSet = new HashSet((Collection) inputArrays.get(0));
        for (int i = 1; i < inputArrays.size(); i++)
        {
            HashSet innerset = new HashSet((Collection) inputArrays.get(i));
            intersectionSet.retainAll(innerset);
        }
        return intersectionSet;
    }

    public HashMap response(Object object){
        HashMap res = new HashMap();
        res.put("code", StatusCode.SUCCESS_OK);
        res.put("msg",object);
        res.put("errors","");
        return res;
    }
}
