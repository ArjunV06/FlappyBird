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

    void move()
    {
        if(tempY==0)
        {   
            //xPos=width;
            tempY=int(random(heightMin,heightMax));
            //while(abs())
            println(tempY);
            remaining=height-(tempY)-gap;
            println(remaining);
        }
        else if(xPos+wid<xThreshold)
        {
            xPos=width;
            prevTempY=tempY;
            tempY=int(random(heightMin,heightMax));
            if(tempY>prevTempY)
                while(abs(prevTempY-tempY)>gap*1.5)
                {
                    tempY-=int(random(100));
                }
            else
            {
                while(abs(prevTempY-tempY)>gap*1.5)
                {
                    tempY+=int(random(100));
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

    void display()
    {
        
        rect(xPos,0,wid,tempY);
        
        rect(xPos,height-(remaining/2),wid,remaining);
    }
}

//method to move x, method to move y with visuals (like factory going up and down)