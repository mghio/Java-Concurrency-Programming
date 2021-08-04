package cn.mghio;

/**
 * @author mghio
 * @since 2021-08-04
 */
public class FinalReferenceEscapeExample {

  final int i;
  static FinalReferenceEscapeExample obj;

  public FinalReferenceEscapeExample() {
    i = 1;  // 写 final 域
    obj = this;  //  this 引用再次"逸出"
  }

  public static void writer() {
    new FinalReferenceEscapeExample();
  }

  public static void reader() {
    if (obj != null) {
      int temp = obj.i;
    }
  }

}
