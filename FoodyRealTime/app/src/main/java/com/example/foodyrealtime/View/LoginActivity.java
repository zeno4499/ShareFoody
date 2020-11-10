package com.example.foodyrealtime.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodyrealtime.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {

    private Button btnSignGG;
    private Button btnLogin;
    private EditText edEmail, edPasswword;
    private TextView txtForgetpassword;
    private TextView txtRegister;
    GoogleSignInClient mGoogleSignInClient;
    public static int REQUEST_CODE_GG = 99;
    public static int CHECK_AUTH_PROVIDER_SIGN = 0;
    FirebaseAuth firebaseAuth;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.edtEmail);
        edPasswword = findViewById(R.id.edtPassword);
        btnSignGG = findViewById(R.id.btnLoginGG);
        btnLogin = findViewById(R.id.btnDangnhap);
        txtForgetpassword = findViewById(R.id.txtForgetPassword);
        txtRegister = findViewById(R.id.txtRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        btnSignGG.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
        txtForgetpassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("luudangnhap", MODE_PRIVATE);
        CreateClientGG();

    }

    /**
     * tạo client đăng nhập gg
     */
    private void CreateClientGG() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }
    //end tạo client đăng nhập gg

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.addAuthStateListener(this);
    }

    /**
     * mở form đăng nhập gg
     *
     * @param mGoogleSignInClient
     */
    private void LoginGG(GoogleSignInClient mGoogleSignInClient) {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQUEST_CODE_GG);
    }
    //end mở form đăng nhập gg

    /**
     * lấy tokenID đã đăng nhập gg  để đăng nhập trên firebase
     *
     * @param tokenID: lấy tokenID đã đăng nhập gg  để đăng nhập trên firebase
     */
    private void ChungThucDangNhap(String tokenID) {
        if (CHECK_AUTH_PROVIDER_SIGN == 0) { // check login with gg
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }
    //end lấy tokenID


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GG) {
            if (resultCode == RESULT_OK) {
                //get result login gg
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                //get account
                GoogleSignInAccount account = signInResult.getSignInAccount();
                //get tokenid
                String tokenID = account.getIdToken();
                ChungThucDangNhap(tokenID);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * listen event click to button login gg  and email
     *
     * @param v : view
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLoginGG: {
                LoginGG(mGoogleSignInClient);
                break;
            }
            case R.id.txtRegister: {
                Intent iRegister = new Intent(this, RegisterActivity.class);
                startActivity(iRegister);
                break;
            }
            case R.id.btnDangnhap: {
                dangnhap();
                break;
            }
            case R.id.txtForgetPassword: {
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            }
        }
    }//end listen event click to button login gg  and email

    private void dangnhap() {
        String email = edEmail.getText().toString();
        String password = edPasswword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.dangnhapthatbai), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * event check user Login is Successfull or logOut
     *
     * @param firebaseAuth
     */
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mauser", user.getUid());
            editor.commit();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
//            Toast.makeText(LoginActivity.this, "LOGIN FAIL!", Toast.LENGTH_LONG).show();
        }
    }
    //end event check user Login is Successfull or logOut
}