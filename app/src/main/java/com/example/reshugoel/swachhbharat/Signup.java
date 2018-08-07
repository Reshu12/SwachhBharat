package com.example.reshugoel.swachhbharat;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

   private Button btn;
    private EditText email,password,phone;
    private TextView signin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    String em,pa,ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabase.keepSynced(true);
        firebaseAuth= FirebaseAuth.getInstance();
        btn=(Button)findViewById(R.id.sub);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        phone=(EditText)findViewById(R.id.phone);
        signin=(TextView)findViewById(R.id.signin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em= email.getText().toString().trim();
                pa= password.getText().toString().trim();
                ph= phone.getText().toString().trim();
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

                Log.i("TAG","createUser");
                firebaseAuth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final String device_token = FirebaseInstanceId.getInstance().getToken();
                            Map<String, String> userMap = new HashMap<>();
                            userMap.put("email", em);
                            userMap.put("phone", ph);
                            Log.i("TAG", "Savetodatabase: " + userMap.toString());
                            mDatabase.child(ph).setValue(userMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Signup.this,"Registered successfully..",Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(Signup.this,Login.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }
                                    });
                        }
                        else {

                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Toast.makeText(Signup.this,"Couldn't register try again!!!"+e.getMessage(),Toast.LENGTH_SHORT).show();
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
                finish();


            }
        });

    }
}
