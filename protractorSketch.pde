JFrameWindow myWindow ;
ProtractorController pc = new ProtractorController();
void setup() {
  fullScreen();
  myWindow = new JFrameWindow(pc);
}
void draw(){
  background(207.0,207.0);
  myWindow.draw();
}
