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
Hitbox test;

ArrayList<Hitbox> hitboxes;
public void setup()
{
    
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2f,8,20);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    
    frameRate(60);
    
    //pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,150,5,height/10,40,0,width);
    test = new Hitbox(100,100,100,100,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    //pipes
    

}

public void draw()

{   
    //println(frameRate);
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(0);

    //rect(960,540,100,100);
    //stroke(255);
    //strokeWeight(12);
    //line(0,height/2,960,540);
    ////float theta=radians(45);
    //float newX=(((960*cos(theta))+(540*sin(theta))));
    //float newY=(((540*cos(theta))-(960*sin(theta))));
    //println(960,540,newX,newY);
    //rect(newX,newY,100,100);
    //line(0,height/2,newX,newY);
    //rect(width/2,0,100,500);
    test.display();
    //test.test();
    //rect(width/2,800,100,280);
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
    if(pipes.collision(hitboxes))
    {
        rect(100,100,100,100);
    }
    else
    {
        ellipse(100,100,100,100);
    }
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
        //println(sin(PI/4),sin((PI/4)+radians(360)));
        
    }
    if(key=='f')
    {
        bird.flap();
    }
        
    
    if(key=='r')
    {
        sb.reset();
        pipes.reset();
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
class Hitbox
{
    int xPos;
    int yPos;
    int wid;
    int hei;
    int type;
    int majorAxisRadius;
    int minorAxisRadius;

    Hitbox(int xPos_, int yPos_, int wid_, int hei_, int type_)
    {
        xPos=xPos_;
        yPos=yPos_;
        wid=wid_;
        hei=hei_;
        type=type_;
        if(wid<hei && type == 1)
        {
            minorAxisRadius=wid/2;
            majorAxisRadius=hei/2;
        }
        else if(wid>hei && type == 1)
        {
            majorAxisRadius=wid/2;
            minorAxisRadius=hei/2;
        }
        else if(wid==hei && type == 1)
        {
            majorAxisRadius=wid;
            minorAxisRadius=wid;
        }
        else
        {
            majorAxisRadius=0;
            minorAxisRadius=0;
        }
    }

    public void test()
    {
        
        //println(mouseX,mouseY);
    }
    public void display()
    {
        switch(type)
        {
            case 0:
            {
                rect(xPos,yPos,wid,hei);
            }
            break;
            case 1:
            {
                xPos=mouseX;
                yPos=mouseY;
                ellipse(xPos,yPos,wid,hei);
            }
            break;
        }
        //rect(xPos,yPos,wid,hei);
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
    int yPosTop;
    int yPosBottom;
    int yPosTopHeight,yPosBottomHeight;
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
    public void verticalMove(int ySpeed, int freq)
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
        if(currentTime-startTime>3000)
        {
            //println(random(100));
            //println(go,up);
            if(PApplet.parseInt(random(100))%2==0)
            {
                this.up=true;
            }
            else
            {
                this.up=false;
            }
            if(PApplet.parseInt(random(100))<=freq)
            {
                this.go=true;
            }
            else
            {
                this.go=false;
            }
            
            
            startTime=millis();
        }
        
        if(!up && remaining>height/8 && tempY>height/8 && go)
        {
            tempY+=ySpeed;
            //println("down");
        }
        else if(up && go && remaining>height/8 && tempY>height/8)
        {
            //println("up");
            tempY-=ySpeed;
        }
        
        
        remaining=height-(tempY)-gap;
        //println(startTime,currentTime);
    }

    public void display()
    {
        
        rect(xPos,tempY/2,wid,tempY);
        yPosTop = tempY/2;
        yPosTopHeight=tempY;
        rect(xPos,height-(remaining/2),wid,remaining);
        yPosBottom=height-(remaining/2);
        yPosBottomHeight=remaining;
    }

    public boolean collision(ArrayList<Hitbox> hitboxes)
    {
        
        for(int i=hitboxes.size()-1; i>=0; i--)
        {
            Hitbox quick = hitboxes.get(i);
            if(quick.type==1)
            {
                int circleDistanceX=abs(quick.xPos-this.xPos);
                int circleDistanceY1=abs(quick.yPos-this.yPosTop);
                int circleDistanceY2=abs(quick.yPos-this.yPosBottom);
                //println(circleDistanceX,circleDistanceY1,circleDistanceY2,((this.wid)/2) + quick.minorAxisRadius);
                if(circleDistanceX > ((this.wid)/2) + quick.minorAxisRadius-20 && circleDistanceX > ((this.wid)/2) + quick.majorAxisRadius-20)
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY1 >((this.yPosTopHeight/2)+quick.minorAxisRadius)&&circleDistanceY1 >((this.yPosTopHeight/2)+quick.majorAxisRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY1 >((this.yPosBottomHeight/2)+quick.minorAxisRadius)&&circleDistanceY1 >((this.yPosBottomHeight/2)+quick.majorAxisRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY2 >((this.yPosTopHeight/2)+quick.minorAxisRadius)&&circleDistanceY1 >((this.yPosTopHeight/2)+quick.majorAxisRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY2 >((this.yPosBottomHeight/2)+quick.minorAxisRadius)&&circleDistanceY1 >((this.yPosBottomHeight/2)+quick.majorAxisRadius))
                {
                    println("f");
                    return false;
                    
                }
                
                if(circleDistanceY1<=(this.yPosTopHeight/2))
                {
                    println("t");
                    return true;
                    
                }
                if(circleDistanceY1<=(this.yPosBottomHeight/2))
                {
                    println("t");
                    return true;
                    
                }
                if(circleDistanceY2<=(this.yPosTopHeight/2))
                {
                    println("t");
                    return true;
                    
                }
                if(circleDistanceY2<=(this.yPosBottomHeight/2))
                {
                    println("t");
                    return true;
                    
                }
                if(circleDistanceX<=(this.wid/2))
                {
                    println("t");
                    return true;
                   
                }
                else
                {
                    return false;
                }

            }
         
            
        }
        return false;
    }

    
}

//method to move x, method to move y with visuals (like factory going up and down)
class PipeManager
{
    ArrayList<Pipe> pipes;
    int number;
    int space;
    int wid;
    int originalXpos;
    //Pipe quick;

    PipeManager(int space_, int screenWid_, int gap_, int xSpeed_, int heightMin_, int wid_, int xThreshold_, int xPos_)
    {
        pipes = new ArrayList<Pipe>();
        
        space=space_;
        originalXpos=xPos_;
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

    public void verticalMove(int ySpeed, int freq)
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.verticalMove(ySpeed,freq);
        }
    }

    public void reset()
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.xPos=originalXpos+space*i;
            
        }
    }

    public boolean collision(ArrayList<Hitbox> hitboxes)
    {
        //boolean collide=false;
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            if(quick.collision(hitboxes))
            {
                //println("hi");
                
                return true;
                
            }
            
            
            
            
        }
        return false;
        
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
  public void settings() {  size(1920,1080,P2D);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Flappy" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
