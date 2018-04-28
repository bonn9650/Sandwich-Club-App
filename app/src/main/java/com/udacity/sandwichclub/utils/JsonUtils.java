package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        String mainName = null;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();


        try {
            //Create JSON Object from string resource
            JSONObject sandwichJSONObject = new JSONObject(json);
            //Get JSON Object mapped to "name"
            JSONObject nameObject = sandwichJSONObject.getJSONObject("name");

            //Get Sandwich object attributes from nameObject
            mainName = nameObject.getString("mainName");
            JSONArray akaList = nameObject.getJSONArray("alsoKnownAs");
            //Iterate through akaList to build List<String> for alsoKnownAs
            if (akaList != null){
                for (int i = 0; i< akaList.length(); i++){
                    if (i == 0){
                        alsoKnownAs.add(akaList.get(i).toString());

                    }else {
                        alsoKnownAs.add("\n" + akaList.get(i).toString());
                    }
                }
            }

            //Get Sandwich object attributes from sandwichJSONObject
            placeOfOrigin = sandwichJSONObject.getString("placeOfOrigin");
            description = sandwichJSONObject.getString("description");
            image = sandwichJSONObject.getString("image");

            //Get JSON Array mapped to ingredients from sandwichJSONObject
            JSONArray ingredientsArray = sandwichJSONObject.getJSONArray("ingredients");
            //iterate through ingredientsArray to build List<String> for ingredients
            if (ingredientsArray != null){
                for (int i = 0; i<ingredientsArray.length(); i++){
                    if (i == 0){
                        ingredients.add(ingredientsArray.get(i).toString());
                    }else {
                        ingredients.add("\n" + ingredientsArray.get(i).toString());
                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
