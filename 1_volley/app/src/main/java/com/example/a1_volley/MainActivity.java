package com.example.a1_volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

class MyDialogFragment extends AppCompatDialogFragment {}

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_INTERNET = 1;
    String   url = "http://date.jsontest.com";
    TextView text;
    TextView internet_permission;
    Button   button;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text =                (TextView) findViewById(R.id.text);
        internet_permission = (TextView) findViewById(R.id.internet_permission);
        button =              (Button) findViewById(R.id.button);
        permissionCheck();
    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View view)
    {
        this.getData();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    text.setText("date: " + response.getString("date")
                                + "\n" +
                                "time: " + response.getString("time"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText("\toops\n\tError:\n" + error);
            }
        });
        queue.add(jsObjRequest);
    }

    @SuppressLint("SetTextI18n")
    private void permissionCheck() {
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_PERMISSION_INTERNET);
        } else {
            internet_permission.setText("Internet permission: done");
        }
    }

}
