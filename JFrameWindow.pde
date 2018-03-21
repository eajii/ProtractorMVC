import processing.awt.PSurfaceAWT ;
public class JFrameWindow implements java.awt.event.KeyListener{
  PSurfaceAWT surf = (PSurfaceAWT) getSurface() ;
  PSurfaceAWT.SmoothCanvas turf = (PSurfaceAWT.SmoothCanvas) surf.getNative() ;
  javax.swing.JFrame jframe = (javax.swing.JFrame) turf.getFrame() ;
  ProtractorController pController ;
  JFrameWindow(ProtractorController controller) {
    jframe.removeNotify() ;
    jframe.setUndecorated(true) ;
    jframe.setOpacity(0.045) ;
    this.pController = controller.getController(jframe) ;
    jframe.setGlassPane(this.pController.getView()) ;
    jframe.addKeyListener(this) ;
    jframe.setVisible(true) ;
  }
  void draw() { this.pController.getView().update(); }
  public void keyTyped(java.awt.event.KeyEvent e) {  }
  public void keyPressed(java.awt.event.KeyEvent e) {  }
  public void keyReleased(java.awt.event.KeyEvent e) { this.pController.keyReleased(e) ; }
}
