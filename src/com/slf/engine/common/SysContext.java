package com.slf.engine.common;

public class SysContext {

    public static boolean              IS_START                = false;                                               // 服务是否开启

    public static ThreadGroup          DEAL_FAILBACK_THREAD_GROUP       = new ThreadGroup("THREAD_GROUP");

    public static ThreadGroup          DEAL_STATUSBACK_THREAD_GROUP  = new ThreadGroup("THREAD_FAIL_GROUP");
}
