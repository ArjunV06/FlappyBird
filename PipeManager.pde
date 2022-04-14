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
    
    void move()
    {
        
        for (int i = pipes.size() - 1; i >= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.move();
            quick.xSpeed += quick.xSpeed * 0.00001;
        }
    }
    
    void display()
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.display();
        }
    }
    
    void verticalMove(int ySpeed, int freq)
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.verticalMove(ySpeed,freq);
        }
    }
    
    void reset()
    {
        for (int i = pipes.size() - 1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.xPos = originalXpos + space * i;
            
        }
    }
    
    boolean collision(ArrayList<Hitbox> hitboxes)
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
    
    boolean checkScore(Bird bird)
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
