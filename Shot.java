import java.awt.Graphics;

public class Shot{
  double xShot, yShot;
  int direction;
  final double SHOT_SPEED = 2;
  
  public Shot(int xShip, int yShip, int direction){
    this.direction = direction;
    switch(direction){
      case 1: xShot = xShip+20; yShot = yShip -30; break;
      case 2: xShot = xShip+70; yShot = yShip +20; break;
      case 3: xShot = xShip+20; yShot = yShip +70; break;
      case 4: xShot = xShip-30; yShot = yShip +20; break;
    }
  }
  
  public void move(){
    switch(direction){
      case 1: yShot -= SHOT_SPEED; break;
      case 2: xShot += SHOT_SPEED; break;
      case 3: yShot += SHOT_SPEED; break;
      case 4: xShot -= SHOT_SPEED; break;
    }
  }
  
  public void draw (Graphics g){
    g.fillOval((int) xShot, (int) yShot, 10, 10);
  }
  
  public int getX(){return (int)xShot;}
  
  public int getY(){return (int)yShot;}
}