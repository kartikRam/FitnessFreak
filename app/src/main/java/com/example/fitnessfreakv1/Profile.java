package com.example.fitnessfreakv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    ImageView user_image;
    EditText name,email,phone;
    String userid,useremail;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        user_image=findViewById(R.id.user_image);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        auth=FirebaseAuth.getInstance();
        useremail=auth.getCurrentUser().getEmail();
        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference file=storageReference.child(useremail+".jpg");
        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(user_image);
            }
        });

        firestore=FirebaseFirestore.getInstance();
        userid=auth.getCurrentUser().getUid();
        if(auth.getCurrentUser()!=null) {
            DocumentReference documentReference = firestore.collection("users").document(userid);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if( value.exists()) {
                        name.setText(value.getString("name"));
                        email.setText(value.getString("email"));
                        phone.setText(value.getString("phone"));
                    }
                }
            });
        }

    }

    public void get_image(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                Uri image=data.getData();/*
                uimage.setImageURI(image);*/
                uploadImagetoFirebase(image);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImagetoFirebase(Uri image) {

        StorageReference file=storageReference.child(useremail+".jpg");
        file.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(user_image);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),"Image Not Uploaded",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(getApplicationContext(),SelectFunctionality.class);
        startActivity(intent);
        super.onBackPressed();
    }
}