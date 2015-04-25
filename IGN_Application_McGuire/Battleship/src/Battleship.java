/**
 * Created by Harrison on 4/8/2015.
 *

 IIIII       GGGGGGGGGGGGGGGG             NNNNNNNNNN         NNNNNN
 IIIII       GGGGGGGGGGGGGGGG             NNNNNN NNNN        NNNNNN
 IIIII       GGGGGGGGGGGGGGGG             NNNNNN  NNNN       NNNNNN
 IIIII       GGGGGGG                      NNNNNN   NNNN      NNNNNN
 IIIII       GGGGGGG                      NNNNNN    NNNN     NNNNNN
 IIIII       GGGGGGG     GGGGGGGGG        NNNNNN     NNNN    NNNNNN
 IIIII       GGGGGGG     GGGGGGGGG        NNNNNN      NNNN   NNNNNN
 IIIII       GGGGGGG       GGGGGGG        NNNNNN       NNNN  NNNNNN
 IIIII       GGGGGGGGGGGGGGGGGGGGG        NNNNNN        NNNN NNNNNN
 IIIII       GGGGGGGGGGGGGGGGGGGGG        NNNNNN         NNNNNNNNNN


                            BBBBBBBBBBB
                        BBBBBBBBBBBBBBBBBBB
  BBBBBBBBBBBBB         BBBBBBBBBBBBBBBBBBB         BBBBBBBBBBBBBBBBB
        BBBBBBB         BBBBBBBBBBBBBBBBBBB         BBBBBBB
 BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
   BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
     BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
       BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
        BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
 */

import java.util.*;

public class Battleship {

    public static void main(String[] args) {


        System.out.println("Welcome to BATTLESHIP!!!....except there's no Rihanna.\nBut if you win, you will get some Rihanna.\nYou will have to win to find out...or just check the source code to see a crappy image of a girl designed with lines and semicolons\n");
        System.out.println("The frist thing is to set up your board\n");


        Scanner input = new Scanner(System.in);
        iBoard();
        createBattleshipBoard(boardCoords);
        System.out.println("\n");

        populateMyShips();
        System.out.println("\nYour current board is below. Your ships are marked with '|'. \n");
        printCurrentBoard1();
        System.out.println("\n");
        populateMyShips2();
        System.out.println("\nYour current board is below. Your ships are marked with '|'. \n");
        printCurrentBoard2();
        System.out.println("\n");
        populateMyShips3();
        System.out.println("\nYour current board is below. Your ships are marked with '|'. \n");
        printCurrentBoard3();
        System.out.println("\n");
        populateMyShips4();
        System.out.println("\nYour current board is below. Your ships are marked with '|'. \n");
        printCurrentBoard4();
        System.out.println("\n");
        populateMyShips5();
        System.out.println("\nYour current board is below. Your ships are marked with '|'. \n");
        printCurrentBoard5();
        System.out.println("\n");


        System.out.println("Waiting for your opponent to set his board...\n");
        populateEnemyList();


        System.out.println("It's Game Time...Good Luck\n");

        System.out.println("You'll be prompted to input a row and then a column for your attack coordinates.\nYou'll have one turn and then the AI will have one turn." +
                "\nYou will switch off until all the ships on a player's board have been destroyed. " +
                "\nA missed hit will be indicated by a '*', if you hit a battleship it will be indicated by a 'X'." +
                "\nAn empty mark on the board is indicated by a 'O'." +
                "\nGo get 'em soldier.\n");

        createBattleshipBoard(boardCoords);

       while(successfulHitList.size() < 17 && enemySuccessfulHitList.size() < 17){

           coordinateInput();

           populateHitList(successfulHitList);

           printEnemyBoard(successfulHitList, missedHitList);

           //break the while loop if player wins. We need to do this before the opponent has the ability to make his last hit
           //this prevents both players winning at the same time
           if(successfulHitList.size() == 17){
               break;
           }

           enemyAttack();

           printYourBoard(enemySuccessfulHitList, enemyMissedHitList);

       }

        //if player wins, show Riahnna
        if(successfulHitList.size()==17) {
            System.out.println("\nCONGRATULATIONS!!! YOU FRICKIN' WON!!! YOU ARE AWESOME!!!\nNOW AS PROMISED, BELOW IS RIHANNA...kinda\n");
            System.out.println("    {((((((}");
            System.out.println("   {)))))))))");
            System.out.println("  ((((( _   _))");
            System.out.println("  ))))))).  (.((");
            System.out.println("  (((((((    > )))");
            System.out.println(" )))))))    - /(");
            System.out.println("  (((((   _ _| )))");
        }

        //if AI opponent wins show Liam Neeson
        if(enemySuccessfulHitList.size()==17) {
            System.out.println("\nGAME OVER.\nThe enemy attacked and they attacked hard and sunk all your battleships.\n" +
                            "Try again and do it for Rihanna.\nAnd then start an online partition for Battleship 2 - The Return of More Battleships.\n" +
                            "You lost so instead of Rihanna, you get Liam Neeson. ENJOY!!");
            System.out.println(" ))))))(((((((((  ");
            System.out.println("|    _     _    | ");
            System.out.println("|   |_|   |_|   | ");
            System.out.println("|               | ");
            System.out.println("|       {}      | ");
            System.out.println(" |     ____    | ");
            System.out.println("  |___________| ");
        }

    }


    //global variables to establish all checks and balances for the game
    //I know using global variables is usually bad programming practice
    //but I stuck with global variables and I put everything one script
    //to ensure the game functioned properly and the variables were kept in check.

    private static char row = '\0';

    private static List<Coordinates> missedHitList = new ArrayList<Coordinates>();
    private static List<Coordinates> myDisplayList = new ArrayList<Coordinates>();
    private static List<Coordinates> successfulHitList = new ArrayList<Coordinates>();
    private static List<Coordinates> boardCoords = new ArrayList<Coordinates>();
    private static ArrayList<String> letterGrid = new ArrayList<String>();

    private static int[] ones = new int[] {-1, 1};
    private static Coordinates[] enemyShips = new Coordinates[17];
    private static Coordinates[] myShips = new Coordinates[17];

    private static List<Coordinates> enemyMissedHitList = new ArrayList<Coordinates>();
    private static List<Coordinates> enemySuccessfulHitList = new ArrayList<Coordinates>();


    private static boolean enemyShip1Destroyed = false;
    private static boolean enemyShip2Destroyed = false;
    private static boolean enemyShip3Destroyed = false;
    private static boolean enemyShip4Destroyed = false;
    private static boolean enemyShip5Destroyed = false;


    private static boolean ship1Destroyed = false;
    private static boolean ship2Destroyed = false;
    private static boolean ship3Destroyed = false;
    private static boolean ship4Destroyed = false;
    private static boolean ship5Destroyed = false;


    private static boolean shipHit0R = false;
    private static boolean shipHit0L = false;
    private static boolean shipHit0U = false;
    private static boolean shipHit0D = false;

    private static boolean shipHit2R = false;
    private static boolean shipHit2R2 = false;
    private static boolean shipHit2L = false;
    private static boolean shipHit2L2 = false;
    private static boolean shipHit2U = false;
    private static boolean shipHit2U2 = false;
    private static boolean shipHit2D = false;
    private static boolean shipHit2D2 = false;


    private static boolean shipHit3R = false;
    private static boolean shipHit3R2 = false;
    private static boolean shipHit3L = false;
    private static boolean shipHit3L2 = false;
    private static boolean shipHit3U = false;
    private static boolean shipHit3U2 = false;
    private static boolean shipHit3D = false;
    private static boolean shipHit3D2 = false;

    private static boolean shipHit4R = false;
    private static boolean shipHit4R2 = false;
    private static boolean shipHit4R3 = false;
    private static boolean shipHit4L = false;
    private static boolean shipHit4L2 = false;
    private static boolean shipHit4L3 = false;
    private static boolean shipHit4U = false;
    private static boolean shipHit4U2 = false;
    private static boolean shipHit4U3 = false;
    private static boolean shipHit4D = false;
    private static boolean shipHit4D2 = false;
    private static boolean shipHit4D3 = false;

    private static boolean shipHit5R = false;
    private static boolean shipHit5R2 = false;
    private static boolean shipHit5R3 = false;
    private static boolean shipHit5R4 = false;
    private static boolean shipHit5L = false;
    private static boolean shipHit5L2 = false;
    private static boolean shipHit5L3 = false;
    private static boolean shipHit5L4 = false;
    private static boolean shipHit5U = false;
    private static boolean shipHit5U2 = false;
    private static boolean shipHit5U3 = false;
    private static boolean shipHit5U4 = false;
    private static boolean shipHit5D = false;
    private static boolean shipHit5D2 = false;
    private static boolean shipHit5D3 = false;
    private static boolean shipHit5D4 = false;


