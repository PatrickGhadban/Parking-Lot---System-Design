import java.util.*;

public class parkingLot{
  // Cost of reserving spot
  private float cost;

  // Number of available spots per size
  private int sm_limit = 3;
  private int med_limit = 6;
  private int lrg_limit = 6;
  private int xl_limit = 2;
  private int hc_limit = 3;

  // Store available parking spots
  private Stack<parkingSpot> available_sm_spots = new Stack<>();
  private Stack<parkingSpot> available_med_spots = new Stack<>();
  private Stack<parkingSpot> available_lrg_spots = new Stack<>();
  private Stack<parkingSpot> available_xl_spots = new Stack<>();
  private Stack<parkingSpot> available_hc_spots = new Stack<>();

  // Store unavailable spots and the vehicle reserving them {ID : vehicle}
  private HashMap<String, vehicle> unavailable_spots = new HashMap<>();

  public parkingLot(){
    // Args:
    // Description:
    //    Constructor - Populate stacks that hold available spots
    this.available_sm_spots = populateStacks(this.sm_limit, "SM", 1);
    this.available_med_spots = populateStacks(this.med_limit, "MED", 2);
    this.available_lrg_spots = populateStacks(this.lrg_limit, "LRG", 3);
    this.available_xl_spots = populateStacks(this.xl_limit, "XL", 5);
    this.available_hc_spots = populateStacks(this.hc_limit, "HC", 2);
  }

  public Stack<parkingSpot> populateStacks(int limit, String size, int price){
    // Args:
    //    limit - number of available spots
    //    size - size of spots
    //    price - hourly rate corresponding to the size of the spot
    // Description:
    //    Populate stacks that hold available spots

    Stack<parkingSpot> temp_stack = new Stack<>();
    for (int i = 1; i <= limit; i++){
      int id = i;
      if (i < 2){
        parkingSpot spot = new parkingSpot(i, "1st", size, price);
        temp_stack.push(spot);
      } else{
        parkingSpot spot = new parkingSpot(i + 10, "2nd", size, price);
        temp_stack.push(spot);
      }
    }
    return temp_stack;
  }

