package com.robj.simplechangelog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.robj.radicallyreusable.base.base_list.BaseListFragment;
import com.robj.simplechangelog.R;
import com.robj.simplechangelog.ui.adapter.ChangelogAdapter;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogDialogFragment extends BaseListFragment<ChangelogView, ChangelogPresenter, ChangelogAdapter, Object> implements ChangelogView {

    public static final String CHANGELOG = ChangelogPresenter.CHANGELOG;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadChangelog(getActivity(), getArguments());

        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.cl_fragment_changelog;
    }

    @Override
    public ChangelogPresenter createPresenter() {
        return new ChangelogPresenter();
    }

    @Override
    protected String getSearchString() {
        return getString(R.string.cl_progress_changelog);
    }

    @Override
    protected ChangelogAdapter createAdapter() {
        return new ChangelogAdapter(getActivity());
    }

    @Override
    public void onRefresh() {

    }
}
