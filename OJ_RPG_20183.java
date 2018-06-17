import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; // Allow use of timer
import javax.imageio.*;
import java.util.ArrayList; //allows use of Arraylist  
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class OJ_RPG_20183 extends JFrame implements ActionListener
{// start
  static Scanner sc;
  JButton choice[] = new JButton[3];
  
//=================================<Menu Panel constructor>======================= 
  public OJ_RPG_20183 () 
  {// start of panel setup
    // Creating JPanel for the background and JPanel for interations (options)
    setLayout (new BorderLayout ());// Use BorderLayout for main panel
    JPanel background = new JPanel (); // Create a content panel to hold the backgournd images
    background.setLayout(new GridLayout ());// sets layout to null so that set bounds can be used 
    // timer setup   
    
    // Colouring panels  
    //background.setBackground(Color.BLACK);
    //timer setup   
    
    // Creating Drawing Area and Panel Setup
    DrawArea playfield = new DrawArea (900, 600);
    
    choice[0] = new JButton("Play Game"); // create letter button
    playfield.add(choice[0]); // add to board
    choice[0].addActionListener(this); // link to listener (same for the next 3 buttons)
    
    choice[1] = new JButton("View Instructions"); 
    playfield.add(choice[1]); 
    choice[1].addActionListener(this); 
    
    choice[2] = new JButton("Quit"); 
    playfield.add(choice[2]); 
    choice[2].addActionListener(this);
    
    background.add(playfield); // Output area
    add("Center",background);
    
    setContentPane(background);
    background.setFocusable(true);
    pack();
    // Game window Setup  
    setTitle("Menu");
    background.setPreferredSize (new Dimension (900,600));
    setSize(900,700); //Sets the JFrame size
    setVisible(true); //Reveals JFrame
    setResizable(false); 
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setLocationRelativeTo(null);//centers window on launch  
  }
//==================================================================================== 
  public void actionPerformed (ActionEvent e)
  {   
    if (e.getActionCommand().equals("Play Game")) { //if the user presses the first button
      try {
        character ch = new character();
        setVisible(false);
      }
      catch (Exception ex) {
      }
    }
    else if (e.getActionCommand().equals("View Instructions")) { //if the user presses the second button
      choice[1].setEnabled(true); //the second choice has now been played and cannot be played again, disable the button
      Instructions window = new Instructions (); //create a hangman game class object according to the second choice
      window.setVisible (true);      
    }
    
    else if (e.getActionCommand().equals("Quit")) { //if the user presses the third button
      choice[2].setEnabled(true); //the third choice has now been played and cannot be played again, disable button
      System.exit(0);
    }
    
  }
  //=========================================<drawarea method>========================================================= 
  
  class DrawArea extends JPanel
  {//start of drawarea
    // load images for use
    
    public DrawArea (int width, int height)// Create panel of given size
    {//start of drawarea
      this.setBounds( 0, 0, width, height);//(new Dimension (width, height));
    }//end of drawarea
    
    public void paintComponent (Graphics g)  // g can be passed to a class method
    {//start of paintComponent
      //setBackground(Color.lightGray); //set colour of the background of window
      g.setFont(new Font("American Typewriter", Font.ITALIC, 24)); //set font and colour
      g.setColor(Color.red);
      g.drawString ("RPG", 426, 160); //prompt user to choose a word to guess
      
      g.setFont(new Font("Times New Roman", Font.BOLD, 20));
      g.setColor(Color.blue);
      g.drawString("1. START GAME", 377, 230); //display options
      g.drawString ("2. VIEW INSTRUCTIONS", 339, 280);
      g.drawString ("3. QUIT", 412, 330); 
      g.drawString ("Press the correlated button", 330, 470); 
    }
    
  }//end of drawarea
  
  public static void main(String[] args) 
  {///start of main 
    sc = new Scanner (System.in); 
    OJ_RPG_20183 game = new  OJ_RPG_20183 ();
    game.setVisible (true);   
  }//end of main
  
}///end of class

//==================================New class for instructions panel=====================================================
class Instructions extends JFrame
{
  DrawArea board;
  
