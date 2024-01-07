package com.example.instagramloginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
Button b1,b2;
EditText etUsername,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        b1=findViewById(R.id.button);
        etUsername = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextText2);
        b2=findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }
    public void onClick( View v){
        if(v.getId()==R.id.button){
            performApiCall();
        }
        else{
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }
    private void performApiCall() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Replace "your_ip_address" with the actual IP address of your development machine
                URL url = new URL("https://instgram-app-backend.onrender.com/create");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    // Set the request method to POST
                    urlConnection.setRequestMethod("POST");
                    // Set the content type to JSON
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    // Enable input/output streams
                    urlConnection.setDoOutput(true);

                    // Create a JSON object with the data to be sent
                    JSONObject postData = new JSONObject();
                    postData.put("Email", etUsername.getText().toString());
                    postData.put("Password", etPassword.getText().toString());

                    // Write the JSON data to the output stream
                    try (OutputStream os = urlConnection.getOutputStream()) {
                        byte[] input = postData.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    // Get the response from the server
                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }

                        // Update UI on the main thread with the result
                        runOnUiThread(() -> {
                            // Display the API response in a toast
                            Toast.makeText(SignUpActivity.this, "API Response: " + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                            if(stringBuilder.toString().toLowerCase().contains("account already exist")){

                            }
                            else{

                            Thread thread = new Thread(){
                                @Override
                                public void run() {
                                    try{
                                        sleep(2000);

                                    }
                                    catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    finally {
                                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            };thread.start();

                            }
                        });
                    }
                    catch(Exception e){

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
                    Toast.makeText(SignUpActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                });
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}