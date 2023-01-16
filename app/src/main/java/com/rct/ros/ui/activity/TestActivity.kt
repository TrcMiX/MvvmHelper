package com.rct.ros.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.rct.ros.app.base.BaseActivity
import com.rct.ros.app.ext.initBack
import com.win.mvvmhelper.base.BaseViewModel
import com.rct.ros.databinding.ActivityTestBinding
import com.rct.ros.ui.fragment.TestFragment1

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestActivity : BaseActivity<com.win.mvvmhelper.base.BaseViewModel, ActivityTestBinding>() {

    private val titles = arrayOf("页面1", "页面2", "页面3")
    
    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.initBack("测试Fragment") {
            finish()
        }
        mBind.testViewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return TestFragment1()
            }

            override fun getCount(): Int {
                return titles.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titles[position]
            }
        }
        mBind.testTableLayout.setupWithViewPager(mBind.testViewPager)
        mBind.testViewPager.offscreenPageLimit = titles.size
    }
}