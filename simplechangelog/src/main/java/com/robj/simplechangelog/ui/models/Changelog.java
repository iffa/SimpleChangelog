package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 */
public class Changelog implements Parcelable {
    private final String title;
    private final List<LineItem> lines;

    Changelog(String title, List<LineItem> lines) {
        this.title = title;
        this.lines = new ArrayList<>();
        this.lines.addAll(lines);
    }

    private Changelog(Parcel in) {
        title = in.readString();
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

    public String getTitle() {
        return title;
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
        dest.writeString(title);
        dest.writeTypedList(lines);
    }
}
