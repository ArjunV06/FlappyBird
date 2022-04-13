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
    void verticalMove(int ySpeed, int freq)
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
            if(int(random(100))%2==0)
            {
                this.up=true;
            }
            else
            {
                this.up=false;
            }
            if(int(random(100))<=freq)
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

    void display()
    {
        
        rect(xPos,tempY/2,wid,tempY);
        yPosTop = tempY/2;
        yPosTopHeight=tempY;
        rect(xPos,height-(remaining/2),wid,remaining);
        yPosBottom=height-(remaining/2);
        yPosBottomHeight=remaining;
    }

    boolean collisions(ArrayList<Hitbox> hitboxes)
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
    boolean checkScore(Bird bird)
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