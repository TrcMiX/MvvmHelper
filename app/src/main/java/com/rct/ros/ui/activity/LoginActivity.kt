package com.rct.ros.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.win.mvvmhelper.ext.getStringExt
import com.rct.ros.R
import com.rct.ros.app.api.NetUrl
import com.rct.ros.app.base.BaseActivity
import com.rct.ros.app.ext.initBack
import com.rct.ros.databinding.ActivityLoginBinding
import com.rct.ros.ui.viewmodel.LoginViewModel
import com.win.mvvmhelper.ext.showDialogMessage
import com.win.mvvmhelper.net.LoadStatusEntity
import com.win.mvvmhelper.net.LoadingDialogEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　: 虽然在Activity代码少了，但是DataBinding 不太好用
 */
class LoginActivity: BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //初始化toolbar
        mToolbar.initBack(getStringExt(R.string.login_submit)) {
            finish()
        }
        mBind.viewModel = mViewModel
        mBind.click = LoginClickProxy()
    }

    /**
     * 请求成功
     */
    override fun onRequestSuccess() {
        //监听登录结果
        mViewModel.loginData.observe(this, Observer {
            //做保存信息等操作
            finish()
        })
    }

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        when(loadStatus.requestCode){
            NetUrl.LOGIN ->{
                showDialogMessage(loadStatus.errorMessage)
            }
        }
    }

    inner class LoginClickProxy{

        fun clear() {
            mViewModel.userName.set("")
        }

        //登录
        fun login(){
            when {
                mViewModel.userName.get().isEmpty() -> showDialogMessage("手机号不能为空")
                mViewModel.password.get().isEmpty() -> showDialogMessage("密码不能为空")
                else -> mViewModel.login(mViewModel.userName.get(), mViewModel.password.get())
            }
        }

        var onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }

    override fun showCustomLoading(setting: LoadingDialogEntity) {
        if(setting.requestCode== NetUrl.LOGIN){
            //可以根据不同的code 做不同的loading处理
            showLoadingUi()
        }
    }

    override fun dismissCustomLoading(setting: LoadingDialogEntity) {
        if(setting.requestCode==NetUrl.LOGIN){
            //可以根据不同的code 做不同的loading处理
            showSuccessUi()
        }
    }

}