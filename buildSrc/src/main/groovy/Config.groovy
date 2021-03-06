class Config {

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

    /*// appConfig 配置的是可以跑 app 的模块，git 提交务必只包含 launcher
    static appConfig = ['launcher']
    // pkgConfig 配置的是要依赖的功能包，为空则依赖全部，git 提交务必为空
    static pkgConfig = ['feature0']
    static depConfig = [
            plugin       : [
                    gradle: new DepConfig("com.android.tools.build:gradle:3.6.4"),
                    kotlin: new DepConfig("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"),
                    api   : new DepConfig("com.blankj:api-gradle-plugin:1.2"),
                    bus   : new DepConfig("com.blankj:bus-gradle-plugin:2.4"),
            ],
            feature      : [
                    mock    : new DepConfig(":feature:mock"),
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
    ]*/

    /*// appConfig 配置的是可以跑 app 的模块，git 提交务必只包含 launcher
    static appConfig = ['launcher']
    // pkgConfig 配置的是要依赖的功能包，为空则依赖全部，git 提交务必为空
    static pkgConfig = ['feature0']
    static depConfig = [
            feature_mock               : new DepConfig(":feature:mock"),
            feature_launcher_app       : new DepConfig(":feature:launcher:app"),
            feature_feature0_app       : new DepConfig(":feature:feature0:app"),
            feature_feature0_pkg       : new DepConfig(":feature:feature0:pkg"),
            feature_feature0_export    : new DepConfig(":feature:feature0:export"),
            feature_feature1_app       : new DepConfig(":feature:feature1:app"),
            feature_feature1_pkg       : new DepConfig(":feature:feature1:pkg"),
            feature_feature1_export    : new DepConfig(":feature:feature1:export"),

            lib_base                   : new DepConfig(":lib:base"),
            lib_common                 : new DepConfig(":lib:common"),

            plugin_gradle              : new DepConfig(pluginPath: "com.android.tools.build:gradle:3.6.4"),
            plugin_kotlin              : new DepConfig(pluginPath: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"),
            plugin_api                 : new DepConfig(pluginPath: "com.blankj:api-gradle-plugin:1.2", pluginId: "com.blankj.api"),
            plugin_bus                 : new DepConfig(pluginPath: "com.blankj:bus-gradle-plugin:2.4", pluginId: "com.blankj.bus"),

            androidx_core_ktx          : new DepConfig("androidx.core:core-ktx:1.3.2"),
            androidx_appcompat         : new DepConfig("androidx.appcompat:appcompat:1.2.0"),
            androidx_multidex          : new DepConfig("androidx.multidex:multidex:2.0.0"),
            androidx_constraintlayout  : new DepConfig("androidx.constraintlayout:constraintlayout:2.0.4"),
            kotlin                     : new DepConfig("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"),
            material                   : new DepConfig("com.google.android.material:material:1.2.1"),
            leakcanary_android         : new DepConfig("com.squareup.leakcanary:leakcanary-android:$leakcanary_version"),
            leakcanary_android_no_op   : new DepConfig("com.squareup.leakcanary:leakcanary-android-no-op:$leakcanary_version"),
            leakcanary_support_fragment: new DepConfig("com.squareup.leakcanary:leakcanary-support-fragment:$leakcanary_version"),

            utilcode                   : new DepConfig("com.blankj:utilcodex:1.30.5"),
            free_proguard              : new DepConfig("com.blankj:free-proguard:1.0.1"),
            swipe_panel                : new DepConfig("com.blankj:swipe-panel:1.1"),
    ]*/

    static applicationId = 'com.watayouxiang.androidframe'
    static appName = 'AndroidFrame'

    static compileSdkVersion = 30
    static minSdkVersion = 16
    static targetSdkVersion = 30
    static versionCode = 1
    static versionName = '1.0.0'

    static kotlinVersion = '1.4.32'

    static depConfig = [
            /*Never delete this line*/
            /*Generated by "config.json"*/
            feature_mock               : new DepConfig(false, true , ":feature:mock"),
            feature_launcher_app       : new DepConfig(true , true , ":feature:launcher:app"),
            feature_feature0_app       : new DepConfig(false, true , ":feature:feature0:app"),
            feature_feature0_pkg       : new DepConfig(true , true , ":feature:feature0:pkg"),
            feature_feature0_export    : new DepConfig(true , true , ":feature:feature0:export"),
            feature_feature1_app       : new DepConfig(false, true , ":feature:feature1:app"),
            feature_feature1_pkg       : new DepConfig(true , true , ":feature:feature1:pkg"),
            feature_feature1_export    : new DepConfig(true , true , ":feature:feature1:export"),
            lib_base                   : new DepConfig(true , true , ":lib:base"),
            lib_common                 : new DepConfig(true , true , ":lib:common"),
            /*Never delete this line*/

            plugin_gradle              : new DepConfig(pluginPath: "com.android.tools.build:gradle:4.1.2"),
            plugin_kotlin              : new DepConfig(pluginPath: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"),
            plugin_api                 : new DepConfig(pluginPath: "com.blankj:api-gradle-plugin:1.2", pluginId: "com.blankj.api"),
            plugin_bus                 : new DepConfig(pluginPath: "com.blankj:bus-gradle-plugin:2.4", pluginId: "com.blankj.bus"),

            androidx_core_ktx          : new DepConfig("androidx.core:core-ktx:1.3.2"),
            androidx_appcompat         : new DepConfig("androidx.appcompat:appcompat:1.2.0"),
            androidx_multidex          : new DepConfig("androidx.multidex:multidex:2.0.0"),
            androidx_constraintlayout  : new DepConfig("androidx.constraintlayout:constraintlayout:2.0.4"),
            kotlin                     : new DepConfig("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"),
            material                   : new DepConfig("com.google.android.material:material:1.2.1"),
            leakcanary_android         : new DepConfig("com.squareup.leakcanary:leakcanary-android:2.1"),

            utilcode                   : new DepConfig("com.blankj:utilcodex:1.30.5"),
            free_proguard              : new DepConfig("com.blankj:free-proguard:1.0.1"),
            swipe_panel                : new DepConfig("com.blankj:swipe-panel:1.1"),
    ]
}