import javax.swing.JFrame;

public class SpaceGame{
  JFrame frame;
  AlienAttack alienAttack;
  public SpaceGame(){
    frame = new JFrame("Space Shooter");
    alienAttack = new AlienAttack();
    frame.setBounds(320, 25, 800, 800);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(alienAttack);
  }
  
  public static void main(String[] args){
    new SpaceGame();
  }
}