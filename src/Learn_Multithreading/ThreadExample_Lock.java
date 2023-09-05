package Learn_Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
为什么需要加锁才能实现线程的同步
在没有锁的情况下，两个线程可以同时访问和修改 counter 变量，这可能导致竞态条件（race condition）和数据不一致性问题。
例如，考虑以下情况：
线程1读取 counter 的值为 0。
此时，线程2也读取 counter 的值为 0。
线程1递增 counter，将其值设置为 1。
线程2递减 counter，将其值设置为 -1。
最终，counter 的值不是 0，而是 -1。
这是因为在没有锁的情况下，两个线程可以同时执行递增和递减操作，它们之间的执行顺序是不确定的，从而导致了竞态条件和不一致的结果。
通过使用锁（lock），我们确保在访问和修改 counter 变量之前，只有一个线程能够获得锁并进入临界区。这样，其他线程就会被阻塞，直到锁被释放。
通过对临界区进行加锁，我们保证了线程对 counter 的访问是互斥的，每次只有一个线程执行递增或递减操作，从而避免了竞态条件和不一致性结果的发生。
 */

public class ThreadExample_Lock {
    private int counter = 0;
    private Lock lock = new ReentrantLock(); // 创建一个 ReentrantLock 实例作为锁对象

    public static void main(String[] args) {
        ThreadExample_Lock example = new ThreadExample_Lock();
        example.runThreads();
    }

    public void runThreads() {
        Thread thread1 = new Thread(new IncrementRunnable()); // 创建一个线程并指定 Runnable 对象
        Thread thread2 = new Thread(new DecrementRunnable()); // 创建另一个线程并指定 Runnable 对象

        thread1.start(); // 启动线程1
        thread2.start(); // 启动线程2

        try {
            thread1.join(); // 等待线程1执行完毕
            thread2.join(); // 等待线程2执行完毕,这段代码中，
            // 通过调用 thread1.join() 和 thread2.join()，主线程（运行 runThreads() 方法的线程）会等待 thread1 和 thread2 执行完成，然后再继续执行后面的代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter); // 输出最终的计数器值
    }

    // 递增线程的 Runnable 实现类
    class IncrementRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                //lock.lock(); // 获取锁
                try {
                    counter++; // 递增计数器
                } finally {
                    //lock.unlock(); // 释放锁
                }
            }
        }
    }

    // 递减线程的 Runnable 实现类
    class DecrementRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lock.lock(); // 获取锁
                try {
                    counter--; // 递减计数器
                } finally {
                    lock.unlock(); // 释放锁
                }
            }
        }
    }
}
