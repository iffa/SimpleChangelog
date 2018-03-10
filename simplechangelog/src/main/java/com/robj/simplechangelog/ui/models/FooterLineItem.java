package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * @author Santeri Elo
 */
public class FooterLineItem extends BaseLineItem {
    FooterLineItem(@NonNull CharSequence line) {
        super(line);
    }

    FooterLineItem(int lineRes) {
        super(lineRes);
    }

    static final Creator<FooterLineItem> CREATOR = new Creator<FooterLineItem>() {
        @Override
        public FooterLineItem createFromParcel(Parcel in) {
            return new FooterLineItem(in);
        }

        @Override
        public FooterLineItem[] newArray(int size) {
            return new FooterLineItem[size];
        }
    };

    private FooterLineItem(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
