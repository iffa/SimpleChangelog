package com.robj.simplechangelog.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.robj.simplechangelog.R;
import com.robj.simplechangelog.ui.fragment.ChangelogDialogFragment;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogActivity extends AppCompatActivity {
    public static final String THEME = "theme";
    public static final String CHANGELOG = "changelog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getIntent() == null || getIntent().getExtras() == null) {
            throw new IllegalArgumentException("ChangelogActivity started without required extras");
        }

        setTheme(getIntent().getIntExtra(THEME, R.style.Theme_SimpleChangelog_DayNight_Dialog));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.cl_activity_dialog);

        Fragment fragment = Fragment.instantiate(this, ChangelogDialogFragment.class.getName(), getIntent().getExtras());
        pushFragment(fragment);

        // Cancel any notifications pointing to this activity
        NotificationUtils.cancelNotification(this);
    }

    private void pushFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        ft.commit();
    }

}
