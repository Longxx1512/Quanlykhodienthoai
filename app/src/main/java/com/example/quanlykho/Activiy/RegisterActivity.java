package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtEmail, edtPassword, edtConfirmpassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmpassword = edtConfirmpassword.getText().toString();
                //DBHelper db = new DBHelper(getApplicationContext(), "quanlykho", null, 1);
                DBHelper db = new DBHelper(getApplicationContext());
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirmpassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(confirmpassword) == 0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu phải chứa ít nhất 8 ký tự, có chữ cái, chữ số và ký hiệu đặc biệt", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu và Xác nhận mật khẩu không khớp nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere){
        int f1=0, f2=0, f3 =0;
        if(passwordhere.length() < 8){
            return false;
        }else {
            for (int p = 0; p < passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c>33 && c<=46 ||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }

    }
    private void anhxa() {
        edtUsername = findViewById(R.id.editTextRegUsername);
        edtEmail = findViewById(R.id.editTextRegEmail);
        edtPassword = findViewById(R.id.editTextRegPassword);
        edtConfirmpassword = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.textViewNewExistingUser);
    }
}