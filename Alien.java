//importing java packages
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class Alien{
  double xVel, yVel, xAlien, yAlien;.//variables for alien location and speed
  double xShip, yShip;//variables for ship location
  Random rnd;
  
  //connstructor
  public Alien(int xShip, int yShip){
    rnd = new Random();
    this.xShip = xShip;
    this.yShip = yShip;
    xVel = rnd.nextDouble()*2-1;//assigns a random speed to alien between -1 and 1
    yVel = rnd.nextDouble()*2-1;
    xAlien = (rnd.nextDouble() * 768);//assigns a random location to alien
    yAlien = (rnd.nextDouble() * 768);
    while(Math.abs(xAlien-xShip)<200 && Math.abs(yAlien-yShip)<200){//assigns a new location to the alien if it is too close to the alien
      xAlien = rnd.nextDouble() * 768;
      yAlien = rnd.nextDouble() * 768;
    }
    
  }
  
  //draws the alien
  public void draw(Graphics g){
    g.setColor(Color.GREEN);//sets colour to green
    g.fillOval((int)xAlien, (int)yAlien, 30, 30);//draws the face
    g.setColor(Color.BLACK);//sets colour to black
    g.fillOval((int)xAlien +5, (int)yAlien+5, 5, 10);//draws the eyes
    g.fillOval((int)xAlien +20, (int)yAlien+5, 5, 10);
    g.fillOval((int)xAlien +13, (int)yAlien+22, 4, 3);//draws the mouth

  }
  
  //moves the alien
  public void move(){
    if(xAlien<=0 || xAlien>=770){//makes the alien  bounce off the east and west wall
     xVel = -xVel;
    }
    if(yAlien<=0 || yAlien>=770){//makes he alien bounce off the north and south wall
      yVel = -yVel;
    }
    xAlien += xVel; yAlien += yVel;//moves alien
  }
  
  //finds the distance between the shot and the alien
  public int getDistanceToBullet(double xBullet, double yBullet){
    double x = xAlien-xBullet;//finds distance in x between alien and shot
    double y = yAlien-yBullet;//finds distance in y between alien and shot
    return (int) Math.sqrt((x*x)+(y*y));//finds the final distance and returns it
  }
  
  //finds the distance between the ship and the alien
  public int getDistanceToShip(double xS, double yS){
    double x = xAlien-xS+40;//same idea as the getDistanceToBullet() method
    double y = yAlien-yS+40;
    return (int) Math.sqrt((x*x)+(y*y));
  }
  
  public boolean shipCollision(int shipX, int shipY){
    if(Math.pow(40, 2) > Math.pow(shipX-xAlien, 2) + Math.pow(shipY - yAlien, 2)){
      return true;
    }
    else return false;
  }
  
  //gets the x and y of the alien
  public int getX() {return (int)xAlien;}
  public int getY() {return (int)yAlien;}
}
