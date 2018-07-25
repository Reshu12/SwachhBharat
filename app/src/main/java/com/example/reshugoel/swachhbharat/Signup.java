package com.example.reshugoel.swachhbharat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

   private Button btn;
    private EditText email,password,phone;
    private TextView signin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth= FirebaseAuth.getInstance();


        btn=(Button)findViewById(R.id.sub);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        phone=(EditText)findViewById(R.id.phone);
        signin=(TextView)findViewById(R.id.signin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em= email.getText().toString().trim();
                String pa= password.getText().toString().trim();
                String ph= phone.getText().toString().trim();
                if(TextUtils.isEmpty(em)){
                    Toast.makeText(Signup.this,"Please enter username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pa)){
                    Toast.makeText(Signup.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ph)){
                    Toast.makeText(Signup.this,"Please enter phone number",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this,"Registered successfully..",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Signup.this,Login.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Signup.this,"Couldn't register try again!!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Signup.this,Login.class);
                startActivity(intent1);
            }
        });

    }
}