  public Instructions() //constructor
  {
    board = new DrawArea (100, 100);
    add(board); //Add to JFrame   
    setTitle ("Instructions Panel"); //set title and size of window
    setSize (860, 700);
    //setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo (null); //center window
    setResizable(false);  //disable users ability to resize window
  }
  
  class DrawArea extends JPanel
  {
    public DrawArea (int width, int height)  // Create panel of given size
    {
      this.setPreferredSize (new Dimension (width, height));
    }
    
    public void paintComponent (Graphics g)
    {
      g.setColor (new Color (120, 0, 0)); //create the hangman stand
      g.setFont(new Font("Times New Roman", Font.ITALIC, 20)); //set font and colour
      g.drawString("Welcome to the RPG made by Jonathan, Gajan and Sonia", 192, 45);   
      g.setFont(new Font("Times New Roman", Font.BOLD, 20)); //set font and colour
      g.drawString("StoryLine", 375, 100);   
      g.setFont(new Font("Times New Roman", Font.BOLD, 15)); //set font and colour
      g.drawString("In this game you get to customize parts of your player (name and gender).", 179, 130);   
      g.drawString("The story involves your player starting on top of a tower and suffering from complete memory", 114, 170);   
      g.drawString("loss. The tower has 3 floors that contain new challenges and enemies the player must defeat.", 127, 220);   
      g.drawString("To restore the players memory you must successfully escape the tower. In order to defeat the enemies", 79, 270);    
      g.drawString("utilize the swords and keys on the floors of the levels.", 235, 320);  
      g.setFont(new Font("Times New Roman", Font.BOLD, 20)); //set font and colour
      g.drawString("Controls", 370, 385); 
      g.setFont(new Font("Times New Roman", Font.BOLD, 15)); //set font and colour
      g.drawString("The arrow keys will help you move around the level.", 240, 420); 
      g.drawString("To pick up objects you have to stand on top of them to add them to your inventory. ", 126, 470);     
      g.drawString("To exit a room you must have a key in your inventory and walk through the door for it to open. ", 105, 520); 
      g.drawString("To use the sword press s. To view instructions during the game press i.", 168, 570); 
      g.drawString("Good Luck!", 375, 615); 
    }   
  }
}
//==================================New class for character panel=======================================================
class character extends JFrame implements ActionListener
{
  DrawArea board;
  JButton choice[] = new JButton[3]; //for quit button
  public boolean gender;
  String nameOfPlayer;
  private JTextField _addNameTF = new JTextField (15);
  private JPanel addNamePanel   = new JPanel();
  
  public character() throws IOException//constructor
  {
    setLayout (new FlowLayout ());// Use BorderLayout for main panel
    JPanel background = new JPanel (); // Create a content panel to hold the backgournd images
    background.setLayout(new FlowLayout ());// sets layout to null so that set bounds can be used 
    
    // Creating Drawing Area and Panel Setup
    board = new DrawArea (1000, 1000);
    
    choice[0] = new JButton("Female"); // create female button
    board.add(choice[0], "North"); // add to board
    choice[0].addActionListener(this); // link to listener
    
    choice[1] = new JButton("Male"); // create male button
    board.add(choice[1], "Female"); // add to board
    choice[1].addActionListener(this); // link to listener
    
    choice[2] = new JButton("Proceed"); // create quit button
    board.add(choice[2], "North"); // add to board
    choice[2].addActionListener(this); // link to listener
    
    JButton addNameBtn = new JButton ("Add Name");
    addNameBtn.addActionListener (this); // Connect button to listener class
    addNameBtn.setActionCommand( "add" );
    
    background.add(addNamePanel);
    
    addNamePanel.add (new JLabel ("Enter your characters name:")); // Create, add label
    addNamePanel.add (_addNameTF);            // Add input field
    addNamePanel.add (addNameBtn);  
    
    background.add(board); // Output area
    add("Center",background);
    setContentPane(background);
    background.setFocusable(true);
    pack();
    setTitle("Character Panel"); 
    background.setPreferredSize (new Dimension (900,800));
    setSize(900,700); //Sets the JFrame size
    setVisible(true); //Reveals JFrame
    setResizable(false); 
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);//centers window on launch  
  }
  //==================================<method for proceed button>==============================
  public void actionPerformed (ActionEvent e)
  {   
    String newName = _addNameTF.getText (); // Retrieve values from text fields
    if (e.getActionCommand().equals("Proceed")) { //if the user presses the first button
      try {
        skynet game = new skynet(gender, nameOfPlayer);  
        setVisible(false); //closes the character making window and will only keep the game open now
      }
      catch (Exception ex) {
      }
    }
    if (e.getActionCommand().equals("Male")) { //if the user presses the first button
      gender= false;
      System.out.println (gender);
    }
    if (e.getActionCommand().equals("Female")) { //if the user presses the first button
      gender= true;
      System.out.println (gender);
    }
    if (e.getActionCommand().equals( "add" ) && !newName.equals (""))
    {
      nameOfPlayer = newName;
    }
  }

