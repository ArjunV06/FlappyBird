
class BackgroundAnimation
{
    int frameNumberAll;
    String nameAll;
    int counter;
    BackgroundAnimation(int frameNumber, String name)
    {
        frameNumberAll=frameNumber;
        nameAll=name;
        counter=0;
    }

    PImage[] imageMatrix = new PImage[20];
    void Initialize(int sizeX, int sizeY)
    {
        
        for(int i=0; i<frameNumberAll; i++)
        {
            PImage temp;
            temp=loadImage(nameAll+""+i+".png");
            temp.resize(sizeX,sizeY);
            imageMatrix[i]=temp;
        }
    }
    void DisplayAnimation(int posX, int posY,int fps)
    {
        //println(counter);
        if(counter==0){image(imageMatrix[counter],posX,posY);}
        else{image(imageMatrix[counter-1],posX,posY);}
        if((float)frameCount%(float)fps==0)
        {
            if(counter<frameNumberAll)
            {
                counter++;
                //return true;
            }
            else 
            {
                counter=0;
                //return false;

            }
        }
    }
    void DisplayStatic(int posX, int posY)
    {
        image(imageMatrix[17],posX,posY);
        
    }
    boolean Running()
    {
        if(counter!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
