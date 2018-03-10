package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class Changelog implements Parcelable {
    @StringRes
    private final int title;

    @StringRes
    private final int subtitle;

    private final List<LineItem> lines;

    Changelog(@StringRes int title, @StringRes int subtitle, List<LineItem> lines) {
        this.title = title;
        this.subtitle = subtitle;
        this.lines = new ArrayList<>();
        this.lines.addAll(lines);
    }

    private Changelog(Parcel in) {
        title = in.readInt();
        subtitle = in.readInt();
        lines = in.createTypedArrayList(LineItem.CREATOR);
    }

    public static final Creator<Changelog> CREATOR = new Creator<Changelog>() {
        @Override
        public Changelog createFromParcel(Parcel in) {
            return new Changelog(in);
        }

        @Override
        public Changelog[] newArray(int size) {
            return new Changelog[size];
        }
    };

    @StringRes
    public int getTitle() {
        return title;
    }

    @StringRes
    public int getSubtitle() {
        return subtitle;
    }

    public List<LineItem> getLineItems() {
        return lines;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(subtitle);
        dest.writeTypedList(lines);
    }
}