//  public boolean returnGender() {
//    return gender; 
//  }
//  public String returnName () {
//    return nameOfPlayer; 
//  }
//  
//==================================<method for drawarea>=============================================================
  class DrawArea extends JPanel
  {
    public DrawArea (int width, int height)  // Create panel of given size
    {
      this.setPreferredSize (new Dimension (width, height));
    }
    
    public void paintComponent (Graphics g)
    {
      g.setColor (new Color (120, 0, 0)); //create the hangman stand
      g.setFont(new Font("Times New Roman", Font.ITALIC, 20)); //set font and colour
      g.drawString("Create your character by picking your: ", 345, 100);   
      g.setFont(new Font("Times New Roman", Font.BOLD, 20)); //set font and colour
      g.drawString("Gender (press it)", 435, 130);   
      g.drawString("Name (Enter it above and press add name)", 330, 190);   
      g.drawString("Press the corresponding buttons and then press proceed", 270, 250);   
      
    }
  }
  
}

//==================================New class for actual rpgpanel=======================================================

class skynet extends JFrame //implements ActionListener
{// start
  Timer t = new Timer(80,null);// updates graphics and game
  int num = 0, lvl =0;
  Map floor = new Map(lvl);
  boolean walk = false;
  Entities en = new Entities(lvl);
  Player player;// = new Player (2*32,21*32,0,gender,"Raw Vodka",1); //player object is created and gender variable is used
  Player enemy=new Player (15*32,9*32,0,true,"Monster",1);
  static int enemyBound[] = { -1, -1, -1, -1}; // up,down,left,right
  // JButton choice[] = new JButton[1]; //for quit button
  boolean end = false;
  //=================================<Panel constructor>==========================
  public skynet (boolean gender, String name) throws IOException
  {// start of panel setup
   // System.out.println (name);
    player = new Player (2*32,21*32,0,gender,name,1); //player object is created and gender variable is used
    
    // Creating JPanel for the background and JPanel for interations (options)
    setLayout (new BorderLayout ());// Use BorderLayout for main panel
    JPanel background = new JPanel (); // Create a content panel to hold the backgournd images
    background.setLayout(new BorderLayout ());// sets layout to null so that set bounds can be used 
    
    // Colouring panels  
    background.setBackground(Color.BLACK);
    //timer setup   
    t.addActionListener(new TimerListener());
    background.addKeyListener(new playerListener());
    // Creating Drawing Area and Panel Setup
    DrawArea playfield = new DrawArea (1000, 1000);
//    choice[0] = new JButton("Quit"); // create quit button
//    playfield.add(choice[0], "East"); // add to board
//    choice[0].addActionListener(this); // link to listener
    
    background.add(playfield); // Output area
    add("Center",background);
    setContentPane(background);
    background.setFocusable(true);
    pack();
    // Game window Setup  
    setTitle("Official RPG");  //CHANGE LATER
    background.setPreferredSize (new Dimension (1000,800));
    setSize(1080,825); //Sets the JFrame size
    setVisible(true); //Reveals JFrame
    setResizable(false); 
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);//centers window on launch  
  }
