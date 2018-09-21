package com.wnuo.ztong.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-09-21 17:28
 * @description: 全局Avtivity页面 主要写事件（按键）处理
 */
open class BaseActivity : AppCompatActivity() {
    var tag: String? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info();
        // 添加到 AppManager 应用管理
        ActivityManager.addActivity(this)
        tag = this.localClassName;
    }

    //主要是初始化页面
    protected open fun info() {}

    /**
     * 重写 onDestroy() 方法，移除 Activity 管理以及 MVP 生命周期管理
     */
    override fun onDestroy() {

        // 从应用管理移除当前 Activity 对象
        ActivityManager.removeActivity(this);
        super.onDestroy()
    }
}