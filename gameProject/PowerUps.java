import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class PowerUps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUps extends Actor
{
    /**
     * Act - do whatever the PowerUps wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        changeType();
    }    
    public void changeType()//whenever it hits one of the players it changes the ball
    {
       Cat c= (Cat) getOneIntersectingObject(Cat.class);
       Dog d = (Dog) getOneIntersectingObject(Dog.class);
       
       if(c!=null || d!=null)
       {
           ((Field)getWorld()).changeBall();
           getWorld().removeObject(this);
           
        }
        
        
    }
    
}
