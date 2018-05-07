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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.origin_tv)
    TextView originTv;

    @BindView(R.id.also_known_tv)
    TextView alsoKnownTv;

    @BindView(R.id.description_tv)
    TextView descriptionTv;

    @BindView(R.id.ingredients_tv)
    TextView ingredientsTv;

    @BindView(R.id.place_of_origin_label_tv)
    TextView placeOfOriginLabelTv;

    @BindView(R.id.also_known_as_label_tv)
    TextView alsoKnownAsLabelTv;

    @BindView(R.id.description_label_tv)
    TextView descriptionLabelTv;

    @BindView(R.id.ingredients_label_tv)
    TextView ingredientsLabelTv;

    @BindView(R.id.image_iv)
    ImageView ingredientsIv;

    private Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        sandwich = new Sandwich();

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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        //Add a string arg to help in debugging
        String argumentToTest = sandwich.getPlaceOfOrigin();
        String emptyArgumentPlaceholder = "Message not available";
        if (!argumentToTest.equals("")) {
//            originTv.setVisibility(View.VISIBLE);
//            placeOfOriginLabelTv.setVisibility(View.VISIBLE);
            originTv.setText(argumentToTest);
        } else {
//            originTv.setVisibility(View.GONE);
//            placeOfOriginLabelTv.setVisibility(View.GONE);
            originTv.setText(emptyArgumentPlaceholder);
        }

        argumentToTest = sandwich.getAlsoKnownAs();
        if (!argumentToTest.equals("")) {
//            alsoKnownTv.setVisibility(View.VISIBLE);
//            alsoKnownAsLabelTv.setVisibility(View.VISIBLE);
            alsoKnownTv.setText(argumentToTest);
        } else {
//            alsoKnownTv.setVisibility(View.GONE);
//            alsoKnownAsLabelTv.setVisibility(View.GONE);
            alsoKnownTv.setText(emptyArgumentPlaceholder);
        }

        argumentToTest = sandwich.getDescription();
        if (!argumentToTest.equals("")) {
//            descriptionTv.setVisibility(View.VISIBLE);
//            descriptionLabelTv.setVisibility(View.VISIBLE);
            descriptionTv.setText(argumentToTest);
        } else {
//            descriptionTv.setVisibility(View.GONE);
//            descriptionLabelTv.setVisibility(View.GONE);
            descriptionTv.setText(emptyArgumentPlaceholder);
        }

        argumentToTest = sandwich.getIngredients();

        if (!argumentToTest.equals("")) {
//            ingredientsTv.setVisibility(View.VISIBLE);
//            ingredientsLabelTv.setVisibility(View.VISIBLE);
            ingredientsTv.setText(argumentToTest);
        } else {
//            ingredientsTv.setVisibility(View.GONE);
//            ingredientsLabelTv.setVisibility(View.GONE);
            ingredientsTv.setText(emptyArgumentPlaceholder);
        }
    }
}
