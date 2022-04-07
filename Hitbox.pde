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
            majorAxisRadius=minorAxisRadius=wid;
        }
        else
        {
            majorAxisRadius=0;
            minorAxisRadius=0;
        }
    }

    void display()
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