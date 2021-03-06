package com.robj.simplechangelog.ui.fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robj.simplechangelog.R;
import com.robj.simplechangelog.ui.ChangelogActivity;
import com.robj.simplechangelog.ui.ChangelogPrefs;
import com.robj.simplechangelog.ui.adapter.ChangelogAdapter;
import com.robj.simplechangelog.ui.adapter.ChangelogFooter;
import com.robj.simplechangelog.ui.adapter.ChangelogItem;
import com.robj.simplechangelog.ui.adapter.ChangelogTitle;
import com.robj.simplechangelog.ui.models.Changelog;
import com.robj.simplechangelog.ui.models.FooterLineItem;
import com.robj.simplechangelog.ui.models.LineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogDialogFragment extends Fragment {
    private ChangelogAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cl_fragment_changelog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });

        recyclerView = view.findViewById(R.id.list);
        setupRecyclerView();

        loadChangelog(getArguments());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new ChangelogAdapter(getContext()));

    }

    private void loadChangelog(Bundle bundle) {
        List<Object> viewModels = new ArrayList<>();

        int currentVersionCode;
        try {
            // getContext() does not return null in this instance
            //noinspection ConstantConditions
            currentVersionCode = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }

        if (bundle == null || bundle.isEmpty() || !bundle.containsKey(ChangelogActivity.CHANGELOG)) {
            throw new UnsupportedOperationException("Cannot show empty changelog!");
        }

        Changelog changelog = bundle.getParcelable(ChangelogActivity.CHANGELOG);
        assert changelog != null;

        // Set given title or use default
        if (changelog.getTitle() == null) {
            //noinspection ConstantConditions
            getActivity().setTitle(changelog.getTitleRes() != 0
                    ? changelog.getTitleRes() : R.string.cl_changelog_title);
        } else {
            //noinspection ConstantConditions
            getActivity().setTitle(changelog.getTitle());
        }

        ChangelogTitle subtitle;

        if (changelog.getSubtitleRes() == 0 && changelog.getSubtitle() == null) {
            // If no subtitle is set, default to version name
            try {
                String versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
                subtitle = new ChangelogTitle(versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return;
            }
        } else if (changelog.getSubtitleRes() != 0) {
            subtitle = new ChangelogTitle(getString(changelog.getSubtitleRes()));
        } else {
            subtitle = new ChangelogTitle(changelog.getSubtitle());
        }

        viewModels.add(subtitle);

        if (changelog.getLinesArray() != 0) {
            String[] lines = getResources().getStringArray(changelog.getLinesArray());
            for (String line : lines) {
                viewModels.add(new ChangelogItem(line));
            }
        }

        for (LineItem line : changelog.getLineItems()) {
            if ((line.getMinSdkVersion() == 0 && line.getMaxSdkVersion() == 0)
                    || ((line.getMinSdkVersion() != 0 && line.getMinSdkVersion() <= Build.VERSION.SDK_INT) && (line.getMaxSdkVersion() == 0 || line.getMaxSdkVersion() >= Build.VERSION.SDK_INT))
                    || (line.getMaxSdkVersion() >= Build.VERSION.SDK_INT && (line.getMinSdkVersion() == 0 || line.getMinSdkVersion() <= Build.VERSION.SDK_INT))) {
                viewModels.add(new ChangelogItem(line.getLine() != null ? line.getLine() : getString(line.getLineRes())));
            }
        }

        for (FooterLineItem line : changelog.getFooterLineItems()) {
            viewModels.add(new ChangelogFooter(line.getLine() != null ? line.getLine() : getString(line.getLineRes())));
        }

        ChangelogPrefs.setChangelogShown(getContext(), currentVersionCode);

        adapter.addAll(viewModels);
    }
}
