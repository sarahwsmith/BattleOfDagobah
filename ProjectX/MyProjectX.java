import java.util.*;
import java.io.*;
public class MyProjX
{
    //define class level variables
    public static char player;
    public static int wkRw;   
    public static int wkCl;
    public static int ewkRw;
    public static int ewkCl;
    
    public static void main(String[] args) throws java.io.IOException
    {
        //initialize location variables and scanner
        Scanner input = new Scanner(System.in);
        player = 'E';
        ewkRw = 0;
        ewkCl = 0;
        wkRw = 0;
        wkCl = 0;         
        
        char[][] planet = new char[5][5];
        int choice = 1;
        int whereFrom = 0;
        boolean checkWin = false;
        
        //display instructions and welcome message
        welcomeString(); 
        
        //load planet from data file
        System.out.println("\n");
        System.out.println("Would you like to load a saved battle or start from the beginning?");
        System.out.println("Press 1 for load saved battle, 2 for start from beginning.");
        whereFrom = input.nextInt();
                
            if (whereFrom == 1)
            {
                loadPlanet(planet);
            }
            else if (whereFrom == 2)
            {
                loadOriginalPlanet(planet);
            }
                       
        while (choice != 2)
        {
            System.out.println("\n");
            System.out.println("Would you like to continue the battle?");
            System.out.println("Press 1 for yes, 2 for no.");
            choice = input.nextInt();
            
            if (choice == 1)
            {
                
                //display planet
                displayPlanet(planet);
                
                //take turn
                takeTurn(planet); 
                
                //check it turn results in a win
                checkWin = isWin(planet);
                if (checkWin == true)
                {
                    displayPlanet(planet);
                    displayWin();
                    break;
                }
            }
            else 
            {
                saveGame(planet);
                System.out.println("Goodbye! Your game has been saved. Return soon, the galaxy needs you.");
            } // end else
        } // end while
    } // end main

    public static void takeTurn(char[][] planet)throws java.io.IOException
    {
            int currRow = 0;
            int currCol = 0;
            int newRow = 0;
            int newCol = 0;
            Scanner input = new Scanner(System.in);
            
            //get current location -- row and column
            currRow = getCurrent(planet, 'R');
            currCol = getCurrent(planet, 'C');
            
            if (player == 'E')
                System.out.println("Ewok, it is your move.");
                else
                System.out.println("Wookie, it is your move.");     
                
                System.out.print("Enter the column (0-4) to which you would like to move:");
                newCol = input.nextInt();
                System.out.print("Enter the row (0-4) to which you would like to move:");
                newRow = input.nextInt();
                
                // check to see if column entry is valid
                if (isValid(newCol, currCol, newRow, currRow) == 101)
                {
                    if (player == 'E')
                    {
                        ewkRw = newRow;
                        ewkCl = newCol;
                    }
                    else
                    {
                        wkRw = newRow;
                        wkCl = newCol;
                    }
                }
                else {
                    while(isValid(newCol, currCol, newRow, currRow) != 101)
                        {
                            if (player == 'E')
                            System.out.println("That move was invalid. Ewok, please try again.");
                            else
                            System.out.println("That move was invalid. Wookie, please try again.");     
                            
                            System.out.print("Enter the column (0-4) to which you would like to move:");
                            newCol = input.nextInt();
                            System.out.print("Enter the row (0-4) to which you would like to move:");
                            newRow = input.nextInt();
                            
                            if(isValid(newCol, currCol, newRow, currRow) == 101)
                            {
                                if (player == 'E')
                                {
                                    ewkRw = newRow;
                                    ewkCl = newCol;
                                }
                                else
                                {
                                    wkRw = newRow;
                                    wkCl = newCol;
                                }
                                break;
                            }
                        }
                    
                }
                
                changePlanet(planet, newCol, currCol, newRow, currRow);
                
            //changes whose turn it is once valid move is entered
                if (player == 'E')
                {
                    player = 'W';
                }   
                else
                {
                    player = 'E';
                }
    }        
    
