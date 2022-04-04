class PipeManager
{
    ArrayList<Pipe> pipes;
    int number;
    int space;
    int wid;
    //Pipe quick;

    PipeManager(int space_, int screenWid_, int gap_, int xSpeed_, int heightMin_, int wid_, int xThreshold_, int xPos_)
    {
        pipes = new ArrayList<Pipe>();
        
        space=space_;
        
        wid=screenWid_;
        number=wid/space;
        for(int i=0; i<number; i++)
        {
            pipes.add(new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_+(i*space)));
        }
        
        
        //quick = new Pipe(gap_,xSpeed_,heightMin_,wid_,xThreshold_,xPos_);
        
    }

    void move()
    {
        
        for(int i = pipes.size()-1; i >= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.move();
        }
    }

    void display()
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.display();
        }
    }

    void verticalMove(int ySpeed)
    {
        for(int i = pipes.size()-1; i>= 0; i--)
        {
            Pipe quick = pipes.get(i);
            quick.verticalMove(ySpeed);
        }
    }
}