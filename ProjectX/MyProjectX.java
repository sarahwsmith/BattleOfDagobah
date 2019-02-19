/*
 * Sarah Wigg
 * CSC 225
 * Client class for project X
 */
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class MyProjectX
{
    //main method
    public static void main(String[] args) throws java.io.IOException
    {
        Scanner input = new Scanner(System.in);
        int userInput = 0;
        int choice = 0;
        
        //display welcome message
        ProjectX.welcomeString(); 
        
        //load in data from text file
        char[][] planet = new char[5][5];
        ProjectX.loadPlanet(planet);
                
        //display planet
        System.out.println("\n");
        displayPlanet(planet);
                
        //use search methods to find initial location of ewok and wookie
        //use constructor methods in server class to create user1 and user2
        ProjectX ewok = new ProjectX(ProjectX.startingERow(planet), ProjectX.startingECol(planet));
        ProjectX wookie = new ProjectX(ProjectX.startingWRow(planet), ProjectX.startingWCol(planet));
        
        //enter while loop to allow players to keep going until escape key is pressed
        while (choice != 2)
        {
            System.out.println("\n");
            
            //ask if user would like to continue
            System.out.println("Would you like to continue the battle?");
            System.out.println("Press 1 for yes, 2 for no.");
            choice = input.nextInt();
            
            if (choice == 1)
            {
                //define ewok and wookie row and col variables
                int ewokRow = ewok.getUserRow();
                int origEwokRow = ewok.getUserRow();
                int ewokCol = ewok.getUserCol();
                int origEwokCol = ewok.getUserCol();
                
                int wookieRow = wookie.getUserRow();
                int origWookieRow = wookie.getUserRow();
                int wookieCol = wookie.getUserCol();
                int origWookieCol = wookie.getUserCol();
        
                //ewok move
                System.out.println("Ewok, it is your move.");
                System.out.println("Enter the column (0-4) to which you would like to move:");
                userInput = input.nextInt();
                
                    if (isValid(userInput, ewokCol) == 101)
                        {
                            origEwokCol = ewokCol;
                            ewok.setUserCol(userInput);
                            ewokCol = userInput;
                        }
                    
                        else{
                            while (isValid(userInput, ewokCol) != 101)
                            {
                                System.out.println("This move is invalid.");
                                System.out.println("Enter the column (0-4) to which you would like to move:");
                                userInput = input.nextInt();
                                
                                if (isValid(userInput, ewokCol) == 101)
                                {
                                    origEwokCol = ewokCol;
                                    ewok.setUserCol(userInput);
                                    ewokCol = userInput;
                                    
                                    break;
                                }
                            }
                        }
                
                    //ask for row input
                System.out.println("Enter the row (0-4) to which you would like to move:");
                userInput = input.nextInt();
                    
                    //check to see if move is valid
                    if (isValid(userInput, ewokRow) == 101)
                        {
                            //check to see if move is diagonal
                                if (isDiagonal(origEwokRow, userInput, 
                                origEwokCol, ewokCol) == 101)
                                {
                                    origEwokRow = ewokRow;
                                    ewok.setUserRow(userInput);
                                    ewokRow = userInput;
                                }
                                else{
                                    while (isDiagonal(origEwokRow, userInput, 
                                    origEwokCol, ewokCol) != 101)
                                    {
                                        System.out.println("Diagonal moves are not permitted.");
                                        System.out.println("Enter the row (0-4) to which you would like to move:");
                                        userInput = input.nextInt();
                                        
                                        if (isValid(userInput, ewokRow) == 101 && 
                                        isDiagonal(origEwokRow, userInput, 
                                        origEwokCol, ewokCol) == 101)
                                        {
                                            origEwokRow = ewokRow;
                                            ewok.setUserRow(userInput);
                                            ewokRow = userInput;
                            
                                            break;
                                        }
                                    }   
                                }
                            }
                     else {
                                while (isValid(userInput, ewokRow) != 101)
                                {
                                    System.out.println("This move is invalid.");
                                    System.out.println("Enter the row (0-4) to which you would like to move:");
                                    userInput = input.nextInt();
                        
                                    if (isValid(userInput, ewokRow) == 101)
                                    {
                                        origEwokRow = ewokRow;
                                        ewok.setUserRow(userInput);
                                        ewokRow = userInput;
                                        
                                        break;
                                    }
                                }
                            }
                
                //check to see if move results in a win
                int checkWin = isWin(ewokRow, ewokCol, wookieRow, wookieCol);
                displayWin(checkWin);
                
                //if move results in a win, exit game
                    if (checkWin == 99)
                    {
                        System.out.println("Goodbye!");
                        break;
                    }
                
                //wookie's turn
                System.out.println("Wookie, it is your move.");
                System.out.println("Enter the column (0-4) to which you would like to move:");
                userInput = input.nextInt();
                
                    if (isValid(userInput, wookieCol) == 101)
                        {
                            origWookieCol = wookieCol;
                            wookie.setUserCol(userInput);
                            wookieCol = userInput;
                        }
                    else{
                            while (isValid(userInput, wookieCol) != 101)
                            {
                                System.out.println("This move is invalid.");
                                System.out.println("Enter the column (0-4) to which you would like to move:");
                                userInput = input.nextInt();
                                
                                if (isValid(userInput, wookieCol) == 101)
                                {
                                    origWookieCol = wookieCol;
                                    wookie.setUserCol(userInput);
                                    wookieCol = userInput;
                            
                                    break;
                                }
                            }
                        }
                
                    //ask for row move
                System.out.println("Enter the row (0-4) to which you would like to move:");
                userInput = input.nextInt();
                
                //check to see if move is valid
                    if (isValid(userInput, wookieRow) == 101)
                        {
                            //check to see if move is diagonal
                                if (isDiagonal(origWookieRow, userInput, 
                                origWookieCol, wookieCol) == 101)
                                {
                                    origWookieRow = wookieRow;
                                    wookie.setUserRow(userInput);
                                    wookieRow = userInput;
                                }
                                else{
                                    while (isDiagonal(origWookieRow, userInput, 
                                    origWookieCol, wookieCol) != 101)
                                    {
                                        System.out.println("Diagonal moves are not permitted.");
                                        System.out.println("Enter the row (0-4) to which you would like to move:");
                                        userInput = input.nextInt();
                                        
                                        if (isValid(userInput, wookieRow) == 101 &&
                                        isDiagonal(origWookieRow, userInput, 
                                        origWookieCol, wookieCol) == 101)
                                        {
                                            origWookieRow = wookieRow;
                                            wookie.setUserRow(userInput);
                                            wookieRow = userInput;
                            
                                            break;
                                        }
                                    }
                                }
                        }
                    else{
                        while (isValid(userInput, wookieRow) != 101)
                        {
                            System.out.println("This move is invalid.");
                            System.out.println("Enter the row (0-4) to which you would like to move:");
                            userInput = input.nextInt();
                            
                            if (isValid(userInput, wookieRow) == 101)
                            {
                                origWookieRow = wookieRow;
                                wookie.setUserRow(userInput);
                                wookieRow = userInput;
                                
                                break;
                            }
                        }
                    }
                
                checkWin = isWin(ewokRow, ewokCol, wookieRow, wookieCol);
                displayWin(checkWin);
                    if (checkWin == 99)
                    {
                        System.out.println("Goodbye!");
                        break;
                    }
                
                ProjectX.changePlanet(origEwokRow, ewokRow, origEwokCol, ewokCol,
                origWookieRow, wookieRow, origWookieCol, wookieCol, planet);
                
                ProjectX.saveGame(planet);
                
                //display planet
                System.out.println("\n");
                displayPlanet(planet);
            }
        
            else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    
    //check to make sure move is valid 
    public static int isValid(int input, int currentLoc)
    {
        int isVlid = 0; 
        int difference = input - currentLoc;
        
        if (Math.abs(difference) > 1)
        {
            isVlid = 0;
        }
        else
        {
            isVlid = 101;
        }
        
        return isVlid;
    }
    
    //check to make sure move is not diagonal
    public static int isDiagonal(int origRow, int row, int origCol, int col)
    {   
        int isDiagonal = 0;
        int colDifference = Math.abs(origCol - col);
        int rowDifference = Math.abs(origRow - row);
        
            if (colDifference == 1 && rowDifference == 0)
            {
                isDiagonal = 101;
            }
            else if (colDifference == 0 && rowDifference == 1)
            {
                isDiagonal = 101;
            }
            else if (colDifference == 0 && rowDifference == 0)
            {
                isDiagonal = 101;
            }
            else 
            {
                isDiagonal = 0;
            }
        
        return isDiagonal;
    }
    
    //check to see if move is a win
    public static int isWin(int ewkRw, int ewkCl, int wkRw, int wkCl)
    {
        int isWin = 0;
        
            if (ewkRw == wkRw && ewkCl == wkCl)
            {
                isWin = 99;
            }
            else 
            {
                isWin = 0;
            }
        
        return isWin;
    }
    
    //display win
    public static void displayWin (int isWin)
    {
        if (isWin == 99)
        {
            System.out.println("You win! Dagobah is yours.");
        }
    }
    
    //display planet
    public static void displayPlanet (char[][] planet) throws java.io.IOException
    {
        
        for (int r = 0; r < planet.length; r++)
        {
            for (int c = 0; c < planet[r].length; c++)
            {
                System.out.print("|" + planet[r][c]);
            } // end for col loop
            System.out.println("|");
        } // end for row loop
    }
    
}