package com.example.omkar.firebaserecyclerview;

import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
//
//    String bookName, bookAuthor, bookPicUrl;
//    int bookId;
//    FirebaseModel[] library;
//    FirebaseAdapter adapter;
//
//    DatabaseReference databaseReference;
//    int maxSize;


//    private FcmService fcmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

//        fcmService = new FcmService();

        FirebaseMessaging.getInstance().subscribeToTopic("libraryBooks")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfully subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Error in subscription";
                        }

                        Log.d("subscription message", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FcmService.createNotificationChannel(this);

        final Button btn_notif = findViewById(R.id.button_notif);
        final EditText et_name = findViewById(R.id.book_name);
        final EditText et_author = findViewById(R.id.book_author);

        btn_notif.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                String bookName = et_name.getText().toString();
                String bookAuthor = et_author.getText().toString();

                if (!TextUtils.isEmpty(bookName) && !TextUtils.isEmpty(bookAuthor)) {
                    Notification.Builder builder = FcmService.showNotification(
                            MainActivity.this, "New Book", bookName, bookAuthor);
                    FcmService.getManager(MainActivity.this).notify(101, builder.build());
                }
            }
        });


//        maxSize = 12;
//        library = new FirebaseModel[maxSize];
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Library001");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int index=0;
//                for(DataSnapshot book: dataSnapshot.getChildren()){
//
//                    bookName = (book.child("Name").getValue(String.class));
//                    bookAuthor = (book.child("Author").getValue(String.class));
//                    bookId = (book.child("Id").getValue(Integer.class));
//                    bookPicUrl = (book.child("Picture").getValue(String.class));
//
//                    library[index] = new FirebaseModel(bookName, bookAuthor, bookPicUrl, bookId);
//                    index++;
//                }
//
//                recyclerView = findViewById(R.id.rv);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//                recyclerView.setHasFixedSize(true);
//
//                adapter = new FirebaseAdapter(library);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
}
