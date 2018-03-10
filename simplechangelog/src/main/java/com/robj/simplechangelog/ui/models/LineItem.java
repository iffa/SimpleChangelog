package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class LineItem implements Parcelable {
    @Nullable
    private final CharSequence line;

    @StringRes
    private final int lineRes;

    private int minSdkVersion, maxSdkVersion;

    LineItem(@NonNull CharSequence line) {
        this(line, 0);
    }

    LineItem(@StringRes int lineRes) {
        this(null, lineRes);
    }

    private LineItem(@Nullable CharSequence line, @StringRes int lineRes) {
        this.line = line;
        this.lineRes = lineRes;
    }

    private LineItem(Parcel in) {
        line = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        lineRes = in.readInt();
        minSdkVersion = in.readInt();
        maxSdkVersion = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(line, dest, flags);
        dest.writeInt(lineRes);
        dest.writeInt(minSdkVersion);
        dest.writeInt(maxSdkVersion);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Nullable
    public CharSequence getLine() {
        return line;
    }

    @StringRes
    public int getLineRes() {
        return lineRes;
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
