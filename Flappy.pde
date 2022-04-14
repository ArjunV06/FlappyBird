Score sb;
PFont font;
Bird bird;
Pipe pipe;
PipeManager pipes;
Hitbox test;
Hitbox test2;
Hitbox test3;
int lightningTrigger1=0;
int lightningTrigger2=0;
int lightningTrigger3=0;
int lightningTrigger4=0;
int lightningTrigger5=0;
int lightningTrigger6=0;
BackgroundAnimation bubbleMaker3 = new BackgroundAnimation(18, "bubblemaker");
BackgroundAnimation bubbleMaker2 = new BackgroundAnimation(18,"bubblemaker");
BackgroundAnimation bubbleMaker = new BackgroundAnimation(18,"bubblemaker");
BackgroundAnimation distantLightning = new BackgroundAnimation(18, "distantlightning");
BackgroundAnimation largeLightning = new BackgroundAnimation(18,"largelightning");
BackgroundAnimation smallLightning = new BackgroundAnimation(18, "smalllightning");
PImage photo;



ArrayList<Hitbox> hitboxes;
void setup()
{
  
    PImage dronemove = loadImage("dronemove.png");
   
  
    bubbleMaker3.Initialize(16,40);
    bubbleMaker2.Initialize(16,40);
    bubbleMaker.Initialize(16,40);
    largeLightning.Initialize(320,240);
    smallLightning.Initialize(160,160);
    distantLightning.Initialize(100,100);
    rectMode(CENTER);
    font = createFont("font.ttf",128);
    bird = new Bird(width/4,height/2,91,40,0.6,12,40,dronemove,1,5,273,600);
    textFont(font);
    sb = new Score(width/2,height/2,0);
    size(1920,1080,P2D);
    frameRate(60);
    smooth(8);
    pipe = new Pipe(200,5,height/10,40,0,width);
    pipes =  new PipeManager(384,width,300,5,height/10,40,0,width);
    test = new Hitbox(100,100,50,50,1);
    test2 = new Hitbox(100,150,30,30,1);
    test3 = new Hitbox(100,150,20,20,1);
    hitboxes = new ArrayList<Hitbox>();
    hitboxes.add(test);
    hitboxes.add(test2);
    hitboxes,add(test3);
    photo = loadImage("background.png");
    photo.resize(width,height);
  
    

}

void draw()

{   
    
 
    backgroundDraw();
   
    
    pipes.move();
    pipes.display();
    pipes.verticalMove(1,15);
   
    
   
  
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
        sb.reset();
    }
    if(pipes.checkScore(bird))
    {
        sb.up();
    }
    
    
    test.update(bird.xPos-15,bird.yPos-18);
    test2.update(bird.xPos+10,bird.yPos-18);
    test3.update(bird.xPos-5,bird.yPos+8);
    boolean collide=pipes.collision(hitboxes);
    if(collide)
    {
        pipes.reset();
        bird.reset();
        sb.reset();
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
    if(key=='t')
    {
        test.display();
        test2.display();
        test3.display();
    }
}


void backgroundDraw()
{
  image(photo, 0, 0);
  
  if(largeLightning.Running()==false)
  {
    lightningTrigger1=(int)random(100);
    largeLightning.DisplayStatic(200,620);
  }  
  if(smallLightning.Running()==false)
  {
    lightningTrigger2=(int)random(100);
    smallLightning.DisplayStatic(1700,680);
  }

  if(distantLightning.Running()==false)
  {
    lightningTrigger3=(int)random(100);
    distantLightning.DisplayStatic(815,475);
  }
  if(bubbleMaker.Running()==false)
  {
    lightningTrigger4=(int)random(100);
    bubbleMaker.DisplayStatic(1100,800);
  }
  if(bubbleMaker2.Running()==false)
  {
    lightningTrigger5=(int)random(70);
    bubbleMaker2.DisplayStatic(1080,790);
  }
  if(bubbleMaker3.Running()==false)
  {
    lightningTrigger6=(int)random(90);
    bubbleMaker3.DisplayStatic(1120,790);
  }
    
  
  if(lightningTrigger1==1)
  {
    largeLightning.DisplayAnimation(200,620,6);
  }
  
  if(lightningTrigger2==2)
  {
    smallLightning.DisplayAnimation(1700,680,6);
  }

  if(lightningTrigger3==3)
  {
    distantLightning.DisplayAnimation(815,475,6);
  }
  
  if(lightningTrigger4==4)
  {
    bubbleMaker.DisplayAnimation(1100,800,4);
  }
  if(lightningTrigger5==5)
  {
    bubbleMaker2.DisplayAnimation(1080,790,4);
  }
  if(lightningTrigger6==6)
  {
    bubbleMaker3.DisplayAnimation(1120,790,4);
  }

}
