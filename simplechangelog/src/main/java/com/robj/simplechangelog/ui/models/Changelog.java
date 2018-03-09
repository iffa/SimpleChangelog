package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 */
public class Changelog implements Parcelable {
    private final int versionCode;
    private final String title;
    private final List<LineItem> lines;

    Changelog(int versionCode, String title, List<LineItem> lines) {
        this.versionCode = versionCode;
        this.title = title;
        this.lines = new ArrayList<>();
        this.lines.addAll(lines);
    }

    protected Changelog(Parcel in) {
        versionCode = in.readInt();
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

    public int getVersionCode() {
        return versionCode;
    }

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
        dest.writeInt(versionCode);
        dest.writeString(title);
        dest.writeTypedList(lines);
    }
}
