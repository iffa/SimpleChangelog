package com.robj.simplechangelog.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.robj.radicallyreusable.base.mvp.BaseViewHolder;
import com.robj.simplechangelog.R;

/**
 * @author Rob J
 * @author Santeri Elo
 */
class ChangelogTitleViewHolder extends BaseViewHolder {
    private final TextView text;

    public ChangelogTitleViewHolder(View itemView) {
        super(itemView);

        text = itemView.findViewById(R.id.changelog_text);
    }

    public void bind(ChangelogTitle item) {
        text.setText(item.text);
    }
}
