package com.robj.simplechangelog.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Santeri Elo
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected Context getContext() {
        return itemView.getContext();
    }
}
