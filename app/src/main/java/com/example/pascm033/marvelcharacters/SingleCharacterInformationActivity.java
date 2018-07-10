package com.example.pascm033.marvelcharacters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleCharacterInformationActivity extends AppCompatActivity {
    TextView mNameText;
    TextView mDescriptionText;
    ImageView mCharacterImg;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_IMGURL = "imageUrl";

    public static void start(Context context, String name, String description, ThumbnailInfo url) {
        Intent intent  = new Intent(context, SingleCharacterInformationActivity.class);
        intent.putExtra(SingleCharacterInformationActivity.EXTRA_NAME, name);
        intent.putExtra(SingleCharacterInformationActivity.EXTRA_DESC, description);
        intent.putExtra(SingleCharacterInformationActivity.EXTRA_IMGURL,
                url.getPath() + "." + url.getExtension());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_character_information);

        mNameText = findViewById(R.id.nameText);
        mDescriptionText = findViewById(R.id.descriptionText);
        mCharacterImg = findViewById(R.id.characterImg);

        Intent intent = getIntent();
        String characterName = intent.getStringExtra(SingleCharacterInformationActivity.EXTRA_NAME);
        mNameText.setText(characterName);

        String characterDescription = intent.getStringExtra(SingleCharacterInformationActivity.EXTRA_DESC);
        mDescriptionText.setText(characterDescription);
        
        String characterImage = intent.getStringExtra(SingleCharacterInformationActivity.EXTRA_IMGURL);
        Picasso.get()
                .load(Uri.parse(characterImage))
                .into(mCharacterImg);

    }


}
