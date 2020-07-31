package com.myproject.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *锁机制
 * @author shuyi
 * @date 2020/7/31
 */
public class Testlock {
    Lock lock=new ReentrantLock();
    Thread current =Thread.currentThread();


}
