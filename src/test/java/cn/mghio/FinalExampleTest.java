package cn.mghio;

/**
 * @author mghio
 * @since 2021-08-04
 */
public class FinalExampleTest {

  int i;  // 普通变量

  final int j;  // final 变量

  static FinalExampleTest obj;

  public FinalExampleTest() {  // 构造函数
    i = 1;  // 写普通域
    j = 2;  // 写 final 域
  }

  public static void writer() {  // 写线程 A 执行
    obj = new FinalExampleTest();
  }

  public static void reader() {  // 读线程 B 执行
    FinalExampleTest object = obj;  // 读对象引用
    int a = object.i;  // 读普通域
    int b = object.j;  // 读 final 域
  }

}
