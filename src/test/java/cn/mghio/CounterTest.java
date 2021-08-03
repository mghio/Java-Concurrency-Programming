package cn.mghio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mghio
 * @since 2021-08-02
 */
public class CounterTest {

  private int i = 0;
  private final AtomicInteger atomicI = new AtomicInteger(0);

  public static void main(String[] args) {
    final CounterTest cas = new CounterTest();
    List<Thread> ts = new ArrayList<>();
    long start = System.currentTimeMillis();

    for (int j = 0; j < 100; j++) {
      Thread t = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
          cas.count();
          cas.safeCount();
        }
      });
      ts.add(t);
    }

    for (Thread t : ts) {
      t.start();
    }

    for (Thread t : ts) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(cas.i);
    System.out.println(cas.atomicI.get());
    System.out.println("took: " + (System.currentTimeMillis() - start) + " ms");
  }

  private void safeCount() {
    for (; ; ) {
      int i = atomicI.get();
      boolean suc = atomicI.compareAndSet(i, ++i);
      if (suc) {
        break;
      }
    }
  }

  private void count() {
    i++;
  }

}
