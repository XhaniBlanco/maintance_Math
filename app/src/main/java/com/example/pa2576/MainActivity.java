    package com.example.pa2576;

    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.android.volley.AuthFailureError;
    import com.android.volley.DefaultRetryPolicy;
    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin;
    EditText password;
    SharedPreferences sharedPreferences;

    CheckBox loginState;

    ArrayList<String> searchTagsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        usernameLogin = findViewById(R.id.userNameLogin);
        password = findViewById(R.id.password);

        final Button signINBtn =  findViewById(R.id.signInBtn);
        Button createAccount = findViewById(R.id.createAccountBtn);
        Button forgotPass = findViewById(R.id.forgotbtn);
        loginState = findViewById(R.id.loginStateBox);



        signINBtn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        forgotPass.setOnClickListener(this);






        searchMethod();
        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if (loginStatus.equals("Loggedin")){
            startActivity(new Intent(MainActivity.this, Homepage.class));
        }
    }

        private void searchMethod() {

        }

        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInBtn:
                String txtUsername = usernameLogin.getText().toString();
                String txtPassword = password.getText().toString();
                if(txtUsername.isEmpty() || txtPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All field Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    login(txtUsername,txtPassword);
                }
                break;
            case R.id.createAccountBtn:
                openCreateAccount();
                break;
            case R.id.forgotbtn:
                openForgot();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }






    public void openCreateAccount() {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }


    public void openForgot() {
        Intent intent = new Intent(this, Forgot.class);
        startActivity(intent);
    }

        //Input value username and password into database and checks if there is a user with that Username and has that password
    private void login(final String username,final String password){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Welcome");
        progressDialog.show();
        String url = "http://192.168.1.112/login.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Login Success")) {
                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (loginState.isChecked()) {
                        editor.putString(getResources().getString(R.string.prefLoginState), "Loggedin");
                    } else {
                        editor.putString(getResources().getString(R.string.prefLoginState), "Loggedout");
                    }
                    startActivity(new Intent(MainActivity.this, Homepage.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username",username);
                param.put("password",password);
                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(request);
    }
    }


