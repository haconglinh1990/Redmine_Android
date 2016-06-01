package haconglinh1990.redmineandroid.activities.authenticate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import haconglinh1990.redmineandroid.network.api.APIClient;
import haconglinh1990.redmineandroid.R;

public class LoginActivity extends AppCompatActivity {
    String address, userName, passWord;
    TextView tvForgotPassword;
    EditText etAddress,etUserName, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etAddress = (EditText) findViewById(R.id.activity_login_et_address);
        etUserName = (EditText) findViewById(R.id.activity_login_et_username);
        etPassword = (EditText) findViewById(R.id.activity_login_et_password);
        tvForgotPassword = (TextView) findViewById(R.id.activity_login_tv_forgot_password);
        btnLogin = (Button) findViewById(R.id.activity_login_btn_login);
        btnRegister = (Button) findViewById(R.id.activity_login_btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = "linhhc";
                passWord = "linh@123";
                address = "http://192.168.1.59/users/current.json";
                //userName = etUserName.getText().toString();
                //passWord = etPassword.getText().toString();
                //address = "http://" + etAddress.getText().toString() + "/users/current.json";
                new APIClient(LoginActivity.this).logInUseAPI(address, userName, passWord);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

}
