class Bird
{
    float xPos,yPos,gravity,flapStrength,yVel,originalYPos;
    int wid,hei,termVel,counter;
    PImage spriteSheet;
    PImage[] sprites; 

    Bird(float xPos_, float yPos_, int wid_, int hei_, float gravity_, float flapStrength_, int termVel_, PImage spriteSheet_, int xDim, int yDim, int sheetWid, int sheetHei)
    {
        counter=0;
        xPos=xPos_;
        yPos=yPos_;
        originalYPos=yPos;
        gravity=gravity_;
        flapStrength=flapStrength_;
        wid=wid_;
        hei=hei_;
        yVel=0;
        termVel=termVel_;
        spriteSheet=spriteSheet_;
        spriteSheet.resize(sheetWid,sheetHei);
        sprites = new PImage[xDim*yDim];
        int w=spriteSheet.width/xDim;
        int h=spriteSheet.height/yDim;
        for(int i=0;i<sprites.length;i++)
        {
            int x = i%xDim*w;
            int y = i%yDim*h;
            sprites[i]=spriteSheet.get(x,y,w,h);
            println(sprites[i].width,sprites[i].height);
        }
        
        
    }

    void display()
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
    void reset()
    {
        yPos=originalYPos;
        yVel=0;
        counter=0;
    }
    boolean inBounds()
    {
        if(int(yPos)+(hei/2)>height)
        {
            return false;
        }
        return true;
    }
    void move()
    {
        if(frameCount%6==0)
        {
            if(counter<4)
            {
                counter++;
            }
            else
            {
                counter=0;
            }
        }
        
        if(yVel<termVel)
        {
            yVel+=gravity;
            //yVel*=abs(yVel/gravity);
        }
        
        
        yPos+=yVel;
    }

    void flap()
    {
        yVel=0;
        yVel-=flapStrength;
        //yVel*=abs(flapStrength/yVel);
        yPos+=yVel;

    }
    void superFlap()
    {

        
        yVel--;
        yVel*=1.05;
        
        yPos+=yVel;
    }
}