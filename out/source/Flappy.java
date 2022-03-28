import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Flappy extends PApplet {

Score sb;
PFont font;
Bird bird;
public void setup()
{
    
    
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2f,8);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    
    frameRate(60);
    

}

public void draw()
{

    background(0);
    //rect(100,100,100,100);
    sb.display();
    
    bird.display();
    bird.move();
}
public void keyPressed() {
    if(key=='w')
    {
        sb.up();
        
    }
    if(key=='f')
    {
        bird.flap();
    }
        
    
    if(key=='r')
    {
        sb.reset();
    }
}



class Bird
{
    float xPos,yPos,gravity,flapStrength,yVel;
    int wid,hei;

    Bird(float xPos_, float yPos_, int wid_, int hei_, float gravity_, float flapStrength_)
    {
        xPos=xPos_;
        yPos=yPos_;
        gravity=gravity_;
        flapStrength=flapStrength_;
        wid=wid_;
        hei=hei_;
        yVel=0;
    }

    public void display()
    {
        ellipse(xPos,yPos,wid,hei);
    }

    public void move()
    {
        yVel+=gravity;
        yPos+=yVel;
    }

    public void flap()
    {
        yVel-=flapStrength;
        yPos+=yVel;

    }
}

class Score
{
    int xPos;
    int yPos;
    int count;
    Score(int xPos_, int yPos_, int count_)
    {
        xPos=xPos_;
        yPos=yPos_;
        count=count_;
    }
    public void display()
    {
        pushStyle();
        textFont(font);
        String display=(""+count+"");
        text(display,xPos,yPos);
        popStyle();
    }
    public void up()
    {
        count++;
    }
    public void reset()
    {
        count=0;
    }
}
  public void settings() {  size(1920,1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Flappy" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
