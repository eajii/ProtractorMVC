static ProtractorController instance;
public class ProtractorController {
  ProtractorView pView;
  Protractor protractor;
  ProtractorController() { }
  Protractor getModel() { return (instance != null)? instance.protractor : protractor ; }
  ProtractorView getView() { return (instance != null)? instance.pView : pView ; }
  ProtractorController getController(javax.swing.JFrame jframe){ 
    if ( null != instance ) return instance ;
    instance = new ProtractorController ( ) ;
    instance.protractor = new Protractor( ) ;
    instance.pView = new ProtractorView ( instance ) ;
    return instance;
  }
  boolean isReady () {return pView != null && protractor != null;}
  void keyReleased(java.awt.event.KeyEvent e) {
    char key = e.getKeyChar();
    if (key == '.') add();
    else if (key == 'a') set(instance.protractor.size() - 1);
    else if (key == '0' || key == '1') set(0);
    else if (key == '2') set(1);
    else if (key == '3') set(2);
    if ( instance.protractor.size() == 3) label();
    else if ( instance.protractor.size() <3 ) println(instance.protractor.size());
    else {
      instance.protractor.remove(0);
      instance.protractor.remove(0);
    }
    updateView();
  }
  int size(){ return instance.protractor.size() ; }
  void updateView () { instance.pView.update() ; }
  void label(){ instance.pView.label( instance.protractor.toString() ); }
  void add () { instance.protractor.add(getUpdatedMousePoint()); }
  void set (int index) {
    if(index < size()) instance.protractor.set(index, getUpdatedMousePoint());
    else add();
  }
  Point getUpdatedMousePoint() { return new Point ((float)mouseX,(float)mouseY) ; }
}
