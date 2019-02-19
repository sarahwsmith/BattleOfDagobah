/*
 * Server Class for Project X
 * CSC 225
 * Sarah Wigg
 */

import java.util.*;
import java.io.*;


public class ProjectX
{
    private int row; //row entered by user
    private int column;//column entered by user
    private int userRow;//row of user
    private int userCol;//column of user
    
    //constructor methods
    
    public ProjectX ()
    {
       row = 0;
       column = 0;
    }
    
    public ProjectX (int r, int c)
    {
        row = r;
        column = c;
    }
    
    //Mutator methods
    //set user location
    public void setUserRow (int row)
    {
        row = row;
    }
    public void setUserCol (int col)
    {
        column = col;
    }
    
    //accessor methods
    //get user location
    public int getUserRow()
    {
        return row;
    }
    
    public int getUserCol()
    {
        return column;
    }
    
    //load planet text file into program
    public static void loadPlanet(char[][] plnt) throws java.io.IOException
    {
    	//update directory here
        String filNm = "/UPDATE DIRECTORY/Planet.txt";

        Scanner input = new Scanner(new File(filNm));

        for (int row = 0; row < plnt.length; row++)
        {
            for (int col = 0; col < 5; col++)
            {
               plnt[row][col] = input.next().charAt(0);
            }
        }

        input.close();// Always, always, ALWAYS remember to close the file!
    }
    
    //save planet to txt file when escape key is pressed
    public static void saveGame(char[][] planet) throws java.io.IOException
    {
    	//update directory path here
        PrintWriter output = new PrintWriter("/UPDATE DIRECTORY/Planet.txt");
        char letter = ' ';
            for (int row = 0; row < planet.length; row++)
            {
                for (int col = 0; col < planet[row].length; col++)
                {
                // Avoids null pointer exception
                letter = planet[row][col];
                if (letter == (' '))
                     planet[row][col] = 'o';
                // Writes the contents of the cell out to an external file
                output.println(planet[row][col]); 
            } // end for-col loop
        } // end for-row loop

        // Remember to close that file!
        output.close();
    } // end saveGame method
   
    //search for starting row for ewok
    public static int startingERow(char[][] planet)
    throws java.io.IOException
    {
        int currentRow = 0;
        int currentCol = 0;
        
        char target = 'E';
        
        for ( int r = 0; r <planet.length; r++) {
            for(int c = 0; c < planet[r].length; c++) {
                if ( planet[r][c] == target) {
                    currentRow = r;
                    break;
                }
            }
        }
        
        return currentRow;
    }
    
    
    //search for starting col for ewok
    public static int startingECol(char[][] planet)
    throws java.io.IOException
    {
        int currentRow = 0;
        int currentCol = 0;
        
        char target = 'E';
        
        for ( int r = 0; r <planet.length; r++) {
            for(int c = 0; c < planet[r].length; c++) {
                if ( planet[r][c] == target) {
                    currentCol = c;
                    break;
                }
            }
        }
        
        return currentCol;
    }
    
    //search for starting row for wookie
    public static int startingWRow(char[][] planet)
    throws java.io.IOException
    {
        int currentRow = 0;
        int currentCol = 0;
        
        char target = 'W';
        
        for ( int r = 0; r <planet.length; r++) {
            for(int c = 0; c < planet[r].length; c++) {
                if ( planet[r][c] == target) {
                    currentRow = r;
                    break;
                }
            }
        }
        
        return currentRow;
    }
    
    //search for starting column for ewok
    public static int startingWCol(char[][] planet)
    throws java.io.IOException
    {
        int currentRow = 0;
        int currentCol = 0;
        
        char target = 'W';
        
        for ( int r = 0; r <planet.length; r++) {
            for(int c = 0; c < planet[r].length; c++) {
                if ( planet[r][c] == target) {
                    currentCol = c;
                    break;
                }
            }
        }
        
        return currentCol;
    }
    
    //change planetary display based on user entry
    public static void changePlanet(int ewkRow, int newEwkRow, int ewkCol, 
    int newEwkCol,int wookRow, int newWookRow,int wookCol, int newWookCol, char[][] planet)
    throws java.io.IOException
    {
        planet[newEwkRow][newEwkCol] = 'E';
        planet[newWookRow][newWookCol] = 'W';
        planet[ewkRow][ewkCol] = 'O';
        planet[wookRow][wookCol] = 'O';
    }
    
    //display welcome message
    public static void welcomeString()
    {
        System.out.print("A LONG TIME AGO IN A GALAXY FAR, FAR AWAY...");
        
               //make program pause
               try {
                   Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        
        System.out.println("\n" + "\n" + "Welcome to the battle for the planet Dagobah." +
               "\n" + "Dagobah has been reduced to two-dimensions, and is now five sectors by five sectors." + "\n"
               + "Both the Ewok and the Wookie currently have places on the planet," +
               "\n" + "E for the Ewok and W for the Wookie.");
               
               //make program pause
               try {
                   Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
        System.out.println("\n" + "\n"  + "Each player may move only one sector per turn." +
               "\n" + "He may move up, down, left, or right.  No diagonal moves are permitted." +
               "\n" + "To win the battle,one player must land on the other player’s sector." +
               "\n" + "The player landing on the other’s sector wins the battle.");
               
               //make program pause
               try {
                   Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        
        System.out.println("The battle begins...");
    }
}