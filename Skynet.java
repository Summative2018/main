import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; // Allow use of timer
import javax.imageio.*;
import java.io.*;
import java.util.*;



public class Skynet extends JFrame  
{// start
  Timer t = new Timer(80,null);// updates graphics and game
  Map floor = new Map(0);
  boolean walk = false;
  Player player = new Player (2*32,21*32,0,true,"Raw Vodka",1);
  Player enemy = new Player (15*32,9*32,0,true,"Monster",1);
  static int enemyBound[] = { -1, -1, -1, -1}; // up,down,left,right
  double disx, disy;
  int num = 0;
//==================================<Image Method>====================================
  public static Image loadImage (String name)  //Loads image from file
  { Image img = null;
    try{img = ImageIO.read (new File (name));}
    catch (IOException e){}
    return img;}
//===============================<Update>===========================================
  public void update ()
  {repaint();}
//=====================================<>===========================================
  class Map    
  {
    char[][] floor = new char[25][25];
    String[] map = new String[] {"map1", "map2", "map3"};
    int lvl;
    Image pic;
    
    public Map (int l)throws IOException 
    {lvl = l;
      this.loadMap();}
    
    public void loadMap() throws IOException 
    {
      File level = new File("res/text/maps/" + map[lvl] + ".txt");
      pic = loadImage("res/images/maps/" + map[lvl] + ".png");
      Scanner sc = new Scanner(level);
      String  line="";
      for (int y = 0; y < 25; y++) 
      { line = sc.nextLine();
        for (int x = 0; x < 25; x++)
        {floor[x][y] = line.charAt(x);}}}
    
    public Image getMap()
    {return pic;}
    
    public char getFloorID (int x, int y)
    {return floor[x][y];}
    
  }
  
