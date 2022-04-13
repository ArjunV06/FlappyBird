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
Pipe pipe;
PipeManager pipes;
Hitbox test;
Hitbox test2;

ArrayList<Hitbox> hitboxes;
public void setup()
{
    
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,100,50,0.6f,12,40);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    
    frameRate(60);
    
    pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,300,5,height/10,40,0,width);
    test = new Hitbox(100,100,50,50,1);
    test2 = new Hitbox(100,150,50,50,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    hitboxes.add(test2);
    //pipes
    

}

public void draw()

{   
    
    //println(frameRate);
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(40);
    
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
    

    
    //test.test();
    //rect(width/2,800,100,280);
    /*if(frameCount%30==0)
    {
        pipe.move();

    }
    
    pipe.display();
    pipe.verticalMove(1,0);
    if(pipe.collision(hitboxes))
    {
        fill(255,0,0);
        rect(100,100,100,100);
    }
    else
    {
        fill(0,255,0);
    }*/
    
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
   
    
   
    //pipe.move();
    //pipe.display();

    //rect(100,100,100,100);
    sb.display();
    
    bird.display();
    bird.move();
    if(superFlap)
    {
        bird.superFlap();
    }
    
    if(!bird.inBounds())
    {
        pipes.reset();
        bird.reset();
    }
    if(pipes.checkScore(bird))
    {
        sb.up();
    }
    test.display();
    test2.display();
    test.update(bird.xPos+25,bird.yPos);
    test2.update(bird.xPos-25,bird.yPos);
    boolean collide=pipes.collision(hitboxes);
    if(collide)
    {
        rect(width/2,height/2,100,100);
    }
    
}
boolean superFlap=false;
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
    if(key=='g')
    {
        superFlap=!superFlap;
        
    }
        
    
    if(key=='r')
    {
        sb.reset();
        pipes.reset();
    }
}



class Bird
{
    float xPos,yPos,gravity,flapStrength,yVel,originalYPos;
    int wid,hei,termVel;

