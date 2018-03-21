class ProtractorView extends javax.swing.JComponent  {
  ProtractorController pc;
  ProtractorView(ProtractorController controller){ pc = controller; setVisible(true);}
  String vertexAngle = "";
  protected void draw() {
    if ( !pc.isReady()) return;
    if (vertexAngle.length() > 0)
      text(vertexAngle, pc.getModel().get(1).x,pc.getModel().get(1).y);
    for(Point point : pc.getModel()) {
      fill(207,0,0) ;
      ellipse(point.x, point.y, 20, 20);
    }
    drawModel();
  }
  void setController (ProtractorController pc) {this.pc = pc;}
  void update() { draw() ; }
  void label(String s) { vertexAngle = s; }
  void drawModel(){
    fill(207,0,0);
    int z = pc.getModel().size();
    Point previousPoint = null;
    if (z>0) {
      for(Point p : pc.getModel()) {
        if (null != previousPoint )
          line(previousPoint.x,previousPoint.y,p.x,p.y);
        previousPoint = p;
      }
      line(pc.getModel().get(z-1).x,pc.getModel().get(z-1).y,mouseX,mouseY);
    }
    ellipse(mouseX,mouseY,20,20);
  }
}
