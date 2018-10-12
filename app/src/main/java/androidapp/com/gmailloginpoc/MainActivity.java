package androidapp.com.gmailloginpoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin;
    EditText emailID ;
    EditText password;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (EditText) findViewById(R.id.PasswordText);
        buttonLogin = findViewById(R.id.LoginButton);
        emailID = findViewById(R.id.emailIDText);
        context = MainActivity.this;
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginSuccessful.class);
                intent.putExtra("emailIDKey",emailID.getText().toString());
                intent.putExtra("passwordKey", password.getText().toString());
                context.startActivity(intent);
            }
        });
    }
}
