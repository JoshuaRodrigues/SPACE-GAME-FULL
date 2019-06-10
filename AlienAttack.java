import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import java.lang.*;

public class AlienAttack extends JPanel implements KeyListener, Runnable
{
  private final int HEIGHT = 800; private final int WIDTH = 800;
  double xVel; double yVel; final double SPEED = 0.02;
  Thread thread;
  JLabel levelLabel;
  int direction;
  double x, y;
  final double FRICTION = 0.98;
  boolean upAccel, downAccel, leftAccel, rightAccel;
  ArrayList<Shot> shots;
  ArrayList<Alien> aliens;
  boolean shipActive;
  int level;
  Font font1;
  String levelAsString;
  int timesActive;
  boolean gameOver;
  
  public AlienAttack(){
    x=375;
    y=375;
    font1 = new Font("Times New Roman", Font.BOLD, 50);
    level = 1;
    gameOver = false;
    levelAsString = String.valueOf(level);
    direction = 1;

    xVel = 0; yVel = 0;
    shipActive = false;
    timesActive = 0;
    upAccel = false; downAccel = false; leftAccel = false; rightAccel = false;
    shots = new ArrayList<Shot>();
    aliens = new ArrayList<Alien>();
    setFocusable(true);
    addKeyListener(this);
    thread = new Thread(this);
    thread.start();
  } 
  
  public void paint(Graphics g){
    
    //DRAWS AN OVAL AND STUFF
    /*g.setColor(Color.BLUE);
     g.drawOval(100, 50, 300, 400);
     g.fillOval(175, 150, 30, 30);
     g.fillOval(295, 150, 30, 30);
     g.setColor(Color.RED);
     g.fillRect(230, 220, 40, 40);*/
    
    
    //DRAWS A STAR
    /*int[] xPoints = {50, 80, 90, 100, 150, 150, 200, 210, 220, 250};
     int[] yPoints = {150, 400, 200, 150, 50, 350, 150, 200, 400, 150};
     g.setColor(Color.BLACK);
     g.fillPolygon(xPoints, yPoints, 10);*/
    
    //System.out.println("("+(int)x+","+(int)y+")");
    //Sets the background (black)
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    
    //Draws the ship on the screen
    g.setColor(Color.BLUE);
    g.fillRect((int)x, (int)y, 50, 50);
    
    //Draws the turret on the screen
    g.setColor(Color.GRAY);
    switch(direction){
      case 1: g.fillRect((int)x+18, (int)y-20, 14, 10); g.fillRect((int) x+20,(int) y-20, 10, 20); break;
      case 2: g.fillRect((int)x+60, (int)y+18, 10, 14); g.fillRect((int) x+50,(int) y+20, 20, 10); break;
      case 3: g.fillRect((int)x+18, (int)y+60, 14, 10); g.fillRect((int) x+20,(int) y+50, 10, 20); break;
      case 4: g.fillRect((int)x-20, (int)y+18, 10, 14); g.fillRect((int) x-20,(int) y+20 , 20, 10); break;
    }
    
    //Draws the detail on the ship
    if(shipActive == true){
      g.setColor(Color.CYAN);
    }
    else {g.setColor(Color.RED);}
    switch(direction){
      case 1: g.fillOval((int)x+18, (int)y+5, 14, 20); break;
      case 2: g.fillOval((int)x+25, (int)y+18, 20, 14); break;
      case 3: g.fillOval((int)x+18, (int)y+25, 14, 20); break;
      case 4: g.fillOval((int)x+5, (int)y+18, 20, 14); break;
    }
    
    g.setColor(Color.GRAY);
    switch(direction){
      case 1: g.fillOval((int)x+14, (int)y+10, 3, 10); g.fillOval((int)x+33, (int)y+10, 3, 10); break;
      case 2: g.fillOval((int)x+30, (int)y+14, 10, 3); g.fillOval((int)x+30, (int)y+33, 10, 3); break;
      case 3: g.fillOval((int)x+14, (int)y+30, 3, 10); g.fillOval((int)x+33, (int)y+30, 3, 10); break;
      case 4: g.fillOval((int)x+10, (int)y+14, 10, 3); g.fillOval((int)x+10, (int)y+33, 10, 3); break;
    }
    
    //Draws the shots fired on the screen
    g.setColor(Color.YELLOW);
    for(int a =0;a<shots.size();a++){
      (shots.get(a)).draw(g);
    }
    
    //Draws each of the aliens on the screen
    
    for(int d =0;d<aliens.size();d++){
      (aliens.get(d)).draw(g);
    }
    
    //Sets the font of the text
    g.setFont(font1);
    
    //Prints "PRESS ENTER TO START" in the center of the screen
    g.setColor(Color.WHITE);
    font1 = new Font("Times New Roman", Font.BOLD, 20);
    if(timesActive == 0){
      g.drawString("PRESS ENTER TO START", 275, 300);
    }
    
    //Prints the level in the top right hand side of the screen
    g.drawString("LEVEL: "+levelAsString, 675, 20);
    
    //Prints "GAMEOVER" on the screen if it is game over
    if(gameOver){
      g.drawString("GAME OVER", 325, 375);
    }
  }
  
  public void keyPressed(KeyEvent e){
    
    //Starts the game if ENTER is pressed (can only be done once)
    if(e.getKeyCode() == KeyEvent.VK_ENTER && timesActive == 0){
      shipActive = true;
      timesActive++;
    }
    
    if(shipActive){  
      //Changes the ship's acceleration based on the arrow keys pressed.
      if(e.getKeyCode() == KeyEvent.VK_DOWN){
        downAccel = true;
      }
      else if(e.getKeyCode() == KeyEvent.VK_UP){
        upAccel = true;
      }
      else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        leftAccel = true;
      }
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        rightAccel = true;
      }
      
