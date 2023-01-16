package com.rct.ros.ui.fragment

import android.os.Bundle
import com.rct.ros.R
import com.rct.ros.ui.viewmodel.TestViewModel
import com.win.mvvmhelper.base.BaseVmFragment
import com.win.mvvmhelper.ext.logD

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestFragment3 : BaseVmFragment<TestViewModel>() {

    override val layoutId: Int = R.layout.fragment_test

    override fun initView(savedInstanceState: Bundle?) {
        "TestFragment3-initView".logD("hgj")
    }

    override fun lazyLoadData() {
        "TestFragment3-lazyLoadData".logD("hgj")
    }

    override fun onBindViewClick() {
        super.onBindViewClick()

    }

}