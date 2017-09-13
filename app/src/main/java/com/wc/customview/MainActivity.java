package com.wc.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wc.customview.view.CheckMaterial;

public class MainActivity extends AppCompatActivity {

    private CheckMaterial checkMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkMaterial = (CheckMaterial) findViewById(R.id.check);

        checkMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMaterial.toggle();
            }
        });
        
        /*checkBoxSample1 = (CheckBox)findViewById(R.id.check);
 
        checkBoxSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxSample1.toggle();
            }
        });*/
    }
}
