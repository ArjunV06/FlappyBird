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
    
    void test()
    {
        
        //println(mouseX,mouseY);
    }
    void display()
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
    void update(float xPos_,float yPos_)
    {
        xPos = int(xPos_);
        yPos = int(yPos_);
    }
}