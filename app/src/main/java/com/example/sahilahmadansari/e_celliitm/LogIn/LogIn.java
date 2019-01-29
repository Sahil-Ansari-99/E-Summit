package com.example.sahilahmadansari.e_celliitm.LogIn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sahilahmadansari.e_celliitm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {
    EditText userEmail, userPassword;
    Button logInButton;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInButton=(Button)findViewById(R.id.login_button);
        userEmail=(EditText)findViewById(R.id.login_email);
        userPassword=(EditText)findViewById(R.id.login_password);

        progressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=firebaseAuth.getCurrentUser();

                if(firebaseUser!=null){
                    userId=firebaseUser.getUid();
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG).show();
                    Log.e("Test", "Logged In");
                }else{
                    Toast.makeText(getApplicationContext(), "Not Signed In", Toast.LENGTH_LONG).show();
                }
            }
        };

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(userEmail.getText().toString()) && !TextUtils.isEmpty(userPassword.getText().toString())){
                    String emailId=userEmail.getText().toString();
                    String pwd=userPassword.getText().toString();
                    loginUser(emailId,pwd);
                }else{
                    Toast.makeText(getApplicationContext(), "Please fill out the required fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loginUser(String email, String password){
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Log In Successful", Toast.LENGTH_LONG).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
