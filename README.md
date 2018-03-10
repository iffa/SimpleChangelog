# SimpleChangelog - a simple changelog library for Android

This library allows you to create and show a changelog dialog to the user when (s)he has
updated your application. The library will handle the logic behind this for you, however
you can also show the dialog manually if you so desire.

## Installation

SimpleChangelog is hosted on Jitpack - to add the library to your project first add
Jitpack as a repository in your project's root build.gradle file:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then you can add the library as a dependency:
```gradle
implementation 'com.github.iamrobj:SimpleChangelog:1.3'
```

## Usage

Creating a changelog is simple:

```java
Changelog createChangelog() {
    Changelog changelog = new ChangelogBuilder()
        .setTitle("A title") // Optional title, defaults to "Changelog"
        .setSubtitle("A subtitle") // Optional subtitle, defaults to version name
        .addLineItem("Changelog entry") // Add an entry
        .addLineItem(R.string.hello) // Text from resources
        .addLineItem(Html.fromHtml(getString(R.string.some_html))) // Entry with HTML
        .addMinSdkVersionLineItem(Build.VERSION_CODES.O, "Oreo and up") // Specify minimum SDK version
        .addMaxSdkVersionLineItem(Build.VERSION_CODES.N, "Up to Nougat") // Specify maximum SDK version
        .addSdkVersionRangeLineItem(Build.VERSION_CODES.O, Build.VERSION_CODES.O_MR1, "From 8.0 to 8.1") // Specify SDK version range
        .build();
    
    return changelog;
}
```

Once you've created your changelog, you can easily show the dialog using any of the following method calls:

```java
// Simply show the changelog
ChangelogUtil.showChangelog(context, changelog, styleRes);

// Show the changelog if required, i.e. first launch after an update
ChangelogUtil.showChangelogIfRequired(context, changelog, styleRes);
```

You can trigger a notification to be shown instead, if required:
```java
// Create a notification that opens the changelog when clicked by the user
// The notification is only shown if required, i.e. user has not yet seen the changelog
ChangelogUtil.showChangelogNotifIfRequired(context,
        changelog,
        "changelogs", // Notification channel id
        R.drawable.notification_icon, // Notification icon
        "App updated", // Notification title
        "Click to see what's new!", // Notification description
        styleRes);
```

The library creates a notification channel for you: all you need to do is give the channel 
an ID. The created notification channel is named 'Changelogs' and has medium importance,
so notifications do not make a sound but still appear in the status bar. If you want to rename
the notification channel, you can override the string resource 'cl_notification_channel' in your
application.

**Note**: Passing a style resource to any of the methods is optional - if no style is specified the default style 
will be used instead (Theme.AppCompat.DayNight.Dialog).

