package com.example.jbb.sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jbb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    private EditText nama, email, pass, phone;
    private Button daftar;
    private TextView login;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nama = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        phone = (EditText)findViewById(R.id.phone);
        daftar = (Button)findViewById(R.id.btnSignUp);
        login = (TextView)findViewById(R.id.masuk);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), com.example.jbb.sign.login.class));
            finish();;
        }

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                String ps = pass.getText().toString().trim();

                if(TextUtils.isEmpty(em)){
                    email.setError("Email jangan kosong :(");
                    return;
                }

                if(TextUtils.isEmpty(ps)){
                    pass.setError("Password jangan kosong :(");
                    return;
                }

                if(ps.length()<6){
                    pass.setError("Password Min 6 karakter ya");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(em,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(sign_up.this, "Yeay Pembuatan Akun Berhasil :)",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), com.example.jbb.sign.login.class));

                        }else {
                            Toast.makeText(sign_up.this,"Belum Berhasil :("+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this, com.example.jbb.sign.login.class));
            }
        });

    }

}
