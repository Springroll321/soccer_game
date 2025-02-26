import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private final static int RANGE=500;
    private int gravity=0;

    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        gravity++;
        seek();
        setLocation(getX(),getY()+gravity);      
        explode();

        
    }    

    public void seek()
    {
        //when in range of a cat or a dog it will move towards it
        List<Cat> c=getObjectsInRange(RANGE,Cat.class);
        if(c.size()>0)
        {
            Cat cat=c.get(0);
            turnTowards(cat.getX(),cat.getY());
            move(3);

        }
         List<Dog> d=getObjectsInRange(RANGE,Dog.class);
        if(d.size()>0)
        {
            Dog dog=d.get(0);
            turnTowards(dog.getX(),dog.getY());
            move(3);

        }
        
    }
    public void explode()//when touches the ground it explodes
    {
        if(isTouching(Ground.class))
        {
            getWorld().addObject(new Explosion(),getX(),getY());  
            getWorld().removeObject(this);
        }
    }
}

