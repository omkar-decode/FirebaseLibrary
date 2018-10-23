package com.example.omkar.firebaserecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Omkar on 18-02-2018.
 */

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    protected TextView nameTv;
    protected TextView idTv;
    protected TextView authorTv;
    protected ImageView imageIv;

    protected FirebaseViewHolder(View itemView) {
        super(itemView);

        nameTv = itemView.findViewById(R.id.bookName);
        idTv = itemView.findViewById(R.id.bookId);
        authorTv = itemView.findViewById(R.id.authorName);
        imageIv = itemView.findViewById(R.id.bookPic);
    }
}
