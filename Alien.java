import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class Alien{
  double xVel, yVel, xAlien, yAlien;
  double xShip, yShip;
  Random rnd;
  int explosionFontSize;
  String explosionText;
  
  public Alien(int xShip, int yShip){
    rnd = new Random();
    this.xShip = xShip;
    this.yShip = yShip;
    explosionFontSize = 5;
    explosionText = "DIJWAYO";
    xVel = rnd.nextDouble()*2-1;
    yVel = rnd.nextDouble()*2-1;
    xAlien = (rnd.nextDouble() * 768);
    yAlien = (rnd.nextDouble() * 768);
    while(Math.abs(xAlien-xShip)<200 && Math.abs(yAlien-yShip)<200){
      xAlien = rnd.nextDouble() * 768;
      yAlien = rnd.nextDouble() * 768;
    }
    
  }
  
  public void draw(Graphics g){
    g.setColor(Color.GREEN);
    g.fillOval((int)xAlien, (int)yAlien, 30, 30);
    g.setColor(Color.BLACK);
    g.fillOval((int)xAlien +5, (int)yAlien+5, 5, 10);
    g.fillOval((int)xAlien +20, (int)yAlien+5, 5, 10);
    g.fillOval((int)xAlien +13, (int)yAlien+22, 4, 3);

  }
  
  public void move(){
    if(xAlien<=0 || xAlien>=770){
     xVel = -xVel;
    }
    if(yAlien<=0 || yAlien>=770){
      yVel = -yVel;
    }
    xAlien += xVel; yAlien += yVel;
  }
  
  /*public boolean collidesWith(int xBullet, int yBullet){
    if((xAlien>=xBullet) && (xAlien<=xBullet+10) && (yAlien>=yBullet) && (yAlien<=yBullet+10)){
     return true; 
    }
    else return false;
  }*/
  
  public int getDistanceToBullet(double xBullet, double yBullet){
    double x = xAlien-xBullet;
    double y = yAlien-yBullet;
    return (int) Math.sqrt((x*x)+(y*y));
  }
  
  public int getDistanceToShip(double xS, double yS){
    double x = xAlien-xS+40;
    double y = yAlien-yS+40;
    return (int) Math.sqrt((x*x)+(y*y));
  }
  
  public boolean shipCollision(int shipX, int shipY){
    if(Math.pow(40, 2) > Math.pow(shipX-xAlien, 2) + Math.pow(shipY - yAlien, 2)){
      return true;
    }
    else return false;
  }
  
  public void explosion(Graphics g){
    g.drawString(explosionText, (int)xAlien, (int)yAlien);
  }
  
  public int getX() {return (int)xAlien;}
  public int getY() {return (int)yAlien;}
}