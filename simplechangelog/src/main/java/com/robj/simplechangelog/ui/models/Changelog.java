package com.robj.simplechangelog.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class Changelog implements Parcelable {
    @StringRes
    private final int titleRes, subtitleRes;

    @Nullable
    private final String title, subtitle;

    private final List<LineItem> lines;

    private final List<FooterLineItem> footerLines;

    @ArrayRes
    private final int stringArrayLine;

    Changelog(@StringRes int titleRes, @Nullable String title, @StringRes int subtitleRes,
              @Nullable String subtitle, List<LineItem> lines, List<FooterLineItem> footerLines,
              @ArrayRes int stringArrayLine) {
        this.titleRes = titleRes;
        this.subtitleRes = subtitleRes;
        this.title = title;
        this.subtitle = subtitle;
        this.lines = lines;
        this.footerLines = footerLines;
        this.stringArrayLine = stringArrayLine;
    }

    private Changelog(Parcel in) {
        titleRes = in.readInt();
        subtitleRes = in.readInt();
        title = in.readString();
        subtitle = in.readString();
        lines = in.createTypedArrayList(LineItem.CREATOR);
        footerLines = in.createTypedArrayList(FooterLineItem.CREATOR);
        stringArrayLine = in.readInt();
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
    public int getTitleRes() {
        return titleRes;
    }

    @StringRes
    public int getSubtitleRes() {
        return subtitleRes;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    public List<LineItem> getLineItems() {
        return lines;
    }

    public List<FooterLineItem> getFooterLineItems() {
        return footerLines;
    }

    @ArrayRes
    public int getLinesArray() {
        return stringArrayLine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(titleRes);
        dest.writeInt(subtitleRes);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeTypedList(lines);
        dest.writeTypedList(footerLines);
        dest.writeInt(stringArrayLine);
    }
}
