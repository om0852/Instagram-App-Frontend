package com.example.instagramloginform;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextText2);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    Log.d("Signup","successfully");
if(v.getId()==R.id.button2){
    Thread thread = new Thread(){
        @Override
        public void run() {
            try{

            }
            catch(Exception e){
                Log.d("Signup",e.toString());
                e.printStackTrace();
            }
            finally {
                Log.d("Signup","successfully");

                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);

            }
        }
    };
    thread.start();
}
else{

        performApiCall();
}

        // Perform the API call when the button is clicked

    }

    private void performApiCall() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Replace "your_ip_address" with the actual IP address of your development machine
                URL url = new URL("https://instgram-app-backend.onrender.com/data");
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
                            // Display the API response in a toast
//                            Toast.makeText(MainActivity.this, "API Response: " + stringBuilder.toString(), Toast.LENGTH_SHORT).show();

//                        });
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "API Response: " + stringBuilder.toString(), Toast.LENGTH_SHORT).show();

                            boolean b = stringBuilder.toString().trim().contains("LoginSuccessfully");
                            Log.d("boolean", String.valueOf(b));

                            // Perform other UI-related actions here if needed
                            // For example, navigate to another activity
                            if (b) {
                                Intent intent = new Intent(MainActivity.this, HomePage.class);
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
                    Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }


}
