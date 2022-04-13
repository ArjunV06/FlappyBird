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

    void display()
    {
        ellipse(xPos,yPos,wid,hei);
    }
    void reset()
    {
        yPos=originalYPos;
        yVel=0;
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