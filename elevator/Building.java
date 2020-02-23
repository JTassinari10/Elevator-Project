/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
/**
 *
 * @author jtass
 */
public class Building {
    
    private ArrayList<Elevator> elevators = new ArrayList<>();
   // private ArrayList<Elevator> elevators;
    
    private  ArrayList<ArrayList<Integer>> floors = new ArrayList<>();
    //private ArrayList<ArrayList<Integer>> floors;
    
    Building(int numFloors, int numElevators){

       for (int i =1; i<=numElevators;i++){
           elevators.add(new Elevator(i,this));
       }
       
        for (int k =0; k<numFloors; k++){
            
            floors.add(new ArrayList<>());
           // System.out.println("here");
            
        }
        
    }
    
    public ArrayList<Integer> getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }
    
    public void tick() {
        Random rand = Simulation.getRandom();
        for (int i = 0;i<floors.size();i++){
        
      //  for (ArrayList<Integer> floor:floors){
            int floorNum = i + 1;
            int randInt = rand.nextInt(20);
            boolean already = false;
            if (randInt == 0){
                int randPassenger;
                while (true){
                    randPassenger = rand.nextInt(floors.size()) + 1;
                    if (randPassenger != floorNum){
                        //System.out.println("")
                      //  floor.add(randPassenger);
                        floors.get(i).add(randPassenger);
                        System.out.println("Adding passenger with destination " + randPassenger + " to floor " + floorNum);
                        System.out.println("floors " + floors);
                        
                        already = true;
                        break;
                    }
                    
                }
                 if (already == true){
                break;
            }
                }
                
            }
            int k = 0;
            for (Elevator elevator:elevators){
                            elevator.tick();
                            k++;
                            //already = true;
                         //   System.out.println("tick");
                        }
            
           
        

        
    }
//    public void tick() {
//      Random rand = Simulation.getRandom();
//        for (ArrayList<Integer> floor:floors){
//         //   System.out.println("Floor")
//           int floorNum = floors.indexOf(floor) + 1;
//          System.out.println("floornum:  " + floorNum);
//           int randInt = rand.nextInt(20);
//            int randPassenger;
//            if (randInt == 0){
//                while (true){ 
//                 //   System.out.println("floorsize" + floors.size());
//                    randPassenger = rand.nextInt(floors.size());
//                    randPassenger+=1;
//                  //  System.out.println("randPassenger: " + randPassenger);
//                    if (randPassenger != floorNum){
//                    floor.add(randPassenger);
//                    System.out.println("floor " + floor);
//                    System.out.println("Adding passenger with destination " + randPassenger + "to floor" + floorNum);
//                    
//                    for (Elevator elevator:elevators){
//                        elevator.tick();
//                    }
//                    break;
//                    }
//            }
//        }
//        }
//    }
    
    @Override
    public String toString(){
//        int num = floors.size();
//        String elevatorLoc = "X";
//        for(int i =1 ;i<=num;i++){
//            System.out.println(num + ": ");
//            num--;
//            for (int k=0;k<=elevators.size();k++){
//                System.out.print("|  ");
//             //   if Elevator.
//            
//        }
//        }
        int numElev = elevators.size();
        for (Elevator elevator:elevators){
            System.out.println(elevator);
        }
        return   " ";
    }
}
