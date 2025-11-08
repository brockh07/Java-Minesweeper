//Brock Hughes
//ICS4U
//Mrs. Twist
//1/06/2025
//Minesweeper With Visuals
//This game is a fully functional recreation of the classic game "Minesweeper"
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;//Import for Randint
import java.awt.event.*;

//Useful values
//Light grey rgb val: 211, 211, 211
//Grey rgb val: 128, 128, 128

public class Main {


    public static void updateGrid(ArrayList<Tile> NG, JButton[] tiles, ImageIcon bomb, ImageIcon flag ) {//Used to display the # of adjacent tiles
        for(int i = 0;i < 100;i++) {
            tiles[i].setText(NG.get(i).getVisStr());//Set the vis string as the text on the JLabel
            tiles[i].setFont(new Font("MV Boli",Font.PLAIN,25));//Change font
            if(NG.get(i).getVisStr().equals("/")){//If it's a flag: set image to flag, remove text
                tiles[i].setIcon(flag);
                tiles[i].setText(null);
            }
        }//close loop
    }//Close funct


    public static void numAdjGrid(ArrayList<Tile> NG) {//DEVELOPMENT TOOL FROM PREVIOUS VERSION, UNUSED IN FINAL COPY...
        System.out.print("|");
        for (int i = 0; i < 10; i++) {//for loop for each row (moves it down the y axis)
            for (int x = 0; x < 10; x++) {//For loop for each "tile" in each row (moves across the x axis)
                System.out.print(NG.get(Integer.parseInt(i+"" + x+"")).getNumAdj()+"|");//Adds the strings (1+2 = 12), uses that to loop through and create the board
            }//Close for loop
            if(i == 9){//For last row, stops adding "|"
                System.out.println();
            }//Close If
            else {
                System.out.println();
                System.out.print("|");
            }//Close else
        }//Close for loop #2
    }//Close funct


