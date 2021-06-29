package DesignPattern.factory;

import java.lang.reflect.InvocationTargetException;

public class VehicleFactory {
  public Moveable produceVehicle(Class classname) {
    try {
      return (Moveable)Class.forName(classname.getName()).getDeclaredConstructor().newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
