# Basics of how to handle an interview system design question:

1. Handling Ambuguity:
    * Recognize details / Ask clarfying questions
        * I.E. Is this a system or algorithmic sdesign question? Do you want to see a class hierarchy?
2. Systematic Approach:
    * Clarify details / assumptions (Some details may be purposely left out. It's your job to ask)
    * Mindset: Make $ for the client while producing a design product that works best for them and there particular needs. 
        * The client may not know what information you need, it is your job to find that out. Just as in writing, asssume the reader doesn't know what you're thinking and be as detailed as possible.
    
# (Possible) Questions:
* Building or open space?
    * Mulitple locations?
* How many levels and number of open spots per?
    * Any dependancies on levels?
* How many entrances?
* Types of spots?
    * Does pricing per spot differ?

# Details for this example:
* Building
* 1 location
* 2 floors: 
    * 1st floor - 10spots
    * 2nd floor - 10 spots
    * *I chose less spots for simplicity and testing purposes*
* 2 entrances - {North, South}
* 6 Types of spots - {SM, MED, LRG, EXT LRG, HANDICAP, PREMIUM}
    * Note: vehicles may fit into spots larger spots than needed (not vice versa), however, they will have to pay the same price.
* Number of / Pricing (per hour): 
    * SM - 3: 1(1st), 2(2nd) / $1
    * MED - 6: 2(1st), 4(2nd) / $2
    * LRG - 6: 2(1st), 4(2nd) / $3
    * EXT LRG - 2 (1st) / $5
    * HANDICAP (size: LRG) - 3 (1st) / $2
    
    
 ### Class Hierarchy:
 1. ParkingLot
 2. A.)Vehicle
      * Bus
      * Truck
      * Car
      * MotorCycle
      * Handicap
2. B.) Parking Spot

### Data Structures / Algoirthms
* Used multiple stacks to store ParkingSpot objects which represented parking spots that were still available. 
      * Run-time: O(1)
* Used a hashmap to store { ID : Vehicles } which represent unavailable parking spots and the cars currently occupying them.
      * Run-time: O(1)
* NOTE: In a system such a this, a real life example would have some sort of backend that would store this information.
