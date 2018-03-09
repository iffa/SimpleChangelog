package com.robj.simplechangelog.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.robj.radicallyreusable.base.base_list.BaseListRecyclerAdapter;
import com.robj.radicallyreusable.base.mvp.BaseViewHolder;
import com.robj.simplechangelog.R;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogAdapter extends BaseListRecyclerAdapter<Object, BaseViewHolder> {
    private final static int TYPE_TITLE = 0;
    private final static int TYPE_CONTENT = 1;

    public ChangelogAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int type) {
        switch (type) {
            case TYPE_TITLE:
                return createTitleViewHolder(parent);
            case TYPE_CONTENT:
                return createContentViewHolder(parent);
            default:
                return null;
        }
    }

    private BaseViewHolder createContentViewHolder(ViewGroup parent) {
        return new ChangelogViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.cl_row_changelog_content, parent, false));
    }

    private BaseViewHolder createTitleViewHolder(ViewGroup parent) {
        return new ChangelogTitleViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.cl_row_changelog_title, parent, false));
    }

    @Override
    protected void onBindViewHolder(BaseViewHolder viewHolder, int pos, int type) {
        switch (type) {
            case TYPE_TITLE:
                ChangelogTitleViewHolder titleViewHolder = (ChangelogTitleViewHolder) viewHolder;
                titleViewHolder.bind((ChangelogTitle) getItemAtPosition(pos));
                break;
            case TYPE_CONTENT:
                ChangelogViewHolder changelogViewHolder = (ChangelogViewHolder) viewHolder;
                changelogViewHolder.bind((ChangelogItem) getItemAtPosition(pos));
                break;
            default:
                break;
        }
    }

    @Override
    protected int getViewType(int position) {
        Object o = getItemAtPosition(position);
        if (o instanceof ChangelogTitle)
            return TYPE_TITLE;
        else if (o instanceof ChangelogItem)
            return TYPE_CONTENT;
        else
            return super.getViewType(position);
    }
}
