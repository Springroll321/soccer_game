import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title extends World
{

    /**
     * Constructor for objects of class Title.
     * 
     */
    public Title()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        addObject(new Start(),getWidth()/2,getHeight()/2);
       
        
    }

    public void act()//when space is pressed it set the world to the field world
    {
        if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Field());
        }
    }

    
    
    
}
