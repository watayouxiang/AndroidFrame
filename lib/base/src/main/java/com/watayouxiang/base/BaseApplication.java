package com.watayouxiang.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ProcessUtils;

import java.util.ArrayList;


public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    private Boolean isDebug;
    private Boolean isMainProcess;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initLog();
        initCrash();
    }

    // init it in ur application
    public void initLog() {
        LogUtils.Config config = LogUtils.getConfig()
                // 设置 log 总开关，包括输出到控制台和文件，默认开
                .setLogSwitch(isDebug())
                // 设置是否输出到控制台开关，默认开
                .setConsoleSwitch(isDebug())
                // 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setGlobalTag(null)
                // 设置 log 头信息开关，默认为开
                .setLogHeadSwitch(true)
                // 打印 log 时是否存到文件的开关，默认关
                .setLog2FileSwitch(false)
                // 当自定义路径为空时，写入应用的/cache/log/目录中
                .setDir("")
                // 当文件前缀为空时，默认为"util"，即写入文件为"util-yyyy-MM-dd$fileExtension"
                .setFilePrefix("")
                // 设置日志文件后缀
                .setFileExtension(".log")
                // 输出日志是否带边框开关，默认开
                .setBorderSwitch(true)
                // 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setSingleTagSwitch(true)
                // log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setConsoleFilter(LogUtils.V)
                // log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.V)
                // log 栈深度，默认为 1
                .setStackDeep(1)
                // 设置栈偏移，比如二次封装的话就需要设置，默认为 0
                .setStackOffset(0)
                // 设置日志可保留天数，默认为 -1 表示无限时长
                .setSaveDays(3)
                // 新增 ArrayList 格式化器，默认已支持 Array, Throwable, Bundle, Intent 的格式化输出
                .addFormatter(new LogUtils.IFormatter<ArrayList>() {
                    @Override
                    public String format(ArrayList arrayList) {
                        return "LogUtils Formatter ArrayList { " + arrayList.toString() + " }";
                    }
                })
                .setFileWriter(null);
        LogUtils.i(config.toString());
    }

    private void initCrash() {
        CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(CrashUtils.CrashInfo crashInfo) {
                LogUtils.e(crashInfo);
                AppUtils.relaunchApp();
            }
        });
    }

    private boolean isDebug() {
        if (isDebug == null) isDebug = AppUtils.isAppDebug();
        return isDebug;
    }

    public boolean isMainProcess() {
        if (isMainProcess == null) isMainProcess = ProcessUtils.isMainProcess();
        return isMainProcess;
    }
}
