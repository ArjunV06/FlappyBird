Score sb;
PFont font;
Bird bird;
Pipe pipe;
PipeManager pipes;
Hitbox test;
Hitbox test2;

ArrayList<Hitbox> hitboxes;
void setup()
{
    
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,100,50,0.6,12,40);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    size(1920,1080,P2D);
    frameRate(60);
    smooth(8);
    pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,300,5,height/10,40,0,width);
    test = new Hitbox(100,100,50,50,1);
    test2 = new Hitbox(100,150,50,50,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    hitboxes.add(test2);
    //pipes
    

}

void draw()

{   
    
    //println(frameRate);
    //ellipse(100,z)
    //fill(255);
    //rect(100,100,100,100);
    //rect(width-40,60,40,123);
    
    
    
    background(40);
    
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
    

    
    //test.test();
    //rect(width/2,800,100,280);
    /*if(frameCount%30==0)
    {
        pipe.move();

    }
    
    pipe.display();
    pipe.verticalMove(1,0);
    if(pipe.collision(hitboxes))
    {
        fill(255,0,0);
        rect(100,100,100,100);
    }
    else
    {
        fill(0,255,0);
    }*/
    
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
   
    
   
    //pipe.move();
    //pipe.display();

    //rect(100,100,100,100);
    sb.display();
    
    bird.display();
    bird.move();
    if(superFlap)
    {
        bird.superFlap();
    }
    
    if(!bird.inBounds())
    {
        pipes.reset();
        bird.reset();
    }
    if(pipes.checkScore(bird))
    {
        sb.up();
    }
    test.display();
    test2.display();
    test.update(bird.xPos+25,bird.yPos);
    test2.update(bird.xPos-25,bird.yPos);
    boolean collide=pipes.collision(hitboxes);
    if(collide)
    {
        rect(width/2,height/2,100,100);
    }
    
}
boolean superFlap=false;
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
    if(key=='g')
    {
        superFlap=!superFlap;
        
    }
        
    
    if(key=='r')
    {
        sb.reset();
        pipes.reset();
    }
}