//==================================<Image Method>====================================
  public static Image loadImage (String name)  //Loads image from file
  { Image img = null;
    try{img = ImageIO.read (new File (name));}
    catch (IOException e){}
    return img;}
//===============================<Update>===========================================
  public void update ()
  {repaint();}
  
//=====================================<Map class>=====================================================================
  class Entities
  {//s - sword
   //k - key
   //e - enemy 
    boolean[] onscreen;
    int[] xpos, ypos;
    String[] list;
    char[] id;
    int enemies = 0, swords = 0, keys = 0;
    int[] lines = new int[] {4,6,8};
    int velx =6, vely = 6;
    
    public Entities (int lvl)
    {  
      this.makeEntities(lvl);
    }
    public Scanner loadList (int lvl)
    {
       Scanner sc = null;
       try{ File level = new File("res/text/entities/entity" + lvl + ".txt");
       sc = new Scanner(level);
       }catch (IOException e){}
       return sc;
    }
    public void makeEntities (int lvl)
    {
      list = new String[lines[lvl]];
      onscreen = new boolean[lines[lvl]];
      Scanner sc = this.loadList(lvl);
      
      for(int x = 0; x < lines[lvl] ; x++)
      {
        list[x] = sc.nextLine();
        System.out.println(list[x]);
      }  
      xpos = new int[lines[lvl]];
      ypos = new int[lines[lvl]];
      id = new char[lines[lvl]];
      System.out.println("hi");
      
    for (int y = 0; y < lines[lvl] ; y++)  
    {
      id[y] = list[y].charAt(0);
      System.out.println(id[y]);
      
      if(id[y] == 's')
      {this.makeSword(y);
      System.out.println("bye");}
      else if (id[y] == 'k')
      {this.makeKey(y);}
      else if (id[y] == 'e')
      {this.makeEnenmy(y);}
      else{System.out.println("bye");}
      onscreen[y] = true;   
    }
    
    }
    
    public void makeSword(int x)
    {
      xpos[x] = ((int)list[x].charAt(1) - 65) *32;   
      ypos[x] = ((int)list[x].charAt(2) - 65) *32;
      System.out.println(xpos[x]);
      System.out.println(ypos[x]);
      swords++;
    }
     public void makeKey(int x)
    {
      xpos[x] = ((int)list[x].charAt(1) -65) *32;   
      ypos[x] = ((int)list[x].charAt(2) -65) *32; 
      keys++;
    }
      public void makeEnenmy(int x)
    {
      xpos[x] = ((int)list[x].charAt(1) -65) *32;   
      ypos[x] = ((int)list[x].charAt(2) -65) *32;
      enemies++;
    }    
     public int numofEn (int x)
     {
       if(x == 1)
         return swords;
       if(x == 2)
         return keys;
       if(x == 3)
         return enemies;
       else
       return 0;
     }
    public int getX(int x)
    {
      return xpos[x];
    }
    public int getY(int x)
    {
      return ypos[x];
    }
    public boolean appear(int x)
    {
    return onscreen[x];
    } 
    public int getSize()
    {
      return list.length;
    }
    public char getID(int x)
    {
      return id[x];
    }
    
    //=======================================
     public void changeX(int num, int in) { //change the enemy's x position
      int x = xpos[in] + velx ,y = ypos[in] + vely;
         if (floor.getFloorID((x+24)/32 ,(y+26)/32) != 'f')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
         {xpos[in] += num;} //right
      else 
      { if (floor.getFloorID((x)/32 ,(y+26)/32) != 'f')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
        {xpos[in] += num;}} //left
    }
    public void changeY(int num, int in) { //change the enemy's y position
      int x = xpos[in] +velx ,y = ypos[in] +vely;
         if (floor.getFloorID((x+26)/32,(y+26)/32) != 'f' && floor.getFloorID(x/32 ,(y+32)/32) != 'w')
        {ypos[in] += num;} //down
      else
      { if (floor.getFloorID((x+26)/32,y/32)!= 'f' && floor.getFloorID(x/32 , (y+24)/32) != 'w')
        {ypos[in] += num;}}//up
    }
    //====================== tony =====================
    public int[] EnemyAI (Player p,int index, Map floor, int[] enemyBound) //hero has id = 1, enemy has id = 2
    { //25 by 25 map tile is  32by32
      if (onscreen[index]) // if monster is alive
      {
        int dx = p.getX()/32 - this.getX(index)/32; //distance between player and enemy in x direction
        //System.out.println ("dx = " + dx);
        int dy = p.getY()/32 - this.getY(index)/32; //distance between player and enemy in y direction
        //System.out.println ("dy = " + dy);
        double ds = Math.sqrt( (dx*dx+0.0) + (dy*dy+0.0) ); //find distance between player position and enemy position
        //System.out.println ("ds = " + ds);
        boolean moveX = true; //if monster can see "player" (x direction)
        boolean moveY = true; //if monster can see "player" (y direction)
        int vel = 6; //velocity of monster
        
        if (enemyBound[0] == -1)
        { 
          enemyBound[3] = this.getX(index); enemyBound[2] = this.getX(index); enemyBound[0] = this.getY(index); enemyBound[1] =this.getY(index);
          for (int x = this.getX(index)/32; x>0; x--)
          {
            if (floor.getFloorID( x,this.getY(index)/32) != 'w') //if there's not a wall
              enemyBound[2]=x+1;
          }
          for (int x = this.getX(index)/32; x < 25; x++)
          {
            if (floor.getFloorID( x,this.getY(index)/32) != 'w') //if there's not a wall
              enemyBound[3]=x-1;
          }
          for (int y = this.getY(index)/32; y>0; y--)
          {
            if (floor.getFloorID( this.getX(index)/32,y) != 'w') //if there's not a wall
              enemyBound[0]=y+1;
          }
          for (int y = this.getY(index)/32; y<25; y++)
          {
            if (floor.getFloorID( this.getX(index)/32,y) != 'w') //if there's not a wall
              enemyBound[1]=y-1;
          }
       }
        
        for (int x = (Math.min(en.getX(index)/32,this.getX(index)/32)) ; x < (Math.max(p.getX()/32,this.getX(index)/32)) ; x++) // check tiles between monster and player (x direction)
        {
          if (floor.getFloorID( x,this.getY(index)/32) == 'w') //if there's a wall
          {
            moveX = false; //monster cant see player (monster stops moving)
            
          }
        }
        for (int y = (Math.min(p.getY()/32,this.getY(index)/32)) ; y < (Math.max(p.getY()/32,this.getY(index)/32)) ; y++) // check tiles between monster and player (y direction)
        {
          if (floor.getFloorID( this.getX(index)/32,y) == 'w') //if there's a wall
          {
            moveY = false; //monster cant see player (monster stops moving) 
          }
        }
        
        if( this.getY(index) > enemyBound[0] *32 && this.getY(index) < enemyBound[1] *32 && this.getX(index) > enemyBound[2]*32 && this.getX(index) < enemyBound[3]*32)
        {
          if(moveX && moveY) //if monster can see you
          {
            //when dx is larger than dy
            if ( Math.abs(dx) >= Math.abs(dy)) //if x direction is farther than y - move in x direction
            { 
              if (this.getX(index)-26 != enemyBound[2] &&  this.getX(index)+32 < enemyBound[3])//if spot to the right/left is empty
              {this.changeX(Integer.signum(dx)*vel, index);}//move 1 tile in that direction
              
              if (this.getY(index)-32 != enemyBound[0] &&  this.getX(index)+26 < enemyBound[1])//if spot above/below is empty
              {this.changeY(Integer.signum(dy)*vel, index);}//move 1 tile in that direction
              
            }
            //when dy is larger than dx
            if ( Math.abs(dy) > Math.abs(dx)) //if y direction is farther than x - move in y direction
            {
              
              if (this.getY(index)-32 <= enemyBound[0] &&  this.getX(index)+26 < enemyBound[1])//if spot above/below is empty
              {this.changeY(Integer.signum(dy)*vel, index);}//move 1 tile in that direction
              
              if (floor.getFloorID(this.getX(index)/32+(Integer.signum(dx)),this.getY(index)/32) == 'f' && this.getX(index)-26 <= enemyBound[2] &&  this.getX(index)+26 < enemyBound[3])//if spot to the right/left is empty
              {this.changeX(Integer.signum(dx)*vel, index);}//move 1 tile in that direction
              
            }
          }
          
        }
      }
      return enemyBound;
    }  
    //===============================================
  }
  
  class Map  
  {
    
    char[][] floor = new char[25][25];
    String[] map = new String[] {"map1", "map2", "map3"};
    int lvl;
    Image pic;
    
    public Map (int l)
    { lvl = l;
       this.loadMap();}
    
    public Scanner load()
    {
      Scanner sc = null;
      try{ File level = new File("res/text/maps/" + map[lvl] + ".txt");
      pic = loadImage("res/images/maps/" + map[lvl] + ".png");
      sc = new Scanner(level);
      }catch (IOException e){}
      
      return sc;
    }
    
    public void loadMap() 
    {
      Scanner sc = this.load(); 
      
      String  line = "";
     
        for (int y = 0; y < 25; y++) 
        {line = sc.nextLine();
        for (int x = 0; x < 25; x++)
        {floor[x][y] = line.charAt(x);} }
     }
    
    public Image getMap()
    {return pic;}
    
    public char getFloorID (int x, int y)
    {return floor[x][y];}
    
    
    public void changeMap(int l)
    {lvl = l;
      this.loadMap();}
    
  }
  
     
