import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor
{
   
    public int score;
    private String type;
    public ScoreBoard(String name, int i)
    {
        GreenfootImage img = new GreenfootImage(180,40);
        img.setColor(Color.BLACK);

        img.setFont(new Font("Berlin Sans FB Demi",true,false,30));

        img.drawString(name+": " + i,5,35);
        setImage(img);
        type= name;
        score= i;
        
    }

    

    /**
     * Act - do whatever the ScoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    public  void changeScoreC(int num)//changes the score of the cat
    {
        score = score + num;
        GreenfootImage img = getImage();
        img.clear();
        if(score<5)
        {
            img.drawString(type+": "+score,5,35);
        }
        else
        {
            img.drawString("You Win!",5,35);
            Greenfoot.stop();
            
        }
    }

    public void changeScoreD(int num)//changes the score of the dog
    {
         score = score + num;
        GreenfootImage img = getImage();
        img.clear();
        if(score<5)
        {
            img.drawString(type+": "+score,5,35);
        }
        else
        {
            img.drawString("You Win!",5,35);
            Greenfoot.stop();
            
        }
    }
}
