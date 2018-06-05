import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; // Allow use of timer
import javax.imageio.*;
import java.io.*;
import java.util.*;



public class OJ_RPG_2018 extends JFrame 
{// start
 

//===============================<Update>===========================================
  public void update ()
  {
    
    repaint();
    
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
   OJ_RPG_2018 game = new  OJ_RPG_2018 ();
    game.setVisible (true);
  }//end of main
}///end of class
