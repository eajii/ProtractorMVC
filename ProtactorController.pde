public class ProtractorController {
  ProtractorView pView;
  Protractor protractor;
  Protractor getModel() { return protractor; }
  ProtractorController(ProtractorView pv, Protractor p){ pView = pv; protractor = p; }
  void keyReleased(java.awt.event.KeyEvent e) {
    char key = e.getKeyChar();
    if (key == '.') protractor.add(getUpdatedMousePoint());
    else if (key == 'a') protractor.set(protractor.size() - 1, getUpdatedMousePoint());
    else if (key == '0' || key == '1') protractor.set(0, getUpdatedMousePoint());
    else if (key == '2') protractor.set(1, getUpdatedMousePoint());
    else if (key == '3') protractor.set(2, getUpdatedMousePoint());
    if ( protractor.size() == 3) print(protractor);
    else if ( protractor.size() <3 ) println(protractor.size());
    else {
      Point p = protractor.get(protractor.size()-1);
      protractor = new Protractor();
      protractor.add(p);
    }
    pView.update();
  }
  Point getUpdatedMousePoint() { return new Point ((float)MouseInfo.getPointerInfo().getLocation().getX(),(float)MouseInfo.getPointerInfo().getLocation().getY()) ; }
}