    private static int hitRow0;
    private static int hitRow2;
    private static int hitRow5;
    private static int hitRow8;
    private static int hitRow12;

    private static int hitCol0;
    private static int hitCol2;
    private static int hitCol5;
    private static int hitCol8;
    private static int hitCol12;


    private static boolean attackingShip1 = false;
    private static boolean attackingShip2 = false;
    private static boolean attackingShip3 = false;
    private static boolean attackingShip4 = false;
    private static boolean attackingShip5 = false;

    private static boolean ship1FirstHit = false;
    private static boolean ship2FirstHit = false;
    private static boolean ship3FirstHit = false;
    private static boolean ship4FirstHit = false;
    private static boolean ship5FirstHit = false;

    private static int hitSize1 = 0;
    private static int hitSize2 = 0;
    private static int hitSize3 = 0;
    private static int hitSize4 = 0;


    //Ask the player where they want to place their ships
    //5 seperate functions for placing ships to ensure the arrayList of
    //hits were hard coded in for simplicity.
    //You should never repeat yourself in code like I do below
    //but I did it to ensure my lists were exact.
    private static void populateMyShips(){

        System.out.println("Where do you want to put your first battleship, it will only take up 2 spots.\nEnter a row from A-F and column 1-10. After you enter a coordinate I will ask you to enter a direction.");
        Scanner input = new Scanner(System.in);
        char rowChar = '\0';
        int rowInt = 0;
        int col = -1;
        String direction = "";
        boolean acceptRow = false;
        boolean acceptCol = false;

        boolean ship2Placed = false;

        while(!ship2Placed) {
            while (!acceptRow) {
                System.out.print("Row: ");
                rowChar = input.next().toUpperCase().charAt(0);

                switch (rowChar) {
                    case 'A':
                        rowInt = 65;
                        acceptRow = true;
                        break;
                    case 'B':
                        rowInt = 66;
                        acceptRow = true;
                        break;
                    case 'C':
                        rowInt = 67;
                        acceptRow = true;
                        break;
                    case 'D':
                        rowInt = 68;
                        acceptRow = true;
                        break;
                    case 'E':
                        rowInt = 69;
                        acceptRow = true;
                        break;
                    case 'F':
                        rowInt = 70;
                        acceptRow = true;
                        break;
                    case 'G':
                        rowInt = 71;
                        acceptRow = true;
                        break;
                    case 'H':
                        rowInt = 72;
                        acceptRow = true;
                        break;
                    case 'I':
                        rowInt = 73;
                        acceptRow = true;
                        break;
                    case 'J':
                        rowInt = 74;
                        acceptRow = true;
                        break;
                    default:
                        System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                        break;
                }
            }


            while (!acceptCol) {
                try {
                    System.out.print("Col: ");
                    col = input.nextInt();
                    if (col > 10 || col < 0) {
                        System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                    } else {
                        acceptCol = true;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                    input.next();
                }
            }

            System.out.println("Choose a direction you want your ship to go. Enter either of the following 'up', 'down', 'left', or 'right'.");
            System.out.print("Direction: ");
            direction = input.next().toUpperCase();

            if (direction.equals("UP")) {
                if (((rowInt - 1) >= 65)) {
                    myShips[0] = new Coordinates(((char) rowInt), col);
                    myShips[1] = new Coordinates(((char) (rowInt - 1)), col);
                    ship2Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship2Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("DOWN")) {
                if (((rowInt + 1) <= 74)) {
                    myShips[0] = new Coordinates(((char) rowInt), col);
                    myShips[1] = new Coordinates(((char) (rowInt + 1)), col);
                    ship2Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship2Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("RIGHT")) {
                if (((col + 1) <= 10)) {
                    myShips[0] = new Coordinates(((char)rowInt), col);
                    myShips[1] = new Coordinates(((char)rowInt), (col + 1));
                    ship2Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship2Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("LEFT")) {
                if (((col - 1) >= 1)) {
                    myShips[0] = new Coordinates(((char)rowInt), col);
                    myShips[1] = new Coordinates(((char)rowInt), (col - 1));
                    ship2Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship2Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

        }
    }




    private static void populateMyShips2() {

        System.out.println("Where do you want to put your second battleship, it will take up 3 spots.\nEnter a row from A-F and column 1-10. After you enter a coordinate I will ask you to enter a direction.");
        Scanner input = new Scanner(System.in);
        char rowChar = '\0';
        int rowInt = 0;
        int col = -1;
        String direction = "";
        boolean acceptRow = false;
        boolean acceptCol = false;

        boolean ship3Placed1 = false;


        while(!ship3Placed1) {
            while (!acceptRow) {
                System.out.print("Row: ");
                rowChar = input.next().toUpperCase().charAt(0);

                switch (rowChar) {
                    case 'A':
                        rowInt = 65;
                        acceptRow = true;
                        break;
                    case 'B':
                        rowInt = 66;
                        acceptRow = true;
                        break;
                    case 'C':
                        rowInt = 67;
                        acceptRow = true;
                        break;
                    case 'D':
                        rowInt = 68;
                        acceptRow = true;
                        break;
                    case 'E':
                        rowInt = 69;
                        acceptRow = true;
                        break;
                    case 'F':
                        rowInt = 70;
                        acceptRow = true;
                        break;
                    case 'G':
                        rowInt = 71;
                        acceptRow = true;
                        break;
                    case 'H':
                        rowInt = 72;
                        acceptRow = true;
                        break;
                    case 'I':
                        rowInt = 73;
                        acceptRow = true;
                        break;
                    case 'J':
                        rowInt = 74;
                        acceptRow = true;
                        break;
                    default:
                        System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                        break;
                }
            }

            while (!acceptCol) {
                try {
                    System.out.print("Col: ");
                    col = input.nextInt();
                    if (col > 10 || col < 0) {
                        System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                    } else {
                        acceptCol = true;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                    input.next();
                }
            }

            System.out.println("Choose a direction you want your ship to go. Enter either of the following 'up', 'down', 'left', or 'right'.");
            System.out.print("Direction: ");
            direction = input.next().toUpperCase();

            if (direction.equals("UP")) {
                if (((rowInt - 2) >= 65)) {
                    myShips[2] = new Coordinates(((char) rowInt), col);
                    myShips[3] = new Coordinates(((char) (rowInt - 1)), col);
                    myShips[4] = new Coordinates(((char) (rowInt - 2)), col);
                    ship3Placed1 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed1 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("DOWN")) {
                if (((rowInt + 2) <= 74)) {
                    myShips[2] = new Coordinates(((char) rowInt), col);
                    myShips[3] = new Coordinates(((char) (rowInt + 1)), col);
                    myShips[4] = new Coordinates(((char) (rowInt + 2)), col);
                    ship3Placed1 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed1 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("RIGHT")) {
                if (((col + 1) <= 10)) {
                    myShips[2] = new Coordinates(((char)rowInt), col);
                    myShips[3] = new Coordinates(((char)rowInt), (col + 1));
                    myShips[4] = new Coordinates(((char)rowInt), (col + 2));
                    ship3Placed1 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed1 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("LEFT")) {
                if (((col - 1) >= 1)) {
                    myShips[2] = new Coordinates(((char)rowInt), col);
                    myShips[3] = new Coordinates(((char)rowInt), (col - 1));
                    myShips[4] = new Coordinates(((char)rowInt), (col - 2));
                    ship3Placed1 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed1 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if(myShips[2] != null && myShips[3] != null && myShips[4] != null) {
                for (int i = 2; i < 5; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (myShips[i].equals(myShips[j]) && ship3Placed1) {
                            System.out.println("Oops, you accidentally overlapped your ships, try a new spot on the map");
                            ship3Placed1 = false;
                            acceptRow = false;
                            acceptCol = false;
                            break;
                        }
                    }
                }
            }

        }
    }


    private static void populateMyShips3() {

        System.out.println("Where do you want to put your third battleship, it will take up 3 spots.\nEnter a row from A-F and column 1-10. After you enter a coordinate I will ask you to enter a direction.");
        Scanner input = new Scanner(System.in);
        char rowChar = '\0';
        int rowInt = 0;
        int col = -1;
        String direction = "";
        boolean acceptRow = false;
        boolean acceptCol = false;

        boolean ship3Placed2 = false;


        while(!ship3Placed2) {
            while (!acceptRow) {
                System.out.print("Row: ");
                rowChar = input.next().toUpperCase().charAt(0);

                switch (rowChar) {
                    case 'A':
                        rowInt = 65;
                        acceptRow = true;
                        break;
                    case 'B':
                        rowInt = 66;
                        acceptRow = true;
                        break;
                    case 'C':
                        rowInt = 67;
                        acceptRow = true;
                        break;
                    case 'D':
                        rowInt = 68;
                        acceptRow = true;
                        break;
                    case 'E':
                        rowInt = 69;
                        acceptRow = true;
                        break;
                    case 'F':
                        rowInt = 70;
                        acceptRow = true;
                        break;
                    case 'G':
                        rowInt = 71;
                        acceptRow = true;
                        break;
                    case 'H':
                        rowInt = 72;
                        acceptRow = true;
                        break;
                    case 'I':
                        rowInt = 73;
                        acceptRow = true;
                        break;
                    case 'J':
                        rowInt = 74;
                        acceptRow = true;
                        break;
                    default:
                        System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                        break;
                }
            }

            while (!acceptCol) {
                try {
                    System.out.print("Col: ");
                    col = input.nextInt();
                    if (col > 10 || col < 0) {
                        System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                    } else {
                        acceptCol = true;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                    input.next();
                }
            }

            System.out.println("Choose a direction you want your ship to go. Enter either of the following 'up', 'down', 'left', or 'right'.");
            System.out.print("Direction: ");
            direction = input.next().toUpperCase();

            if (direction.equals("UP")) {
                if (((rowInt - 2) >= 65)) {
                    myShips[5] = new Coordinates(((char) rowInt), col);
                    myShips[6] = new Coordinates(((char) (rowInt - 1)), col);
                    myShips[7] = new Coordinates(((char) (rowInt - 2)), col);
                    ship3Placed2 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed2 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("DOWN")) {
                if (((rowInt + 2) <= 74)) {
                    myShips[5] = new Coordinates(((char) rowInt), col);
                    myShips[6] = new Coordinates(((char) (rowInt + 1)), col);
                    myShips[7] = new Coordinates(((char) (rowInt + 2)), col);
                    ship3Placed2 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed2 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("RIGHT")) {
                if (((col + 1) <= 10)) {
                    myShips[5] = new Coordinates(((char)rowInt), col);
                    myShips[6] = new Coordinates(((char)rowInt), (col + 1));
                    myShips[7] = new Coordinates(((char)rowInt), (col + 2));
                    ship3Placed2 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed2 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("LEFT")) {
                if (((col - 1) >= 1)) {
                    myShips[5] = new Coordinates(((char)rowInt), col);
                    myShips[6] = new Coordinates(((char)rowInt), (col - 1));
                    myShips[7] = new Coordinates(((char)rowInt), (col - 2));
                    ship3Placed2 = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship3Placed2 = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if(myShips[5] != null && myShips[6] != null && myShips[7] != null) {
                for (int i = 5; i < 8; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (myShips[i].equals(myShips[j]) && ship3Placed2) {
                            System.out.println("Oops, you accidentally overlapped your ships, try a new spot on the map");
                            ship3Placed2 = false;
                            acceptRow = false;
                            acceptCol = false;
                            break;
                        }
                    }
                }
            }

        }
    }

    private static void populateMyShips4() {

        System.out.println("Where do you want to put your fourth battleship, it will take up 4 spots.\nEnter a row from A-F and column 1-10. After you enter a coordinate I will ask you to enter a direction.");
        Scanner input = new Scanner(System.in);
        char rowChar = '\0';
        int rowInt = 0;
        int col = -1;
        String direction = "";
        boolean acceptRow = false;
        boolean acceptCol = false;

        boolean ship4Placed = false;


        while(!ship4Placed) {
            while (!acceptRow) {
                System.out.print("Row: ");
                rowChar = input.next().toUpperCase().charAt(0);

                switch (rowChar) {
                    case 'A':
                        rowInt = 65;
                        acceptRow = true;
                        break;
                    case 'B':
                        rowInt = 66;
                        acceptRow = true;
                        break;
                    case 'C':
                        rowInt = 67;
                        acceptRow = true;
                        break;
                    case 'D':
                        rowInt = 68;
                        acceptRow = true;
                        break;
                    case 'E':
                        rowInt = 69;
                        acceptRow = true;
                        break;
                    case 'F':
                        rowInt = 70;
                        acceptRow = true;
                        break;
                    case 'G':
                        rowInt = 71;
                        acceptRow = true;
                        break;
                    case 'H':
                        rowInt = 72;
                        acceptRow = true;
                        break;
                    case 'I':
                        rowInt = 73;
                        acceptRow = true;
                        break;
                    case 'J':
                        rowInt = 74;
                        acceptRow = true;
                        break;
                    default:
                        System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                        break;
                }
            }

            while (!acceptCol) {
                try {
                    System.out.print("Col: ");
                    col = input.nextInt();
                    if (col > 10 || col < 0) {
                        System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                    } else {
                        acceptCol = true;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                    input.next();
                }
            }

            System.out.println("Choose a direction you want your ship to go. Enter either of the following 'up', 'down', 'left', or 'right'.");
            System.out.print("Direction: ");
            direction = input.next().toUpperCase();

            if (direction.equals("UP")) {
                if (((rowInt - 2) >= 65)) {
                    myShips[8] = new Coordinates(((char) rowInt), col);
                    myShips[9] = new Coordinates(((char) (rowInt - 1)), col);
                    myShips[10] = new Coordinates(((char) (rowInt - 2)), col);
                    myShips[11] = new Coordinates(((char) (rowInt - 3)), col);
                    ship4Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship4Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("DOWN")) {
                if (((rowInt + 2) <= 74)) {
                    myShips[8] = new Coordinates(((char) rowInt), col);
                    myShips[9] = new Coordinates(((char) (rowInt + 1)), col);
                    myShips[10] = new Coordinates(((char) (rowInt + 2)), col);
                    myShips[11] = new Coordinates(((char) (rowInt + 3)), col);
                    ship4Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship4Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("RIGHT")) {
                if (((col + 1) <= 10)) {
                    myShips[8] = new Coordinates(((char)rowInt), col);
                    myShips[9] = new Coordinates(((char)rowInt), (col + 1));
                    myShips[10] = new Coordinates(((char)rowInt), (col + 2));
                    myShips[11] = new Coordinates(((char)rowInt), (col + 3));
                    ship4Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship4Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("LEFT")) {
                if (((col - 1) >= 1)) {
                    myShips[8] = new Coordinates(((char)rowInt), col);
                    myShips[9] = new Coordinates(((char)rowInt), (col - 1));
                    myShips[10] = new Coordinates(((char)rowInt), (col - 2));
                    myShips[11] = new Coordinates(((char)rowInt), (col - 3));
                    ship4Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship4Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if(myShips[8] != null && myShips[9] != null && myShips[10] != null && myShips[11] != null) {
                for (int i = 8; i < 12; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (myShips[i].equals(myShips[j]) && ship4Placed) {
                            System.out.println("Oops, you accidentally overlapped your ships, try a new spot on the map");
                            ship4Placed = false;
                            acceptRow = false;
                            acceptCol = false;
                            break;
                        }
                    }
                }
            }

        }
    }

    private static void populateMyShips5() {

        System.out.println("Where do you want to put your fifth battleship, it will take up 5 spots.\nEnter a row from A-F and column 1-10. After you enter a coordinate I will ask you to enter a direction.");
        Scanner input = new Scanner(System.in);
        char rowChar = '\0';
        int rowInt = 0;
        int col = -1;
        String direction = "";
        boolean acceptRow = false;
        boolean acceptCol = false;

        boolean ship5Placed = false;


        while(!ship5Placed) {
            while (!acceptRow) {
                System.out.print("Row: ");
                rowChar = input.next().toUpperCase().charAt(0);

                switch (rowChar) {
                    case 'A':
                        rowInt = 65;
                        acceptRow = true;
                        break;
                    case 'B':
                        rowInt = 66;
                        acceptRow = true;
                        break;
                    case 'C':
                        rowInt = 67;
                        acceptRow = true;
                        break;
                    case 'D':
                        rowInt = 68;
                        acceptRow = true;
                        break;
                    case 'E':
                        rowInt = 69;
                        acceptRow = true;
                        break;
                    case 'F':
                        rowInt = 70;
                        acceptRow = true;
                        break;
                    case 'G':
                        rowInt = 71;
                        acceptRow = true;
                        break;
                    case 'H':
                        rowInt = 72;
                        acceptRow = true;
                        break;
                    case 'I':
                        rowInt = 73;
                        acceptRow = true;
                        break;
                    case 'J':
                        rowInt = 74;
                        acceptRow = true;
                        break;
                    default:
                        System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                        break;
                }
            }

            while (!acceptCol) {
                try {
                    System.out.print("Col: ");
                    col = input.nextInt();
                    if (col > 10 || col < 0) {
                        System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                    } else {
                        acceptCol = true;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                    input.next();
                }
            }

            System.out.println("Choose a direction you want your ship to go. Enter either of the following 'up', 'down', 'left', or 'right'.");
            System.out.print("Direction: ");
            direction = input.next().toUpperCase();

            if (direction.equals("UP")) {
                if (((rowInt - 2) >= 65)) {
                    myShips[12] = new Coordinates(((char) rowInt), col);
                    myShips[13] = new Coordinates(((char) (rowInt - 1)), col);
                    myShips[14] = new Coordinates(((char) (rowInt - 2)), col);
                    myShips[15] = new Coordinates(((char) (rowInt - 3)), col);
                    myShips[16] = new Coordinates(((char) (rowInt - 4)), col);
                    ship5Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship5Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("DOWN")) {
                if (((rowInt + 2) <= 74)) {
                    myShips[12] = new Coordinates(((char) rowInt), col);
                    myShips[13] = new Coordinates(((char) (rowInt + 1)), col);
                    myShips[14] = new Coordinates(((char) (rowInt + 2)), col);
                    myShips[15] = new Coordinates(((char) (rowInt + 3)), col);
                    myShips[16] = new Coordinates(((char) (rowInt + 4)), col);
                    ship5Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship5Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("RIGHT")) {
                if (((col + 1) <= 10)) {
                    myShips[12] = new Coordinates(((char)rowInt), col);
                    myShips[13] = new Coordinates(((char)rowInt), (col + 1));
                    myShips[14] = new Coordinates(((char)rowInt), (col + 2));
                    myShips[15] = new Coordinates(((char)rowInt), (col + 3));
                    myShips[16] = new Coordinates(((char)rowInt), (col + 4));
                    ship5Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship5Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if (direction.equals("LEFT")) {
                if (((col - 1) >= 1)) {
                    myShips[12] = new Coordinates(((char)rowInt), col);
                    myShips[13] = new Coordinates(((char)rowInt), (col - 1));
                    myShips[14] = new Coordinates(((char)rowInt), (col - 2));
                    myShips[15] = new Coordinates(((char)rowInt), (col - 3));
                    myShips[16] = new Coordinates(((char)rowInt), (col - 4));
                    ship5Placed = true;
                } else {
                    System.out.println("Oops, it likes your ship is out of bounds, try again");
                    ship5Placed = false;
                    acceptRow = false;
                    acceptCol = false;
                }
            }

            if(myShips[12] != null && myShips[13] != null && myShips[14] != null && myShips[15] != null && myShips[16] != null) {
                for (int i = 12; i < 17; i++) {
                    for (int j = 0; j < 12; j++) {
                        if (myShips[i].equals(myShips[j]) && ship5Placed) {
                            System.out.println("Oops, you accidentally overlapped your ships, try a new spot on the map");
                            ship5Placed = false;
                            acceptRow = false;
                            acceptCol = false;
                            break;
                        }
                    }
                }
            }

        }
    }




    //function below will populate the enemy's board and place their ships
    //There are a lot of checks and balances to ensure the ships don't overlap
    //and stay on the board. This one function will take care of all five ships.
    //The array list is hard coded in to ensure exact coordinates.
    public static void populateEnemyList() {

        Random random = new Random();
        int max = 10;
        int min = 1;
        int cMax = 74;
        int cMin = 65;

        boolean ship2Placed = false;
        boolean ship3Placed1 = false;
        boolean ship3Placed2 = false;
        boolean ship4Placed = false;
        boolean ship5Placed = false;

        while(!ship2Placed) {
            int num1 = random.nextInt((max - min) + 1) + min;
            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;

            int num3 = random.nextInt(3) - 1;
            int newChar = c1 + (num3);

            if(num3==0) {
                int index = new Random().nextInt(ones.length);
                Integer one = ones[index];
                Integer shipLength = one;
                Integer endSpot = num1 + shipLength;
                if (endSpot >= 1 && endSpot <= 10) {
                    enemyShips[0] = new Coordinates(((char)c1), num1 );
                    enemyShips[1] = new Coordinates(((char)c1), num1 + (one) );
                    ship2Placed = true;
                } else {
                    ship2Placed = false;
                }
            }
            if(num3!=0 && (newChar >= 65 && newChar <= 74)){
                enemyShips[0] = new Coordinates(((char)c1), num1 );
                enemyShips[1] = new Coordinates(((char)(c1+num3)), num1 );
                ship2Placed = true;
            }
        }


        while(!ship3Placed1) {
            int num1 = random.nextInt((max - min) + 1) + min;
            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;

            int num3 = random.nextInt(3) - 1;
            int newChar = c1 + (num3*2);

                if(num3==0) {
                    int index = new Random().nextInt(ones.length);
                    Integer one = ones[index];
                    Integer shipLength = 2 * one;
                    Integer endSpot = num1 + shipLength;
                    if (endSpot >= 1 && endSpot <= 10) {
                        enemyShips[2] = new Coordinates(((char)c1), num1 );
                        enemyShips[3] = new Coordinates(((char)c1), num1 + (one) );
                        enemyShips[4] = new Coordinates(((char)c1), num1 + 2 * (one) );
                        ship3Placed1 = true;
                    } else {
                        ship3Placed1 = false;
                    }
                }
                if(num3!=0 && (newChar >= 65 && newChar <= 74)) {
                    enemyShips[2] = new Coordinates(((char) c1), num1);
                    enemyShips[3] = new Coordinates(((char) (c1 + num3)), num1);
                    enemyShips[4] = new Coordinates(((char) (c1 + (2 * num3))), num1);
                    ship3Placed1 = true;
                }
            if(enemyShips[2] != null && enemyShips[3] != null && enemyShips[4] != null) {
                for (int i = 2; i < 5; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (enemyShips[i].equals(enemyShips[j])) {
                            ship3Placed1 = false;
                            break;
                        }
                    }
                }
            }
        }

       while(!ship3Placed2) {
            int num1 = random.nextInt((max - min) + 1) + min;
            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;

            int num3 = random.nextInt(3) - 1;
            int newChar = c1 + (num3*2);
            if(num3==0) {
                int index = new Random().nextInt(ones.length);
                Integer one = ones[index];
                Integer shipLength = 2 * one;
                Integer endSpot = num1 + shipLength;
                if (endSpot >= 1 && endSpot <= 10) {
                    enemyShips[5] = new Coordinates(((char)c1), num1);
                    enemyShips[6] = new Coordinates(((char)c1), num1 + (one));
                    enemyShips[7] = new Coordinates(((char)c1), num1 + 2 * (one));

                    ship3Placed2 = true;
                } else {
                    ship3Placed2 = false;
                }
            }
            if(num3!=0 && (newChar >= 65 && newChar <= 74)){
                enemyShips[5] = new Coordinates(((char)c1), num1);
                enemyShips[6] = new Coordinates(((char)(c1+num3)), num1);
                enemyShips[7] = new Coordinates(((char)(c1+(2*num3))), num1);
                ship3Placed2 = true;
            }

           if(enemyShips[5] != null && enemyShips[6] != null && enemyShips[7] != null) {
               for (int i = 5; i < 8; i++) {
                   for (int j = 0; j < 5; j++) {
                       if (enemyShips[i].equals(enemyShips[j])) {
                           ship3Placed2 = false;
                           break;
                       }
                   }
               }
           }
        }

        while(!ship4Placed) {
            int num1 = random.nextInt((max - min) + 1) + min;
            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;

            int num3 = random.nextInt(3) - 1;
            int newChar = c1 + (num3*3);

            if(num3==0) {
                int index = new Random().nextInt(ones.length);
                Integer one = ones[index];
                Integer shipLength = 3 * one;
                Integer endSpot = num1 + shipLength;
                if (endSpot >= 1 && endSpot <= 10) {
                    enemyShips[8] = new Coordinates(((char)c1), num1);
                    enemyShips[9] = new Coordinates(((char)c1), num1 + (one));
                    enemyShips[10] = new Coordinates(((char)c1), num1 + 2 * (one));
                    enemyShips[11] = new Coordinates(((char)c1), num1 + 3 * (one));

                    ship4Placed = true;
                } else {
                    ship4Placed = false;
                }
            }
            if(num3!=0 && (newChar >= 65 && newChar <= 74)){
                enemyShips[8] = new Coordinates(((char)c1), num1 );
                enemyShips[9] = new Coordinates(((char)(c1+num3)), num1 );
                enemyShips[10] = new Coordinates(((char)(c1+(2*num3))), num1);
                enemyShips[11] = new Coordinates(((char)(c1+(3*num3))), num1);
                ship4Placed = true;
            }

            if(enemyShips[8] != null && enemyShips[9] != null && enemyShips[10] != null && enemyShips[11] != null) {
                for (int i = 8; i < 12; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (enemyShips[i].equals(enemyShips[j])) {
                            ship4Placed = false;
                            break;
                        }
                    }
                }
            }
        }

        while(!ship5Placed) {
            int num1 = random.nextInt((max - min) + 1) + min;
            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;

            int num3 = random.nextInt(3) - 1;
            int newChar = c1 + (num3*4);

            if(num3==0) {
                int index = new Random().nextInt(ones.length);
                Integer one = ones[index];
                Integer shipLength = 4 * one;
                Integer endSpot = num1 + shipLength;
                if (endSpot >= 1 && endSpot <= 10) {
                    enemyShips[12] = new Coordinates(((char)c1), num1);
                    enemyShips[13] = new Coordinates(((char)c1), num1 + (one));
                    enemyShips[14] = new Coordinates(((char)c1), num1 + 2 * (one));
                    enemyShips[15] = new Coordinates(((char)c1), num1 + 3 * (one));
                    enemyShips[16] = new Coordinates(((char)c1), num1 + 4 * (one));

                    ship5Placed = true;
                } else {
                    ship5Placed = false;
                }
            }
            if(num3!=0 && (newChar >= 65 && newChar <= 74)){
                enemyShips[12] = new Coordinates(((char)c1), num1 );
                enemyShips[13] = new Coordinates(((char)(c1+num3)), num1 );
                enemyShips[14] = new Coordinates(((char)(c1+(2*num3))), num1);
                enemyShips[15] = new Coordinates(((char)(c1+(3*num3))), num1);
                enemyShips[16] = new Coordinates(((char)(c1+(4*num3))), num1);
                ship5Placed = true;
            }

            if(enemyShips[12] != null && enemyShips[13] != null && enemyShips[14] != null && enemyShips[15] != null && enemyShips[16] != null) {
                for (int i = 12; i < 17; i++) {
                    for (int j = 0; j < 12; j++) {
                        if (enemyShips[i].equals(enemyShips[j])) {
                            ship5Placed = false;
                            break;
                        }
                    }
                }
            }
        }
    }


    //a new class for x and y corrdinates
    static class Coordinates {
        public char row;
        public int col;

        Coordinates(char row, int col) {
            this.row = row;
            this.col = col;
        }

        //override the equals bool so we can check coord to coord
        @Override
        public boolean equals(Object object) {
            Coordinates coord = (Coordinates) object;
            return coord.row == row && coord.col == col;
        }

        //override the toString() function to print the row and column coord
        @Override
        public String toString() {
            return ("row:" + this.row + " col: " + this.col);
        }

        public char getRow(){ return (this.row);}
        public int getCol(){ return (this.col); }
    }

    public static void iBoard(){
        for(char row = 'A'; row < 'K'; row++ ){
            for(int col = 0; col < 10; col++){
                boardCoords.add(new Coordinates(row, col+1));
            }
        }
    }

    //create the battleship board
    public static void createBattleshipBoard(List<Coordinates> boardCoords){
        String gridLine = "";
        for(int i = 0; i < 10; i++) {
            System.out.print("\t" + boardCoords.get(i).getCol());
        }

        System.out.println("\n");

        for(int j = 0; j < boardCoords.size(); j+=10){
            System.out.print(boardCoords.get(j).getRow());
            for(int column=0 ; column < 10 ; column++ ){
                gridLine= "\t" + "O";
                System.out.print(gridLine);
                letterGrid.add(gridLine.trim().replaceAll("\\s+", ""));
            }
            System.out.println();
        }
    }

    //accept user input for row and column
    //checks to to make sure the row inside the board and is between A-J
    public static void coordinateInput(){
        Scanner input = new Scanner(System.in);
        int col = -1;
        boolean acceptRow = false;
        boolean acceptCol = false;
        Coordinates[] newMark = new Coordinates[1];

    while(!acceptRow && !acceptCol) {
        while (!acceptRow) {
            System.out.print("\n\nRow: ");
            row = input.next().toUpperCase().charAt(0);

            switch (row) {
                case 'A':
                    acceptRow = true;
                    break;
                case 'B':
                    acceptRow = true;
                    break;
                case 'C':
                    acceptRow = true;
                    break;
                case 'D':
                    acceptRow = true;
                    break;
                case 'E':
                    acceptRow = true;
                    break;
                case 'F':
                    acceptRow = true;
                    break;
                case 'G':
                    acceptRow = true;
                    break;
                case 'H':
                    acceptRow = true;
                    break;
                case 'I':
                    acceptRow = true;
                    break;
                case 'J':
                    acceptRow = true;
                    break;
                default:
                    System.out.println("Oops, you didn't enter a valid row, try entering a letter between A and J");
                    break;
            }
        }

        while (!acceptCol) {
            try {
                System.out.print("Col: ");
                col = input.nextInt();
                if (col > 10 || col < 0) {
                    System.out.println("Oops, you entered a column that isn't available, try a number between 1 and 10");
                } else {
                    acceptCol = true;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Oops, you didn't enter a valid number, try entering an integer number between 1 and 10");
                input.next();
            }
        }

        newMark[0] = new Coordinates(row, col);

        if (missedHitList.contains(newMark[0]) || successfulHitList.contains(newMark[0])) {
            System.out.println("Oops, you already chose that spot on the map, try another coordinate");
            acceptRow = false;
            acceptCol = false;
        } else {
            missedHitList.add(new Coordinates(row, col));
        }
      }
    }


    //show the enemy's board and where you have hit and missed on the opponent's side
    public static void printEnemyBoard(List<Coordinates> hitList, List<Coordinates> missList) {

        int cols = 10;

        System.out.println("\nBelow is your hit board showing your hits and misses\n");
        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
            }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");


            for (int c = 1; c <= cols; c++) {
                if(hitList.contains(new Coordinates(r, c))) {
                    System.out.print('X' + "\t");
                }  else if (missList.contains(new Coordinates(r, c))){
                    System.out.print('*' + "\t");
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }

    //print the player's board so they know where they are placing their ships
    public static void printCurrentBoard1() {
        int cols = 10;

        myDisplayList.add(myShips[0]);
        myDisplayList.add(myShips[1]);

        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {
                    if (myDisplayList.contains(new Coordinates(r, c))){
                        System.out.print('|' + "\t");
                    } else {
                        System.out.print('O' + "\t");
                    }
            }
        }
    }

    //print the player's board so they know where they are placing their ships
    public static void printCurrentBoard2() {
        int cols = 10;

        myDisplayList.add(myShips[2]);
        myDisplayList.add(myShips[3]);
        myDisplayList.add(myShips[4]);

        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {
                if (myDisplayList.contains(new Coordinates(r, c))){
                    System.out.print('|' + "\t");
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }

    //print the player's board so they know where they are placing their ships
    public static void printCurrentBoard3() {
        int cols = 10;

        myDisplayList.add(myShips[5]);
        myDisplayList.add(myShips[6]);
        myDisplayList.add(myShips[7]);

        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {
                if (myDisplayList.contains(new Coordinates(r, c))){
                    System.out.print('|' + "\t");
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }

    //print the player's board so they know where they are placing their ships
    public static void printCurrentBoard4() {
        int cols = 10;

        myDisplayList.add(myShips[8]);
        myDisplayList.add(myShips[9]);
        myDisplayList.add(myShips[10]);
        myDisplayList.add(myShips[11]);

        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {
                if (myDisplayList.contains(new Coordinates(r, c))){
                    System.out.print('|' + "\t");
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }

    //print the player's board so they know where they are placing their ships
    public static void printCurrentBoard5() {
        int cols = 10;

        myDisplayList.add(myShips[12]);
        myDisplayList.add(myShips[13]);
        myDisplayList.add(myShips[14]);
        myDisplayList.add(myShips[15]);
        myDisplayList.add(myShips[16]);

        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {
                if (myDisplayList.contains(new Coordinates(r, c))){
                    System.out.print('|' + "\t");
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }


    //create the hit list for the player to know where they have been successful
    public static void populateHitList(List<Coordinates> hitList){

        for (int e = 0; e < 17; e++) {
            for (int m = 0; m < missedHitList.size(); m++) {
                if (enemyShips[e].equals(missedHitList.get(m))) {
                    hitList.add(enemyShips[e]);
                    System.out.println("YOU HIT A BATTLESHIP");
                    missedHitList.remove(m);
                }
            }
        }

        if(hitList.contains(enemyShips[0]) && hitList.contains(enemyShips[1]) && !enemyShip1Destroyed){
            enemyShip1Destroyed = true;
            System.out.println("YOU SUNK THE BATTLESHIP");
        }

        if(hitList.contains(enemyShips[2]) && hitList.contains(enemyShips[3]) && hitList.contains(enemyShips[4]) && !enemyShip2Destroyed){
            enemyShip2Destroyed = true;
            System.out.println("YOU SUNK THE BATTLESHIP");
        }

        if(hitList.contains(enemyShips[5]) && hitList.contains(enemyShips[6]) && hitList.contains(enemyShips[7]) && !enemyShip3Destroyed){
                enemyShip3Destroyed = true;
                System.out.println("YOU SUNK THE BATTLESHIP");
        }

        if(hitList.contains(enemyShips[8]) && hitList.contains(enemyShips[9]) && hitList.contains(enemyShips[10]) && hitList.contains(enemyShips[11]) && !enemyShip4Destroyed){
                enemyShip4Destroyed = true;
                System.out.println("YOU SUNK THE BATTLESHIP");
        }

        if(hitList.contains(enemyShips[12]) && hitList.contains(enemyShips[13]) && hitList.contains(enemyShips[14]) && hitList.contains(enemyShips[15]) && hitList.contains(enemyShips[16]) && !enemyShip5Destroyed){
                enemyShip5Destroyed = true;
                System.out.println("YOU SUNK THE BATTLESHIP");
        }
    }



    /*
    AI opponent attacks
    This is a very long and bloated function with checks and balances to make sure the AI
    acts appropriately when attacking.

    The first move will be random and subsequently any move proceeding a miss will be random.
    If the AI hits, they will check right then left, then down, then up.
    If the AI hits another ship while trying to sink a current ship, The AI will
    act on the last spot hit. So the AI does not know they have hit the second ship, but
    the AI will react to the last spot that was successfully hit attempting to get a
    successfully receive a sinking ship boolean to equal true.

     */
    public static void enemyAttack(){

        Random random = new Random();
        int max = 10;
        int min = 1;
        int cMax = 74;
        int cMin = 65;
        Coordinates[] attackMark = new Coordinates[1];

        boolean attack = false;
        boolean duplicate;


        while(!attack) {

            int c1 = random.nextInt((cMax - cMin) + 1) + cMin;
            int num1 = random.nextInt((max - min) + 1) + min;
            attackMark[0] = new Coordinates((char) c1, num1);

            outerloop1:
            if(attackingShip1) {
                if(attackingShip2 || attackingShip3 || attackingShip4 || attackingShip5){
                    break outerloop1;
                }

                if (hitCol0 + 1 <= 10 && !shipHit0R) {
                    num1 = hitCol0 + 1;
                    c1 = hitRow0;
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit0R = true;
                    break outerloop1;
                } else {
                    shipHit0R = true;
                }


                if (hitCol0 - 1 >= 1 && shipHit0R && !shipHit0L){
                    num1 = hitCol0 - 1;
                    c1 = hitRow0;
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit0L = true;
                    break outerloop1;
                } else{
                    shipHit0L = true;
                }

                if (hitRow0 + 1 <= 74 && shipHit0R && shipHit0L && !shipHit0D){
                    num1 = hitCol0;
                    c1 = hitRow0 + 1;
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit0D = true;
                    break outerloop1;
                } else {
                    shipHit0D = true;
                }

                if (hitRow0 - 1 >= 65 && shipHit0R && shipHit0L && shipHit0D && !shipHit0U){
                    num1 = hitCol0;
                    c1 = hitRow0 - 1;
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit0U = true;
                } else {
                    shipHit0U = true;
                }
            }


            outerloop2:
            if(attackingShip2){
                if(attackingShip3 || attackingShip4 || attackingShip5){
                    break outerloop2;
                }

                attackLoop1:
                if (hitCol2 + 1 <= 10 && !shipHit2R) {
                    num1 = hitCol2 + 1;
                    c1 = hitRow2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2R = true;
                        break attackLoop1;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2R = true;
                    break outerloop2;
                } else {
                    shipHit2R = true;
                }

                attackLoop2:
                if (hitCol2 + 2 <= 10 && shipHit2R && !shipHit2R2 && (hitSize1 < enemySuccessfulHitList.size())) {
                    num1 = hitCol2 + 2;
                    c1 = hitRow2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2R2 = true;
                        break attackLoop2;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2R2 = true;
                    break outerloop2;
                } else {
                    shipHit2R2 = true;
                }


                attackLoop3:
                if (hitCol2 - 1 >= 1 && shipHit2R && shipHit2R2 && !shipHit2L){
                    num1 = hitCol2 - 1;
                    c1 = hitRow2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2L = true;
                        break attackLoop3;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2L = true;
                    break outerloop2;
                } else{
                    shipHit2L = true;
                }

                attackLoop4:
                if (hitCol2 - 2 >= 1 && shipHit2R && shipHit2R2 && shipHit2L && !shipHit2L2 && (hitSize1 < enemySuccessfulHitList.size())) {
                    num1 = hitCol2 - 2;
                    c1 = hitRow2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2L2 = true;
                        break attackLoop4;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2L2 = true;
                    break outerloop2;
                } else {
                    shipHit2L2 = true;
                }


                attackLoop5:
                if (hitRow2 + 1 <= 74 && shipHit2R && shipHit2R2 && shipHit2L && shipHit2L2 && !shipHit2D){
                    num1 = hitCol2;
                    c1 = hitRow2 + 1;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2D = true;
                        break attackLoop5;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2D = true;
                    break outerloop2;
                } else {
                    shipHit2D = true;
                }

                attackLoop6:
                if (hitRow2 + 2 <= 74 && shipHit2R && shipHit2R2 && shipHit2L && shipHit2L2 && shipHit2D && !shipHit2D2 && (hitSize1 < enemySuccessfulHitList.size())) {
                    num1 = hitCol2;
                    c1 = hitRow2 + 2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2D2 = true;
                        break attackLoop6;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2D2 = true;
                    break outerloop2;
                } else {
                    shipHit2D2 = true;
                }

                attackLoop7:
                if (hitRow2 - 1 >= 65 && shipHit2R && shipHit2R2 && shipHit2L && shipHit2L2 && shipHit2D && shipHit2D2 && !shipHit2U){
                    num1 = hitCol2;
                    c1 = hitRow2 - 1;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2U = true;
                        break attackLoop7;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2U = true;
                    break outerloop2;
                } else {
                    shipHit2U = true;
                }


                attackLoop8:
                if (hitRow2 - 2 >= 65 && shipHit2R && shipHit2R2 && shipHit2L && shipHit2L2 && shipHit2D2 && shipHit2U && !shipHit2U2 && (hitSize1 < enemySuccessfulHitList.size())) {
                    num1 = hitCol2;
                    c1 = hitRow2 - 2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit2U2 = true;
                        break attackLoop8;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit2U2 = true;
                    break outerloop2;
                } else {
                    shipHit2U2 = true;
                }

            }

          outerloop3:
            if(attackingShip3){
                if(attackingShip4 || attackingShip5){
                    break outerloop3;
                }

                attackLoop1:
                if (hitCol5 + 1 <= 10 && !shipHit3R) {
                    num1 = hitCol5 + 1;
                    c1 = hitRow5;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3R = true;
                        break attackLoop1;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3R = true;
                    break outerloop3;
                } else {
                    shipHit3R = true;
                }

                attackLoop2:
                if (hitCol5 + 2 <= 10 && shipHit3R && !shipHit3R2 && (hitSize2 < enemySuccessfulHitList.size())) {
                    num1 = hitCol5 + 2;
                    c1 = hitRow5;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3R2 = true;
                        break attackLoop2;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3R2 = true;
                    break outerloop3;
                } else {
                    shipHit3R2 = true;
                }


                attackLoop3:
                if (hitCol5 - 1 >= 1 && shipHit3R && shipHit3R2 && !shipHit3L){
                    num1 = hitCol5 - 1;
                    c1 = hitRow5;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3L = true;
                        break attackLoop3;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3L = true;
                    break outerloop3;
                } else{
                    shipHit3L = true;
                }

                attackLoop4:
                if (hitCol5 - 2 >= 1 && shipHit3R && shipHit3R2 && shipHit3L && !shipHit3L2 && (hitSize2 < enemySuccessfulHitList.size())) {
                    num1 = hitCol5 - 2;
                    c1 = hitRow5;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3L2 = true;
                        break attackLoop4;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3L2 = true;
                    break outerloop3;
                } else {
                    shipHit3L2 = true;
                }


                attackLoop5:
                if (hitRow5 + 1 <= 74 && shipHit3R && shipHit3R2 && shipHit3L && shipHit3L2 && !shipHit3D){
                    num1 = hitCol5;
                    c1 = hitRow5 + 1;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3D = true;
                        break attackLoop5;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3D = true;
                    break outerloop3;
                } else {
                    shipHit3D = true;
                }

                attackLoop6:
                if (hitRow5 + 2 <= 74 && shipHit3R && shipHit3R2 && shipHit3L && shipHit3L2 && shipHit3D && !shipHit3D2 && (hitSize2 < enemySuccessfulHitList.size())) {
                    num1 = hitCol5;
                    c1 = hitRow5 + 2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3D2 = true;
                        break attackLoop6;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3D2 = true;
                    break outerloop3;
                } else {
                    shipHit3D2 = true;
                }

                attackLoop7:
                if (hitRow5 - 1 >= 65 && shipHit3R && shipHit3R2 && shipHit3L && shipHit3L2 && shipHit3D && shipHit3D2 && !shipHit3U){
                    num1 = hitCol5;
                    c1 = hitRow5 - 1;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3U = true;
                        break attackLoop7;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3U = true;
                    break outerloop3;
                } else {
                    shipHit3U = true;
                }

                attackLoop8:
                if (hitRow5 - 2 >= 65 && shipHit3R && shipHit3R2 && shipHit3L && shipHit3L2 && shipHit3D && shipHit3D2 && shipHit3U && !shipHit3U2 && (hitSize2 < enemySuccessfulHitList.size())) {
                    num1 = hitCol5;
                    c1 = hitRow5 - 2;
                    if(enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit3U2 = true;
                        break attackLoop8;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit3U2 = true;
                    break outerloop3;
                } else {
                    shipHit3U2 = true;
                }
            }


            outerloop4:
            if(attackingShip4) {
                if (attackingShip5) {
                    break outerloop4;
                }
                attackLoop1:
                if (hitCol8 + 1 <= 10 && !shipHit4R) {
                    num1 = hitCol8 + 1;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4R = true;
                        break attackLoop1;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4R = true;
                    break outerloop4;
                } else {
                    shipHit4R = true;
                }

                attackLoop2:
                if (hitCol8 + 2 <= 10 && shipHit4R && !shipHit4R2 && (hitSize3 < enemySuccessfulHitList.size())) {
                    num1 = hitCol8 + 2;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4R2 = true;
                        break attackLoop2;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4R2 = true;
                    break outerloop4;
                } else {
                    shipHit4R2 = true;
                }

                attackLoop9:
                if (hitCol8 + 3 <= 10 && shipHit4R && shipHit4R2 && !shipHit4R3 && ((hitSize3+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol8 + 3;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4R3 = true;
                        break attackLoop9;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4R3 = true;
                    break outerloop4;
                } else {
                    shipHit4R3 = true;
                }


                attackLoop3:
                if (hitCol8 - 1 >= 1 && shipHit4R && shipHit4R2 && shipHit4R3 && !shipHit4L) {
                    num1 = hitCol8 - 1;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4L = true;
                        break attackLoop3;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4L = true;
                    break outerloop4;
                } else {
                    shipHit4L = true;
                }

                attackLoop4:
                if (hitCol8 - 2 >= 1 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && !shipHit4L2 && (hitSize3 < enemySuccessfulHitList.size())) {
                    num1 = hitCol8 - 2;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4L2 = true;
                        break attackLoop4;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4L2 = true;
                    break outerloop4;
                } else {
                    shipHit4L2 = true;
                }

                attackLoop10:
                if (hitCol8 - 3 >= 1 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && !shipHit4L3 && ((hitSize3+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol8 - 3;
                    c1 = hitRow8;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4L3 = true;
                        break attackLoop10;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4L3 = true;
                    break outerloop4;
                } else {
                    shipHit4L3 = true;
                }


                attackLoop5:
                if (hitRow8 + 1 <= 74 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && !shipHit4D) {
                    num1 = hitCol8;
                    c1 = hitRow8 + 1;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4D = true;
                        break attackLoop5;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4D = true;
                    break outerloop4;
                } else {
                    shipHit4D = true;
                }

                attackLoop6:
                if (hitRow8 + 2 <= 74 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && shipHit4D && !shipHit4D2 && (hitSize3 < enemySuccessfulHitList.size())) {
                    num1 = hitCol8;
                    c1 = hitRow8 + 2;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4D2 = true;
                        break attackLoop6;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4D2 = true;
                    break outerloop4;
                } else {
                    shipHit4D2 = true;
                }

                attackLoop11:
                if (hitRow8 + 3 <= 74 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && shipHit4D && shipHit4D2 && !shipHit4D3 && ((hitSize3+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol8;
                    c1 = hitRow8 + 3;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4D3 = true;
                        break attackLoop11;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4D3 = true;
                    break outerloop4;
                } else {
                    shipHit4D3 = true;
                }


                attackLoop7:
                if (hitRow8 - 1 >= 65 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && shipHit4D && shipHit4D2 && shipHit4D3 && !shipHit4U) {
                    num1 = hitCol8;
                    c1 = hitRow8 - 1;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4U = true;
                        break attackLoop7;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4U = true;
                    break outerloop4;
                } else {
                    shipHit4U = true;
                }

                attackLoop8:
                if (hitRow8 - 2 >= 65 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && shipHit4D && shipHit4D2 && shipHit4D3 && shipHit4U && !shipHit4U2 && (hitSize3 < enemySuccessfulHitList.size())) {
                    num1 = hitCol8;
                    c1 = hitRow8 - 2;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4U2 = true;
                        break attackLoop8;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4U2 = true;
                    break outerloop4;
                } else {
                    shipHit4U2 = true;
                }

                attackLoop12:
                if (hitRow8 - 3 >= 65 && shipHit4R && shipHit4R2 && shipHit4R3 && shipHit4L && shipHit4L2 && shipHit4L3 && shipHit4D && shipHit4D2 && shipHit4D3 && shipHit4U && shipHit4U2 && !shipHit4U3 && ((hitSize3+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol8;
                    c1 = hitRow8 - 3;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit4U3 = true;
                        break attackLoop12;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit4U3 = true;
                    break outerloop4;
                } else {
                    shipHit4U3 = true;
                }
            }


            outerloop5:
            if(attackingShip5){
                attackLoop1:
                if (hitCol12 + 1 <= 10 && !shipHit5R) {
                    num1 = hitCol12 + 1;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5R = true;
                        break attackLoop1;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5R = true;
                    break outerloop5;
                } else {
                    shipHit5R = true;
                }

                attackLoop2:
                if (hitCol12 + 2 <= 10 && shipHit5R && !shipHit5R2 && (hitSize4 < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 + 2;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5R2 = true;
                        break attackLoop2;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5R2 = true;
                    break outerloop5;
                } else {
                    shipHit5R2 = true;
                }

                attackLoop9:
                if (hitCol12 + 3 <= 10 && shipHit5R && shipHit5R2 && !shipHit5R3 && ((hitSize4+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 + 3;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5R3 = true;
                        break attackLoop9;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5R3 = true;
                    break outerloop5;
                } else {
                    shipHit5R3 = true;
                }

                attackLoop13:
                if (hitCol12 + 4 <= 10 && shipHit5R && shipHit5R2 && shipHit5R3 && !shipHit5R4 && ((hitSize4+2) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 + 4;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5R4 = true;
                        break attackLoop13;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5R4 = true;
                    break outerloop5;
                } else {
                    shipHit5R4 = true;
                }


                attackLoop3:
                if (hitCol12 - 1 >= 1 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && !shipHit5L) {
                    num1 = hitCol12 - 1;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5L = true;
                        break attackLoop3;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5L = true;
                    break outerloop5;
                } else {
                    shipHit5L = true;
                }

                attackLoop4:
                if (hitCol12 - 2 >= 1 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && !shipHit5L2 && (hitSize4 < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 - 2;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5L2 = true;
                        break attackLoop4;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5L2 = true;
                    break outerloop5;
                } else {
                    shipHit5L2 = true;
                }

                attackLoop10:
                if (hitCol12 - 3 >= 1 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && !shipHit5L3 && ((hitSize4+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 - 3;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5L3 = true;
                        break attackLoop10;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5L3 = true;
                    break outerloop5;
                } else {
                    shipHit5L3 = true;
                }

                attackLoop14:
                if (hitCol12 - 4 >= 1 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && !shipHit5L4 &&((hitSize4+2) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12 - 4;
                    c1 = hitRow12;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5L4 = true;
                        break attackLoop14;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5L4 = true;
                    break outerloop5;
                } else {
                    shipHit5L4 = true;
                }


                attackLoop5:
                if (hitRow12 + 1 <= 74 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && !shipHit5D) {
                    num1 = hitCol12;
                    c1 = hitRow12 + 1;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5D = true;
                        break attackLoop5;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5D = true;
                    break outerloop5;
                } else {
                    shipHit5D = true;
                }

                attackLoop6:
                if (hitRow12 + 2 <= 74 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && !shipHit5D2 && (hitSize4 < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 + 2;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5D2 = true;
                        break attackLoop6;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5D2 = true;
                    break outerloop5;
                } else {
                    shipHit5D2 = true;
                }

                attackLoop11:
                if (hitRow12 + 3 <= 74 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && !shipHit5D3 && ((hitSize4+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 + 3;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5D3 = true;
                        break attackLoop11;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5D3 = true;
                    break outerloop5;
                } else {
                    shipHit5D3 = true;
                }

                attackLoop15:
                if (hitRow12 + 4 <= 74 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && shipHit5D3 && !shipHit5D4 && ((hitSize4+2) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 + 4;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5D4 = true;
                        break attackLoop15;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5D4 = true;
                    break outerloop5;
                } else {
                    shipHit5D4 = true;
                }


                attackLoop7:
                if (hitRow12 - 1 >= 65 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && shipHit5D3 && shipHit5D4 && !shipHit5U) {
                    num1 = hitCol12;
                    c1 = hitRow12 - 1;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5U = true;
                        break attackLoop7;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5U = true;
                    break outerloop5;
                } else {
                    shipHit5U = true;
                }

                attackLoop8:
                if (hitRow12 - 2 >= 65 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && shipHit5D3 && shipHit5D4 && shipHit5U && !shipHit5U2 && (hitSize4 < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 - 2;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5U2 = true;
                        break attackLoop8;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5U2 = true;
                    break outerloop5;
                } else {
                    shipHit5U2 = true;
                }

                attackLoop12:
                if (hitRow12 - 3 >= 65 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && shipHit5D3 && shipHit5D4 && shipHit5U && shipHit5U2 && !shipHit5U3 && ((hitSize4+1) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 - 3;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5U3 = true;
                        break attackLoop12;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5U3 = true;
                    break outerloop5;
                } else {
                    shipHit5U3 = true;
                }

                attackLoop16:
                if (hitRow12 - 4 >= 65 && shipHit5R && shipHit5R2 && shipHit5R3 && shipHit5R4 && shipHit5L && shipHit5L2 && shipHit5L3 && shipHit5L4 && shipHit5D && shipHit5D2 && shipHit5D3 && shipHit5D4 && shipHit5U && shipHit5U2 && shipHit5U3 && !shipHit5U4 && ((hitSize4+2) < enemySuccessfulHitList.size())) {
                    num1 = hitCol12;
                    c1 = hitRow12 - 4;
                    if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                        shipHit5U4 = true;
                        break attackLoop16;
                    }
                    attackMark[0] = new Coordinates((char) c1, num1);
                    shipHit5U4 = true;
                    break outerloop5;
                } else {
                    shipHit5U4 = true;
                }

            }



            if (enemyMissedHitList.contains(new Coordinates((char) c1, num1)) || enemySuccessfulHitList.contains(new Coordinates((char) c1, num1))) {
                duplicate = true;
            } else {
                duplicate = false;
                attack = true;
            }


            if (!duplicate) {
                for (int i = 0; i < 17; i++) {
                    if (myShips[i].equals(attackMark[0])) {
                        enemySuccessfulHitList.add(new Coordinates((char) c1, num1));
                        System.out.println("\n\nThe ENEMY HIT one of your BATTLESHIPS");
                    }
                }
            }


            if((myShips[0].equals(attackMark[0]) || myShips[1].equals(attackMark[0])) && (!duplicate && !ship1FirstHit)){
                hitRow0 = c1;
                hitCol0 = num1;
                attackingShip1 = true;
                ship1FirstHit = true;
            }

            if((myShips[2].equals(attackMark[0]) || myShips[3].equals(attackMark[0]) || myShips[4].equals(attackMark[0])) && (!duplicate && !ship2FirstHit)){
                hitRow2 = c1;
                hitCol2 = num1;
                attackingShip2 = true;
                ship2FirstHit = true;
                hitSize1 = enemySuccessfulHitList.size();
            }

            if ((myShips[5].equals(attackMark[0]) || myShips[6].equals(attackMark[0]) || myShips[7].equals(attackMark[0])) && (!duplicate && !ship3FirstHit)){
                hitRow5 = c1;
                hitCol5 = num1;
                attackingShip3 = true;
                ship3FirstHit = true;
                hitSize2 = enemySuccessfulHitList.size();
            }

            if ((myShips[8].equals(attackMark[0]) || myShips[9].equals(attackMark[0]) || myShips[10].equals(attackMark[0]) || myShips[11].equals(attackMark[0])) && (!duplicate && !ship4FirstHit)){
                hitRow8 = c1;
                hitCol8 = num1;
                attackingShip4 = true;
                ship4FirstHit = true;
                hitSize3 = enemySuccessfulHitList.size();
            }

            if ((myShips[12].equals(attackMark[0]) || myShips[13].equals(attackMark[0]) || myShips[14].equals(attackMark[0]) || myShips[15].equals(attackMark[0]) || myShips[16].equals(attackMark[0])) && (!duplicate && !ship5FirstHit)){
                hitRow12 = c1;
                hitCol12 = num1;
                attackingShip5 = true;
                ship5FirstHit = true;
                hitSize4 = enemySuccessfulHitList.size();
            }





            if (enemySuccessfulHitList.contains(myShips[0]) && enemySuccessfulHitList.contains(myShips[1]) && !ship1Destroyed) {
                System.out.println("\nThe ENEMY attacked and sunk your BATTLESHIP");
                attackingShip1 = false;
                ship1Destroyed = true;
            }

            if (enemySuccessfulHitList.contains(myShips[2]) && enemySuccessfulHitList.contains(myShips[3]) && enemySuccessfulHitList.contains(myShips[4]) && !ship2Destroyed) {
                System.out.println("\nThe ENEMY attacked and sunk your BATTLESHIP");
                attackingShip2 = false;
                ship2Destroyed = true;
            }

            if (enemySuccessfulHitList.contains(myShips[5]) && enemySuccessfulHitList.contains(myShips[6]) && enemySuccessfulHitList.contains(myShips[7]) && !ship3Destroyed) {
                System.out.println("\nThe ENEMY attacked and sunk your BATTLESHIP");
                attackingShip3 = false;
                ship3Destroyed = true;
            }

            if (enemySuccessfulHitList.contains(myShips[8]) && enemySuccessfulHitList.contains(myShips[9]) && enemySuccessfulHitList.contains(myShips[10]) && enemySuccessfulHitList.contains(myShips[11]) && !ship4Destroyed) {
                System.out.println("\nThe ENEMY attacked and sunk your BATTLESHIP");
                attackingShip4 = false;
                ship4Destroyed = true;
            }

            if (enemySuccessfulHitList.contains(myShips[12]) && enemySuccessfulHitList.contains(myShips[13]) && enemySuccessfulHitList.contains(myShips[14]) && enemySuccessfulHitList.contains(myShips[15]) && enemySuccessfulHitList.contains(myShips[16]) && !ship5Destroyed) {
                System.out.println("\nThe ENEMY attacked and sunk your BATTLESHIP");
                attackingShip5 = false;
                ship5Destroyed = true;
            }

            if (!enemyMissedHitList.contains(new Coordinates((char) c1, num1)) && !enemySuccessfulHitList.contains(new Coordinates((char) c1, num1)) && !duplicate) {
                enemyMissedHitList.add(new Coordinates((char) c1, num1));
            }
        }
    }

    //print your board showing where they enemy has attacked and missed
    public static void printYourBoard(List<Coordinates> hitList, List<Coordinates> missList) {
        int cols = 10;
        Coordinates[] newPoint = new Coordinates[1];
        boolean bShip = false;

        System.out.println("\n\nBelow is your base grid showing where the enemy has attacked\n");
        for(int k = 0; k < 10; k++) {
            System.out.print("\t" + boardCoords.get(k).getCol());
        }
        System.out.println();

        for (char r = 'A'; r < 'K'; r++) {
            System.out.println();
            System.out.print(r + "\t");

            for (int c = 1; c <= cols; c++) {

                newPoint[0] = new Coordinates(r, c);
                for(int i = 0; i < 17; i++){
                    if(newPoint[0].equals(myShips[i])){
                        bShip = true;
                    }
                }

                if(hitList.contains(new Coordinates(r, c))){
                    System.out.print('X' + "\t");
                    bShip = false;
                } else if (missList.contains(new Coordinates(r, c))) {
                    System.out.print('*' + "\t");
                } else if(!hitList.contains(new Coordinates(r, c)) && bShip){
                        System.out.print('|' + "\t");
                        bShip = false;
                } else {
                    System.out.print('O' + "\t");
                }
            }
        }
    }

}
