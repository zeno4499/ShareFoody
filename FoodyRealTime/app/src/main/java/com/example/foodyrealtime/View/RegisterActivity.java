package com.example.foodyrealtime.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    FirebaseAuth firebaseAuth;
    EditText edEmailDK, edPasswwordDK, edNhaplaiPasswordDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnDangki);
        edEmailDK = findViewById(R.id.edtEmailDK);
        edPasswwordDK = findViewById(R.id.edtPasswordDK);
        edNhaplaiPasswordDK = findViewById(R.id.edtNhaplaiMK);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String email = edEmailDK.getText().toString();
        String matkhau = edPasswwordDK.getText().toString();
        String nhaplaimatkhau = edNhaplaiPasswordDK.getText().toString();
        if (email.trim().length() == 0) {
            Toast.makeText(this, getString(R.string.thongbaoloiEmailDK), Toast.LENGTH_SHORT).show();
        } else if (matkhau.trim().length() == 0) {
            Toast.makeText(this, getString(R.string.thongbaoloimatkhauDK), Toast.LENGTH_SHORT).show();
        } else if (!nhaplaimatkhau.equals(matkhau)) {
            Toast.makeText(this, getString(R.string.thongbaonhaplaiMK), Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, getString(R.string.thongbaoDKthanhcong), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}