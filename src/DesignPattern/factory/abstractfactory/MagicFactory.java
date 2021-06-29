package DesignPattern.factory.abstractfactory;

public class MagicFactory extends Abstractfactory{

  @Override
  Vehicle createVehicle() {
    return new Broom();
  }

  @Override
  Weapon createWeapon() {
    return new Stick();
  }
  
}
