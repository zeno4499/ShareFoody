package com.example.foodyrealtime.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDangkiKP;
    private Button btnSendMail;
    private EditText edEmailKP;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgetpassword);
        edEmailKP = findViewById(R.id.edtEmailKP);
        btnSendMail = findViewById(R.id.btnSendEmail);
        txtDangkiKP = findViewById(R.id.txtDangkiKP);
        firebaseAuth = FirebaseAuth.getInstance();
        btnSendMail.setOnClickListener(this);
        txtDangkiKP.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSendEmail: {
                String email = edEmailKP.getText().toString();
                boolean kiemtraemail = kiemtraEmail(email);
                if (kiemtraemail) {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetPasswordActivity.this, getString(R.string.guiMKquaEmail), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, getString(R.string.Emailkhonghople), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.txtDangkiKP: {
                Intent intent = new Intent(ForgetPasswordActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private boolean kiemtraEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
