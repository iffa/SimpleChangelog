package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class LineItem extends BaseLineItem {
    private int minSdkVersion, maxSdkVersion;

    LineItem(@NonNull CharSequence line) {
        super(line);
    }

    LineItem(int lineRes) {
        super(lineRes);
    }

    private LineItem(Parcel in) {
        super(in);
        minSdkVersion = in.readInt();
        maxSdkVersion = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(minSdkVersion);
        dest.writeInt(maxSdkVersion);
    }

    static final Creator<LineItem> CREATOR = new Creator<LineItem>() {
        @Override
        public LineItem createFromParcel(Parcel in) {
            return new LineItem(in);
        }

        @Override
        public LineItem[] newArray(int size) {
            return new LineItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getMinSdkVersion() {
        return minSdkVersion;
    }

    public int getMaxSdkVersion() {
        return maxSdkVersion;
    }

    void setMinSdkVersion(int minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    void setMaxSdkVersion(int maxSdkVersion) {
        this.maxSdkVersion = maxSdkVersion;
    }
}
