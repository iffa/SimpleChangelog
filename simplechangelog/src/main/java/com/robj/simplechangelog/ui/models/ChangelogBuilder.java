package com.robj.simplechangelog.ui.models;

import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogBuilder {
    private String title;
    private List<LineItem> lines = new ArrayList<>();

    /**
     * Set a changelog title.
     *
     * @param title Title
     * @return Updated builder object
     */
    public ChangelogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Add a line item.
     *
     * @param line Line text
     * @return Updated builder object
     */
    public ChangelogBuilder addLineItem(CharSequence line) {
        this.lines.add(new LineItem(line));
        return this;
    }

    /**
     * Add a line item.
     *
     * @param line Line text (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addLineItem(@StringRes int line) {
        this.lines.add(new LineItem(line));
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is greater than or equal to
     * the specified minimum SDK version.
     *
     * @param minSdkVersion Minimum SDK version required for this line to be shown
     * @param line          Line text
     * @return Updated builder object
     */
    public ChangelogBuilder addMinSdkVersionLineItem(int minSdkVersion, String line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMinSdkVersion(minSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is greater than or equal to
     * the specified minimum SDK version.
     *
     * @param minSdkVersion Minimum SDK version for this line to be shown
     * @param line          Line text (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addMinSdkVersionLineItem(int minSdkVersion, @StringRes int line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMinSdkVersion(minSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is less than or equal to
     * the specified maximum SDK version.
     *
     * @param maxSdkVersion Maximum SDK version for this line to be shown
     * @param line          Line text
     * @return Updated builder object
     */
    public ChangelogBuilder addMaxSdkVersionLineItem(int maxSdkVersion, String line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMaxSdkVersion(maxSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is less than or equal to
     * the specified maximum SDK version.
     *
     * @param maxSdkVersion Maximum SDK version for this line to be shown
     * @param line          Line text (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addMaxSdkVersionLineItem(int maxSdkVersion, @StringRes int line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMaxSdkVersion(maxSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is between the specified
     * minimum and maximum SDK versions (inclusive).
     *
     * @param minSdkVersion Minimum SDK version for this line to be shown
     * @param maxSdkVersion Maximum SDK version for this line to be shown
     * @param line          Line text
     * @return Updated builder object
     */
    public ChangelogBuilder addSdkVersionRangeLineItem(int minSdkVersion, int maxSdkVersion, String line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMinSdkVersion(minSdkVersion);
        lineItem.setMaxSdkVersion(maxSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    /**
     * Add a line item that is only shown when the device's SDK version is between the specified
     * minimum and maximum SDK versions (inclusive).
     *
     * @param minSdkVersion Minimum SDK version for this line to be shown
     * @param maxSdkVersion Maximum SDK version for this line to be shown
     * @param line          Line text (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addSdkVersionRangeLineItem(int minSdkVersion, int maxSdkVersion, @StringRes int line) {
        LineItem lineItem = new LineItem(line);
        lineItem.setMinSdkVersion(minSdkVersion);
        lineItem.setMaxSdkVersion(maxSdkVersion);
        this.lines.add(lineItem);
        return this;
    }

    public Changelog build() {
        return new Changelog(title, lines);
    }
}