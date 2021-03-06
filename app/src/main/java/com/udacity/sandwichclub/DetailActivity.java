package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView placeOfOriginTextView = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        TextView descriptionTextView = findViewById(R.id.description_tv);
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.sandwich_not_found)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        if (sandwich.getPlaceOfOrigin().equals("")){
            placeOfOriginTextView.setText(R.string.unknown_attribute);
        }else {
            placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getAlsoKnownAs().toString().equals("[]")){
            alsoKnownAsTextView.setText(R.string.unknown_attribute);
        }else {
            alsoKnownAsTextView.setText(sandwich.getAlsoKnownAs().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replace(",",""));
        }
        descriptionTextView.setText(sandwich.getDescription());
        if (sandwich.getIngredients().toString().equals("[]")){
            ingredientsTextView.setText(R.string.unknown_attribute);
        }else {
            ingredientsTextView.setText(sandwich.getIngredients().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replace(",",""));
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
