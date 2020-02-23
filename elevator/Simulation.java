/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author jtass
 */
public class Simulation {
    static Random mRandom;
    
    public static Random getRandom(){
        return mRandom;
    }
    
    
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a seed a value.");
        int userInput = scan.nextInt();
        mRandom = new Random(userInput);
       // mRandom.setSeed(userInput);
        //mRandom.
        
        System.out.println("Please input the number of floors");
        int  numOfFloors = scan.nextInt();
        
        System.out.println("Please enter the number of elevators");
        int numOfElevators = scan.nextInt();
        
        
        
        Building bldg1 = new Building(numOfFloors, numOfElevators);
        System.out.println(bldg1);
        
        System.out.println("Please enter the number of ticks to simulate");
        int numOfTicks = scan.nextInt();
        
        if (numOfTicks == 0){
            System.out.print(bldg1);
        }
        else{
            int i = 1;
            while (numOfTicks>0){
                System.out.println("Step " + i);
                bldg1.tick();
                System.out.print(bldg1);
                numOfTicks--;
                i++;
            }
        }
        

        
}
    
}


