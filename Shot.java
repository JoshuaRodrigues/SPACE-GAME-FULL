import java.awt.Graphics;//importing graphics

public class Shot{
  double xShot, yShot;//variables for location of the shot
  int direction;//direction of turret
  final double SHOT_SPEED = 2;//speed of the shot
  
  //constructor
  public Shot(int xShip, int yShip, int direction){
    this.direction = direction;
    switch(direction){//draws the shot depending on where turret is facing
      case 1: xShot = xShip+20; yShot = yShip -30; break;
      case 2: xShot = xShip+70; yShot = yShip +20; break;
      case 3: xShot = xShip+20; yShot = yShip +70; break;
      case 4: xShot = xShip-30; yShot = yShip +20; break;
    }
  }
  
  //move the shot
  public void move(){
    switch(direction){//moves the shot depending on direction
      case 1: yShot -= SHOT_SPEED; break;
      case 2: xShot += SHOT_SPEED; break;
      case 3: yShot += SHOT_SPEED; break;
      case 4: xShot -= SHOT_SPEED; break;
    }
  }
  
  //draws the shot
  public void draw (Graphics g){
    g.fillOval((int) xShot, (int) yShot, 10, 10);
  }
  
  //returns the x and y of the shot 
  public int getX(){return (int)xShot;}
  public int getY(){return (int)yShot;}
}
