package com.watayouxiang.feature1.pkg;

import android.content.Context;

import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.LogUtils;
import com.watayouxiang.feature1.export.api.Feature1Api;
import com.watayouxiang.feature1.export.bean.Feature1Param;
import com.watayouxiang.feature1.export.bean.Feature1Result;
import com.watayouxiang.feature1.pkg.main.Feature1Activity;

@ApiUtils.Api
public class Feature1ApiImpl extends Feature1Api {

    @Override
    public Feature1Result startFeature1Activity(Context context, Feature1Param param) {
        Feature1Activity.start(context);
        LogUtils.d(param.getName());
        return new Feature1Result("Feature1Result");
    }

}
