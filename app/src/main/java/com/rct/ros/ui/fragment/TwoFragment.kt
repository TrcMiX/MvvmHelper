package com.rct.ros.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.rct.ros.R
import com.rct.ros.app.base.BaseFragment
import com.rct.ros.databinding.FragmentTwoBinding
import com.win.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TwoFragment : BaseFragment<com.win.mvvmhelper.base.BaseViewModel, FragmentTwoBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBind.customToolbar.setCenterTitle(R.string.bottom_title_paper)
        mBind.customToolbar.setBackgroundResource(R.color.colorRed)
    }

    override fun onResume() {
        super.onResume()
        immersionBar {
            titleBar(mBind.customToolbar)
        }
    }
}