    //change planetary display based on user entry
    public static void changePlanet(char[][] planet, int newCol, int currCol, int newRow, int currRow)
    throws java.io.IOException
    {
        if (player == 'E')
        {
            planet[newRow][newCol] = 'E';
            planet[currRow][currCol] = 'O';
        }
        else {
            planet[newRow][newCol] = 'W';
            planet[currRow][currCol] = 'O';
        }
    }
    
    //check to make sure move is valid 
    public static int isValid(int newCol, int currCol, int newRow, int currRow)
    {
        int isVlid = 0; 
        int colDifference = Math.abs(newCol - currCol);
        int rowDifference = Math.abs(newRow - currRow);
        
        if (colDifference == 1 && rowDifference == 0)
        {
            isVlid = 101;
        }
        else if (colDifference == 0 && rowDifference == 1)
        {
            isVlid = 101;
        }
        else if (colDifference == 0 && rowDifference == 0)
        {
            isVlid = 101;
        }
        else
        {
            isVlid = 0;
        }
        
        return isVlid;
    }
    
    //check to see if move is a win
    public static boolean isWin(char[][] planet)
    {
        boolean foundWinner = false;
        if (ewkRw == wkRw && ewkCl == wkCl)
            foundWinner = true;       
        return foundWinner;
    }
    
    public static void displayWin ()
    {
        System.out.println("You win! The battle has ended. Dagobah is yours.");
        System.out.println("Princess Leia thanks you for your service.");
        System.out.println("   |l");
        System.out.println("   ||");
        System.out.println("   ||");
        System.out.println("  .'`.");
        System.out.println("  |==|");
        System.out.println("  |==|           ___");
        System.out.println("  |==|         x88|88x");
        System.out.println("  |==|      ,8V888|8X8V8b");
        System.out.println("  |==|     (888888|88X88X)");
        System.out.println("  `.=|    (8888P~  ~788X88)");
        System.out.println("   |=|    (888,_    _  888)");
        System.out.println("  .l l____ (88~~~| '~~ 888'");
        System.out.println("  |::::::|  ~|: :|,    |");
        System.out.println("  |:__|~~    `.:___   /");
        System.out.println("  //,==\\      `.`--~ /-..");
        System.out.println("  ( (~)|    .-:`----'.'  ~~`-.");
        System.out.println("  |\\ \\_| .-~::|\\----'         `.");
        System.out.println("  |:\\  `: |                   | :");
        System.out.println("  `--|  | |                  .' |");
        System.out.println("     |  | |               LS |  |");
        
        //Leia Organa art is credit to ascii-art.de
    }
    
    //display planet
    public static void displayPlanet (char[][] planet) throws java.io.IOException
    {
        System.out.println("\n");
        for (int r = 0; r < planet.length; r++)
        {
            for (int c = 0; c < planet[r].length; c++)
            {
                System.out.print("|" + planet[r][c]);
            } // end for col loop
            System.out.println("|");
        } // end for row loop
    }
    
    public static int getCurrent(char[][] planet, char location)
    {
        int place = 0;
        for (int r = 0; r < 5; r++)
        {
            for (int c = 0; c < 5; c++)
            {
                if (planet[r][c] == player)
                {
                    if (location == 'R')
                        place = r;
                    else
                        place = c;
                }  // end if
            } // end for-col
       } // end for-row
       return place;
    } // end getCurrent

    // load planet text file into program from original planet -- blank canvas
    public static void loadOriginalPlanet(char[][] plnt) throws java.io.IOException
    {
        String filNm = "/UPDATE DIRECTORY/OriginalPlanet.txt";

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
    
    // load planet text file into program from saved game
    public static void loadPlanet(char[][] plnt) throws java.io.IOException
    {
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
               "\n" + "Dagobah has been reduced to two-dimensions, and is now 5 sectors by 5 sectors." + "\n"
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
    
    //save planet to txt file when escape key is pressed
    public static void saveGame(char[][] planet) throws java.io.IOException
    {
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
}​
