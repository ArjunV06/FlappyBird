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
Pipe pipe,pipe1,pipe2,pipe3,pipe4;
public void setup()
{
    
    
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2f,8,20);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    
    frameRate(60);
    
    pipe = new Pipe(200,5,height/10,40,0,width);
    pipe1 = new Pipe(200,5,height/10,40,0,width-400);
    pipe2 = new Pipe(200,5,height/10,40,0,width-800);
    pipe3 = new Pipe(200,5,height/10,40,0,width-1000);

}

public void draw()

{   
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(0);
    //rect(width/2,0,100,500);
    
    //rect(width/2,800,100,280);
    pipe.move();
    pipe.display();
    pipe1.move();
    pipe1.display();
    pipe2.display();
    pipe2.move();
    pipe3.move();
    pipe3.display();
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
    int wid,hei,termVel;

    Bird(float xPos_, float yPos_, int wid_, int hei_, float gravity_, float flapStrength_, int termVel_)
    {
        xPos=xPos_;
        yPos=yPos_;
        gravity=gravity_;
        flapStrength=flapStrength_;
        wid=wid_;
        hei=hei_;
        yVel=0;
        termVel=termVel_;
    }

    public void display()
    {
        ellipse(xPos,yPos,wid,hei);
    }

    public void move()
    {
        if(yVel<termVel)
        {
            yVel+=gravity;
            //yVel*=abs(yVel/gravity);
        }
        
        yPos+=yVel;
    }

    public void flap()
    {
        yVel=0;
        yVel-=flapStrength;
        //yVel*=abs(flapStrength/yVel);
        yPos+=yVel;

    }
}
class Pipe 
{
    int gap;
    int xSpeed;
    int heightMin;
    int wid;
    int xThreshold;
    int heightMax;
    int prevTempY;
    int tempY;
    int remaining;
    int xPos;

    Pipe(int gap_, int xSpeed_, int heightMin_, int wid_, int xThreshold_, int xPos_)
    {
        gap=gap_;
        xSpeed=xSpeed_;
        heightMin=heightMin_;
        heightMax=height-gap_-heightMin;
        wid=wid_;
        xThreshold=xThreshold_;
        tempY=0;
        xPos=xPos_;
        
    }

    public void move()
    {
        if(tempY==0)
        {   
            //xPos=width;
            tempY=PApplet.parseInt(random(heightMin,heightMax));
            //while(abs())
            println(tempY);
            remaining=height-(tempY)-gap;
            println(remaining);
        }
        else if(xPos+wid<xThreshold)
        {
            xPos=width;
            prevTempY=tempY;
            tempY=PApplet.parseInt(random(heightMin,heightMax));
            if(tempY>prevTempY)
                while(abs(prevTempY-tempY)>gap*1.5f)
                {
                    tempY-=PApplet.parseInt(random(100));
                }
            else
            {
                while(abs(prevTempY-tempY)>gap*1.5f)
                {
                    tempY+=PApplet.parseInt(random(100));
                }
            }
            
            remaining=height-(tempY)-gap;
            
            println(remaining);
        }
        else
        {
            xPos-=xSpeed;
        }
        

        
    }

    public void display()
    {
        
        rect(xPos,0,wid,tempY);
        
        rect(xPos,height-(remaining/2),wid,remaining);
    }
}

//method to move x, method to move y with visuals (like factory going up and down)
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
