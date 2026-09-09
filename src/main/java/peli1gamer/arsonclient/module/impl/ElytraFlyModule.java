package peli1gamer.arsonclient.module.impl;
import peli1gamer.arsonclient.module.*;
import peli1gamer.arsonclient.settings.*;
public final class ElytraFlyModule extends ConfigurableModule {
 private final DoubleSetting horizontal=group("Flight").add(new DoubleSetting("horizontal-speed","Horizontal Speed",1.0,0.1,5.0,0.1));
 private final DoubleSetting vertical=group("Flight").add(new DoubleSetting("vertical-speed","Vertical Speed",0.5,0.0,3.0,0.1));
 @Override public String id(){return "elytra-fly";} @Override public String name(){return "Elytra Fly";} @Override public Category category(){return Category.MOVEMENT;}
 public double horizontalSpeed(){return horizontal.get();} public double verticalSpeed(){return vertical.get();}
}