    /* The next Function checks whether the adjacent tiles are bombs
    it checks by subtracting or adding various amounts from the given index (+-1, +-10, +-9, +-11)
    Edge Cases include the 1st and last tile of each row (10 - 1 breaks the feature, so does 19 + 11) where the tiles are no longer adjacent
    Some numbers go out of range, for example: 99+11, 8 -9, 2-10, etc. This is why all cases use a try catch block.
    if the case throws an error that means it wasn't adjacent anyway, so the catch just continues the program to the next case.
    Every case that has an adjacent # adds one to the count.
    The count is "assigned" to the tile at the end.
|00|01|02|03|04|05|06|07|08|09|
|10|11|12|13|14|15|16|17|18|19|
|20|21|22|23|24|25|26|27|28|29|
|30|31|32|33|34|35|36|37|38|39|
|40|41|42|43|44|45|46|47|48|49|

    */
    public static void setAdjacentBombs(int i, ArrayList<Tile> NG){//Checks if the adjacent tiles are bombs
        int count = 0;
        if(i == 0 ||i == 10 ||i == 20 ||i == 30 ||i == 40 ||i == 50 ||i == 60 ||i == 70 ||i == 80 ||i == 90){//Edge Case Right: ignores +1, +11, and -9
            if(NG.get(i).getNumAdj() != -1){

                try{
                    if(NG.get(i+1).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i+10).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i-10).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i+11).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i-9).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}
                NG.get(i).setNumAdj(count); // "Assigning" the count to the tile
            } //1st if statement
        }

        else if(i == 9 || i == 19 || i == 29 || i == 39 || i == 49 || i == 59 || i == 69 || i == 79 || i == 89 || i == 99){//Edge Case Left: ignores -1, -11, and +9
            if(NG.get(i).getNumAdj() != -1){

                try{
                    if(NG.get(i-1).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}


                try{
                    if(NG.get(i+10).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i-10).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i-11).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                try{
                    if(NG.get(i+9).getNumAdj() == -1){
                        count +=1;
                    }
                }catch(IndexOutOfBoundsException e){}

                NG.get(i).setNumAdj(count);
            } //1st if statement
        }


        else {//If it's not an edge case, it checks all 8 cases
            if (NG.get(i).getNumAdj() != -1) {

                try {
                    if (NG.get(i - 1).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }


                try {
                    if (NG.get(i + 1).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i + 10).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i - 10).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i + 11).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i - 11).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i + 9).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (NG.get(i - 9).getNumAdj() == -1) {
                        count += 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
                NG.get(i).setNumAdj(count);
            } //1st if statement
        }//close else
    }//Close Funct


    /*
    Similar to the last function, but instead of counting the bombs at the adjacent tiles it changes the vis string on them / guesses then if 0

    if the guess is an edge case and the tile has no adjacent bombs: It will playerguess() (recursion) on the 5 adjacent tiles.

    if the guess is not an edge case and the tile has no adjacent bombs: It will playerguess() (recursion) on the 8 adjacent tiles.

    if the player guesses a bomb: returns false, ends game

    if the player's guess reveals a tile with adjbombs > 0: reveal the number of adj bombs on that tile

    Everything is try/catched incase the modifier to the index causes results in an IndexOutOfBoundsException
    */
    public static boolean playerGuess(int i, ArrayList<Tile> NG){

        if(NG.get(i).getNumAdj() == 0 && !NG.get(i).getVisStr().equalsIgnoreCase("")) {//If not a bomb
            NG.get(i).setVisStr("");//Set visStr to the number of adjacent bombs
            if(i == 0 ||i == 10 ||i == 20 ||i == 30 ||i == 40 ||i == 50 ||i == 60 ||i == 70 ||i == 80 ||i == 90) {//If edge case: Reveal these 5 adjacent tiles
                try {
                    playerGuess(i - 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 9, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 1, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 11, NG);
                }catch(IndexOutOfBoundsException e){}
                return true;
            }
            else if(i == 9 ||i == 19 ||i == 29 ||i == 39 ||i == 49 ||i == 59 ||i == 69 ||i == 79 ||i == 89 ||i == 99) {//If other edge case: Reveal these 5 adjacent tiles
                try {
                    playerGuess(i - 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 11, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 1, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 9, NG);
                }catch(IndexOutOfBoundsException e){}
                return true;
            }
            else {//If not an edge case: Reveal all 8 adjacent tiles
                try {
                    playerGuess(i - 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 10, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 1, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 1, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 11, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 11, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i + 9, NG);
                }catch(IndexOutOfBoundsException e){}
                try {
                    playerGuess(i - 9, NG);
                }catch(IndexOutOfBoundsException e){}
                return true;
            }
        }//End if
        else if(NG.get(i).getNumAdj() == -1){//If player guesses a bomb
            NG.get(i).setVisStr("X");//Set visStr to "X"
            return false;//Ends the game
        }//end elif
        else if(NG.get(i).getNumAdj() != -1 && NG.get(i).getNumAdj() !=0){//If not a bomb and has an adjacent bomb
            NG.get(i).setVisStr(NG.get(i).getNumAdj()+"");//Set visStr to the number of adjacent bombs
            return true;
        }//End elif

        return true;//Unused: In case conditionals fail (Which they never will, it just kept getting mad if I didn't have it)
    }


    public static void flag(int i, ArrayList<Tile> NG){//Toggles the VisTile back and forth between "?" and "/"

        if(NG.get(i).getVisStr().equals("?")){// ? -> /
            NG.get(i).setVisStr("/");
        }//if
        else if(NG.get(i).getVisStr().equals("/")){// / -> ?
            NG.get(i).setVisStr("?");
        }//Else if

    }//Close funct


    public static int flagCount(ArrayList<Tile> NG) {//Linear Search, counts the number of flags (for the counter at the top)

        int totalFlagCount = 0;

        for (int i = 0; i < 100; i++) {

            if (NG.get(i).getVisStr().equals("/")) {//Counts # of Flags
                totalFlagCount += 1;
            }
        }
        return totalFlagCount;
    }

    public static boolean checkWin(ArrayList<Tile> NG){

        int totalFlagCount = 0;
        int correctFlagCount = 0;

        for(int i = 0;i<100;i++){

            if(NG.get(i).getVisStr().equals("/")){//Counts total # of Flags
                totalFlagCount += 1;
            }

            if(NG.get(i).getVisStr().equals("/") && NG.get(i).getNumAdj() == -1){//Counts total # of correct flags
                correctFlagCount += 1;
            }
        }//For loop


        if(totalFlagCount == 10 && correctFlagCount == 10){//If there are 10 flags, and they're all correct -> Win
            return true;
        }

        else{ //else -> Continue game
            return false;
        }

    }//Close Funct


    //This is my ActionListener function to allow each tile to accept ui
    //I have it for loop after to apply itself to every tile
    public static void mouseIO(int n, JButton[] tiles,JLabel topFlagNumPic, ImageIcon flag, ArrayList<Tile> NG,ImageIcon bomb, JFrame frame, JLabel winnerConfetti, JLabel loser){
        tiles[n].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {//Left click
                    tiles[n].setIcon(null);//Remove any image on the JLabel
                    if(!playerGuess(n, NG)){//It does the player guess function. If the player guess function returns false: it runs the code
                        NG.get(n).setVisStr("");//Removes text
                        tiles[n].setIcon(bomb);//Adds bomb image

                        //This adds the thumbs down on the screen by moving an image (layer) to the front
                        frame.add(loser);
                        frame.setComponentZOrder(loser, 0);
                        frame.revalidate(); // Refresh layout
                        frame.repaint(); // Redraw the container
                    }
                    updateGrid(NG,tiles,bomb,flag);//Updates the visuals
                    if(checkWin(NG)){//If checkWin() returns true
                        frame.add(winnerConfetti);
                        frame.setComponentZOrder(winnerConfetti, 0);
                        frame.revalidate(); // Refresh layout
                        frame.repaint(); // Redraw the container
                    }

                    // tiles[n].setText("?");
                    // topFlagNumPic.setText("10");

                } else if (e.getButton() == MouseEvent.BUTTON3) {//Right click
                    flag(n, NG);
                    if(NG.get(n).getVisStr().equals("/")){
                        tiles[n].setText(null);
                        tiles[n].setIcon(flag);
                        topFlagNumPic.setText(flagCount(NG)+"");
                    }
                    else if(NG.get(n).getVisStr().equals("?")){
                        tiles[n].setIcon(null);
                        topFlagNumPic.setText(flagCount(NG)+"");
                    }
                    updateGrid(NG,tiles,bomb,flag);
                    if(checkWin(NG)){
                        // System.out.println("Winner");
                        frame.add(winnerConfetti);
                        frame.setComponentZOrder(winnerConfetti, 0);
                        frame.revalidate(); // Refresh layout
                        frame.repaint(); // Redraw the container



                    }
                    // tiles[n].setText(null);
                    // tiles[n].setIcon(flag);
                    // topFlagNumPic.setText("9");
                }
            }
        }); // Closes the addMouseListener method and the anonymous class.
    }

    public static void main(String[] args) {

//My images
        ImageIcon bomb = new ImageIcon("minesweeperBomb2.png");
        ImageIcon flag = new ImageIcon("minesweeperFlagResized.png");
        ImageIcon confetti = new ImageIcon("confettiXLarge.png");
        ImageIcon loserPic = new ImageIcon("LoserPicture.png.png");


        JLabel winnerConfetti = new JLabel();
        winnerConfetti.setIcon(confetti);
        winnerConfetti.setVerticalAlignment(JLabel.CENTER);
        winnerConfetti.setHorizontalAlignment(JLabel.CENTER);
        winnerConfetti.setBounds(0,0,800,800);

        JLabel loser = new JLabel();
        loser.setIcon(loserPic);
        loser.setVerticalAlignment(JLabel.CENTER);
        loser.setHorizontalAlignment(JLabel.CENTER);
        loser.setBounds(0,0,800,800);

        //Label for the bomb counter at the top
        JLabel topBombNumPic = new JLabel();
        topBombNumPic.setText("10");
        topBombNumPic.setIcon(bomb);
        topBombNumPic.setHorizontalTextPosition(JLabel.RIGHT);
        topBombNumPic.setFont(new Font("MV Boli",Font.PLAIN,30));
        topBombNumPic.setIconTextGap(10);
        topBombNumPic.setVerticalAlignment(JLabel.TOP);
        topBombNumPic.setHorizontalAlignment(JLabel.CENTER);
        topBombNumPic.setBounds(100,0,100,50);


        //Label for the flag counter at the top
        JLabel topFlagNumPic = new JLabel();
        //topFlagNumPic.setText(fla+"");
        topFlagNumPic.setIcon(flag);
        topFlagNumPic.setHorizontalTextPosition(JLabel.RIGHT);
        topFlagNumPic.setFont(new Font("MV Boli",Font.PLAIN,30));
        topFlagNumPic.setIconTextGap(10);
        topFlagNumPic.setVerticalAlignment(JLabel.TOP);
        topFlagNumPic.setHorizontalAlignment(JLabel.CENTER);
        topFlagNumPic.setBounds(500,0,100,50);


        //Label for the title at the top
        JLabel minesweeperText = new JLabel();
        minesweeperText.setText("Minesweeper");
        minesweeperText.setFont(new Font("MV Boli",Font.PLAIN,30));
        minesweeperText.setVerticalAlignment(JLabel.TOP);
        minesweeperText.setHorizontalAlignment(JLabel.CENTER);
        minesweeperText.setBounds(200,0,300,50);

        //Top panel with the title, bomb count, and flag count
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(211,211,211));
        topPanel.setBounds(0,0,700,50);

        //String test = "Hello";
        // JButton button0 = new JButton();
        // button0.setBounds(0,50,80,80);
        // button0.setText(test);

        //Creates a list of 100 buttons
        JButton[] tiles = new JButton[100];
        for(int i = 0; i<100;i++){
            tiles[i] = new JButton();
        }

