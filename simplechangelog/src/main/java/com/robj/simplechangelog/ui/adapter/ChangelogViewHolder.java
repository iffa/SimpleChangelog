package com.robj.simplechangelog.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.robj.simplechangelog.R;

/**
 * @author Rob J
 * @author Santeri Elo
 */
class ChangelogViewHolder extends BaseViewHolder {
    private final TextView text;

    ChangelogViewHolder(View itemView) {
        super(itemView);

        text = itemView.findViewById(R.id.changelog_text);
    }

    void bind(ChangelogItem item) {
        text.setText(item.text);
    }
}
