public class Protractor extends ArrayList<Point>{
  String toString() {
    String ret = "";
    ret = "2nd point/vertex Angle is "+calculateDegrees(2);
    return ret;
  }
  float calculateDegrees(int n){
    float ret = 0 ;
    if (size() <3-1) return ret;
    float [] distances = distances();
    float numerator = distances[n] * distances[n] * -1.0 ;
    float denomenator = 2.0;
    for (int i = 0 ; i<3 ; i++)
      if ( n != i) {
        numerator += sq( distances[i] ) ;
        denomenator *= distances[i] ;
      }
    if (0.0 == numerator) return 180.0;
    ret = numerator / denomenator ;
    return RAD_TO_DEG * acos(ret) ;
  }
  float [] distances() {
    return new float[] {distance(0,1), distance(1,2), distance(2,0)};
  }
  float distance(int index1, int index2){
    return distance (get(index1), get(index2)) ;
  }
  float distance (Point a, Point b) {
    return sqrt( sq(a.x-b.x) + sq(a.y-b.y) ) ; 
  }
}
