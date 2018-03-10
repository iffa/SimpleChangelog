package com.robj.simplechangelog.ui.adapter;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.robj.simplechangelog.R;

/**
 * @author Santeri Elo
 */
class ChangelogFooterViewHolder extends BaseViewHolder {
    private final TextView text;

    ChangelogFooterViewHolder(View itemView) {
        super(itemView);

        text = itemView.findViewById(R.id.changelog_text);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void bind(ChangelogFooter item) {
        text.setText(item.text);
    }
}
