package com.sih.railwaynavapp.OTPScreen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.sih.railwaynavapp.Constants;
import com.sih.railwaynavapp.LanguageScreen.LanguageSelectScreen;
import com.sih.railwaynavapp.NavScreen.NavScreen;
import com.sih.railwaynavapp.R;
import com.sih.railwaynavapp.StationSelectScreen.StationSelectActivity;
import com.sih.railwaynavapp.data.UserEntity;
import com.sih.railwaynavapp.databinding.ActivityOtpBinding;

import java.util.concurrent.TimeUnit;

public class OTPScreen  extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityOtpBinding binding;
    private String email;
    private String mVerificationId;
    private String enteredOTP;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private OTPViewModel viewModel;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(OTPViewModel.class);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);
        init();
    }

    protected void init() {
        mAuth = FirebaseAuth.getInstance();
        getIntentValues();
        initListener();
        phoneAuthentication();
    }

    protected void getIntentValues(){
        email = getIntent().getStringExtra(Constants.email);
        phone = getIntent().getStringExtra(Constants.phone);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            viewModel.saveUser(new UserEntity(email,phone));
                            startActivity(new Intent(OTPScreen.this, StationSelectActivity.class));
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(OTPScreen.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    protected void phoneAuthentication(){
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        // Invalid request
        // The SMS quota for the project has been exceeded
        // reCAPTCHA verification attempted with null Activity
        // Show a message and update the UI
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        // Save verification ID and resending token so we can use them later
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
        sendOtp(mCallbacks);
    }


    protected void sendOtp(PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(OTPScreen.this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    protected void initListener() {
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 enteredOTP = binding.digit1.getText().toString() + binding.digit2.getText().toString() + binding.digit3.getText().toString() + binding.digit4.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, enteredOTP);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

}
