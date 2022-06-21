import processing.core.PApplet;

public class Sketch extends PApplet {
	
  // for snowflakes
  float[] snowY = new float [40];
  float [] snowX = new float [40];

  // for circle player
  float circleX = 450;
  float circleY = 700;

  // for player lives
  int rectX;
  int rectY;
  int lives = 3;

  // for controlling circle player
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean slowDown = false;
  boolean speedUp = false;

  // to hide the snowflakes
  boolean[] ballHideStatus = new boolean[40];
  

  public void settings() {
	// put your size call here
    size(900, 700);
  }


  public void setup() {
    background(168,209,223);

    // generating random snowflake locations
    for (int i = 0; i < snowY.length; i++) {
      snowY[i] = random(height-100);
    }
  
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
    }

  }

  public void draw() {
  
  background(168,209,223);

  // drawing snowflakes to the screen
  for (int i = 0; i < snowY.length; i++) {
    if(ballHideStatus[i] == false) {
      noStroke();
      fill(255);
      ellipse(snowX[i], snowY[i], 30, 30);

      snowY[i]+=2;
    }

    // collision detection
    if (dist(circleX, circleY, snowX[i], snowY[i]) < 33) {
      lives--;
      snowY[i] = 0;
    }

    // controlling speed of snowflakes
    if (speedUp) {
      snowY[i]+=4;
      }
    if (slowDown) {
      snowY[i]--;
    }
    snowY[i]++;

    if (snowX[i] > width) {
      snowX[i] = 0;
    }
    if (snowY[i] > height) {
      snowY[i] = 0;
    }
  }

    // player circle
    fill(28, 102, 130);
    ellipse(circleX, circleY, 40, 40);

    // player circle movements
    if (upPressed) {
      circleY-=3;
    }
    if (downPressed) {
      circleY+=3;
    }
    if (leftPressed) {
      circleX-=3;
    }
    if (rightPressed) {
      circleX+=3;
    }

    // lives lost
    if (lives == 3) {
      fill(170, 30, 60);
      rect(650, 20, 50, 50);
      rect(730, 20, 50, 50);
      rect(810,20,50,50);
    }
    else if (lives == 2) {
      fill(170, 30, 60);
      rect(730, 20, 50, 50);
      rect(810,20,50,50);
    }
    else if (lives == 1) {
      fill(170, 30, 60);
      rect(810,20,50,50);
    }
    else if (lives == 0) {
      background(255);
      fill(0);
      textSize(60);
      text("GAME OVER", 200, height/2);
      noLoop();
      
    }

  }

  /**
   * This method controls the keyboard inputs when the user presses the key.
   */

  public void keyPressed() {
    if (key == 'w') {
      upPressed = true;
    }
    else if (key == 's') {
      downPressed = true;
    }
    else if (key == 'a') {
      leftPressed = true;
    }
    else if (key == 'd') {
      rightPressed = true;
    }
    else if (keyCode == DOWN){
      speedUp = true;
    }
    else if (keyCode == UP) {
      slowDown = true;
    }
  }

  /**
   * This method controls the keyboard inputs when the user releases the key.
   */
  
  public void keyReleased() {
    if (key == 'w') {
      upPressed = false;
    }
    else if (key == 's') {
      downPressed = false;
    }
    else if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
    else if (keyCode == DOWN){
      speedUp = false;
    }
    else if (keyCode == UP) {
      slowDown = false;
    }
  }
  
  /**
   * This method controls the mouse input when the user clicks the mouse.
   */

  public void mouseClicked() {
    for (int i = 0; i < snowY.length; i++) {
      if (dist(mouseX, mouseY, snowX[i], snowY[i]) < 30) {
        ballHideStatus[i]= true;
      }
    
  }
}
}