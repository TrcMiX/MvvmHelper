package com.win.mvvmhelper.widget.state

import com.kingja.loadsir.callback.Callback
import com.win.mvvmhelper.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/14
 * 描述　:
 */
class BaseEmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}