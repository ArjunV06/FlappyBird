Score sb;
PFont font;
Bird bird;
Pipe pipe;
void setup()
{
    
    
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2,8,20);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    size(1920,1080);
    frameRate(60);
    smooth(8);
    pipe = new Pipe(200,5,height/10,40,0,width);
    //test
    

}

void draw()

{   
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(0);
    //rect(width/2,0,100,500);
    
    //rect(width/2,800,100,280);
    pipe.move();
    pipe.display();

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


