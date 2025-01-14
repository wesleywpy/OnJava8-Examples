// patterns/ShapeFactory2.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

import patterns.shapes.BadShapeCreation;
import patterns.shapes.FactoryMethod;
import patterns.shapes.FactoryTest;
import patterns.shapes.Shape;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ShapeFactory2 implements FactoryMethod {
  private Map<String, Constructor> factories =
    new HashMap<>();
  private static Constructor load(String id) {
    System.out.println("loading " + id);
    try {
      return Class.forName("patterns.shapes." + id)
        .getConstructor();
    } catch(ClassNotFoundException |
            NoSuchMethodException e) {
      throw new BadShapeCreation(id);
    }
  }
  @Override public Shape create(String id) {
    try {
      return (Shape)factories
        .computeIfAbsent(id, ShapeFactory2::load)
        .newInstance();
    } catch(Exception e) {
      throw new BadShapeCreation(id);
    }
  }
  public static void main(String[] args) {
    FactoryTest.test(new ShapeFactory2());
  }
}
/* Output:
loading Circle
Circle[0] draw
Circle[0] erase
loading Square
Square[1] draw
Square[1] erase
loading Triangle
Triangle[2] draw
Triangle[2] erase
Square[3] draw
Square[3] erase
Circle[4] draw
Circle[4] erase
Circle[5] draw
Circle[5] erase
Triangle[6] draw
Triangle[6] erase
*/
