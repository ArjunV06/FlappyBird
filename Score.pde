class Score
{
    int xPos;
    int yPos;
    int count;
    Score(int xPos_, int yPos_, int count_)
    {
        xPos=xPos_;
        yPos=yPos_;
        count=count_;
    }
    void display()
    {
        pushStyle();
        textFont(font);
        
        String display=(""+count+"");
        text(display,xPos,yPos);
        popStyle();
    }
    void up()
    {
        count++;
    }
    void reset()
    {
        count=0;
    }
}
