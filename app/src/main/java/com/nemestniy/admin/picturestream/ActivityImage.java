package com.nemestniy.admin.picturestream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActivityImage extends AppCompatActivity {

    private ImageView imageView;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.image_view);
        layout = (RelativeLayout) findViewById(R.id.layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("img"),0, extras.getByteArray("img").length);
            imageView.setImageBitmap(bitmap);
            layout.setBackgroundColor(Color.parseColor(extras.getString("color")));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
