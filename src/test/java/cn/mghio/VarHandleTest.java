package cn.mghio;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author mghio
 * @since 2021-08-03
 */
public class VarHandleTest {

  /**
   * ordinary variable
   */
  private String plainStr;

  /**
   * static variable
   */
  private static String staticStr;

  /**
   * The variable that generates the handle by reflection
   */
  private String reflectStr;

  /**
   * The variable that generates the handle by reflection
   */
  private String[] arrayStr = new String[10];

  /**
   * ordinary variable handle
   */
  private static final VarHandle plainVar;

  /**
   * static variable handle
   */
  private static final VarHandle staticVar;

  /**
   * reflect variable handle
   */
  private static final VarHandle reflectVar;

  /**
   * array handle
   */
  private static final VarHandle arrayVar;

  static {
    try {
      MethodHandles.Lookup l = MethodHandles.lookup();
      plainVar = l.findVarHandle(VarHandleTest.class, "plainStr", String.class);
      staticVar = l.findStaticVarHandle(VarHandleTest.class, "staticStr", String.class);
      reflectVar = l.unreflectVarHandle(VarHandleTest.class.getDeclaredField("reflectStr"));
      arrayVar = MethodHandles.arrayElementVarHandle(String[].class);
    } catch (ReflectiveOperationException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public void simpleReadAndWriteAccess() {
    // simple read

    // normal write operation of the instance variable
    plainVar.set(this, "I am plain string");

    // normal write operation of the static variable
    staticVar.set("I am static string");

    // normal write operation of the reflection field
    reflectVar.set(this, "I am string create by reflection");

    // ordinary write operation of array variable
    arrayVar.set(arrayStr, 0, "I am string array element");


    // simple write

    // ordinary variable operation of the instance variable
    String plainStr = (String) plainVar.get(this);

    // normal read operation for static variable
    String staticStr = (String) staticVar.get();

    // normal read operation of the reflection variable
    String reflectStr = (String) reflectVar.get(this);

    // normal read operation of the array variable, the second parameter is the index group subscript, the 0th element
    String arrayStrElem = (String) arrayVar.get(arrayStr, 0);

    System.out.println(this);
  }

  public static void main(String[] args) {
    new VarHandleTest().simpleReadAndWriteAccess();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VarHandleTest.class.getSimpleName() + "[", "]")
        .add("plainStr='" + plainStr + "'")
        .add("staticStr='" + staticStr + "'")
        .add("reflectStr='" + reflectStr + "'")
        .add("arrayStr=" + Arrays.toString(arrayStr))
        .toString();
  }
}
