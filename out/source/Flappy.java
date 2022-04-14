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
Hitbox test3;
int lightningTrigger1 = 0;
int lightningTrigger2 = 0;
int lightningTrigger3 = 0;
int lightningTrigger4 = 0;
int lightningTrigger5 = 0;
int lightningTrigger6 = 0;
BackgroundAnimation bubbleMaker3 = new BackgroundAnimation(18, "bubblemaker");
BackgroundAnimation bubbleMaker2 = new BackgroundAnimation(18,"bubblemaker");
BackgroundAnimation bubbleMaker = new BackgroundAnimation(18,"bubblemaker");
BackgroundAnimation distantLightning = new BackgroundAnimation(18, "distantlightning");
BackgroundAnimation largeLightning = new BackgroundAnimation(18,"largelightning");
BackgroundAnimation smallLightning = new BackgroundAnimation(18, "smalllightning");
PImage photo;



ArrayList<Hitbox> hitboxes;
public void setup()
{
    
    PImage dronemove = loadImage("dronemove.png");
    
    
    bubbleMaker3.Initialize(16,40);
    bubbleMaker2.Initialize(16,40);
    bubbleMaker.Initialize(16,40);
    largeLightning.Initialize(320,240);
    smallLightning.Initialize(160,160);
    distantLightning.Initialize(100,100);
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width / 4,height / 2,91,60,0.6f,12,40,dronemove,1,5,273,900);
    textFont(font);
    sb = new Score(width / 2,height / 2,0);
    
    frameRate(60);
    
    pipe = new Pipe(200,5,height / 10,40,0,width);
    pipes =  new PipeManager(384,width,300,5,height / 10,40,0,width);
    test = new Hitbox(100,100,64,64,1);
    //test2 = new Hitbox(100,150,30,30,1);
    test3 = new Hitbox(100,150,20,20,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    //hitboxes.add(test2);
    hitboxes.add(test3);
    photo = loadImage("background.png");
    photo.resize(width,height);
    
    
    
}

public void draw()
    
{   
    
    
    backgroundDraw();
    
    
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
    
    
    
    
    sb.display();
    
    bird.display();
    bird.move();
    if (superFlap)
    {
        bird.superFlap();
    }
    
    if (!bird.inBounds())
    {
        pipes.reset();
        bird.reset();
        sb.reset();
    }
    if (pipes.checkScore(bird))
    {
        sb.up();
    }
    
    
    test.update(bird.xPos - 10,bird.yPos - 18);
    //test2.update(bird.xPos+10,bird.yPos-18);
    test3.update(bird.xPos - 5,bird.yPos + 18);
    boolean collide = pipes.collision(hitboxes);
    if (collide)
    {
        pipes.reset();
        bird.reset();
        sb.reset();
    }
    
    //test.display();
    //test2.display();
    //test3.display();
    
    
}
boolean superFlap = false;
public void keyPressed() {
    if (key ==  'w')
    {
        sb.up();
        //println(sin(PI/4),sin((PI/4)+radians(360)));
        
    }
    if (key ==  'f')
    {
        bird.flap();
    }
    if (key ==  'g')
    {
        superFlap =superFlap;
        
    }
    
    
    if (key ==  'r')
    {
        sb.reset();
        pipes.reset();
    }
    if (key ==  't')
    {
        test.display();
        test2.display();
        test3.display();
    }
}


public void backgroundDraw()
{
    image(photo, 0, 0);
    
    if (largeLightning.Running() ==  false)
        {
        lightningTrigger1 = (int)random(100);
        largeLightning.DisplayStatic(200,620);
    }  
        if (smallLightning.Running() ==  false)
        {
            lightningTrigger2 = (int)random(100);
            smallLightning.DisplayStatic(1700,680);
        }
            
            if (distantLightning.Running() ==  false)
            {
                lightningTrigger3 = (int)random(100);
                distantLightning.DisplayStatic(815,475);
            }
                if (bubbleMaker.Running() ==  false)
                {
                    lightningTrigger4 = (int)random(100);
                    bubbleMaker.DisplayStatic(1100,800);
                }
                    if (bubbleMaker2.Running() ==  false)
                    {
                        lightningTrigger5 = (int)random(70);
                        bubbleMaker2.DisplayStatic(1080,790);
                    }
                        if (bubbleMaker3.Running() ==  false)
                        {
                            lightningTrigger6 = (int)random(90);
                            bubbleMaker3.DisplayStatic(1120,790);
                        }
                            
                            
                            if (lightningTrigger1 ==1)
                            {
                                largeLightning.DisplayAnimation(200,620,6);
                            }
                                
                              if (lightningTrigger2 ==  2)
                                {
                                    smallLightning.DisplayAnimation(1700,680,6);
                                }
                                    
                                  if (lightningTrigger3 ==  3)
                                {
                                        distantLightning.DisplayAnimation(815,475,6);
                                    }
                                        
                                      if (lightningTrigger4 ==  4)
                                    {
                                        bubbleMaker.DisplayAnimation(1100,800,4);
                                    }
                                      if (lightningTrigger5 ==  5)
                                    {
                                        bubbleMaker2.DisplayAnimation(1080,790,4);
                                    }
                                      if (lightningTrigger6 ==  6)
                                    {
                                        bubbleMaker3.DisplayAnimation(1120,790,4);
                                    }
                                        
                                    }
                                        

