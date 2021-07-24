package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    private Button button;
    EditText edit_username;
    EditText edit_email;
    EditText edit_pass;
    Button btn_sign;
    Button btn_redirect;
    String str_name, str_email, str_password;
    //    String url = "https://cettyb6601.000webhostapp.com/register.php";
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    EditText phoneNumber, codeEnter;
    ProgressBar progressBar;
    TextView state;
    DatabaseReference databaseReference;
    CountryCodePicker codePicker;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verificationInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edit_username = (EditText) findViewById(R.id.id_username);
        edit_email = (EditText) findViewById(R.id.id_email);
        edit_pass = (EditText) findViewById(R.id.id_pass);
        btn_redirect = (Button) findViewById(R.id.btn_redirect);
        fAuth = FirebaseAuth.getInstance();
        phoneNumber = (EditText) findViewById(R.id.phone);
        codeEnter = (EditText) findViewById(R.id.codeEnter);
        progressBar = findViewById(R.id.progressBar);
        state = findViewById(R.id.state);
        codePicker = findViewById(R.id.ccp);
        btn_sign = (Button) findViewById(R.id.btn_register);
        btn_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edit_username.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username is empty", Toast.LENGTH_SHORT).show();
                } else if (edit_email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Email is empty", Toast.LENGTH_SHORT).show();
                } else if (edit_pass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Password is empty", Toast.LENGTH_SHORT).show();
                } else {
                    //here the code begins for the authentication
                    if (!verificationInProgress) {
                        if (!phoneNumber.getText().toString().isEmpty() && phoneNumber.getText().toString().length() == 10) {
                            String phoneNum = "+" + codePicker.getSelectedCountryCode() + phoneNumber.getText().toString();
                            Log.d(TAG,
                                    "onclick : Phone No ->" + phoneNum);
                            progressBar.setVisibility(View.VISIBLE); // after the button is clicked it should load a progress bar
                            state.setVisibility(View.VISIBLE);
                            state.setText("Sending OTP...");
                            requestOTP(phoneNum);


                        } else {
                            phoneNumber.setError("Phone Number is not valid");
                        }
                    } else {
                        String userOTP = codeEnter.getText().toString();
                        if (!userOTP.isEmpty() && userOTP.length() == 6) {
                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, userOTP);
                            verifyAuth(credential);
                        } else {
                            codeEnter.setError("Enter valid OTP");
                        }
                    }
                }
            }
        });



    }

    private void verifyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Authentication is successful", Toast.LENGTH_SHORT).show();
                    //main Game is on from here
                    if(btn_sign.getText().toString()=="SUBMIT"){
                        //keep you data registration code here
                        if(fAuth.getCurrentUser()!= null){


                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();
                        }
                        fAuth.createUserWithEmailAndPassword(edit_email.getText().toString().trim(),edit_pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(Signup.this,"User Credted.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                }else{
                                    Toast.makeText(Signup.this,"Error !"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Authentication is unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



    private void requestOTP(String phoneNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) { //called when the otp is sent by the server
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);  //after the code is sent the progress bar should be invisible
                state.setVisibility(View.GONE);
                codeEnter.setVisibility(View.VISIBLE);
                verificationId = s;
                token = forceResendingToken;
                btn_sign.setText("SUBMIT");
//                nextBtn.setEnabled(false);
                verificationInProgress = true;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) { //when the user does not enter the otp within given time //the string s contains the verification id
                super.onCodeAutoRetrievalTimeOut(s);
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) { //automatic verification of otp

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(), "Cannot create account" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
