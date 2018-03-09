package com.robj.simplechangelog.ui.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.robj.radicallyreusable.base.mvp.fragment.BaseMvpPresenter;
import com.robj.simplechangelog.R;
import com.robj.simplechangelog.ui.ChangelogPrefs;
import com.robj.simplechangelog.ui.adapter.ChangelogItem;
import com.robj.simplechangelog.ui.adapter.ChangelogTitle;
import com.robj.simplechangelog.ui.models.Changelog;
import com.robj.simplechangelog.ui.models.LineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
class ChangelogPresenter extends BaseMvpPresenter<ChangelogView> {
    void loadChangelog(Context context, Bundle bundle) {
        List viewModels = new ArrayList<>();
        int currentVersionCode;
        try {
            currentVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            getView().showError(R.string.cl_error_generic);
            return;
        }

        if (bundle == null || bundle.isEmpty() || !bundle.containsKey(ChangelogDialogFragment.CHANGELOG)) {
            getView().showError(R.string.cl_error_no_changelog);
            return;
        }

        Changelog changelog = bundle.getParcelable(ChangelogDialogFragment.CHANGELOG);

        viewModels.add(new ChangelogTitle(changelog.getTitle()));

        for (LineItem line : changelog.getLineItems())
            if ((line.getMinSdkVersion() == 0 && line.getMaxSdkVersion() == 0)
                    || ((line.getMinSdkVersion() != 0 && line.getMinSdkVersion() <= Build.VERSION.SDK_INT) && (line.getMaxSdkVersion() == 0 || line.getMaxSdkVersion() >= Build.VERSION.SDK_INT))
                    || (line.getMaxSdkVersion() >= Build.VERSION.SDK_INT && (line.getMinSdkVersion() == 0 || line.getMinSdkVersion() <= Build.VERSION.SDK_INT))) {
                viewModels.add(new ChangelogItem(line.getLine()));
            }

        ChangelogPrefs.setChangelogShown(context, currentVersionCode);

        getView().addResults(viewModels);
        getView().hideProgress();
    }

}
