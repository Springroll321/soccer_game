import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    public int deltaY=4;
    //individual speeds for the different balls
    public int sDeltaX=0;
    public int bDeltaX=2;

    private String type;
    private boolean inAir;
    private boolean justKicked=false;
    private static final int GRAVITY=1;
    private int counter=0;

    private int netHeight=getImage().getHeight()/2;
   
    public Ball()
    {
        type="Soccer";
        setImage("soccer.png");
    
        inAir=true;
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        setBall();      
        kicked();
        bounceOff();
        if(inAir==true)
        {
            deltaY++;
        }
        if(deltaY>20)//this makes it so that the ball cant be too fast in the verticle velocity
        {
            deltaY=15;
        }
        else
        if(deltaY<-20)
        {
            deltaY=-15;
        }
        if(bDeltaX>5)
        {
            bDeltaX=4;
        }
        else
        if(bDeltaX<-5)
        {
            bDeltaX=-4;
        }
    }    

    public void setBall()
    {
        //the speed will be different based on the type of the ball
        if(type=="Soccer")
        {            
            setImage("soccer.png");
            setLocation(getX()+sDeltaX,getY()+deltaY);
        }

        if(type=="Bowling")//when it turns into a bowling 
        {

            counter++;
            setImage("bowling.png");
            setLocation(getX()+bDeltaX,getY()+deltaY);

            if(counter==500)

            {
                type="Soccer";
                counter=0;

            }
        }
    }

    public void change()
    {      
        type="Bowling";       
    }

    public void kicked()
    {
        //when the cat or dog touches the ball it will kick at a random velocity
        //depending on the type of ball, in the opposite direction
        if(getOneIntersectingObject(Cat.class)!=null || getOneIntersectingObject(Dog.class)!=null )
        { 
            inAir=false;
            if(type=="Soccer"&& justKicked==false)//will move randomly
            {
                sDeltaX=-sDeltaX+ Greenfoot.getRandomNumber(10)-5;
                deltaY=-deltaY+ Greenfoot.getRandomNumber(4)-2;

            }

            if(type=="Bowling" && justKicked==false)//moves much slower than the soccerball 
            {
                bDeltaX=-bDeltaX+ Greenfoot.getRandomNumber(4)-2;
                deltaY=-deltaY+ Greenfoot.getRandomNumber(4)-2;;

            }
            justKicked=true;
        }
        else
        {
            justKicked=false;
        }

    }

    public String getEdge()
    {
        //detects the vertical edges
        int y=getY();
        int x=getX();
        if(y==0)
        {
            return "top";
        }
        else
        if(isTouching(Ground.class) && getY()>=460)
        {
            return "bottom";

        }
        //detects horizontal edges
        else
        if(x==0)
        {
            return "left";
        }
        else
        if(x==getWorld().getWidth()-1)
        {
            return "right";
        }
        else{
            return null;
        }

    }

    public void bounceOff()//bounce of at walls,nets and the ground
    {
        String edge= getEdge();
        //when it hits top of the net it reverses the velocity of the ball
        Actor topNet = getOneObjectAtOffset(0,netHeight+5,Nets.class);
        if(getY()<425)
        {
            if(topNet!=null && getX()<95)
            {

                deltaY=-deltaY*2;//prevents the ball from getting stuck on the net
                sDeltaX=-sDeltaX;
                bDeltaX=-bDeltaX;

            }
            else
            if (topNet!=null && getX()>805)
            {

                deltaY=-deltaY*2;
                sDeltaX=-sDeltaX;
                bDeltaX=-bDeltaX;

            }
        }

        //when the ball hits one of the edges it changes its speed in the opposit direction
        if(isTouching(Ground.class) || edge=="top")
        {  
            deltaY=-deltaY;
            inAir=false;
            if(getY()>=350)//prevents the ball from getting stuck in the ground
            {
                setLocation(getX(),getY()-5);
                setBall();
            }
        }

        else
        {
            inAir=true;   
        }

        if(edge=="left" || edge=="right")
        {
            sDeltaX=-sDeltaX;
            bDeltaX=-bDeltaX;
        }

    }

    public String inNet()//checks what net it goes in
    {

        if(getX()<=95 && getY()>365 )
        {
            return "net1";

        }
        else
        if(getX()>=805 && getY()>365)
        {
            return "net2";
        }

        else 
        {
            return null;
        }
    }
}