  public boolean check_availability(String carSize, String licensePlate, int hrs){
    // Args:
    //    size - size of the car
    //    licensePlate - identification of car looking for an available spots
    //    hrs - the number of hours the driver is looking to leave their vehicle
    // Description:
    //    Check for available spots of the given size. Larger size will constitute as available.

    // Handicap cars
    if (carSize == "HC"){
      if (available_xl_spots.size() == 0){ return false; }
      else{ reserve_spot(size, licensePlate, hrs); return true; }
    }

    // XL Cars
    if (carSize == "XL"){
      if (available_xl_spots.size() == 0){ return false; }
      else{ reserve_spot("XL", licensePlate, hrs); return true; }
    }

    // LRG Cars
    if (carSize == "LRG"){
      if (available_lrg_spots.size() == 0){
        if (available_xl_spots.size() == 0){ return false; }
        else{ reserve_spot("XL", licensePlate, hrs); return true;}
      }
      else{ reserve_spot("LRG", licensePlate, hrs); return true; }
    }

    // MED Cars
    if (carSize == "MED"){
      if (available_med_spots.size() == 0){
        if (available_lrg_spots.size() == 0){
          if (available_xl_spots.size() == 0){ return false; }
          else{ reserve_spot("XL", licensePlate, hrs) return true;}
        }
        else{ reserve_spot("LRG", licensePlate, hrs); return true;}
      }
      else{ reserve_spot("MED", licensePlate, hrs); return true;}
    }

    // SM Cars
    if (carSize == "SM"){
      if (available_sm_spots.size() == 0){
        if (available_med_spots.size() == 0){
          if (available_lrg_spots.size() == 0){
            if (available_xl_spots.size() == 0){ return false; }
            else{ reserve_spot("XL", licensePlate, hrs); return true;}
          }
          else{ reserve_spot("LRG", licensePlate, hrs); return true;}
        }
        else{ reserve_spot("MED", licensePlate, hrs); return true;}
    }
    else{ reserve_spot("SM", licensePlate, hrs); return true;}
  }

  public reserve_spot(String carSize, String licensePlate, int hrs){
    // Args:
    //    size - size of the car
    //    licensePlate - identification of car looking for an available spots
    //    hrs - the number of hours the driver is looking to leave their vehicle
    // Description:
    //    Reserves parking spot by putting { ID : vehicle} in HashMap

    if (carSize == "SM"){
      spot = this.available_sm_spots.pop();
      motorcycle mv = new motorcycle(licensePlate);
      unavailable_spots.put(spot.get_ID(), mv);
      process_ticket(hrs, spot.get_hourlyRate());
    }
    if (carSize == "MED"){
      spot = this.available_med_spots.pop();
      car mv = new car(licensePlate);
      unavailable_spots.put(spot.get_ID(), mv);
      process_ticket(hrs, spot.get_hourlyRate());
    }
    if (carSize == "LRG"){
      spot = this.available_lrg_spots.pop();
      truck mv = new truck(licensePlate);
      unavailable_spots.put(spot.get_ID(), mv);
      process_ticket(hrs, spot.get_hourlyRate());
    }
    if (carSize == "XL"){
      spot = this.available_xl_spots.pop();
      bus mv = new bus(licensePlate);
      unavailable_spots.put(spot.get_ID(), mv);
      process_ticket(hrs, spot.get_hourlyRate());
    }
    if (carSize == "HC"){
      spot = this.available_hc_spots.pop();
      Handicap mv = new Handicap(licensePlate);
      unavailable_spots.put(spot.get_ID(), mv);
      process_ticket(hrs, spot.get_hourlyRate());
    }
  }

  public float get_ticket(){
    // Args:
    // Description:
    //    Accessor function which returns the cost

    return cost;
  }

  public void process_ticket(float hrs, String size){
  // Args:
  // Description:
  //    Calculates the cost of the ticket

     this.cost = hrs * hourlyRate;
  }

  public void free_space(String ID){
    // Args:
    //      ID - ID of the parking spot that was recently freed
    // Description:
    //    Removes the parking spot form unavailable_spots and pushes it to available__spots (multiple stacks)

    vehicle mv = new vehicle();
    mv = unavailable_spots.get(ID);

    // Remove from unavailable_spots
    unavailable_spots.remove(ID);
    VehicleSize size = mv.get_size();

    // Push to stack of available spots
    if (size == "SM"){
      parkingSpot spot = new parkingSpot(ID, "1st", size, 1);
      available_sm_spots.push();
    }
    if (size == "MED"){
      parkingSpot spot = new parkingSpot(ID, "1st", size, 1);
      available_sm_spots.push();
    }
    if (size == "LRG"){
      parkingSpot spot = new parkingSpot(ID, "1st", size, 1);
      available_sm_spots.push();
    }
    if (size == "XL"){
      parkingSpot spot = new parkingSpot(ID, "1st", size, 1);
      available_sm_spots.push();
    }
    if (size == "HC"){
      parkingSpot spot = new parkingSpot(ID, "1st", size, 1);
      available_sm_spots.push();
    }

  }

  public static void main(String[] args){
    // Declare parkingLot object
    parkingLot lot = new parkingLot();

    // Ask for details on vehicle
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please enter your license plate identification: ");
    String licensePlate = br.readLine();
    System.out.println("Please enter the number of hours would you like to stay: ");
    float hrs = br.readLine();

    // Check for availability --> Reserve spot (if available) & calculate price
    System.out.println("Please choose a from the following parking spot sizes: SM, MED, LRG, XL, HC");
    String carSize = br.readLine();
    if (lot.check_availability(carSize, licensePlate, hrs)){
      System.out.println("Sorry there are no more available spots for that size.");
      return;
    }

    // Print and provide ticket (really just the cost)
    float ticket = get_ticket()
    System.out.println("Please take your ticket: " + ticket);
  }
}
