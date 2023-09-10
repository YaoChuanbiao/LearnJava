package Learn_Multithreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadExample_ReadWriteLock {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter value: " + counter.getCounter());
    }
}

class Counter {
    private int counter = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment() {
        lock.writeLock().lock(); // 获取写锁
        try {
            counter++;
        } finally {
            lock.writeLock().unlock(); // 释放写锁
        }
    }

    public void decrement() {
        lock.writeLock().lock(); // 获取写锁
        try {
            counter--;
        } finally {
            lock.writeLock().unlock(); // 释放写锁
        }
    }

    public int getCounter() {
        lock.readLock().lock(); // 获取读锁
        try {
            return counter;
        } finally {
            lock.readLock().unlock(); // 释放读锁
        }
    }
}