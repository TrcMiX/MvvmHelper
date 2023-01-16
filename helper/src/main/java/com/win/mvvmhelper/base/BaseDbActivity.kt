package com.win.mvvmhelper.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.noober.background.BackgroundLibrary
import com.win.mvvmhelper.ext.inflateBinding

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseDbActivity<VM : com.win.mvvmhelper.base.BaseViewModel, DB : ViewDataBinding> :
    com.win.mvvmhelper.base.BaseVmActivity<VM>(),
    com.win.mvvmhelper.base.BaseIView {

    //使用了DataBinding 就不需要 layoutId了，因为 会从DB泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mBind: DB

    /**
     * 创建DataBinding
     */
    override fun initViewDataBind(): View? {
        //利用反射 根据泛型得到 ViewDataBinding
        mBind = inflateBinding()
        BackgroundLibrary.inject(this)
        return mBind.root
    }

}