  class Player {
    int identity;
    int xpos, ypos;
    int velx, vely;
    boolean status, setGender; //true for female, false for male;
    String setName;
    private boolean [] inventory = new boolean [3]; //stores 
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
        { if (floor.getFloorID((x+24)/32 ,(y+24)/32) == 'f')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
        {xpos += num;}
        direction = 2;} //right
      else 
      { if (floor.getFloorID((x)/32 ,(y+24)/32) == 'f')// && floor.getFloorID(x/32 ,(y-24)/32) != 'w')
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
    public void getItem(int num){ //change item entered (based on the number ID) to true so the player has picked up the object
      inventory [num] = true;
    }  
    public int getDirection(){ //draw class will have 4 numbers each assigned an arrow key
      return direction;  
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] EnemyAI (Player hero, Map floor, int[] enemyBound) //hero has id = 1, enemy has id = 2
    { //25 by 25 map tile is  32by32
      if (this.status) // if monster is alive
      {
        int dx = hero.getX()/32 - this.getX()/32; //distance between player and enemy in x direction
        //System.out.println ("dx = " + dx);
        int dy = hero.getY()/32 - this.getY()/32; //distance between player and enemy in y direction
        //System.out.println ("dy = " + dy);
        double ds = Math.sqrt( (dx*dx+0.0) + (dy*dy+0.0) ); //find distance between player position and enemy position
        //System.out.println ("ds = " + ds);
        boolean moveX = true; //if monster can see "player" (x direction)
        boolean moveY = true; //if monster can see "player" (y direction)
        int vel = 8; //velocity of monster
        
        if (enemyBound[0] == -1)
        { 
          enemyBound[3] = this.getX(); enemyBound[2] = this.getX(); enemyBound[0] = this.getY(); enemyBound[1] =this.getY();
          for (int x = this.getX()/32; x>0; x--)
          {
            if (floor.getFloorID( x,this.getY()/32) != 'w') //if there's not a wall
              enemyBound[2]=x+1;
          }
          for (int x = this.getX()/32; x < 25; x++)
          {
            if (floor.getFloorID( x,this.getY()/32) != 'w') //if there's not a wall
              enemyBound[3]=x-1;
          }
          for (int y = this.getY()/32; y>0; y--)
          {
            if (floor.getFloorID( this.getX()/32,y) != 'w') //if there's not a wall
              enemyBound[0]=y+1;
          }
          for (int y = this.getY()/32; y<25; y++)
          {
            if (floor.getFloorID( this.getX()/32,y) != 'w') //if there's not a wall
              enemyBound[1]=y-1;
          }
       }
        
        for (int x = (Math.min(hero.getX()/32,this.getX()/32)) ; x < (Math.max(hero.getX()/32,this.getX()/32)) ; x++) // check tiles between monster and player (x direction)
        {
          if (floor.getFloorID( x,this.getY()/32) == 'w') //if there's a wall
          {
            moveX = false; //monster cant see player (monster stops moving)
            
          }
        }
        for (int y = (Math.min(hero.getY()/32,this.getY()/32)) ; y < (Math.max(hero.getY()/32,this.getY()/32)) ; y++) // check tiles between monster and player (y direction)
        {
          if (floor.getFloorID( this.getX()/32,y) == 'w') //if there's a wall
          {
            moveY = false; //monster cant see player (monster stops moving) 
          }
        }
        
        if( this.getY()/32 > enemyBound[0] && this.getY()/32 < enemyBound[1] && this.getX()/32 > enemyBound[2] && this.getX()/32 < enemyBound[3])
        {
          if(moveX && moveY) //if monster can see you
          {
            //when dx is larger than dy
            if ( Math.abs(dx) >= Math.abs(dy)) //if x direction is farther than y - move in x direction
            { 
              if (floor.getFloorID( this.getX()/32+(Integer.signum(dx)),this.getY()/32) == 'f' && this.getX()/32-1 != enemyBound[2] &&  this.getX()/32+1 != enemyBound[3])//if spot to the right/left is empty
              {this.changeX(Integer.signum(dx)*vel);}//move 1 tile in that direction
              
              if (floor.getFloorID(this.getX()/32,this.getY()/32+(Integer.signum(dy))) == 'f' && this.getY()/32-1 != enemyBound[0] &&  this.getX()/32+1 != enemyBound[1])//if spot above/below is empty
              {this.changeY(Integer.signum(dy)*vel);}//move 1 tile in that direction
              
            }
            //when dy is larger than dx
            if ( Math.abs(dy) > Math.abs(dx)) //if y direction is farther than x - move in y direction
            {
              if (floor.getFloorID(this.getX()/32,this.getY()/32+(Integer.signum(dy))) == 'f' && this.getY()/32-1 != enemyBound[0] &&  this.getX()/32+1 != enemyBound[1])//if spot above/below is empty
              {this.changeY(Integer.signum(dy)*vel);}//move 1 tile in that direction
              
              if (floor.getFloorID(this.getX()/32+(Integer.signum(dx)),this.getY()/32) == 'f' && this.getX()/32-1 != enemyBound[2] &&  this.getX()/32+1 != enemyBound[3])//if spot to the right/left is empty
              {this.changeX(Integer.signum(dx)*vel);}//move 1 tile in that direction
              
            }
          }
          
        }
      }
      return enemyBound;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  }
//=================================<Panel constructor>============================== 
  public Skynet () throws IOException
  {// start of panel setup
    // Creating JPanel for the background and JPanel for interations (options)
    setLayout (new BorderLayout ());// Use BorderLayout for main panel
    JPanel background = new JPanel (); // Create a content panel to hold the backgournd images
    background.setLayout(new BorderLayout ());// sets layout to null so that set bounds can be used 
    // timer setup   
    
    // Colouring panels  
    background.setBackground(Color.BLACK);
    //timer setup   
    t.addActionListener(new TimerListener());
    background.addKeyListener(new playerListener());
    // Creating Drawing Area and Panel Setup
    DrawArea playfield = new DrawArea (1000, 1000);
    background.add(playfield); // Output area
    add("Center",background);
    setContentPane(background);
    background.setFocusable(true);
    pack();
    // Game window Setup  
    setTitle("Hangman Plus");
    background.setPreferredSize (new Dimension (1000,800));
    setSize(1080,825); //Sets the JFrame size
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
      Image[][][] tony = loadPlayer();
      int x,y,z;
      if (player.getGender() == true)
      {z=0;}
      else {z=1;}
      
      y = player.getDirection();
      //System.out.println("<"+y+">");
      g.drawImage(floor.getMap(),0,0,null);
//           for (int a = 0; a < 25; a++) 
//     {for (int b = 0; b < 25; b++)
//     {if (floor.getFloorID(b,a) =='f')
//       {g.setColor(Color.white);
//       g.fillRect(0+(32*b),0+(32*a),32,32);}}}
      
      if (!walk)
      {g.drawImage(tony[z][y][0], player.getX(), player.getY()-16,null);}
      else{g.drawImage(tony[z][y][num],player.getX(), player.getY()-16,null);}
      
      Image[][][] monster = loadPlayer();
      g.drawImage(monster[0][1][0], enemy.getX(), enemy.getY()-16,null);
    }
    
    public Image[][][] loadPlayer()
    {
      Image[][][] player = new Image[2][4][3];//z/y/x
      for (int z = 0; z < 1; z++)
      {for (int y = 0; y < 4; y++)
        {for (int x = 0; x < 3; x++)
        {player[z][y][x] = loadImage("res/images/player/"+z+"/"+y+x+".png");
        }}}
      return player;}
    
  }//end of drawarea
  
//==================================================================================
  //                                 Constructor                                \\
//==================================================================================
  
  
//==================================================================================
  //                                 Listeners                                  \\
//==================================================================================
  class TimerListener implements ActionListener //reacts to timer
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
      enemy.EnemyAI(player,floor,enemyBound);
      player.changeY(player.getVelY());
      player.changeX(player.getVelX());//}
      //else{}
    }//end of void
    
    
    public void steps()
    {
      
      //try{
      // Thread.sleep(5);}
      //catch(InterruptedException e){}
      
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
//        try{
//          Thread.sleep(5);}
//        catch(InterruptedException e){}
      
    }
    
    
  }//end of class
//==============================================================================
  class playerListener implements KeyListener
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
//==================================================================================    
  public static void main(String[] args) throws IOException
  {///start of main
    Skynet game = new  Skynet ();
    game.setVisible (true);
  }//end of main
}///end of class
