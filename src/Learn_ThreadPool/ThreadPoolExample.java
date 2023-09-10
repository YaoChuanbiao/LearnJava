package Learn_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 创建线程池，使用Executors工厂类提供的静态方法创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交任务给线程池执行
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.submit(new Runnable() {
                public void run() {
                    System.out.println("Task " + taskNumber + " is being executed by " + Thread.currentThread().getName());
                    try {
                        // 模拟任务执行时间
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task " + taskNumber + " is completed by " + Thread.currentThread().getName());
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