      //Turns the turret anti-clockwise if A is pressed
      else if(e.getKeyCode() == KeyEvent.VK_A){
        if(direction == 1){
          direction = 4;
        }
        else direction -= 1;
      }
      
      //Turns the turret clockwise if D is pressed
      else if(e.getKeyCode() == KeyEvent.VK_D){
        if(direction == 4){
          direction = 1;
        }
        else direction += 1;
      }
      
      //Adds shots to the shot array if SPACE is pressed
      else if(e.getKeyCode() == KeyEvent.VK_SPACE){
        if(shots.size()<7){
          shots.add(new Shot((int)x, (int)y, direction));
        }
      }
      /*else if(e.getKeyCode() == KeyEvent.VK_P){
       aliens.add(new Alien((int)x, (int) y));
       }*/
    }
  }
  
  public void run(){
    while(true){
      //Moves the ship according to the keys pressed
      moveShip();
      
      //Checks if the ship is going off the screen. Also makes sure that the turret does not go off-screen
      checkCollisionShipWall();
      
      //Moves the shots
      for(int b=0;b<shots.size();b++){
        (shots.get(b)).move();
      }
      
      //Moves the aliens
      for(int e=0;e<aliens.size();e++){
        (aliens.get(e)).move();
      }
      
      //Deletes the shot if they hit the wall
      checkCollisionShotWall();
      
      //Checks if aliens and shots collide. If they do, they are both deleted.
      checkCollisionAlienShot();
      
      //Checks if aliens and ship collide. If they do, the ship is deactivated
      if(shipActive){
        checkCollisionAlienShip();
      }
      
      //Converts the int 'level' into a String
      levelAsString = String.valueOf(level-1);      
      
      //If there are no aliens left, the game goes to the next level and spawns enemies
      if(aliens.size() == 0){
        levelUp();
      }
      
      //If the ship has been active once before but is now deactivated, it is game over.
      if(shipActive == false && timesActive == 1){
        gameOver = true;
      }
      
      repaint();
      try{
        thread.sleep(5);
      }
      catch(InterruptedException e){
      }
    }
  }
  
  public void keyReleased(KeyEvent e){
    if(e.getKeyCode() == KeyEvent.VK_UP){
      upAccel = false;
    }
    else if(e.getKeyCode() == KeyEvent.VK_DOWN){
      downAccel = false;
    }
    else if(e.getKeyCode() == KeyEvent.VK_LEFT){
      leftAccel = false;
    }
    else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      rightAccel = false;
    }
  }
  
  public void keyTyped(KeyEvent arg0){
    
  }
  
  public void moveShip(){
    if(!upAccel && !downAccel){
      yVel *= FRICTION;
    }
    if(!leftAccel && !rightAccel){
      xVel *= FRICTION;
    }
    if(Math.abs(yVel)<3){
      if(upAccel){
        yVel -= SPEED;
      }
      if(downAccel){
        yVel += SPEED;
      }
    }
    if(Math.abs(xVel)<3){
      if(leftAccel){
        xVel -= SPEED;
      }
      if(rightAccel){
        xVel += SPEED;
      }
    }
    if(shipActive){
      x += xVel;
      y += yVel;
    }
  }
  
  public void checkCollisionShipWall(){
    if(direction == 1 || direction ==3){
      if(direction == 1){
        if(y<=20){
          y=749;
        }
        if(y>=800){
          y=21;
        }
      }
      else{
        if(y<=0){
          y=729;
        }
        if(y>=730){
          y = 1;
        }
      }
      if(x<=0){
        x=749; 
      }
      if(x>=750){
        x = 1;
      }
    }
    else{
      if(direction == 2){
        if(x<=0){
          x=729; 
        }
        if(x>=730){
          x = 1;
        }
      }
      else{
        if(x<=20){
          x=749; 
        }
        if(x>=750){
          x = 21 ;
        }
      }
      if(y<=0){
        y=749;
      }
      if(y>=750){
        y = 1;
      }
    }
  }
  
  public void checkCollisionAlienShip(){
    for(int g =0;g<aliens.size();g++){
      if((aliens.get(g)).shipCollision((int)x, (int)y)){
        shipActive = false;
      }
    }
  }
  
  public void checkCollisionAlienShot(){
    for(int f=0;f<shots.size() && f>= 0;f++){
      for(int g=0;g<aliens.size() && g>=0;g++){
        if(shots.size()>0 && aliens.size()>0){
          if(((aliens.get(g)).getDistanceToBullet((shots.get(f)).getX(), (shots.get(f)).getY()))<=20){
            aliens.remove(g);
            g--;
            shots.remove(f);
            f--;
            //(aliens.get(g)).explosion(Graphics g);
          }
        }
      }
    }
  }
  
  public void checkCollisionShotWall(){
    for(int c=0;c<shots.size() && c>=0;c++){
      if((shots.get(c)).getX()<=10 || (shots.get(c)).getX()>=790 || (shots.get(c)).getY()<=10 || (shots.get(c)).getY()>=790){
        shots.remove(c);
        c--;
      }
    }
  }
  
  public void levelUp(){
    for(int h = 0;h<level; h++){
      aliens.add(new Alien((int)x, (int) y));
    }
    level++;
  }
  
  //public void
}