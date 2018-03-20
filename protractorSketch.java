import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.MouseInfo; 
import javax.swing.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class protractorSketch extends PApplet {


Protractor pro = new Protractor();
ProtractorView pv = new ProtractorView();
ProtractorController pc = new ProtractorController(pv,pro);
JFrameWindow myWindow = new JFrameWindow(pc);
public void setup() {
  background(207.2f,207.2f);
  pv.setController(pc);
  myWindow.setUndecorated(true);
  myWindow.setOpacity(0.045f);
  myWindow.setSize(1640,970);
  myWindow.setGlassPane(pv);
  myWindow.setVisible(true);
  pv.setVisible(true);
  pv.setOpaque(true);
}
public void draw(){}
public class JFrameWindow extends javax.swing.JFrame implements java.awt.event.KeyListener{
  ProtractorController parent ;
  JFrameWindow(ProtractorController parent){this.parent = parent ;addKeyListener(this);}
  public void keyTyped(java.awt.event.KeyEvent e) {  }
  public void keyPressed(java.awt.event.KeyEvent e) {  }
  public void keyReleased(java.awt.event.KeyEvent e) { parent.keyReleased(e); }
}
public class Point {
  float x,y;
  Point(float x, float y){this.x = x; this.y = y;}
  Point(Point p){this(p.x,p.y);}
  public String toString () {
    return " x: " + x + " y: " + y;
  }
}
public class ProtractorController {
  ProtractorView pView;
  Protractor protractor;
  public Protractor getModel() { return protractor; }
  ProtractorController(ProtractorView pv, Protractor p){ pView = pv; protractor = p; }
  public void keyReleased(java.awt.event.KeyEvent e) {
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
  public Point getUpdatedMousePoint() { return new Point ((float)MouseInfo.getPointerInfo().getLocation().getX(),(float)MouseInfo.getPointerInfo().getLocation().getY()) ; }
}
public class Protractor extends ArrayList<Point>{
  public String toString() {
    String ret = "";
    ret = "2nd point/vertex Angle is "+calculateDegrees(2);
    return ret;
  }
  public float calculateDegrees(int n){
    float ret = 0 ;
    if (size() <3-1) return ret;
    float [] distances = distances();
    float numerator = distances[n] * distances[n] * -1.0f ;
    float denomenator = 2.0f;
    for (int i = 0 ; i<3 ; i++)
      if ( n != i) {
        numerator += sq( distances[i] ) ;
        denomenator *= distances[i] ;
      }
    if (0.0f == numerator) return 180.0f;
    ret = numerator / denomenator ;
    return RAD_TO_DEG * acos(ret) ;
  }
  public float [] distances() {
    return new float[] {distance(0,1), distance(1,2), distance(2,0)};
  }
  public float distance(int index1, int index2){
    return distance (get(index1), get(index2)) ;
  }
  public float distance (Point a, Point b) {
    return sqrt( sq(a.x-b.x) + sq(a.y-b.y) ) ; 
  }
}

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
    public void setController (ProtractorController pc) {this.pc = pc;}
    public void update() { repaint(); }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "protractorSketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
