package com.agileboot.infrastructure.thread;

/**
 * @author valarchie
 */
public class ThreadConfig {

    public static final int CORE_POOL_SIZE = 50;

    public static final int MAX_POOL_SIZE = 200;

    public static final int QUEUE_CAPACITY = 1000;

    public static final int KEEP_ALIVE_SECONDS = 300;

    /**
     * 操作延迟10毫秒
     */
    public static final int OPERATE_DELAY_TIME = 10;

    private ThreadConfig() {
    }
}
