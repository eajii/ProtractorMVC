import javax.swing.*;
class ProtractorView extends JComponent  {
    ProtractorController pc;
    ProtractorView(){}
    protected void paintComponent(java.awt.Graphics g) {
      if ( null == pc) return;
        for(Point point : pc.getModel()) {
            g.setColor( new java.awt.Color(207,0,0) );
            g.fillOval((int)point.x - 10, (int)point.y - 10, 20, 20);
        }
    }
    void setController (ProtractorController pc) {this.pc = pc;}
    void update() { repaint(); }
}
