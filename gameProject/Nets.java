import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Nets here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nets extends Actor
{
    
    public Nets(int type)//changes the image depending on the type
    {
        if(type == 1) {
            setImage("goal.png");
        } else {
            setImage("goal.png");
            getImage().mirrorHorizontally();
        }
        getImage().scale(100,160);
    }

    /**
     * Act - do whatever the Nets wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.

    }  
    
}
