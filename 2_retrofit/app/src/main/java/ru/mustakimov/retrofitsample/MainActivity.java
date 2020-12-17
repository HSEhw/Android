package ru.mustakimov.retrofitsample;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        type = "ip";

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.getDATE:
                type = "date";
                this.getInfo();
                break;
            case R.id.getIP:
                type = "ip";
                this.getInfo();
                break;
        }
    }
        protected void getInfo() {
        App.getApi().getData(type).enqueue(new Callback<PostModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                PostModel info = response.body();
                if (type.equals("ip")) {
                    textView.setText("IP: " + info.getIp());
                } else {
                    textView.setText("DATE: " + info.getDate() + "\nMilliseconds: " + info.getMilliSeconds() + "\nTIME: " + info.getTime());
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something bad happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
