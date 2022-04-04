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
//Pipe pipe;
PipeManager pipes;
public void setup()
{
    
    
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2f,8,20);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    
    frameRate(60);
    
    //pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,150,5,height/10,40,0,width);

    //pipes
    

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
    pipes.move();
    pipes.display();
    pipes.verticalMove(1);
    //pipe.move();
    //pipe.display();

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
    int startTime;
    boolean up;
    boolean go;

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
        startTime=0;
        up=true;
        go=true;
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
                    //tempY--;
                }
            else
            {
                while(abs(prevTempY-tempY)>gap*1.5f)
                {
                    tempY+=PApplet.parseInt(random(100));
                    //tempY++;
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
    //IN PROGRESS, DOES NOT WORK CORRECTLY AT ALL
    public void verticalMove(int ySpeed)
    {
        /*boolean up=true;
        if(frameCount%120==0)
        {
            if(random(1)>0.5)
            {
                println("switch");
                up=true;
            }
            else
            {
                println("switch");
                up=false;
            }
        }
      

        
        */

        
        if(startTime==0)
        {
            startTime=millis();
        }
        int currentTime=millis();
        if(currentTime-startTime>2000)
        {
            //println(random(100));
            println(go,up);
            if(PApplet.parseInt(random(100))%2==0)
            {
                this.up=true;
            }
            else
            {
                this.up=false;
            }
            if(PApplet.parseInt(random(100))%2==0)
            {
                this.go=true;
            }
            else
            {
                this.go=false;
            }
            
            
            startTime=millis();
        }
        
        if(!up && remaining>height/8 && go)
        {
            tempY+=ySpeed;
            //println("down");
        }
        else if(remaining<=height/8)
        {
            remaining=500;
        }
        else if(up && go)
        {
            //println("up");
            tempY-=ySpeed;
        }
        remaining=height-(tempY)-gap;
        //println(startTime,currentTime);
    }

    public void display()
    {
        
        rect(xPos,0,wid,tempY);
        
        rect(xPos,height-(remaining),wid,remaining);
    }
}

//method to move x, method to move y with visuals (like factory going up and down)
class PipeManager
{
    ArrayList<Pipe> pipes;
    int number;
    int space;
    int wid;
    //Pipe quick;

    PipeManager(int space_, int screenWid_, int gap_, int xSpeed_, int heightMin_, int wid_, int xThreshold_, int xPos_)
    {
        pipes = new ArrayList<Pipe>();
        
        space=space_;
        
        wid=screenWid_;
        number=wid/space;
        for(int i=0; i<number; i++)
        {
            pipes.add(new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_+(i*space)));
        }
        
        
        //quick = new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_);
        
    }

    public void move()
    {
        
        for(int i = pipes.size()-1; i >= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.move();
        }
    }

    public void display()
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.display();
        }
    }

    public void verticalMove(int ySpeed)
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.verticalMove(ySpeed);
        }
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
