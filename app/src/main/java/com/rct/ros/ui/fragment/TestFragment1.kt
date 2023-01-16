package com.rct.ros.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rct.ros.R
import com.rct.ros.app.api.NetUrl
import com.rct.ros.app.base.BaseFragment
import com.rct.ros.databinding.ActivityListBinding
import com.rct.ros.ui.adapter.TestAdapter
import com.rct.ros.ui.viewmodel.ListViewModel
import com.win.mvvmhelper.ext.*
import com.win.mvvmhelper.net.LoadStatusEntity
import com.win.mvvmhelper.util.decoration.DividerOrientation

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestFragment1 : BaseFragment<ListViewModel, ActivityListBinding>() {

    private val testAdapter: TestAdapter by lazy { TestAdapter(arrayListOf()) }

    override fun initView(savedInstanceState: Bundle?) {

        mBind.listSmartRefresh.refresh {
            //刷新
            mViewModel.getList(true)
        }.loadMore {
            //加载更多
            mViewModel.getList(false)
        }
        //初始化 recycleView
        mBind.listRecyclerView.grid(1).divider {
            orientation = DividerOrientation.GRID
            includeVisible = true
            setDivider(10,true)
            setColor(getColorExt(R.color.colorRed))
        }.adapter = testAdapter
    }

    /**
     * 懒加载 第一次获取视图的时候 触发
     */
    override fun lazyLoadData() {
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