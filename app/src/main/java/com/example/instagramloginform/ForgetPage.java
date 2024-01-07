package com.example.instagramloginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ForgetPage extends AppCompatActivity implements View.OnClickListener{
Button b1;
EditText t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_page);
        b1 = findViewById(R.id.button);
        t1=findViewById(R.id.editTextText);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if(v.getId()==R.id.button){
    Log.d("orp",t1.getText().toString());
 if(t1.getText().toString()==null){
     Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
 }
 else{
performApiCall();
 }
 }
    }
    private void performApiCall() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                URL url = new URL("https://instgram-app-backend.onrender.com/ForgetPassword");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setDoOutput(true);

                    JSONObject postData = new JSONObject();
                    postData.put("Email", t1.getText().toString());

                    try (OutputStream os = urlConnection.getOutputStream()) {
                        byte[] input = postData.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }

                        runOnUiThread(() -> {
                            Toast.makeText(ForgetPage.this, "API Response: " + stringBuilder.toString(), Toast.LENGTH_SHORT).show();

                            boolean b = stringBuilder.toString().trim().contains("Otp Sent Successfully");
                            Log.d("boolean", String.valueOf(b));

                            if (b) {
                                SharedPreferences pre = getSharedPreferences("Email",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pre.edit();
                                editor.putString("email",t1.getText().toString());
                                editor.commit();
                                Intent intent = new Intent(ForgetPage.this, OtpPage.class);
                                startActivity(intent);
                            }
                        });

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MainActivity", e.toString());
                runOnUiThread(() -> {
                    Toast.makeText(ForgetPage.this, "API call failed", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}