class Config {

    static applicationId = 'com.watayouxiang.myapplication'
    static appName = 'AndroidFrame'

    static compileSdkVersion = 30
    static minSdkVersion = 16
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'

    static kotlin_version = '1.4.32'
    static leakcanary_version = '1.6.3'

    /*static depConfig = [
            androidx     : [
                    core_ktx        : "androidx.core:core-ktx:1.3.2",
                    appcompat       : "androidx.appcompat:appcompat:1.2.0",
                    multidex        : "androidx.multidex:multidex:2.0.0",
                    constraintlayout: "androidx.constraintlayout:constraintlayout:2.0.4",
            ],
            kotlin       : "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version",
            material     : "com.google.android.material:material:1.2.1",
            leakcanary   : [
                    android         : "com.squareup.leakcanary:leakcanary-android:$leakcanary_version",
                    android_no_op   : "com.squareup.leakcanary:leakcanary-android-no-op:$leakcanary_version",
                    support_fragment: "com.squareup.leakcanary:leakcanary-support-fragment:$leakcanary_version",
            ],
            utilcode     : "com.blankj:utilcodex:1.30.5",
            // 混淆
            free_proguard: "com.blankj:free-proguard:1.0.1",
            // 侧边返回
            swipe_panel  : "com.blankj:swipe-panel:1.1",
    ]*/

    // =============================================================================================
    // 新的方式
    // =============================================================================================

    // appConfig 配置的是可以跑 app 的模块，git 提交务必只包含 launcher
    static appConfig = ['launcher', 'feature0']
    // pkgConfig 配置的是要依赖的功能包，为空则依赖全部，git 提交务必为空
    static pkgConfig = ['feature0']
    static depConfig = [
            feature      : [
                    launcher: [
                            app: new DepConfig(":feature:launcher:app"),
                    ],
                    feature0: [
                            app   : new DepConfig(":feature:feature0:app"),
                            pkg   : new DepConfig(true, ":feature:feature0:pkg", "com.blankj:feature-feature0-pkg:1.0", true),
                            export: new DepConfig(":feature:feature0:export"),
                    ],
                    feature1: [
                            app   : new DepConfig(":feature:feature1:app"),
                            pkg   : new DepConfig(":feature:feature1:pkg"),
                            export: new DepConfig(":feature:feature1:export"),
                    ],
            ],
            lib          : [
                    base  : new DepConfig(":lib:base"),
                    common: new DepConfig(":lib:common"),
            ],
            androidx     : [
                    core_ktx        : new DepConfig("androidx.core:core-ktx:1.3.2"),
                    appcompat       : new DepConfig("androidx.appcompat:appcompat:1.2.0"),
                    multidex        : new DepConfig("androidx.multidex:multidex:2.0.0"),
                    constraintlayout: new DepConfig("androidx.constraintlayout:constraintlayout:2.0.4"),
            ],
            kotlin       : new DepConfig("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"),
            material     : new DepConfig("com.google.android.material:material:1.2.1"),
            leakcanary   : [
                    android         : new DepConfig("com.squareup.leakcanary:leakcanary-android:$leakcanary_version"),
                    android_no_op   : new DepConfig("com.squareup.leakcanary:leakcanary-android-no-op:$leakcanary_version"),
                    support_fragment: new DepConfig("com.squareup.leakcanary:leakcanary-support-fragment:$leakcanary_version"),
            ],
            utilcode     : new DepConfig("com.blankj:utilcodex:1.30.5"),
            // 混淆
            free_proguard: new DepConfig("com.blankj:free-proguard:1.0.1"),
            // 侧边返回
            swipe_panel  : new DepConfig("com.blankj:swipe-panel:1.1"),

    ]
}