package io.agora.tutorials1v1vcall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onResearch(View view){
        Intent intent = new Intent(this,VideoChatViewActivity.class);
        startActivity(intent);
    }

    public void onView(View view){
        Intent intent = new Intent(this,VideoListActivity.class);
        startActivity(intent);
    }
}
