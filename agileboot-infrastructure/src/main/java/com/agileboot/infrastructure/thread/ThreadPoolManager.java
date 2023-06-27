package com.agileboot.infrastructure.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 异步任务管理器
 *
 * @author valarchie
 */
@Slf4j
public class ThreadPoolManager {

    private static final ThreadPoolExecutor THREAD_EXECUTOR = new ThreadPoolExecutor(
            ThreadConfig.CORE_POOL_SIZE, ThreadConfig.MAX_POOL_SIZE,
            ThreadConfig.KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(
            ThreadConfig.CORE_POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy()) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (t == null && r instanceof Future<?>) {
                try {
                    Future<?> future = (Future<?>) r;
                    if (future.isDone()) {
                        future.get();
                    }
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            if (t != null) {
                log.error(t.getMessage(), t);
            }
        }
    };

    private ThreadPoolManager() {
    }


    /**
     * 执行schedule任务
     */
    public static void schedule(TimerTask task) {
        SCHEDULED_EXECUTOR.schedule(task, ThreadConfig.OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行异步任务任务
     */
    public static void execute(Runnable task) {
        THREAD_EXECUTOR.execute(task);
    }

    /**
     * 停止任务线程池
     */
    public static void shutdown() {
        THREAD_EXECUTOR.shutdown();
        SCHEDULED_EXECUTOR.shutdown();
    }
}
