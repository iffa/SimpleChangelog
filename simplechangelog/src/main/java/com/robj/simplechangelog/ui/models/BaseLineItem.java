package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;

/**
 * @author Santeri Elo
 */
public abstract class BaseLineItem implements Parcelable {
    @Nullable
    private final CharSequence line;

    @StringRes
    private final int lineRes;

    BaseLineItem(@NonNull CharSequence line) {
        this(line, 0);
    }

    BaseLineItem(@StringRes int lineRes) {
        this(null, lineRes);
    }

    private BaseLineItem(@Nullable CharSequence line, @StringRes int lineRes) {
        this.line = line;
        this.lineRes = lineRes;
    }

    BaseLineItem(Parcel in) {
        line = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        lineRes = in.readInt();
    }

    @CallSuper
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(line, dest, flags);
        dest.writeInt(lineRes);
    }

    @Nullable
    public CharSequence getLine() {
        return line;
    }

    @StringRes
    public int getLineRes() {
        return lineRes;
    }
}
