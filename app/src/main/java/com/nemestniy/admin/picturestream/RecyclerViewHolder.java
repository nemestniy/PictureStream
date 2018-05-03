package com.nemestniy.admin.picturestream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nemestniy.admin.picturestream.API.ExampleResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private String color;

    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        image =(ImageView) itemView.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityImage.class);
                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("img", bs.toByteArray());
                intent.putExtra("color", color);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void bind(ExampleResponse res) {
        new AsyncImage(image).execute(res.urls.small);
        this.color = res.color;
    }




    private static class AsyncImage extends AsyncTask<String, Void, Void> {
        Drawable d;
        ImageView image;
        String color;

        public AsyncImage(ImageView image) {
            this.image = image;
        }

        public AsyncImage(String color) {
            this.color = color;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            image.setImageDrawable(d);
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... voids) {
            URL url = null;
            try {
                url = new URL(voids[0]);
                InputStream content = (InputStream)url.getContent();
                d = Drawable.createFromStream(content, "src");
                //color =
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