//Variables for loop
        int x = 0;
        int z = 0;
        int y = 50;

        for (int i = 0; i < 10; i++) {//Loops through making the buttons

            while (x < 700) {

                tiles[z].setBounds(x, y, 70, 70);
                x += 70;
                z += 1;
            }
            y += 70;
            x = 0;
        }


        JFrame frame = new JFrame();//Window
        frame.setTitle("Brock's Minesweeper Game!!!");//Window text
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//End program on close
        frame.setResizable(false);//Cant resize the screen

        frame.setSize(712,782);
        frame.setLayout(null);
        //frame.setVisible(true);

        frame.add(topBombNumPic);//Bomb label
        frame.add(topFlagNumPic);//flag label
        frame.add(minesweeperText);
        for (int i = 0;i<100;i++){
            frame.add(tiles[i]);
        }
        frame.add(topPanel);
        frame.setIconImage(bomb.getImage());//Image in top left
        //frame.setVisible(true);//Set the frame visible
        //START OF GAME CODE:

        ArrayList<Tile> numGrid = new ArrayList<>();//Makes an ArrayList of Tile objects
        Scanner sc = new Scanner(System.in);

        for(int i = 0;i<100; i++){//Adds 100 Tile objects to the ArrayList
            numGrid.add(new Tile());
        }

        for(int i = 0;i<100;i++){//Add Action listeners
            mouseIO(i,tiles,topFlagNumPic,flag,numGrid,bomb, frame, winnerConfetti, loser);
        }
        frame.setVisible(true);//Set the frame visable
//Initialized
//Begin Game Loop

        topFlagNumPic.setText(flagCount(numGrid)+"");//Sets the number of flags on the counter at the top to 0

        //Sets the bombs
        int numBombs = 0;
        while (numBombs != 10) {//Once there are 8 bombs, break out of loop
            int randint = ThreadLocalRandom.current().nextInt(0, 99 + 1);//Randint from 0-99
            if (numGrid.get(randint).getNumAdj() >= 0) {//If the numAdj() is NOT already -1 (-1 meaning it's a bomb), then proceed
                numGrid.get(randint).setBomb();//Sets numAdj to -1
                numBombs += 1;//Up the count after bomb is added
            }//If statement for bombs
        }//While loop for numBombs

        for (int i = 0; i < 100; i++) {//Loops through all the tiles, assigning each tile with their number of adjacent bombs.
            setAdjacentBombs(i, numGrid);
        }//For loop for setAdjTiles
        //numAdjGrid(numGrid);
        updateGrid(numGrid,tiles,bomb,flag);
    }
}