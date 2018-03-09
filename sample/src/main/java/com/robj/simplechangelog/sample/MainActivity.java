package com.robj.simplechangelog.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;

import com.robj.simplechangelog.ui.ChangelogPrefs;
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
                showChangelog(0, false);
            }
        });

        findViewById(R.id.btn_show_themed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangelog(R.style.CustomChangelogTheme, false);
            }
        });

        findViewById(R.id.btn_show_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangelog(0, true);
            }
        });
    }

    private void showChangelog(@StyleRes int styleRes, boolean notification) {
        ChangelogBuilder builder = new ChangelogBuilder()
                .setTitle(BuildConfig.VERSION_NAME)
                .addLineItem(getString(R.string.cl_line1))
                .addLineItem(Html.fromHtml(getString(R.string.cl_line2)))
                .addMinSdkVersionLineItem(Build.VERSION_CODES.O, getString(R.string.cl_oreo))
                .addMaxSdkVersionLineItem(Build.VERSION_CODES.N, getString(R.string.cl_nougat))
                .addSdkVersionRangeLineItem(Build.VERSION_CODES.O, Build.VERSION_CODES.O_MR1, getString(R.string.cl_sdk_range));

        if (notification) {
            // Reset values so notification is shown (don't do this in your apps)
            ChangelogPrefs.setChangelogNotifShown(this, 1);
            ChangelogPrefs.setChangelogShown(this, 1);

            ChangelogUtil.showChangelogNotifIfRequired(this,
                    builder.build(),
                    "updated",
                    R.mipmap.ic_launcher,
                    getString(R.string.cl_notification_title),
                    getString(R.string.cl_notification_description),
                    styleRes);
        } else {
            ChangelogUtil.showChangelog(this, builder.build(), styleRes);
        }
    }
}
