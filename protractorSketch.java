import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.awt.PSurfaceAWT; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class protractorSketch extends PApplet {

JFrameWindow myWindow ;
ProtractorController pc = new ProtractorController();
public void setup() {
  
  myWindow = new JFrameWindow(pc);
}
public void draw(){
  background(207.0f,207.0f);
  myWindow.draw();
}

public class JFrameWindow implements java.awt.event.KeyListener{
  PSurfaceAWT surf = (PSurfaceAWT) getSurface() ;
  PSurfaceAWT.SmoothCanvas turf = (PSurfaceAWT.SmoothCanvas) surf.getNative() ;
  javax.swing.JFrame jframe = (javax.swing.JFrame) turf.getFrame() ;
  ProtractorController pController ;
  JFrameWindow(ProtractorController controller) {
    jframe.removeNotify() ;
    jframe.setUndecorated(true) ;
    jframe.setOpacity(0.045f) ;
    this.pController = controller.getController(jframe) ;
    jframe.setGlassPane(this.pController.getView()) ;
    jframe.addKeyListener(this) ;
    jframe.setVisible(true) ;
  }
  public void draw() { this.pController.getView().update(); }
  public void keyTyped(java.awt.event.KeyEvent e) {  }
  public void keyPressed(java.awt.event.KeyEvent e) {  }
  public void keyReleased(java.awt.event.KeyEvent e) { this.pController.keyReleased(e) ; }
}
public class Point {
  float x,y;
  Point(float x, float y){this.x = x; this.y = y;}
  Point(Point p){this(p.x,p.y);}
  public String toString () {
    return " x: " + x + " y: " + y;
  }
}
static ProtractorController instance;
public class ProtractorController {
  ProtractorView pView;
  Protractor protractor;
  ProtractorController() { }
  public Protractor getModel() { return (instance != null)? instance.protractor : protractor ; }
  public ProtractorView getView() { return (instance != null)? instance.pView : pView ; }
  public ProtractorController getController(javax.swing.JFrame jframe){ 
    if ( null != instance ) return instance ;
    instance = new ProtractorController ( ) ;
    instance.protractor = new Protractor( ) ;
    instance.pView = new ProtractorView ( instance ) ;
    return instance;
  }
  public boolean isReady () {return pView != null && protractor != null;}
  public void keyReleased(java.awt.event.KeyEvent e) {
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
  public int size(){ return instance.protractor.size() ; }
  public void updateView () { instance.pView.update() ; }
  public void label(){ instance.pView.label( instance.protractor.toString() ); }
  public void add () { instance.protractor.add(getUpdatedMousePoint()); }
  public void set (int index) {
    if(index < size()) instance.protractor.set(index, getUpdatedMousePoint());
    else add();
  }
  public Point getUpdatedMousePoint() { return new Point ((float)mouseX,(float)mouseY) ; }
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
    float numerator = sq( distances[n] ) * -1.0f ;
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
  public void setController (ProtractorController pc) {this.pc = pc;}
  public void update() { draw() ; }
  public void label(String s) { vertexAngle = s; }
  public void drawModel(){
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
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "protractorSketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
