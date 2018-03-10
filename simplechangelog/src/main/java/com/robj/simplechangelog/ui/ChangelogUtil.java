package com.robj.simplechangelog.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;

import com.robj.simplechangelog.ui.models.Changelog;

/**
 * @author Rob J
 * @author Santeri Elo
 */
public class ChangelogUtil {
    /**
     * Show the given changelog to the user if required ({@link ChangelogUtil#isChangelogRequired(Context)}).
     *
     * @param context   Context
     * @param changelog Changelog to show
     */
    public static void showChangelogIfRequired(Context context, Changelog changelog) {
        showChangelogIfRequired(context, changelog, 0);
    }

    /**
     * Show the given changelog with a custom style, if required: ({@link ChangelogUtil#isChangelogRequired(Context)}).
     *
     * @param context    Context
     * @param changelog  Changelog to show
     * @param styleResId Custom dialog style to use
     */
    public static void showChangelogIfRequired(Context context, Changelog changelog, @StyleRes int styleResId) {
        if (isChangelogRequired(context)) {
            showChangelog(context, changelog, styleResId);
            NotificationUtils.cancelNotification(context);
        }
    }

    /**
     * Show a changelog notification, if required: ({@link ChangelogUtil#isChangelogRequired(Context)}).
     * Clicking on the created notification opens the given changelog dialog.
     *
     * @param context   Context
     * @param changelog Changelog to show when the notification is clicked
     * @param channelId Notification channel id
     * @param iconResId Notification icon resource
     * @param title     Notification title
     * @param body      Notification description
     */
    public static void showChangelogNotifIfRequired(Context context, Changelog changelog,
                                                    String channelId, @DrawableRes int iconResId, String title, String body) {
        showChangelogNotifIfRequired(context, changelog, channelId, iconResId, title, body, 0);
    }

    /**
     * Show a changelog notification, if required: ({@link ChangelogUtil#isChangelogRequired(Context)}).
     * Clicking on the created notification opens the given changelog dialog, with the specified
     * custom style applied.
     *
     * @param context    Context
     * @param changelog  Changelog to show when the notification is clicked
     * @param channelId  Notification channel id
     * @param iconResId  Notification icon resource
     * @param title      Notification title
     * @param body       Notification description
     * @param styleResId Custom dialog style to use
     */
    public static void showChangelogNotifIfRequired(Context context, Changelog changelog,
                                                    String channelId, @DrawableRes int iconResId, String title, String body, @StyleRes int styleResId) {
        Intent i = getChangelogIntent(context, changelog, styleResId);
        showChangelogNotifIfRequired(context, i, channelId, iconResId, title, body);
    }

    /**
     * Show the given changelog.
     *
     * @param context   Context
     * @param changelog Changelog to show
     */
    public static void showChangelog(Context context, Changelog changelog) {
        showChangelog(context, changelog, 0);
    }

    /**
     * Show the given changelog with a custom style.
     *
     * @param context    Context
     * @param changelog  Changelog to show
     * @param styleResId Custom dialog style to use
     */
    public static void showChangelog(Context context, Changelog changelog, @StyleRes int styleResId) {
        Intent i = getChangelogIntent(context, changelog, styleResId);
        context.startActivity(i);
    }

    private static boolean isChangelogRequired(Context context) {
        try {
            int currentVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            int versionCode = ChangelogPrefs.getLastChangelogShown(context);
            if (versionCode > 0) {
                if (versionCode < currentVersionCode)
                    return true;
            } else {
                ChangelogPrefs.setChangelogShown(context, currentVersionCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void showChangelogNotifIfRequired(Context context, Intent intent, String channelId, @DrawableRes int iconResId, String title, String body) {
        int currentVersionCode;
        try {
            currentVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int versionCode = ChangelogPrefs.getLastChangelogShown(context);
        if (versionCode > 0) {
            if (versionCode < currentVersionCode) {
                versionCode = ChangelogPrefs.getLastChangelogNotifShown(context);
                if (versionCode > 0 && versionCode < currentVersionCode) {
                    NotificationUtils.showChangelogNotification(context, intent, channelId, iconResId, title, body);
                    ChangelogPrefs.setChangelogNotifShown(context, currentVersionCode);
                }
            }
        } else {
            ChangelogPrefs.setChangelogNotifShown(context, currentVersionCode);
        }
    }

    private static Intent getChangelogIntent(Context context, Changelog changelog, @StyleRes int styleResId) {
        Intent i = new Intent(context, ChangelogActivity.class);
        i.putExtra(ChangelogActivity.CHANGELOG, changelog);
        if (styleResId != 0)
            i.putExtra(ChangelogActivity.THEME, styleResId);
        return i;
    }
}
