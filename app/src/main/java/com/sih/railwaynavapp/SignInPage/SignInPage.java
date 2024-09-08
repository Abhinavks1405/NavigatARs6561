package com.sih.railwaynavapp.SignInPage;

import static android.content.ContentValues.TAG;
import static com.sih.railwaynavapp.databinding.ActivitySigninBinding.inflate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sih.railwaynavapp.R;
import com.sih.railwaynavapp.databinding.ActivitySigninBinding;

import javax.inject.Inject;

public class SignInPage  extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivitySigninBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        init();
    }

    protected void init() {
        mAuth = FirebaseAuth.getInstance();
        initListener();
    }

   protected void updateUI(@Nullable FirebaseUser user){

   }
    protected void initListener() {
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    currentUser.reload();
                } else {
                    mAuth.createUserWithEmailAndPassword(viewModel.getEmail(), viewModel.getPassword())
                            .addOnCompleteListener(SignInPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignInPage.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }
                            });
                }
            }
        });
    }
}