//==================================<Player class>=====================================================================
  class Player {
    int identity;
    int xpos, ypos;
    int velx, vely;
    boolean status, setGender; //true for female, false for male;
    String setName;
    private boolean sword = false, key = false; //stores 
    int direction;
    
    public Player (int x, int y, int id, boolean gender, String name, int dir) { //constructor
      identity = id;
      xpos=x;
      ypos=y;
      status = true;
      setGender = gender;
      setName = name;
      direction = dir;
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
    public int getVelX() { //retrieve the players y position 
      return velx; 
    }
    public int getVelY() { //retrieve the players y position 
      return vely; 
    } 
    public void changeX(int num) { //change the enemy's x position
      int x = xpos+velx ,y = ypos +vely;
      
      if (num != 0)
      {if (num > 0)//if positive
        { if (floor.getFloorID((x+24)/32 ,(y+24)/32) != 'w')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
        {xpos += num;}
        direction = 2;} //right
      else 
      { if (floor.getFloorID((x)/32 ,(y+24)/32) != 'w')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
        {xpos += num;}
      direction = 3;}} //left
    }
    public void changeY(int num) { //change the enemy's y position
      int x = xpos+velx ,y = ypos +vely;
      
      if(num !=0)
      {if (num >0) //if positive
        { if (floor.getFloorID((x+24)/32,(y+24)/32) != 'w' && floor.getFloorID(x/32 ,(y+32)/32) != 'w')
        {ypos += num;}
        direction = 1;} //down
      else
      { if (floor.getFloorID((x+24)/32,y/32)!= 'w' && floor.getFloorID(x/32 , (y+24)/32) != 'w')
        {ypos += num;}
      direction = 0;} }//up
    }
    
    public void setVelX(int vel)
    {velx=vel;}
    public void setVelY(int vel) 
    {vely=vel;}
    public boolean getStatus() { //to see if the player is alive
      return status;
    }
    
    public boolean getGender() { //if it is true then female, false=male
      return setGender;
    }
    public String getName() { //if it is true then female, false=male
      return setName;
    }
    public boolean getSword(){ //change item entered (based on the number ID) to true so the player has picked up the object
      if (sword == true)
        return true;
      else
        return false;
    }  
    public boolean getKey(){ //change item entered (based on the number ID) to true so the player has picked up the object
      if (key == true)
        return true;
      else
        return false;
    }  
    public int getDirection(){ //draw class will have 4 numbers each assigned an arrow key
      return direction;  
    }
     public void changeXY(int x, int y)   
   {xpos =x;
   ypos =y;}
    
//=====================================<Enemy method>================================================== 
   
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  }
//=======================================<DrawArea class>================================================================    
  class DrawArea extends JPanel
  {//start of drawarea
    // load images for use
    
    public DrawArea (int width, int height)// Create panel of given size
    {//start of drawarea
      this.setBounds( 0, 0, width, height);//(new Dimension (width, height));
    }//end of drawarea
    
    public void paintComponent (Graphics g)  // g can be passed to a class method
    {//start of paintComponent
     Image[][][] tony = loadPlayer();
     if (!end) {
        int x,y,z;
        if (player.getGender())
        {z=0;}
        else {z=1;}
        
        y = player.getDirection();
        //System.out.println("<"+y+">");
        g.drawImage(floor.getMap(),0,0,null);

        drawEn(g);
        score(g, player.getGender(), player.getName(), player.getSword(), player.getKey()); //calling scoreboard
        if (!walk)
        {g.drawImage(tony[z][y][0], player.getX(), player.getY()-16,null);}
        else{g.drawImage(tony[z][y][num],player.getX(), player.getY()-16,null);}
      
        Image[][][] monster = loadPlayer();
      //g.drawImage(monster[0][1][0], enemy.getX(), enemy.getY()-16,null);
     }
     else 
       end(g, player.getStatus());
       //draw end graphics
    }
    
    
    ////////////////////// ///////////    ///////////    ///////////    ///////////
    public void score (Graphics g, boolean gender, String name, boolean sword, boolean key)  // g can be passed to a class method
    {
      Image female = loadImage("res/profile pictures/female image.png");
      Image male = loadImage("res/profile pictures/male.png");
      //image loading and text
      if (gender == true)
        g.drawImage(female, 810,15,null);
      else 
        g.drawImage(male, 810,15,null);
      
     g.setFont(new Font("American Typewriter", Font.BOLD, 30)); //set font and colour
     g.drawString(name+"", 918, 70);  
     g.setFont(new Font("American Typewriter", Font.BOLD, 18)); //set font and colour
     g.drawString("Inventory", 886, 170);  
     g.drawString("Sword: ", 810, 220);
     if (sword == true)
       g.drawString("You are armed", 810, 240);
     else
       g.drawString("Not armed", 810, 240);
     g.drawString("Key: ", 810, 290);  
     if (key == true)
       g.drawString ("You have a key", 810, 310);
     else 
       g.drawString ("No key", 810, 310);  
     
     g.drawString ("Use arrow keys to move", 810, 370);
     g.drawString ("Press red x in top left corner to quit", 810, 410);
     g.drawString ("Press i to view instructions", 810, 450);
     
    }
    public void end (Graphics g, boolean ifAlive) {
      if (ifAlive) {
       repaint();
       g.setFont(new Font("American Typewriter", Font.BOLD, 60)); //set font and colour
       g.setColor (Color.GREEN); //set font and colour
       g.drawString ("YOU WIN!", 300, 280);   
      g.drawString ("PRESS R TO RESTART", 200, 380);   
      g.drawString ("PRESS Q TO QUIT", 200, 480);   }
      else {
        repaint();
        g.setFont(new Font("American Typewriter", Font.BOLD, 60)); //set font and colour
        g.setColor (Color.RED); //set font and colour
        g.drawString ("YOU LOSE", 300, 280);   
        g.drawString ("PRESS R TO RESTART", 200, 380);   
        g.drawString ("PRESS Q TO QUIT", 200, 480);   
      }
   
    }
    public Image[][][] loadPlayer()
    {
      Image[][][] player = new Image[2][4][3];//z/y/x
      for (int z = 0; z < 2; z++)
      {for (int y = 0; y < 4; y++)
        {for (int x = 0; x < 3; x++)
        {player[z][y][x] = loadImage("res/images/player/"+z+"/"+y+x+".png");
        }}}
      return player;}
    
    public void drawEn (Graphics g)
    {
     Image s = loadImage("res/images/entities/sword.png");
     Image k = loadImage("res/images/entities/key.png");
     Image[] e = new Image[3];
    
     for (int y = 0; y < 3; y++)
     {
       e[y] = loadImage("res/images/entities/enemy/0/"+y+".png");
     }
      g.drawImage(e[num], enemy.getX(), enemy.getY()-16,null);
      for (int x = 0; x < en.getSize();x++)
      {
        if ( en.getID(x) == 's' && en.appear(x))
         g.drawImage(s, en.getX(x), en.getY(x),null);
        else if (en.getID(x) == 'k'&& en.appear(x))
           g.drawImage(k, en.getX(x), en.getY(x),null);
        else if (en.getID(x) == 'e'&& en.appear(x))
           g.drawImage(e[num], en.getX(x), en.getY(x)-16,null);
      }
      
      
    }
    
  }//end of drawarea
  
//==================================================================================
  //                                 Listeners                                  \\
//==================================================================================
  public class TimerListener implements ActionListener //reacts to timer
  {//start
    int steps = 0;
    
    TimerListener()
    {//start
      t.start();//starts timer
    }//end
    
    public void actionPerformed (ActionEvent e)
    {//start of void     
      update();//updates graphics
      
      //if (walk)
      //{
      steps();
      for (int x = 0; x < en.getSize(); x++)
      {
      if ( en.getID(x) == 'e')   
      {
        en.EnemyAI(player,x,floor,enemyBound);}
      }
      player.changeY(player.getVelY());
      player.changeX(player.getVelX());//}
      
      //else{}
    }//end of void
    
    public void steps()
    {
      steps++;
      if (steps == 1)
      { num = 1;}
      else if (steps == 2)
      {num = 0;}
      else if (steps == 3)
      { num = 2;}
      else if (steps == 4)
      {num = 0;}
      else {num = 0;
        steps = 0;} 
    }    
  }//end of class
//==========================================================================================================================
  public class playerListener implements KeyListener
  {   
    public playerListener()       {} 
    
    @Override
    public void keyTyped(KeyEvent e) { // respond to keys typed
    }
    
    @Override
    public void keyPressed(KeyEvent e) 
    {     
      //System.out.println("hi");
      walk = true;
      int x, y; 
      x = player.getX();
      y = player.getY();
      //System.out.println(code);
      if(e.getKeyCode() == KeyEvent.VK_UP )//&& floor.getFloorID(x,y-1) != 'w')
      {player.setVelY(-8);}
      else if(e.getKeyCode() == KeyEvent.VK_DOWN)// && floor.getFloorID(x,y+1) != 'w')
      {player.setVelY(8);}
      else if(e.getKeyCode() == KeyEvent.VK_LEFT)// && floor.getFloorID(x-1,y) != 'w')
      {player.setVelX(-8);}
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT)// && floor.getFloorID(x+1,y) != 'w')
      {player.setVelX(8);}
      else if(e.getKeyCode() == KeyEvent.VK_A)// && floor.getFloorID(x+1,y) != 'w')
      { 
        player.changeXY(2*32, 22*32);
        ///System.out.println("<"+lvl+">"); //testing
        
        if (lvl >= 0 && lvl <2)
        {
          lvl++;
        }
        else{
          lvl = 0;
        }
        floor.changeMap(lvl);
        if (lvl <=2)
          en.makeEntities(lvl);
      }
      else if(e.getKeyCode() == KeyEvent.VK_I){
        Instructions window2 = new Instructions (); //create a hangman game class object according to the second choice
        window2.setVisible (true);    
      }
      //if game has ended
      if (end) { //if the game has ended then the user can press r to start again from menu or q to quit the game
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
          setVisible(false);
          OJ_RPG_20183 game2 = new  OJ_RPG_20183 ();
          game2.setVisible (true);   
          
        }
        else if (e.getKeyCode() == KeyEvent.VK_Q)
          System.exit(0);
      }
        
    }
    @Override
    public void keyReleased(KeyEvent e) { // All interface methods must be defined
      
      if(e.getKeyCode() == KeyEvent.VK_UP)
      {player.setVelY(0);}
      else if(e.getKeyCode() == KeyEvent.VK_DOWN)
      {player.setVelY(0);}
      else if(e.getKeyCode() == KeyEvent.VK_LEFT)
      {player.setVelX(0);}
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      {player.setVelX(0);}  
      walk = false;}   
  }

}///end of class