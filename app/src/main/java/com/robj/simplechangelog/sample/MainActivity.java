package com.robj.simplechangelog.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.robj.simplechangelog.ui.ChangelogUtil;
import com.robj.simplechangelog.ui.models.ChangelogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangelog(0);
            }
        });

        findViewById(R.id.btn_show_themed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangelog(R.style.CustomChangelogTheme);
            }
        });
    }

    private void showChangelog(@StyleRes int styleRes) {
        ChangelogBuilder builder = new ChangelogBuilder()
                .setVersionCode(BuildConfig.VERSION_CODE)
                .setTitle(BuildConfig.VERSION_NAME);

        builder.addLineItem(getString(R.string.cl_line1));
        builder.addMinSdkVersionLineItem(Build.VERSION_CODES.O, getString(R.string.cl_oreo));
        builder.addMaxSdkVersionLineItem(Build.VERSION_CODES.N, getString(R.string.cl_nougat));
        builder.addSdkVersionRangeLineItem(Build.VERSION_CODES.O, Build.VERSION_CODES.O_MR1, getString(R.string.cl_sdk_range));

        ChangelogUtil.showChangelog(this, builder.build(), styleRes);
    }
}
