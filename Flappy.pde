Score sb;
PFont font;
Bird bird;
void setup()
{
    
    
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2,8);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    size(1920,1080);
    frameRate(60);
    smooth(8);

}

void draw()
{

    background(0);
    //rect(100,100,100,100);
    sb.display();
    
    bird.display();
    bird.move();
}
void keyPressed() {
    if(key=='w')
    {
        sb.up();
        
    }
    if(key=='f')
    {
        bird.flap();
    }
        
    
    if(key=='r')
    {
        sb.reset();
    }
}


