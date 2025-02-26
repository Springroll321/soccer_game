import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * An explosion. It starts by expanding and then collapsing. 
 * The explosion will explode other obejcts that the explosion intersects.
 * 
 * @author Poul Henriksen
 * @version 1.0.1
 */
public class Explosion extends Actor
{
    //How many images should be used in the animation of the explostion 
    private final static int IMAGE_COUNT= 15;

    // The images in the explosion
    private static GreenfootImage[] images;
    //Current size of the explosion 
    private int imageNo = 0;

    //adds the index in the explosion animation
    private int increment=1;
    
    /**
     * Create a new explosion.
     */
    public Explosion() 
    {
        setImages();
        setImage(images[0]);
    }    

    public void setImages() 
    {
        //when there are no images, it creates the explosion
        if(images == null) {
            GreenfootImage baseImage = new GreenfootImage("explosion.png");
            images = new GreenfootImage[IMAGE_COUNT];

            for (int i = 0; i < IMAGE_COUNT; i++)
            {
                int size = (i+1) * ( baseImage.getWidth() / IMAGE_COUNT );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size*3, size*3);
            }
        }
    }

    /**
     * Explodes
     */
    public void act()
    { 
         hit();
        setImage(images[imageNo]);

        imageNo=imageNo+ increment;
        //once the size of the explosion is greater than the number of images in the explosion
        //it will decrease in size
        if(imageNo >= IMAGE_COUNT) {
            increment = -increment;
            imageNo += increment;
        }
        //when it is done exploding it removes the explosion
        if(imageNo < 0) {
            getWorld().removeObject(this);            
        }
       
    }
    public void hit()
    {
        Cat c= (Cat) getOneIntersectingObject(Cat.class);
        Dog d= (Dog) getOneIntersectingObject(Dog.class);
        if(c!=null)
        {
            c.gotHit=true;
        }
        else
        if(d!=null)
        {
            d.gotHit=true;
        }
        
    }
 
}