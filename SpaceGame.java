import javax.swing.JFrame;//importing JFrame

public class SpaceGame{
  JFrame frame;//the frame
  AlienAttack alienAttack;//the game instance
  
  //constructor
  public SpaceGame(){
    frame = new JFrame("Space Shooter");
    alienAttack = new AlienAttack();
    frame.setBounds(320, 25, 800, 800);//size of frame
    frame.setResizable(false);//frame does not change size
    frame.setVisible(true);//frame is visible
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//all frames exit on close
    frame.add(alienAttack);//adds game panel to frame
  }
  
  public static void main(String[] args){
    new SpaceGame();//makes a new instance of the game
  }
}
