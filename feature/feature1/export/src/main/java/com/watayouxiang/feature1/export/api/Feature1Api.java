package com.watayouxiang.feature1.export.api;

import android.content.Context;

import com.blankj.utilcode.util.ApiUtils;
import com.watayouxiang.feature1.export.bean.Feature1Param;
import com.watayouxiang.feature1.export.bean.Feature1Result;


public abstract class Feature1Api extends ApiUtils.BaseApi {

    public abstract Feature1Result startFeature1Activity(Context context, Feature1Param param);

}
