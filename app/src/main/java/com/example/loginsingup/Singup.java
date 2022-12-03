package com.example.loginsingup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Singup extends AppCompatActivity {
    EditText inputimail,inputpassword,ConfirmPassword;
    Button Register ;
    String emailpa ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mauth;
    FirebaseUser muser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        inputimail = findViewById(R.id.imail);
        inputpassword = findViewById(R.id.pass);
        ConfirmPassword = findViewById(R.id.confirmPass);
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();

        Register = findViewById(R.id.register);
        progressDialog=new ProgressDialog(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuther();
            }
        });
    }
    private void PerforAuther(){
        String email =inputimail.getText().toString();
        String password =inputpassword.getText().toString();
        String confirmpassword =ConfirmPassword.getText().toString();

        if(!email.matches(emailpa)){
            inputimail.setError("Enter connext Email");
        }else if(password.isEmpty() || password.length()<4 ){
            inputpassword.setError("Enter Proper password");
        }else if(!password.equals(confirmpassword)){
            ConfirmPassword.setError("sai mat khau");
        }else{
            progressDialog.setMessage("Please wait white register...");
            progressDialog.setTitle("registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserTonextActivity();
                        Toast.makeText(Singup.this, "Register successful", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Singup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }


    }

    private void sendUserTonextActivity() {
        Intent intent = new Intent(Singup.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}