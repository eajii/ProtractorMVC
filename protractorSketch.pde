import java.awt.MouseInfo;
Protractor pro = new Protractor();
ProtractorView pv = new ProtractorView();
ProtractorController pc = new ProtractorController(pv,pro);
JFrameWindow myWindow = new JFrameWindow(pc);
void setup() {
  background(207.2,207.2);
  pv.setController(pc);
  myWindow.setUndecorated(true);
  myWindow.setOpacity(0.045);
  myWindow.setSize(1640,970);
  myWindow.setGlassPane(pv);
  myWindow.setVisible(true);
  pv.setVisible(true);
  pv.setOpaque(true);
}
void draw(){}
