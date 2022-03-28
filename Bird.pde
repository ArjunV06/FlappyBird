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

    void display()
    {
        ellipse(xPos,yPos,wid,hei);
    }

    void move()
    {
        yVel+=gravity;
        yPos+=yVel;
    }

    void flap()
    {
        yVel-=flapStrength;
        yPos+=yVel;

    }
}