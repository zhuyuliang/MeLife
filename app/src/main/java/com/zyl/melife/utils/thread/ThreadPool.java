package com.zyl.melife.utils.thread;

import com.zyl.melife.utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 14-2-26
 * Time: 下午3:37
 */
public class ThreadPool {
    public static final String TAG = ThreadPool.class.getSimpleName();
    static ExecutorService threadPool;

    public static void initThreadPool(int max){ // 可以在Application中进行配置
        if(max > 0){
            max = max < 3 ? 3 : max;
            threadPool = Executors.newFixedThreadPool(max);
        }else{
            //无限
            threadPool = Executors.newCachedThreadPool();
        }

        LogUtils.d(TAG, "[ThreadPool]ThreadPool init success...max thread: " + max);

    }

    public static ExecutorService getInstances(){
        return threadPool;
    }

    public synchronized static<U, R> void go(Runtask<U, R> runtask){
        if(null == threadPool){
            LogUtils.e(TAG, "ThreadPool没有被初始化，请在Application中进行初始化操作...");
            return;
        }
        threadPool.execute(runtask);
    }

    public synchronized static void go(Runnable runnable){
        if(null == threadPool){
            LogUtils.e(TAG, "ThreadPool没有被初始化，请在Application中进行初始化操作...");
            return;
        }
        threadPool.execute(runnable);
    }

}
