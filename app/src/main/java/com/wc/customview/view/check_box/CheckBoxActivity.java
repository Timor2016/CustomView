package com.wc.customview.view.check_box;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wc.customview.R;

/**
 * 作者：wangchao
 * 时间：2017/9/15 0015 09:29
 * 描述：checkbox示例
 */
public class CheckBoxActivity extends AppCompatActivity {

    private CheckMaterial check_1;
    private CheckMaterial check_2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        check_1 = (CheckMaterial) findViewById(R.id.check_1);
        check_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_1.toggle();
                Log.e("check_1", check_1.isChecked() + "");
            }
        });

        check_2 = (CheckMaterial) findViewById(R.id.check_2);
        check_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_2.toggle();
                Log.e("check_2", check_2.isChecked() + "");
            }
        });
        check_2.setChecked(true);
        Log.e("check_1", check_1.isChecked() + "");
        Log.e("check_2", check_2.isChecked() + "");
    }

}
