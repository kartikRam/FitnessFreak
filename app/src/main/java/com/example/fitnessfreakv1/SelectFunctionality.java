package com.example.fitnessfreakv1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SelectFunctionality extends AppCompatActivity {

    CardView profile,logout,plan;
    FirebaseAuth auth;
    TextView user_name;
    ImageView user_image;

    FirebaseFirestore firestore;
    String userid;
    String useremail;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_functionality);
        profile=findViewById(R.id.profile);
        logout=findViewById(R.id.logout);
        getSupportActionBar().hide();
        user_name=findViewById(R.id.user_name);
        user_image=findViewById(R.id.user_image);
        auth=FirebaseAuth.getInstance();
        plan=findViewById(R.id.plan);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
                SelectFunctionality.this.finish();
            }
        });
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),index.class);
                startActivity(intent);
            }
        });

        useremail=auth.getCurrentUser().getEmail();
        userid=auth.getCurrentUser().getUid();
        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference file=storageReference.child(useremail+".jpg");
        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(user_image);
            }
        });

        firestore= FirebaseFirestore.getInstance();
        dataget();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                SelectFunctionality.this.finish();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }

    private void dataget() {

        if(auth.getCurrentUser()!=null) {
            if(!(userid.isEmpty())) {
                DocumentReference documentReference = firestore.collection("users").document(userid);
                documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value.exists()) {
                            user_name.setText(value.getString("name"));
                        }
                    }
                });
            }
        }
    }
}