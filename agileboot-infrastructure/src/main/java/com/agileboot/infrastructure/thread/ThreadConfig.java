package com.agileboot.infrastructure.thread;

public class ThreadConfig {

    public final static int CORE_POOL_SIZE = 50;

    public final static int MAX_POOL_SIZE = 200;

    public final static int QUEUE_CAPACITY = 1000;

    public final static int KEEP_ALIVE_SECONDS = 300;

    /**
     * 操作延迟10毫秒
     */
    public final static int OPERATE_DELAY_TIME = 10;

}
