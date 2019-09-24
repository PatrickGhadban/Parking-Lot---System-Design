import java.util.*;

public enum VehicleSize { SM, MED, LRG, XL, HC }

abstract public class vehicle{
  protected String licensePlate;
  protected VehicleSize size;

  // Constructor
  public vehicle(String licensePlate){
    this.licensePlate = licensePlate;
  }

  // Accessor functions
  public get_license_plat(){
    return this.licensePlate;
  }
  public get_vehicle_size(){
    return this.size;
  }
}
