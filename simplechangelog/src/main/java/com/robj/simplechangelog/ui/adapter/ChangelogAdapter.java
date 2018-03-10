package com.robj.simplechangelog.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robj.simplechangelog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final static int TYPE_TITLE = 0;
    private final static int TYPE_CONTENT = 1;
    private final static int TYPE_FOOTER = 2;
    private final Context context;
    private final List<Object> items = new ArrayList<>();

    public ChangelogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return createTitleViewHolder(parent);
            case TYPE_CONTENT:
                return createContentViewHolder(parent);
            case TYPE_FOOTER:
                return createFooterViewHolder(parent);
            default:
                throw new NullPointerException("No view holder for view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                ChangelogTitleViewHolder titleViewHolder = (ChangelogTitleViewHolder) holder;
                titleViewHolder.bind((ChangelogTitle) items.get(position));
                break;
            case TYPE_CONTENT:
                ChangelogViewHolder changelogViewHolder = (ChangelogViewHolder) holder;
                changelogViewHolder.bind((ChangelogItem) items.get(position));
                break;
            case TYPE_FOOTER:
                ChangelogFooterViewHolder changelogFooterViewHolder = (ChangelogFooterViewHolder) holder;
                changelogFooterViewHolder.bind((ChangelogFooter) items.get(position));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof ChangelogTitle)
            return TYPE_TITLE;
        else if (item instanceof ChangelogItem)
            return TYPE_CONTENT;
        else if (item instanceof ChangelogFooter)
            return TYPE_FOOTER;
        else
            return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private BaseViewHolder createContentViewHolder(ViewGroup parent) {
        return new ChangelogViewHolder(inflate(R.layout.cl_row_changelog_content, parent));
    }

    private BaseViewHolder createTitleViewHolder(ViewGroup parent) {
        return new ChangelogTitleViewHolder(inflate(R.layout.cl_row_changelog_title, parent));
    }

    private BaseViewHolder createFooterViewHolder(ViewGroup parent) {
        return new ChangelogFooterViewHolder(inflate(R.layout.cl_row_changelog_footer, parent));
    }

    private View inflate(int layoutResId, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutResId, parent, false);
    }

    public void addAll(List<Object> viewModels) {
        items.clear();
        items.addAll(viewModels);
    }
}
