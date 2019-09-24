public class parkingSpot{
  private int ID;
  private String level;
  private String size;
  private int hourlyRate;

  // Constructor
  public parkingSpot(int ID, String floor, String size, int hourlyRate){
    this.ID = ID;
    this.level = floor;
    this.size = size;
    this.hourlyRate = hourlyRate;
  }

  // Accessor functions
  public get_ID(){return this.ID;}
  public get_level(){return this.level;}
  public get_size(){return this.size;}
  public get_hourlyRate(){return this.hourlyRate;}
}
