package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * @author Rob J
 */
public class LineItem implements Parcelable {
    private final CharSequence line;
    private int minSdkVersion, maxSdkVersion;

    public LineItem(CharSequence line) {
        this.line = line;
    }

    protected LineItem(Parcel in) {
        line = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        minSdkVersion = in.readInt();
        maxSdkVersion = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(line, dest, flags);
        dest.writeInt(minSdkVersion);
        dest.writeInt(maxSdkVersion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LineItem> CREATOR = new Creator<LineItem>() {
        @Override
        public LineItem createFromParcel(Parcel in) {
            return new LineItem(in);
        }

        @Override
        public LineItem[] newArray(int size) {
            return new LineItem[size];
        }
    };

    public CharSequence getLine() {
        return line;
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
