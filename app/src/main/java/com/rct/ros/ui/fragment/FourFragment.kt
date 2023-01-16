package com.rct.ros.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.rct.ros.R
import com.rct.ros.app.base.BaseFragment
import com.rct.ros.databinding.FragmentFourBinding
import com.rct.ros.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class FourFragment : BaseFragment<TestViewModel, FragmentFourBinding>() {

    override val layoutId: Int get() = R.layout.fragment_four

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun onResume() {
        super.onResume()
        immersionBar {
            titleBar(mBind.userHeadImg)
        }
    }

}