package com.wc.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wc.customview.view.button.ButtonActivity;
import com.wc.customview.view.check_box.CheckBoxActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button checkBox;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (Button) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(this);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBox:
                startActivity(new Intent().setClass(MainActivity.this, CheckBoxActivity.class));
                break;
            case R.id.button:
                startActivity(new Intent().setClass(MainActivity.this, ButtonActivity.class));
                break;
        }
    }
}
