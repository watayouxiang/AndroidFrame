package com.watayouxiang.feature1.pkg.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.watayouxiang.common.CommonTitleActivity;
import com.watayouxiang.feature1.pkg.R;


public class Feature1Activity extends CommonTitleActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, Feature1Activity.class);
        context.startActivity(starter);
    }

    @Override
    public CharSequence bindTitle() {
        return getString(R.string.feature1_title);
    }

    @Override
    public boolean isSwipeBack() {
        return true;
    }

    @Override
    public int bindLayout() {
        return R.layout.feature1_activity;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    public void doBusiness() {

    }
}
