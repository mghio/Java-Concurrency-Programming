package cn.mghio;

/**
 * @author mghio
 * @since 2021-08-04
 */
public class FinalReferenceExampleTest {

  final int[] intArray;   // final 是引用类型

  static FinalReferenceExampleTest obj;

  public FinalReferenceExampleTest() {  // 构造函数
    intArray = new int[1];
    intArray[0] = 1;
  }

  public static void writerOne() {  // 写线程 A 执行
    obj = new FinalReferenceExampleTest();
  }

  public static void writerTwo() {  // 写线程 B 执行
    obj.intArray[0] = 2;
  }

  public static void reader() {  // 读线程 C 执行
    if (obj != null) {
      int temp1 = obj.intArray[0];
    }
  }

}
