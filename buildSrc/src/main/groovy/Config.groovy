class Config {

    static applicationId = 'com.watayouxiang.myapplication'
    static appName = 'AndroidFrame'

    static compileSdkVersion = 30
    static minSdkVersion = 16
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'

    static kotlin_version = '1.4.32'

    static depConfig = [
            kotlin  : "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version",
            material: "com.google.android.material:material:1.2.1",
            androidx: [
                    core_ktx : "androidx.core:core-ktx:1.3.2",
                    appcompat: "androidx.appcompat:appcompat:1.2.0",
                    multidex : "androidx.multidex:multidex:2.0.0",
                    constraintlayout : "androidx.constraintlayout:constraintlayout:2.0.4",
            ],
            utilcode: "com.blankj:utilcodex:1.30.5",
            // 混淆
            free_proguard: "com.blankj:free-proguard:1.0.1",
            // 侧边返回
            swipe_panel  : "com.blankj:swipe-panel:1.1",
    ]

}