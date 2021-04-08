class Config {

    static applicationId = 'com.watayouxiang.myapplication'
    static appName = 'AndroidFrame'

    static compileSdkVersion = 30
    static minSdkVersion = 16
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'

    static kotlin_version = '1.3.10'
    static support_version = '27.1.1'
    static leakcanary_version = '1.6.3'

    static depConfig = [
            plugin       : [
                    gradle: "com.android.tools.build:gradle:3.3.0",
                    kotlin: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            ],
            support      : [
                    appcompat_v7: "com.android.support:appcompat-v7:$support_version",
                    design      : "com.android.support:design:$support_version",
                    multidex    : "com.android.support:multidex:1.0.2",
                    constraint  : "com.android.support.constraint:constraint-layout:1.1.3",
            ],
            kotlin       : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version",
            utilcode     : "com.blankj:utilcode:1.25.0",
            free_proguard: "com.blankj:free-proguard:1.0.1",
            swipe_panel  : "com.blankj:swipe-panel:1.1",

            leakcanary   : [
                    android         : "com.squareup.leakcanary:leakcanary-android:$leakcanary_version",
                    android_no_op   : "com.squareup.leakcanary:leakcanary-android-no-op:$leakcanary_version",
                    support_fragment: "com.squareup.leakcanary:leakcanary-support-fragment:$leakcanary_version",
            ],
    ]

}