package com.example.reshugoel.swachhbharat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Login extends AppCompatActivity {

    private Button button;
    private EditText user,pass;
    private TextView signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=(Button)findViewById(R.id.log);
        user=(EditText)findViewById(R.id.em);
        pass=(EditText)findViewById(R.id.pa);
        signup=(TextView)findViewById(R.id.signup);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= user.getText().toString().trim();
                String p= pass.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Login.this,"Please enter username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(p)){
                    Toast.makeText(Login.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(username,p).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent8= new Intent(Login.this,HomePage.class);
                            startActivity(intent8);
                            finish();
                        }
                        else{

                            FirebaseNetworkException e = (FirebaseNetworkException)task.getException();
                            Toast.makeText(Login.this,"Invalid user",Toast.LENGTH_SHORT).show();
                            Log.i("TAG",""+e.getMessage());
                        }
                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent9= new Intent(Login.this,Signup.class);
                startActivity(intent9);
            }
        });
    }
}
