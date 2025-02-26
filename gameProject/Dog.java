import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Actor
{
    //variables for jumping and falling
    private int jumpHeight=15;
    private double fallSpeed=0.5;
    private boolean inTheAir=false;
    private double y=0;
    private double x=0;
    //keeps track if it is turned to the right
    private boolean turnR=true;
    //holds onto the images
    private GreenfootImage[] deadR= new GreenfootImage[10];
    private GreenfootImage[] deadL= new GreenfootImage[10];

    private GreenfootImage[] idleR= new GreenfootImage[10];
    private GreenfootImage[] idleL= new GreenfootImage[10];

    private GreenfootImage[] runR= new GreenfootImage[8];
    private GreenfootImage[] runL= new GreenfootImage[8];

    //variables for the images  
    private int runIndex=0;
    private int deadIndex=0;
    private int idleIndex=0;

    private int runCounter=0;
    private int deadCounter=0;
    private int idleCounter=0;
    //variables for the ground
    private int groundHeight=getImage().getHeight()/2;

    //variables for world
    private World field;
    int worldHeight;
    int worldWidth;
    public boolean gotHit;
    public void addedToWorld(World Field)//when added to world set the earth variable and sets the environment
    {
        this.field=Field;
        this.worldHeight=field.getHeight();
        this.worldWidth=field.getWidth();

    }

    public Dog()
    {
        setImage("dog_idle(2).png");
        getImage().scale(80,60);
        gotHit=false;
        for(int i=0;i<idleR.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_idle("+i+")"+".png");

            img.scale(80,60);
            idleR[i]= img;

        }
        for(int i=0;i<idleL.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_idle("+i+")"+".png");

            img.scale(80,60);

            idleL[i]= img;
            img.mirrorHorizontally();
        }

        for(int i=0;i<runR.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_run("+i+")"+".png");            
            img.scale(80,60);
            runR[i]= img;

        }
        for(int i=0;i<runL.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_run("+i+")"+".png");            

            img.scale(80,60);
            runL[i]= img;
            img.mirrorHorizontally();

        }
        for(int i=0;i<deadR.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_dead("+i+")"+".png");

            img.scale(80,60);
            deadR[i]= img;

        }
        for(int i=0;i<deadL.length;i++)
        {
            GreenfootImage img = new GreenfootImage("dog_dead("+i+")"+".png");

            img.scale(80,60);

            deadL[i]= img;
            img.mirrorHorizontally();
        }
    }

    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() //keeps falling if he is in the air
    {

        if(inTheAir)
        {
            fall();
        }
        moves();

        movement();
        deadDog();

    }

    public void moves()//replaces old y with newY
    {

        double newX =getX()+x;
        double newY =getY()-y;

        //detects if there is a ground below
        Actor groundBelow = getOneObjectAtOffset(0,groundHeight+5,Ground.class);
        if(groundBelow!=null)//stop us from falling and be able to jump again
        {
            inTheAir=false;
            y=0;
        }
        else

        if(newY>=460)
        {
            y=0;
            inTheAir=false;
        }else
        {
            inTheAir=true;
        }
        setLocation((int)newX,(int)newY);
    }

    public void movement()//detects which key is pressed and move based on the key
    {
        //when moving to the right or left it moves and changes the images
        if(gotHit==false)
        {
            if(Greenfoot.isKeyDown("right"))
            {
                setLocation(getX()+6,getY());
                turnR=true;
                runCounter++;
                if(runCounter%2==0)
                {
                    runIndex++;
                    if(runIndex==runR.length)
                    {
                        runIndex=0;

                    }
                }

                setImage(runR[runIndex]);

            }
            else

            if(Greenfoot.isKeyDown("left"))
            {
                setLocation(getX()-6,getY());
                turnR=false;
                runCounter++;
                if(runCounter%2==0)
                {
                    runIndex++;
                    if(runIndex==runL.length)
                    {
                        runIndex=0;

                    }
                }

                setImage(runL[runIndex]);
            }
            else
            if(Greenfoot.isKeyDown("up")&&inTheAir==false)
            {            
                jump();

            }
            else
            //when its not movind idle depending on the direction it last 
            if(inTheAir==false)
            {
                idleCounter++;
                idle();

            }
        }

    } 
     public void idle()
    {
        if(turnR==true)
        {
            if(idleCounter%2==0)
            {
                idleIndex++;
                if(idleIndex==idleR.length)
                {
                    idleIndex=0;
                }
                setImage(idleR[idleIndex]);
            }
        }
        else
        {
            if(idleCounter%2==0)
            {
                idleIndex++;
                if(idleIndex==idleL.length)
                {
                    idleIndex=0;
                }
                setImage(idleL[idleIndex]);
            }

        }
    }

    public void deadDog()//when called, it will start the death animation and remove itself when finished

    {
        if(gotHit==true)
        {
            deadCounter++;
            if(deadCounter%5==0)
            {
                deadIndex++;
                if(deadIndex==deadR.length)

                {                     
                    ((Field)getWorld()).addDog();
                    deadIndex=0;
                    gotHit=false;
                    getWorld().removeObject(this);
                }
                setImage(deadR[deadIndex]);         
            }
            else
            if(deadCounter%5==0)
            {
                deadIndex++;
                if(deadIndex==deadL.length)
                {

                    ((Field)getWorld()).addDog();
                    deadIndex=0;
                    gotHit=false;
                    getWorld().removeObject(this);
                }
                setImage(deadL[deadIndex]);
            }
        }

    }

    public void jump()//add y to jumpHeight and make inTheAir=true
    {
        y+=jumpHeight;
        inTheAir=true;

    }

   

    /*
     *fall() will be controlled whenever the Dog is in the air.It decreses y by .5, creating gravity
     */
    public void fall()
    {
        y-=fallSpeed;
    }
}