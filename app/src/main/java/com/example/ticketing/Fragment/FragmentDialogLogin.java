package com.example.ticketing.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ticketing.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class FragmentDialogLogin extends DialogFragment {

    private OnLoginSignupSuccess onLoginSignupSuccess;

    View view;
    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    Button btnSignup;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        view = LayoutInflater.from(getContext()).inflate(R.layout.login_dialog, null);

        builder.setView(view);

        setupViews();

        return builder.create();
    }

    private void setupViews() {

        btnLogin = (Button) view.findViewById(R.id.btn_loginFragment_login);
        btnSignup = (Button) view.findViewById(R.id.btn_loginFragment_signup);
        edtEmail = (EditText) view.findViewById(R.id.edt_loginFragment_email);
        edtPassword = (EditText) view.findViewById(R.id.edt_loginFragment_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void login() {

        String url = "https://7e69edce-af20-4c49-9ad9-5697df4e8a20.mock.pstmn.io";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                onLoginSignupSuccess.onSuccess(response);
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("email", edtEmail.getText().toString());
                params.put("password", edtPassword.getText().toString());

                return params;
            }
        };

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void signup() {

        String url = "https://7e69edce-af20-4c49-9ad9-5697df4e8a20.mock.pstmn.io";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                onLoginSignupSuccess.onSuccess(response);
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", edtEmail.getText().toString());
                params.put("password", edtPassword.getText().toString());

                return params;
            }
        };

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public interface OnLoginSignupSuccess {
        void onSuccess(JSONArray response);
    }

    public void setOnLoginSignupSuccess(OnLoginSignupSuccess onLoginSignupSuccess) {
        this.onLoginSignupSuccess = onLoginSignupSuccess;
    }
}
