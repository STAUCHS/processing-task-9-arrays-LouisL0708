import processing.core.PApplet;

/**
* A that makes a dodging game.
* @author: L.Lam
*
*/

public class Sketch extends PApplet {

  float[] fltSnowX = new float[42];
  float[] fltSnowY = new float[42];
  int[] intSnowDiameter = new int[42];
  float fltSpeed = 6;
  float fltPlayerX, fltPlayerY;
  int intPlayerSize = 20;
  int intLives = 3;

  public void settings() {
    size(400, 400);
  }

    /**
  * background and random snow genrator
  * 
  */

  
  public void setup() {
    background(0);

    fltPlayerX = width / 2;
    fltPlayerY = height - 50;

    for (int i = 0; i < fltSnowX.length; i++ ) {
      fltSnowX[i] = random(width);
      fltSnowY[i] = random(-height, 0); 
      intSnowDiameter[i] = 20;
    }
  }

   /**
  * Draws snowballs, player circle and intLives squares
  * 
  */
  
  public void draw() {
    background(0);
    snow();
    player();
    drawintLives();
  }

   /**
  * generates snowballs, fall Speed
  * 
  */

  public void snow() {
    for (int i = 0; i < fltSnowX.length; i++) {
      if (fltSnowX[i] != -1 && fltSnowY[i] != -1) {

        // Draw snowballs
        noStroke();
        fill(255); 
        circle(fltSnowX[i], fltSnowY[i], intSnowDiameter[i]);

        fltSnowY[i] += fltSpeed;
        if (fltSnowY[i] > height){
          fltSnowY[i] = random(-height, 0); 
          fltSnowX[i] = random(width); // Randomize x position
          intSnowDiameter[i] = 20;
        }

        // Check collision with player
        if (dist(fltSnowX[i], fltSnowY[i], fltPlayerX, fltPlayerY) < intSnowDiameter[i] / 2 + intPlayerSize / 2) {
          intSnowDiameter[i] = 0;
          intLives--; 
          fltSnowY[i] = random(-height, 0);
          fltSnowX[i] = random(width); // Randomize x position
         
        }
      }
    }

    // Check for game over
    if (intLives <= 0) {
      background(255); 
      textSize(32);
      fill(0);
      textAlign(CENTER, CENTER);
      text("Game Over", width / 2, height / 2);
      noLoop();
    }
  }

    /**
  * Draws player circle 
  * 
  */

  public void player() {

    // Draw player
    fill(0, 0, 255); 
    circle(fltPlayerX, fltPlayerY, intPlayerSize);
  }

     /**
  * Draws and generate intLives square 
  * 
  */

  public void drawintLives() {

    // Draw live squares
    fill(255, 0, 100); 
    for (int i = 0; i < intLives; i++) {
      square(width - 30 - i * 30, 10, 20); 
    }
  }

    /**
  * make it so that circle dissapears after clicked 
  * 
  */

  public void mousePressed() {

    // Move the snowflake off-screen when circle is clicked
    for (int i = 0; i < fltSnowX.length; i++) {
      if (dist(mouseX, mouseY, fltSnowX[i], fltSnowY[i]) < intSnowDiameter[i] / 2) {
        intSnowDiameter[i] =0; 
      }
    }
  }


    /**
  * Control Keys 
  * 
  */
  
  public void keyPressed() {

    // Controlls
    if (key == 'a') {
      fltPlayerX -= 10;
    } else if (key == 'd') {
      fltPlayerX += 10;
    } else if (key == 'w') {
      fltPlayerY -= 10;
    } else if (key == 's') {
      fltPlayerY += 10;
    } else if (key == '3') {
      fltSpeed = 15;
    } else if (key == '1') {
      fltSpeed = 2;
    } else if (key == '2') {
      fltSpeed = 6;
    }
  }
}
