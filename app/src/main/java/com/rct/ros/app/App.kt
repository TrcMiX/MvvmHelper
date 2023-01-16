package com.rct.ros.app

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.Project
import com.rct.ros.BuildConfig
import com.rct.ros.R
import com.win.mvvmhelper.base.MvvmHelper
import com.win.mvvmhelper.ext.currentProcessName
import org.litepal.LitePal

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LitePal.initialize(this);
        MvvmHelper.init(this, BuildConfig.DEBUG)
        val processName = currentProcessName
        if (currentProcessName == packageName) {
            // 主进程初始化
            onMainProcessInit()
        } else {
            // 其他进程初始化
            processName?.let { onOtherProcessInit(it) }
        }
    }

    /**
     * @description  代码的初始化请不要放在onCreate直接操作，按照下面新建异步方法
     */
    private fun onMainProcessInit() {
        AnchorsManager.getInstance()
            .debuggable(BuildConfig.DEBUG)
            //设置锚点
            .addAnchor(InitNetWork.TASK_ID, InitUtils.TASK_ID, InitComm.TASK_ID, InitToast.TASK_ID)
            .start(
                Project.Builder("app", AppTaskFactory())
                    .add(InitNetWork.TASK_ID)
                    .add(InitComm.TASK_ID)
                    .add(InitUtils.TASK_ID)
                    .add(InitToast.TASK_ID)
                    .build()
            )
    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    private fun onOtherProcessInit(processName: String) {
        initCrash()
    }

    fun initCrash() {
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
            .enabled(true)     //false表示对崩溃的拦截阻止。用它来禁用customactivityoncrash框架
            .showErrorDetails(true) //这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪,—》针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。。
            .showRestartButton(true)    //是否可以重启页面,针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
            .trackActivities(true)     //错误页面中显示错误详细信息；针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
            .minTimeBetweenCrashesMs(1000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
            .errorDrawable(R.mipmap.icon_logo)     //崩溃页面显示的图标
//            .errorActivity(
//                //程序崩溃后显示的页面
//                CustomErrorActivity::class.java
//            )
            .apply()
    }


}