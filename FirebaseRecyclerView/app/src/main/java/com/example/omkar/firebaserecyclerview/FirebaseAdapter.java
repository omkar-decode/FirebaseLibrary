package com.example.omkar.firebaserecyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Omkar on 18-02-2018.
 */

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseViewHolder> {

    private FirebaseModel[] firebaseModel;

    public FirebaseAdapter(FirebaseModel[] firebaseModel) {
        this.firebaseModel = firebaseModel;
        Log.e("Constructor", "Inside constructor with data size = " + firebaseModel.length);
    }

    @Override
    public FirebaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //convert xml to java file
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_firebase, parent, false);
        FirebaseViewHolder firebaseViewHolder = new FirebaseViewHolder(view);
        Log.e("OnCreateViewHolder", "onCreateViewHolder, no position");
        return firebaseViewHolder;
    }

    @Override
    public void onBindViewHolder(FirebaseViewHolder holder, int position) {
        //bind data whenever android requires to update a specific position

        holder.nameTv.setText(firebaseModel[position].getBookName());
        holder.authorTv.setText(firebaseModel[position].getBookAuthor());
        holder.idTv.setText(""+firebaseModel[position].getBookId());
        new DownloadImageTask(holder.imageIv).execute(firebaseModel[position].getBookPicUrl());

        Log.e("onBindViewHolder","onBindViewHolder called for position " + position);
    }

    @Override
    public int getItemCount() {
        //return data size
        Log.e("Item Size", "Item count is " + firebaseModel.length);
        return firebaseModel.length;
    }
}

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
//new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute(MY_URL_STRING);