    Bird(float xPos_, float yPos_, int wid_, int hei_, float gravity_, float flapStrength_, int termVel_)
    {
        xPos=xPos_;
        yPos=yPos_;
        originalYPos=yPos;
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
    public void reset()
    {
        yPos=originalYPos;
        yVel=0;
    }
    public boolean inBounds()
    {
        if(PApplet.parseInt(yPos)+(hei/2)>height)
        {
            return false;
        }
        return true;
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
    public void superFlap()
    {

        
        yVel--;
        yVel*=1.05f;
        
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
    int xRadius;
    int yRadius;

    Hitbox(int xPos_, int yPos_, int wid_, int hei_, int type_)
    {
        xPos=xPos_;
        yPos=yPos_;
        wid=wid_;
        hei=hei_;
        type=type_;
        //need to implement rect rect collisions
        if(type==1)
        {
            xRadius=wid/2;
            yRadius=hei/2;
            xRadius=yRadius;
        }
    }

    public void test()
    {
        
        //println(mouseX,mouseY);
    }
    public void display()
    {
        pushStyle();
        strokeWeight(4);
        noFill();
        switch(type)
        {
            case 0:
            {
                rect(xPos,yPos,wid,hei);
            }
            break;
            case 1:
            {
                ellipse(xPos,yPos,wid,hei);
            }
            break;
        }
        
    
        popStyle();
       
        //rect(xPos,yPos,wid,hei);
    }
    public void update(float xPos_,float yPos_)
    {
        xPos=PApplet.parseInt(xPos_);
        yPos=PApplet.parseInt(yPos_);
    }
}
class Pipe 
{
    int gap;
    float xSpeed;
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

    Pipe(int gap_, float xSpeed_, int heightMin_, int wid_, int xThreshold_, int xPos_)
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

    public boolean collisions(ArrayList<Hitbox> hitboxes)
    {
        for(int i=hitboxes.size()-1; i>=0; i--)
        {
            //println(hitboxes.size());
            Hitbox quick = hitboxes.get(i);
            //println(i);
            //println(quick.yPos+quick.yRadius, yPosBottom-(yPosBottomHeight/2));
            if(quick.type==1)
            {
                /*
                int circleDistanceX=abs(quick.xPos-this.xPos);
                int circleDistanceY1=abs(quick.yPos-this.yPosTop);
                int circleDistanceY2=abs(quick.yPos-this.yPosBottom);
                //println(yPosTop,yPosBottom,mouseX,mouseY);
                //println(circleDistanceX,this.xPos+quick.yRadius);
                
                //println(quick.yPos,yPosBottom+(yPosBottomHeight/2), (remaining-(yPosBottomHeight/2)) );
                //println(mouseY,quick.yPos,quick.yPos+quick.yRadius,quick.yPos-quick.yRadius, quick.yRadius);
                println(quick.yPos,quick.yPos+quick.yRadius,quick.yPos-quick.yRadius,yPosBottom-(yPosBottomHeight/2),yPosTop+(yPosTopHeight/2));
                //check if it INSIDE the gap w/ a little bit of leeway
                if(quick.yPos-quick.yRadius>yPosTop+(yPosTopHeight/2)-1 && quick.yPos+quick.yRadius < yPosBottom-(yPosBottomHeight/2)+1)
                {
                    return false;
                }
                else
                {
                    if(dist())
                }
                */

                float xClose=constrain(quick.xPos,this.xPos-(wid/2),this.xPos+(wid/2));
                float yCloseTop=constrain(quick.yPos,this.yPosTop-(yPosTopHeight/2),this.yPosTop+(this.yPosTopHeight/2));
                float yCloseBottom=constrain(quick.yPos,this.yPosBottom-(yPosBottomHeight/2),this.yPosBottom+(this.yPosBottomHeight/2));
                float distanceX=quick.xPos-xClose;
                float distanceYTop=quick.yPos-yCloseTop;
                float distanceYBottom=quick.yPos-yCloseBottom;
                
                
                if((distanceX*distanceX)+(distanceYTop*distanceYTop)<(quick.xRadius*quick.xRadius)+2||((distanceX*distanceX)+(distanceYBottom*distanceYBottom)<(quick.xRadius*quick.xRadius)+2)) //xrad and yrad are the same now cuz im too lazy
                {
                    return true;
                }
                
                


                
                

                
                
                
                
                
                
            }
           
        }
        return false;
    }
    /*
    boolean collision(ArrayList<Hitbox> hitboxes)
    {
        
        for(int i=hitboxes.size()-1; i>=0; i--)
        {
            Hitbox quick = hitboxes.get(i);
            if(quick.type==1)
            {
                int circleDistanceX=abs(quick.xPos-this.xPos);
                int circleDistanceY1=abs(quick.yPos-this.yPosTop);
                int circleDistanceY2=abs(quick.yPos-this.yPosBottom);
                //println(circleDistanceX,circleDistanceY1,circleDistanceY2,((this.wid)/2) + quick.yRadius);
                if(circleDistanceX > ((this.wid)/2) + quick.yRadius-20 && circleDistanceX > ((this.wid)/2) + quick.xRadius-20)
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY1 >((this.yPosTopHeight/2)+quick.yRadius)&&circleDistanceY1 >((this.yPosTopHeight/2)+quick.xRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY1 >((this.yPosBottomHeight/2)+quick.yRadius)&&circleDistanceY1 >((this.yPosBottomHeight/2)+quick.xRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY2 >((this.yPosTopHeight/2)+quick.yRadius)&&circleDistanceY1 >((this.yPosTopHeight/2)+quick.xRadius))
                {
                    println("f");
                    return false;
                    
                }
                if(circleDistanceY2 >((this.yPosBottomHeight/2)+quick.yRadius)&&circleDistanceY1 >((this.yPosBottomHeight/2)+quick.xRadius))
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
    */
    public boolean checkScore(Bird bird)
    {
        if(this.xPos-wid/2<((bird.xPos+bird.wid/2)+4) && this.xPos-wid/2>((bird.xPos+bird.wid/2)-4))
        {
            return true;
        }
        else
        {
            return false;
        }
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
            quick.xSpeed+=quick.xSpeed*0.00001f;
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
            Pipe queck = pipes.get(i);
            if(queck.collisions(hitboxes))
            {
                //println(quick.collisions(hitboxes));
                //println("hi");
                
                return true;
                
            }
            
            
            
            
        }
        return false;
        
    }

    public boolean checkScore(Bird bird)
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            {
                if(quick.checkScore(bird))
                {
                    return true;
                }
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
