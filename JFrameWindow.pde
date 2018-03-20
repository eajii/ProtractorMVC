public class JFrameWindow extends javax.swing.JFrame implements java.awt.event.KeyListener{
  ProtractorController parent ;
  JFrameWindow(ProtractorController parent){this.parent = parent ;addKeyListener(this);}
  public void keyTyped(java.awt.event.KeyEvent e) {  }
  public void keyPressed(java.awt.event.KeyEvent e) {  }
  public void keyReleased(java.awt.event.KeyEvent e) { parent.keyReleased(e); }
}
