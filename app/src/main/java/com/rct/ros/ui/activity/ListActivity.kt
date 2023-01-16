package com.rct.ros.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rct.library.common.ext.dp
import com.rct.ros.R
import com.rct.ros.app.api.NetUrl
import com.rct.ros.app.base.BaseActivity
import com.rct.ros.app.ext.initBack
import com.rct.ros.databinding.ActivityListBinding
import com.rct.ros.ui.adapter.TestAdapter
import com.rct.ros.ui.viewmodel.ListViewModel
import com.win.mvvmhelper.ext.*
import com.win.mvvmhelper.net.LoadStatusEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
class ListActivity: BaseActivity<ListViewModel, ActivityListBinding>() {

    private  val testAdapter: TestAdapter by lazy { TestAdapter(arrayListOf()) }

    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.initBack("列表界面") {
            finish()
        }
        mBind.listSmartRefresh.refresh {
            //下拉刷新
            mViewModel.getList(true)
        }.loadMore {
            //上拉加载
            mViewModel.getList(false)
        }
        //初始化recyclerview
        mBind.listRecyclerView.run {
            grid(4)
            divider {
                setColor(getColorExt(R.color.colorBlack))
                setDivider(5.dp)
                includeVisible = true
            }
            adapter = testAdapter
        }
        //发起请求
        onLoadRetry()
    }

    /**
     * 请求成功
     */
    override fun onRequestSuccess() {
        mViewModel.listData.observe(this, Observer {
            //请求到列表数据
            testAdapter.loadListSuccess(it,mBind.listSmartRefresh)
        })
    }

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        when (loadStatus.requestCode) {
            NetUrl.HOME_LIST -> {
                //列表数据请求失败
                testAdapter.loadListError(loadStatus,mBind.listSmartRefresh)
            }
        }
    }

    /**
     * 错误界面 空界面 点击重试
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
    }

}