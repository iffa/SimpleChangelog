package com.robj.simplechangelog.ui.models;

import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogBuilder {
    @StringRes
    private int titleRes = 0, subtitleRes = 0;

    @Nullable
    private String title, subtitle;

    @ArrayRes
    private int stringArrayLines = 0;

    private List<LineItem> lines = new ArrayList<>();

    private List<FooterLineItem> footerLines = new ArrayList<>();

    /**
     * Set the title for the changelog. If none is set, the default titleRes will be used instead.
     *
     * @param titleRes Title (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder setTitle(int titleRes) {
        this.titleRes = titleRes;
        return this;
    }

    /**
     * Set the title for the changelog. If none is set, the default titleRes will be used instead.
     *
     * @param title Title
     * @return Updated builder object
     */
    public ChangelogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set a subtitle for the changelog. If none is set, the application's version name
     * will be used as a subtitle.
     *
     * @param subtitleRes Subtitle (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder setSubtitle(int subtitleRes) {
        this.subtitleRes = subtitleRes;
        return this;
    }

    /**
     * Set the subtitle for the changelog. If none is set, the application's version name
     * will be used as a subtitle.
     *
     * @param subtitle Subtitle
     * @return Updated builder object
     */
    public ChangelogBuilder setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
     * Add line items from a string array resource.
     *
     * @param stringArray Lines (string array resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addLineItems(@ArrayRes int stringArray) {
        this.stringArrayLines = stringArray;
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

    /**
     * Add a footer line item.
     *
     * @param line Footer line text
     * @return Updated builder object
     */
    public ChangelogBuilder addFooterLineItem(CharSequence line) {
        FooterLineItem lineItem = new FooterLineItem(line);
        this.footerLines.add(lineItem);
        return this;
    }

    /**
     * Add a footer line item.
     *
     * @param line Footer line text (string resource)
     * @return Updated builder object
     */
    public ChangelogBuilder addFooterLineItem(@StringRes int line) {
        FooterLineItem lineItem = new FooterLineItem(line);
        this.footerLines.add(lineItem);
        return this;
    }

    public Changelog build() {
        return new Changelog(titleRes, title, subtitleRes,
                subtitle, lines, footerLines, stringArrayLines);
    }
}