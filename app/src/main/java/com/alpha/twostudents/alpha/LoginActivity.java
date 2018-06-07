package com.alpha.twostudents.alpha;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = firebaseAuth.getInstance();

        // User is already signed in
        if (firebaseAuth.getCurrentUser() != null){
            // Start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == buttonSignIn) {
            userLogin();
        }
        if (v == textViewSignUp) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        // Checks fields if empty. Tells user if one was empty and stops from executing further
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    // Start profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Could not login. Is the email correct? What about the password?", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    //User is successfully registered and signed in
//                    Toast.makeText(MainActivity.this,"Logged In Successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this,"Could not login... Try again", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}