class BackgroundAnimation
{
    int frameNumberAll;
    String nameAll;
    int counter;
    BackgroundAnimation(int frameNumber, String name)
    {
        frameNumberAll = frameNumber;
        nameAll = name;
        counter = 0;
    }
    
    PImage[] imageMatrix = new PImage[20];
    public void Initialize(int sizeX, int sizeY)
    {
        
        for (int i = 0; i < frameNumberAll; i++)
        {
            PImage temp;
            temp = loadImage(nameAll + "" + i + ".png");
            temp.resize(sizeX,sizeY);
            imageMatrix[i] = temp;
        }
    }
    public void DisplayAnimation(int posX, int posY,int fps)
    {
        //println(counter);
        if (counter ==  0) {image(imageMatrix[counter],posX,posY);}
        else{image(imageMatrix[counter - 1],posX,posY);}
        if ((float)frameCount % (float)fps ==  0)
        {
            if (counter < frameNumberAll)
            {
                counter++;
                //return true;
            }
            else 
            {
                counter = 0;
                //return false;
                
            }
        }
    }
    public void DisplayStatic(int posX, int posY)
    {
        image(imageMatrix[17],posX,posY);
        
    }
    public boolean Running()
    {
        if (counter!= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
class Bird
{
    float xPos,yPos,gravity,flapStrength,yVel,originalYPos;
    int wid,hei,termVel,counter;
    PImage spriteSheet;
    PImage[] sprites; 
    
    Bird(float xPos_, float yPos_, int wid_, int hei_, float gravity_, float flapStrength_, int termVel_, PImage spriteSheet_, int xDim, int yDim, int sheetWid, int sheetHei)
    {
        counter = 0;
        xPos = xPos_;
        yPos = yPos_;
        originalYPos = yPos;
        gravity = gravity_;
        flapStrength = flapStrength_;
        wid = wid_;
        hei = hei_;
        yVel = 0;
        termVel = termVel_;
        spriteSheet = spriteSheet_;
        spriteSheet.resize(sheetWid,sheetHei);
        sprites = new PImage[xDim * yDim];
        int w = spriteSheet.width / xDim;
        int h = spriteSheet.height / yDim;
        for (int i = 0;i < sprites.length;i++)
        {
            int x = i % xDim * w;
            int y = i % yDim * h;
            sprites[i] = spriteSheet.get(x,y,w,h);
            println(sprites[i].width,sprites[i].height);
        }
        
        
    }
    
    public void display()
    {
        pushStyle();
        imageMode(CENTER);
        
        /*if(frameCount%6==0 && frameCount<sprites.length)
        {
        counter++;
    }
        else if(frameCount>=sprites.length && frameCount%6==0)
        {
        counter=0;
    }*/
        
        image(sprites[counter],xPos,yPos);
        println(counter,sprites.length);
        popStyle();
    }
    public void reset()
    {
        yPos = originalYPos;
        yVel = 0;
        counter = 0;
    }
    public boolean inBounds()
    {
        if (PApplet.parseInt(yPos) + (hei / 2)>height)
        {
            return false;
        }
        return true;
    }
    public void move()
    {
        if (frameCount % 6 ==  0)
        {
            if (counter < 4)
            {
                counter++;
            }
            else
            {
                counter = 0;
            }
        }
        
        if (yVel < termVel)
        {
            yVel += gravity;
            //yVel*=abs(yVel/gravity);
        }
        
        
        yPos += yVel;
    }
    
    public void flap()
    {
        yVel = 0;
        yVel -= flapStrength;
        //yVel*=abs(flapStrength/yVel);
        yPos += yVel;
        
    }
    public void superFlap()
    {
        
        
        yVel--;
        yVel *=  1.05f;
        
        yPos += yVel;
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
        xPos = xPos_;
        yPos = yPos_;
        wid = wid_;
        hei = hei_;
        type = type_;
        //need to implement rect rect collisions
        if (type ==  1)
        {
            xRadius = wid / 2;
            yRadius = hei / 2;
            xRadius = yRadius;
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
        xPos = PApplet.parseInt(xPos_);
        yPos = PApplet.parseInt(yPos_);
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
        gap = gap_;
        xSpeed = xSpeed_;
        heightMin = heightMin_;
        heightMax = height - gap_ - heightMin;
        wid = wid_;
        xThreshold = xThreshold_;
        tempY = 0;
        xPos = xPos_;
        startTime = 0;
        up = true;
        go = true;
    }
    
    public void move()
    {
        if (tempY ==  0)
        {   
            //xPos=width;
            tempY = PApplet.parseInt(random(heightMin,heightMax));
            //while(abs())
            println(tempY);
            remaining = height - (tempY) - gap;
            println(remaining);
        }
        else if (xPos + wid < xThreshold)
        {
            xPos = width;
            prevTempY = tempY;
            tempY = PApplet.parseInt(random(heightMin,heightMax));
            if (tempY > prevTempY)
                while(abs(prevTempY - tempY)>gap * 1.5f)
                {
                tempY -= PApplet.parseInt(random(100));
                //tempY--;
            }
            else
            {
                while(abs(prevTempY - tempY)>gap * 1.5f)
                {
                    tempY += PApplet.parseInt(random(100));
                    //tempY++;
                }
            }
            
            remaining = height - (tempY) - gap;
            
            println(remaining);
        }
        else
        {
            xPos -= xSpeed;
        }
        
        
        
    }
    
    public void verticalMove(int ySpeed, int freq)
    {
        
        
        
        if (startTime ==  0)
        {
            startTime = millis();
        }
        int currentTime = millis();
        if (currentTime - startTime > 3000)
        {
            
            if (PApplet.parseInt(random(100)) % 2 ==  0)
            {
                this.up = true;
            }
            else
            {
                this.up = false;
            }
            if (PApplet.parseInt(random(100))<= freq)
            {
                this.go = true;
            }
            else
            {
                this.go = false;
            }
            
            
            startTime = millis();
        }
        
        if (!up && remaining > height / 8 && tempY > height / 8 && go)
        {
            tempY += ySpeed;
            //println("down");
        }
        else if (up && go && remaining > height / 8 && tempY > height / 8)
        {
            //println("up");
            tempY -= ySpeed;
        }
        
        
        remaining = height - (tempY) - gap;
        //println(startTime,currentTime);
    }
    
    public void display()
    {
        
        rect(xPos,tempY / 2,wid,tempY);
        yPosTop = tempY / 2;
        yPosTopHeight = tempY;
        rect(xPos,height - (remaining / 2),wid,remaining);
        yPosBottom = height - (remaining / 2);
        yPosBottomHeight = remaining;
    }
    
    public boolean collisions(ArrayList<Hitbox> hitboxes)
    {
        for (int i = hitboxes.size() - 1; i>= 0; i--)
        {
            
            Hitbox quick = hitboxes.get(i);
            
            if (quick.type ==  1)
            {
                
                
                float xClose = constrain(quick.xPos,this.xPos - (wid / 2),this.xPos + (wid / 2));
                float yCloseTop = constrain(quick.yPos,this.yPosTop - (yPosTopHeight / 2),this.yPosTop + (this.yPosTopHeight / 2));
                float yCloseBottom = constrain(quick.yPos,this.yPosBottom - (yPosBottomHeight / 2),this.yPosBottom + (this.yPosBottomHeight / 2));
                float distanceX = quick.xPos - xClose;
                float distanceYTop = quick.yPos - yCloseTop;
                float distanceYBottom = quick.yPos - yCloseBottom;
                
                
                if ((distanceX * distanceX) + (distanceYTop * distanceYTop)<(quick.xRadius * quick.xRadius) + 2 || ((distanceX * distanceX) + (distanceYBottom * distanceYBottom)<(quick.xRadius * quick.xRadius) + 2)) //xrad and yrad are the same now cuz im too lazy
                {
                    return true;
                }
                
                
                
                
                
                
                
                
                
                
                
                
                
            }
            
        }
        return false;
    }
    
    public boolean checkScore(Bird bird)
    {
        if (this.xPos - wid / 2 < ((bird.xPos + bird.wid / 2) + 4) && this.xPos - wid / 2>((bird.xPos + bird.wid / 2) - 4))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}

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
        
        space = space_;
        originalXpos = xPos_;
        wid = screenWid_;
        number = wid / space;
        for (int i = 0; i < number; i++)
        {
            pipes.add(new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_ + (i * space)));
        }
        
        
        //quick = new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_);
        
    }
    
    public void move()
    {
        
        for (int i = pipes.size() - 1; i >= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.move();
            quick.xSpeed += quick.xSpeed * 0.00001f;
        }
    }
    
    public void display()
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.display();
        }
    }
    
    public void verticalMove(int ySpeed, int freq)
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.verticalMove(ySpeed,freq);
        }
    }
    
    public void reset()
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.xPos = originalXpos + space * i;
            
        }
    }
    
    public boolean collision(ArrayList<Hitbox> hitboxes)
    {
        //boolean collide=false;
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe queck = pipes.get(i);
            if (queck.collisions(hitboxes))
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
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            {
                if (quick.checkScore(bird))
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
        xPos = xPos_;
        yPos = yPos_;
        count = count_;
    }
    public void display()
    {
        pushStyle();
        textFont(font);
        
        String display = ("" + count + "");
        text(display,xPos,yPos);
        popStyle();
    }
    public void up()
    {
        count++;
    }
    public void reset()
    {
        count = 0;
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
