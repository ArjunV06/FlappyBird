Score sb;
PFont font;
Bird bird;
//Pipe pipe;
PipeManager pipes;
Hitbox test;

ArrayList<Hitbox> hitboxes;
void setup()
{
    
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,50,50,0.2,8,20);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    size(1920,1080,P2D);
    frameRate(60);
    smooth(8);
    //pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,150,5,height/10,40,0,width);
    test = new Hitbox(100,100,100,100,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    //pipes
    

}

void draw()

{   
    //println(frameRate);
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(0);

    //rect(960,540,100,100);
    //stroke(255);
    //strokeWeight(12);
    //line(0,height/2,960,540);
    ////float theta=radians(45);
    //float newX=(((960*cos(theta))+(540*sin(theta))));
    //float newY=(((540*cos(theta))-(960*sin(theta))));
    //println(960,540,newX,newY);
    //rect(newX,newY,100,100);
    //line(0,height/2,newX,newY);
    //rect(width/2,0,100,500);
    test.display();
    //test.test();
    //rect(width/2,800,100,280);
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
    if(pipes.collision(hitboxes))
    {
        rect(100,100,100,100);
    }
    else
    {
        ellipse(100,100,100,100);
    }
    //pipe.move();
    //pipe.display();

    //rect(100,100,100,100);
    sb.display();
    
    bird.display();
    bird.move();
    
}
void keyPressed() {
    if(key=='w')
    {
        sb.up();
        //println(sin(PI/4),sin((PI/4)+radians(360)));
        
    }
    if(key=='f')
    {
        bird.flap();
    }
        
    
    if(key=='r')
    {
        sb.reset();
        pipes.reset();
    }
}


