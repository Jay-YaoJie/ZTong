package com.wnuo.ztong

import android.support.multidex.MultiDexApplication
import com.wnuo.ztong.constants.DefaultValue
import com.wnuo.ztong.constants.DelegatesExt
import com.wnuo.ztong.constants.FileUtils
import org.greenrobot.eventbus.EventBus

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-09-21 17:14
 * @description:整体使用的全局对象Application
 */
class ZTong : MultiDexApplication() {
    //companion静态声类声名对象，相当于static关键
    companion object {
        // 按照我们在Java中一样创建一个单例最简单的方式：
//        private var instance:Application?=null;
//        fun instance()= instance!!;
        // 单例不会是null   所以使用notNull委托
        //var instance: FlashLight by Delegates.notNull()
        // 自定义委托实现单例,只能修改这个值一次.
        var instance: ZTong by DelegatesExt.notNullSingleValue<ZTong>();
        //创建一个 EventBus 实例对象
        //var eBus: EventBus by DelegatesExt.notNullSingleValue<EventBus>();
    }

    //override实现接口关键字，override修饰的方法,默认是可以被继承的
    override fun onCreate() {
        super.onCreate();
        instance = this;
        initThirdService();

    }

    /**
     * 初始化第三方的服务和对象
     */
    private fun initThirdService() {
        Thread(Runnable {
            kotlin.run {
                //获取根目录,并保存
                DefaultValue.ROOT_DIR = FileUtils.getRootDir();
                //打开日志工具，主要是保存异常信息
                //CrashHandler.getInit().init();//暂时出现异常交给系统处理，，，，没有自己处理
            }
        }).start();

    }

    override fun onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }
}