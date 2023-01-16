package com.rct.ros.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.rct.ros.R
import com.rct.ros.app.base.BaseFragment
import com.rct.ros.databinding.FragmentThreeBinding
import com.rct.ros.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class ThreeFragment : BaseFragment<TestViewModel, FragmentThreeBinding>() {

    override val layoutId: Int get() = R.layout.fragment_three

    override fun initView(savedInstanceState: Bundle?) {
        mBind.customToolbar.setCenterTitle(R.string.bottom_title_report)
        mBind.customToolbar.setBackgroundResource(R.color.colorPrimary_20)
    }

    override fun onResume() {
        super.onResume()
        immersionBar {
            titleBar(mBind.customToolbar)
        }
    }
}