/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
 *
 * @author jtass
 */
public class Elevator {
    
    private int currentFloor = 1;
    ArrayList<Integer> passengers = new ArrayList<>();
    CurrentDirection CurrentDirection;
    CurrentState CurrentState;
    private int elevatorNum;
    Building building;

    
    public enum CurrentDirection {
        UP,
        DOWN,
        NOT_MOVING
    }
    
    public enum CurrentState {
        IDLE_STATE, DOORS_OPENING, UNLOADING_PASSENGERS, LOADING_PASSENGERS,
        DOORS_CLOSING, ACCELERATING,MOVING, DECELERATING
    }
    
    public Elevator(int num, Building bldg){
        elevatorNum = num;
        building = bldg;
        CurrentDirection = CurrentDirection.NOT_MOVING;
        CurrentState = CurrentState.IDLE_STATE;
    }
    public int getFloor(){
        return currentFloor;
    }
    
    public CurrentDirection getDirection (){
        return CurrentDirection;
    }
    
    public CurrentState getState (){
        return CurrentState;
    }
    
    public int getElevatorNum () {
        return elevatorNum;
    }
    
    public Building getBuilding () {
        return building;
    }
    
    public void tick () {
        if (CurrentState == CurrentState.IDLE_STATE) {
         //   System.out.println("alright");
            if (!passengers.isEmpty() ){
                
                CurrentState = CurrentState.ACCELERATING;
            }
            //ArrayList<Integer> amt = building.getFloor(currentFloor);
           
            if (passengers.isEmpty() && !building.getFloor(currentFloor-1).isEmpty()){
                System.out.println("at doors opening");
                CurrentState = CurrentState.DOORS_OPENING;
            }
        }
        
        else if (CurrentState == CurrentState.DOORS_OPENING){
            if (passengers.contains(currentFloor-1)){
                CurrentState = CurrentState.UNLOADING_PASSENGERS;
                
            }
            else {
                CurrentState = CurrentState.LOADING_PASSENGERS;
            }
        }
        else if (CurrentState == CurrentState.UNLOADING_PASSENGERS) {

           
           // for (int person:passengers){
           for (int i =0;i<passengers.size();i++){
                if (passengers.get(i) == currentFloor-1){
                passengers.remove(i);
            }
            }
            if (passengers.isEmpty()){
                
            CurrentDirection = CurrentDirection.NOT_MOVING;
            }
            
          //  if (CurrentDirection == CurrentDirection.NOT_MOVING || )
            
          //  int inAmt = amt.
          boolean greaterThan = false;
          boolean lessThan = false;
          for (int person:building.getFloor(currentFloor-1)){
              if ((person) > (currentFloor-1)){
                  greaterThan = true;
              }
              else if ((person) < (currentFloor-1)){
                  lessThan = true;
              }
          }
            if (CurrentDirection==CurrentDirection.UP && greaterThan == true){
                CurrentState = CurrentState.LOADING_PASSENGERS;
            }          
            else if (CurrentDirection == CurrentDirection.DOWN && lessThan == true){
                CurrentState = CurrentState.LOADING_PASSENGERS;
            }
            else {
                CurrentState = CurrentState.DOORS_CLOSING;
            }
        
        
        
    }
      
        // loading passengers
        else if (CurrentState == CurrentState.LOADING_PASSENGERS){
            //ArrayList<Integer> peopleWaiting = building.getFloor(currentFloor);
            if (CurrentDirection==CurrentDirection.NOT_MOVING && !building.getFloor(currentFloor-1).isEmpty()){
                System.out.println("CurrentFloor " + currentFloor);
   
                System.out.println("bldg ppl " + building.getFloor(currentFloor-1).get(0));
               int person = building.getFloor(currentFloor-1).get(0);
                passengers.add(person);
                building.getFloor(currentFloor-1).remove(0);
                
                if (currentFloor > person){
                    CurrentDirection = CurrentDirection.DOWN;
                }
                else if (currentFloor < person){
                    CurrentDirection = CurrentDirection.UP;
                }
                ArrayList<Integer> toBeDeleted = new ArrayList<>();
                for (int i =0; i<building.getFloor(currentFloor-1).size();i++){
                    if (CurrentDirection == CurrentDirection.DOWN && building.getFloor(currentFloor-1).get(i)<person){
                        passengers.add(building.getFloor(currentFloor-1).get(i));
                        toBeDeleted.add(building.getFloor(currentFloor-1).get(i));
                    }
                    else if (CurrentDirection == CurrentDirection.UP && building.getFloor(currentFloor-1).get(i) > person){
                        passengers.add(building.getFloor(currentFloor-1).get(i));
                        toBeDeleted.add(building.getFloor(currentFloor-1).get(i));
                    }
                }
                for (int i =0; i<toBeDeleted.size();i++){
                    building.getFloor(currentFloor-1).remove(new Integer(toBeDeleted.get(i)));
                }
               //  CurrentState = CurrentState.DOORS_CLOSING;
            }
           // ArrayList<Integer> peopleWaiting = building.getFloor(floor);
            //for (int person : building.getFloor(currentFloor)) {
            
            for (int i =0; i<building.getFloor(currentFloor-1).size();i++){
                if (building.getFloor(currentFloor-1).get(i) < currentFloor && CurrentDirection==CurrentDirection.DOWN){
                 //
                 //building.getFloor(currentFloor).remove(person);
                // passengers.add(person);
                int temp = building.getFloor(currentFloor-1).get(i);
                    building.getFloor(currentFloor-1).remove(i);
                    passengers.add(temp);
                }
                else if (building.getFloor(currentFloor-1).get(i) > currentFloor && CurrentDirection == CurrentDirection.UP){
                   // peopleWaiting.remove(person);
                  // passengers.add(person);
                   // building.getFloor(currentFloor).remove(person);
                   int temp = building.getFloor(currentFloor-1).get(i);
                    building.getFloor(currentFloor-1).remove(i);
                    passengers.add(temp);
                }
            }
            CurrentState = CurrentState.DOORS_CLOSING;
        
        }
        else if (CurrentState == CurrentState.DOORS_CLOSING){
            if(passengers.size() >= 1){
                CurrentState = CurrentState.ACCELERATING;
            }
            else{
                CurrentState = CurrentState.IDLE_STATE;
            }
        }
        
        else if (CurrentState == CurrentState.ACCELERATING){
            CurrentState = CurrentState.MOVING;
        }
        
        else if (CurrentState == CurrentState.MOVING){
            System.out.println("moving");
            int nextFloor = 0;
            if (CurrentDirection == CurrentDirection.UP){
                nextFloor = currentFloor + 1;
                currentFloor = nextFloor;
            }
            else if (CurrentDirection == CurrentDirection.DOWN) {
                nextFloor = currentFloor - 1;
                currentFloor = nextFloor;
            }
            ArrayList<Integer> peopleNextFloor = building.getFloor(nextFloor-1);
            boolean sameDirection = false;
            for (int person:building.getFloor(nextFloor-1)){
                if (currentFloor> person && CurrentDirection==CurrentDirection.DOWN){
                    sameDirection = true;
                }
                else if(currentFloor<person && CurrentDirection == CurrentDirection.UP){
                    sameDirection = true;
                }
            }
            if (passengers.contains(nextFloor-1) || sameDirection == true){
                CurrentState = CurrentState.DECELERATING;
            }
            //if (people.contains(this))
            // add if passenger waiting on nect floor
        }
        
        else if (CurrentState == CurrentState.DECELERATING){
            CurrentState = CurrentState.DOORS_OPENING;
            if (passengers.isEmpty()){
                CurrentDirection = CurrentDirection.NOT_MOVING;
            }
        }
        
        
        
        
        
    }
    
    
    
    @Override
    public String toString(){
        return "Elevator- " + elevatorNum + " Floor- " + currentFloor  + "- " + CurrentState + "- " + CurrentDirection + " Passengers " + passengers;
    }
}
