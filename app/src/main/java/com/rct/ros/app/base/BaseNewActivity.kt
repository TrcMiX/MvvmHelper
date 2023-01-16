package com.rct.ros.app.base

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.rct.ros.R
import com.rct.ros.app.widget.CustomToolBar
import com.win.mvvmhelper.base.BaseVBActivity
import com.win.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2021/8/10
 * 描述　: 新创建的 使用 ViewBinding 需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
 */
abstract class BaseNewActivity<VM : com.win.mvvmhelper.base.BaseViewModel, VB : ViewBinding> : com.win.mvvmhelper.base.BaseVBActivity<VM, VB>(){

    lateinit var mToolbar: CustomToolBar
    
    override fun getTitleBarView(): View? {
        val titleBarView = LayoutInflater.from(this).inflate(R.layout.layout_titlebar_view, null)
        mToolbar = titleBarView.findViewById(R.id.customToolBar)
        return titleBarView
    }

    override fun initImmersionBar() {
        //设置共同沉浸式样式
        if (showToolBar()) {
            mToolbar.setBackgroundResource(R.color.colorPrimary)
            ImmersionBar.with(this).titleBar(mToolbar).init()
        }
    }
}