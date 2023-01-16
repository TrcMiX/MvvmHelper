package com.rct.ros.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.rct.ros.R
import com.rct.ros.app.base.BaseNewFragment
import com.rct.ros.databinding.FragmentOneBinding
import com.rct.ros.ui.activity.ListActivity
import com.rct.ros.ui.activity.LoginActivity
import com.rct.ros.ui.activity.TestActivity
import com.rct.ros.ui.viewmodel.TestViewModel
import com.win.mvvmhelper.ext.msg
import com.win.mvvmhelper.ext.setOnclickNoRepeat
import com.win.mvvmhelper.ext.showDialogMessage
import com.win.mvvmhelper.ext.toStartActivity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class OneFragment : BaseNewFragment<TestViewModel, FragmentOneBinding>() {

    private var downloadApkPath = ""

    override fun initView(savedInstanceState: Bundle?) {
        mBind.customToolbar.setCenterTitle(R.string.bottom_title_read)
        mBind.customToolbar.setBackgroundResource(R.color.colorOrange)
    }

    override fun onResume() {
        super.onResume()
        immersionBar {
            titleBar(mBind.customToolbar)
        }
    }

    override fun onBindViewClick() {
        setOnclickNoRepeat(mBind.loginBtn, mBind.testPageBtn, mBind.testListBtn, mBind.testDownload, mBind.testUpload) {
            when (it.id) {
                R.id.loginBtn -> {
                    toStartActivity(LoginActivity::class.java)
                }
                R.id.testPageBtn -> {
                    toStartActivity(TestActivity::class.java)
                }
                R.id.testListBtn -> {
                    toStartActivity(ListActivity::class.java)
                }

                R.id.testDownload -> {
                    mViewModel.downLoad({
                        //下载中
                        mBind.testUpdateText.text = "下载进度：${it.progress}%"
                    }, {
                        //下载完成
                        downloadApkPath = it
                        showDialogMessage("下载成功，路径为：${it}")
                    }, {
                        //下载失败
                        showDialogMessage(it.msg)
                    })
                }

                R.id.testUpload -> {
                    mViewModel.upload(downloadApkPath, {
                        //上传中 进度
                        mBind.testUpdateText.text = "上传进度：${it.progress}%"
                    }, {
                        //上传完成
                        showDialogMessage("上传成功：${it}")
                    }, {
                        //上传失败
                        showDialogMessage("${it.msg}--${it.message}")
                    })
                }
            }
        }
    }
}