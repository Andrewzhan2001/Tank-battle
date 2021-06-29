package DesignPattern.factory.abstractfactory;

public class ModernFactory extends Abstractfactory{

  @Override
  Vehicle createVehicle() {
    return new Car();
  }

  @Override
  Weapon createWeapon() {
    return new AK47();
  }
  
}
