package com.watayouxiang.mock.feature1;

import android.content.Context;

import com.blankj.utilcode.util.ApiUtils;
import com.watayouxiang.feature1.export.api.Feature1Api;
import com.watayouxiang.feature1.export.bean.Feature1Param;
import com.watayouxiang.feature1.export.bean.Feature1Result;


// isMock = true 和 isMock = false 同时存在的时候，最终以 isMock = false 为最终实现
@ApiUtils.Api(isMock = true)
public class Feature1ApiMock extends Feature1Api {
    @Override
    public Feature1Result startFeature1Activity(Context context, Feature1Param param) {
        return new Feature1Result("Mock Result");
    }
}
