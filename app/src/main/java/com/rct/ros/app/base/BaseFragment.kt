package com.rct.ros.app.base

import androidx.databinding.ViewDataBinding
import com.win.mvvmhelper.base.BaseDbFragment
import com.win.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　: 使用DataBinding 需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
 */
abstract class BaseFragment<VM : com.win.mvvmhelper.base.BaseViewModel,DB: ViewDataBinding> : com.win.mvvmhelper.base.BaseDbFragment<VM, DB>(){

    //需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看

}