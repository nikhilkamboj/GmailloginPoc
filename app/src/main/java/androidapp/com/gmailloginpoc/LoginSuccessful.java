package androidapp.com.gmailloginpoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSuccessful extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String emailID;
    private String password;
    private  TextView textView;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textView = findViewById(R.id.textViewTest);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        emailID = extras.get("emailIDKey").toString();
        password = extras.get("passwordKey").toString();
        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    //redirect
                  //  updateUI(user);

                } else {
                    // User is signed out
                    Log.d("LoginSuccessful.class", "onAuthStateChanged:signed_out");
                    //updateUI(null);
                }

            }
        };


        mAuth.signInWithEmailAndPassword(emailID,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Successfull", "signInWithEmail:success");
                            textView.setText("successful");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            ///Toast.makeText(EmailPasswordActivity.this, "Authentication failed."
                            Log.d("Failed", "signInWithEmail:success");
                            textView.setText("Failed");
                            // Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
}

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
