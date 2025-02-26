import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Field extends World
{
    private int count=0;
    private Ground ground[]= new Ground [9];
    private final static int PU_CHANCE=5;
    private Ball b;
    private Nets n1;
    private Nets n2;
    private int chance=0;
    private ScoreBoard cats;
     private ScoreBoard dogs;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Field()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900,600, 1); 
        for(int i=0;i<ground.length;i++)
        {
            ground[i]= new Ground();

        }
        int c=0;
        for( int i=0;i<ground.length;i++)
        {
            addObject(ground[i],50+c*100,550);
            c++;
        }
        //adds the nets to te world
        n1=new Nets(1);
        n2=new Nets(2);
        addObject(n1,850,425);
        addObject(n2,50,425);

        //add the players to the world
        addObject(new Dog(),775,475);
        addObject(new Cat(),125,475);
        //add the ball to the world
        b=new Ball();
        addObject(b,getWidth()/2,getHeight()/2);
        cats=new ScoreBoard("Cat",0);
        addObject(cats,150,60);
        dogs=new ScoreBoard("Dog",0);
        addObject(dogs,850,60);
    }

    public void act()
    {
        count++;
        addBombs();
        moreBombs();
        addPowerUps();
        goal();
    }

    public void addPowerUps()
    {
        if(count%50==0)//every 50 iter theres a chance of adding a powerup
        {
            if(Greenfoot.getRandomNumber(100)<PU_CHANCE)
            {
                addObject(new PowerUps(),Greenfoot.getRandomNumber(700)+100,475);
            }
        }
    }
    public void changeBall()//calls the change method in the Ball class
    {
        b.change();
        b.bDeltaX=b.bDeltaX*-1;
    }

    public void moreBombs()
    {
        //for every power up on the screen it increases the chance a bomb will spawn
        List<PowerUps> pu= getObjects(PowerUps.class);
        for(PowerUps p: pu)
        {
            chance++;
        }
    }

    public void addBombs()//add bombs 
    {
        if(chance>=50)//the max chance bombs will spawn
        {
            chance=50;
        }
        //as time increases so will the chance of bombs spawning        
        if(count%100==0)
        {
            chance=chance+3;
            if(Greenfoot.getRandomNumber(100)<chance)
            {
                addObject(new Bomb(),Greenfoot.getRandomNumber(getWidth()),5);
            }
        }
        
    }
    
    public void addCat()//adds a cat
    {
        addObject(new Cat(),50,10);
    }
    
     public void addDog()
    {
        addObject(new Dog(),850,10);
    }
    public void goal()//calls the change score method in the Scoreboard
    {
        if(b.inNet()=="net1")
        {
            dogs.changeScoreC(1);
            
        }
        else
        if(b.inNet()=="net2")
        {
            cats.changeScoreC(1);
             
        }
        
        if(b.inNet()=="net2" || b.inNet()=="net1")
        {
            removeObject(b);
             addObject(b,getWidth()/2,getHeight()/2);
             b.sDeltaX=0;
            b. bDeltaX=0;
            b. deltaY=4;
        }
      
       
    }
    
}
