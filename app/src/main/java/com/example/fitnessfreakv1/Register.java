package com.example.fitnessfreakv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText password, confirm_password, email, name, phone;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Button register;
    String userid, em, pass, nm, con, ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        confirm_password = findViewById(R.id.confirm_password);
        auth = FirebaseAuth.getInstance();
        register = findViewById(R.id.register);
        firestore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = name.getText().toString();
                em = email.getText().toString();
                pass = password.getText().toString();
                con = confirm_password.getText().toString();
                ph = phone.getText().toString();
                if (nm.equals("") || em.equals("") || pass.equals("") || con.equals("") || phone.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill details", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.length() < 8 || con.length() < 8) {
                        password.setError("Password must be of 8 characters");
                        confirm_password.setError("Password must be of 8 characters");
                    } else {

                        if (pass.equals(con)) {

                            auth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Successfully Account Created!", Toast.LENGTH_SHORT).show();
                                        userid = auth.getCurrentUser().getUid();
                                        DocumentReference documentReference = firestore.collection("users").document(userid);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("name", nm);
                                        user.put("email", em);
                                        user.put("phone", ph);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(),"Created!",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Intent intent = new Intent(getApplicationContext(), SelectFunctionality.class);
                                        startActivity(intent);
                                        Register.this.finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        } else {
                            password.setError("Verify Password");
                            confirm_password.setError("Verify Confirm Password");
                        }
                    }

                }

            }

        });

    }

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.show_pass_btn) {

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.show_password);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.hide_password);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }

    }

    public void ShowHidePass1(View view) {

        if (view.getId() == R.id.show_pass_btn1) {

            if (confirm_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.show_password);

                //Show Password
                confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.hide_password);

                //Hide Password
                confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}