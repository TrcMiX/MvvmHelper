package com.rct.ros.ui.activity

import android.os.Bundle
import com.rct.ros.R
import com.rct.ros.app.base.BaseNewActivity
import com.rct.ros.databinding.ActivityMainBinding
import com.rct.ros.ui.adapter.MainAdapter
import com.rct.ros.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/6
 * 描述　:
 */
class MainActivity : BaseNewActivity<TestViewModel, ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.setCenterTitle(R.string.bottom_title_read)
        mBind.mainViewPager.adapter = MainAdapter(this)
        mBind.mainViewPager.offscreenPageLimit = mBind.mainViewPager.adapter!!.itemCount
        mBind.mainViewPager.isUserInputEnabled = false
        mBind.mainNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationRead -> {
                    mBind.mainViewPager.setCurrentItem(0, false)
                }
                R.id.navigationPaper -> {
                    mBind.mainViewPager.setCurrentItem(1, false)
                }
                R.id.navigationReport -> {
                    mBind.mainViewPager.setCurrentItem(2, false)
                }
                R.id.navigationUser -> {
                    mBind.mainViewPager.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun showToolBar() = false

}
