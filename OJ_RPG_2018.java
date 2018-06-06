import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; // Allow use of timer
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class OJ_RPG_2018 extends JFrame 
{// start
     static Scanner sc;

//===============================<Update>===========================================
  public void update ()
  {
    
    repaint();
    
  }
  //=================================<Player class>============================== 
  
  class Player {
    int identity;
    int xpos, ypos;
    boolean status, setGender; //true for female, false for male;
    String setName;
    private int [] inventory = new int [3]; //stores 
    int direction;
    
    public Player (int x, int y, int id, boolean gender, String name) { //constructor
      identity = id;
      xpos=x;
      ypos=y;
      status = true;
      setGender = gender;
      setName = name;
    }
    public int getID() { //retrieve the identity of the player to see whether the object is a player or enemy (player=1, enemy=0)
      return identity; 
    }
    
    public int getX() { //retrieve the players y position 
      return xpos; 
    }
    public int getY() { //retrieve the players x position
      return ypos; 
    }
    public int changeX(int num) { //change the players x position
      return xpos+num; 
    }
    public int changeY(int num) { //change the players y position
      return ypos+num; 
    }
    
    public boolean getStatus() { //to see if the player is alive
      return status;
    }
      
    public boolean getGender() { //if it is true then female, false=male
      return setGender;
    }
    public int getKey(){ //retrieve key from inventory array
      int key= inventory[0];
      return key;
    }
     public int getSword(){ //retrieve key from inventory array
      int sword= inventory[1];
      return sword;
    }      
     public int getMemory(){ //retrieve key from inventory array
      int memory= inventory[2];
      return memory;
    }
     public int getDirection(int num){ //draw class will have 4 numbers each assigned an arrow key
       return num;  
     //getDirectionx(); 
     }
  }
      
  class Item {
    boolean object;
    int ID, xpos,ypos;
    //changing x and y coordinates for the memory objets which will follow the monster
    
    public Item (int identity, int x, int y) { //constructor
      ID = identity; //the id is like 1 for sword, 0 for key and 2 for memory objects
      object = true; //change boolean for object being on the screen to true
      xpos= x; 
      ypos=y;
      
    }
    public int getx (){ //x position of the object
      return xpos;
    }
    
    public int gety () {//y position of the object
      return ypos;    
    }
    
    public void pickUp() { //if the object is picked by the player such as if they walk over it then the boolean for the object being on the screen is changed to false
      object= false;
    }
 }
//=================================<Panel constructor>============================== 
  public OJ_RPG_2018 () 
  {// start of panel setup
    // Creating JPanel for the background and JPanel for interations (options)
    setLayout (new BorderLayout ());// Use BorderLayout for main panel
    JPanel background = new JPanel (); // Create a content panel to hold the backgournd images
    background.setLayout(new BorderLayout ());// sets layout to null so that set bounds can be used 
    // timer setup   
    
    // Colouring panels  
    background.setBackground(Color.BLACK);
    //timer setup   
    
    // Creating Drawing Area and Panel Setup
    DrawArea playfield = new DrawArea (900, 600);
    background.add(playfield); // Output area
    add("Center",background);
    setContentPane(background);
    background.setFocusable(true);
    pack();
    // Game window Setup  
    setTitle("Hangman Plus");
    background.setPreferredSize (new Dimension (900,600));
    setSize(1080,740); //Sets the JFrame size
    setVisible(true); //Reveals JFrame
    setResizable(false); 
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);//centers window on launch  
  }
//=======================================================================================================    
class DrawArea extends JPanel
  {//start of drawarea
    // load images for use
    
    public DrawArea (int width, int height)// Create panel of given size
    {//start of drawarea
      this.setBounds( 0, 0, width, height);//(new Dimension (width, height));
    }//end of drawarea
    
    public void paintComponent (Graphics g)  // g can be passed to a class method
    {//start of paintComponent
      
    }
    
  }//end of drawarea

//==================================================================================
  //                                 Constructor                                \\
//==================================================================================


//==================================================================================
  //                                 Listeners                                  \\
//==================================================================================
  class TimerListener implements ActionListener //reacts to timer
  {//start
    TimerListener()
    {//start
      //t.start();//starts timer
    }//end
    public void actionPerformed (ActionEvent e)
    {//start of void
      update();//updates graphics
//      if (false)
//      {
//        stop = true;
//      }
//      
//      if (true)
//      {
//        stop = false;
//      }
      
    }//end of void
  }//end of class 

public static void main(String[] args) 
  {///start of main 
  sc = new Scanner (System.in); 
   OJ_RPG_2018 game = new  OJ_RPG_2018 ();
    game.setVisible (true);
    
    
    
  }//end of main
}///end of class