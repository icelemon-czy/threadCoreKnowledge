package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示公平和非公平的插队策略
 */
public class NonfairBargeDemo {
    ReentrantReadWriteLock reentrantReadWriteLock =  new ReentrantReadWriteLock(true);

}
