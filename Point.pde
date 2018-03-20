public class Point {
  float x,y;
  Point(float x, float y){this.x = x; this.y = y;}
  Point(Point p){this(p.x,p.y);}
  String toString () {
    return " x: " + x + " y: " + y;
  }
}
