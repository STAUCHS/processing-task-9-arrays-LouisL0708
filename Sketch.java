import processing.core.PApplet;

public class Sketch extends PApplet {

  float[] snowX = new float[42];
  float[] snowY = new float[42];
  int snowDiameter = 20;
  float speed = 6;
  float playerX, playerY;
  int playerSize = 20;
  int lives = 24342;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);

    playerX = width / 2;
    playerY = height - 50;

    for (int i = 0; i < snowX.length; i++ ) {
      snowX[i] = random(width);
      snowY[i] = random(-height, 0); 
    }
  }

  public void draw() {
    background(0);
    snow();
    player();
    drawLives();
  }

  public void snow() {
    for (int i = 0; i < snowX.length; i++) {
      if (snowX[i] != -1 && snowY[i] != -1) {

        // Draw snowballs
        noStroke();
        fill(255); 
        circle(snowX[i], snowY[i], snowDiameter);

        snowY[i] += speed;
        if (snowY[i] > height){
          snowY[i] = random(-height, 0); 
          snowX[i] = random(width); // Randomize x position
        }

        // Check collision with player
        if (dist(snowX[i], snowY[i], playerX, playerY) < snowDiameter / 2 + playerSize / 2) {
          lives--; 
          snowY[i] = random(-height, 0);
          snowX[i] = random(width); // Randomize x position
        }
      }
    }

    // Check for game over
    if (lives <= 0) {
      background(255); 
      textSize(32);
      fill(0);
      textAlign(CENTER, CENTER);
      text("Game Over", width / 2, height / 2);
      noLoop();
    }
  }

  public void player() {

    // Draw player
    fill(0, 0, 255); 
    circle(playerX, playerY, playerSize);
  }

  public void drawLives() {

    // Draw live squares
    fill(255, 0, 100); 
    for (int i = 0; i < lives; i++) {
      square(width - 30 - i * 30, 10, 20); 
    }
  }

  public void mousePressed() {

    // Move the snowflake off-screen when circle is clicked
    for (int i = 0; i < snowX.length; i++) {
      if (dist(mouseX, mouseY, snowX[i], snowY[i]) < snowDiameter / 2) {
        snowX[i] = -1; 
        snowY[i] = -1; 
      }
    }
  }

  public void keyPressed() {

    // Controlls
    if (key == 'a') {
      playerX -= 10;
    } else if (key == 'd') {
      playerX += 10;
    } else if (key == 'w') {
      playerY -= 10;
    } else if (key == 's') {
      playerY += 10;
    } else if (key == '3') {
      speed = 15;
    } else if (key == '1') {
      speed = 2;
    } else if (key == '2') {
      speed = 6;
    }
  }
}
