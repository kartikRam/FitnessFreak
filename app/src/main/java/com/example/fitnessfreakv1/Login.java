package com.example.fitnessfreakv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ImageView show_pass_btn;
    EditText password,email;
    Button login;
    String e,pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser() !=null){
            Intent intent=new Intent(getApplicationContext(),SelectFunctionality.class);
            startActivity(intent);
            Login.this.finish();
        }

        show_pass_btn=findViewById(R.id.show_pass_btn);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=email.getText().toString();
                pass=password.getText().toString();
                if(e.equals("")||pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Fill all details!",Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(e,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(getApplicationContext(),SelectFunctionality.class);
                                startActivity(intent);
                                Login.this.finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Error:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }

    }

    public void nextmethod(View view) {

        Intent intent =new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }
}