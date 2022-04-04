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
                    //tempY--;
                }
            else
            {
                while(abs(prevTempY-tempY)>gap*1.5)
                {
                    tempY+=int(random(100));
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
    void verticalMove(int ySpeed)
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
            if(int(random(100))%2==0)
            {
                this.up=true;
            }
            else
            {
                this.up=false;
            }
            if(int(random(100))%2==0)
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

    void display()
    {
        
        rect(xPos,0,wid,tempY);
        
        rect(xPos,height-(remaining),wid,remaining);
    }
}

//method to move x, method to move y with visuals (like factory going up and down)