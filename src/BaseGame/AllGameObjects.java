package BaseGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
/**
 * <h1>The Super Renderer</h1>
 * 
 * Put this object as the main content pain in order to render all gameobjects at once.<br>
 * 
 * <h3>Make sure you add ALL gameobejects to the gameLayor array inside of this class.</h3>
 */
public class AllGameObjects extends JComponent
{
    /**
     * All the objects possible to render in the game. Use to paint everything.
     */
    public ArrayList<GameObject> gameLayor = new ArrayList<>();
    /**
     * Size of the screen in the X axis
     */
    protected int screenX;
    /**
     * Size of the screen in the Y axis
     */
    protected int screenY;
    /**
     * Set the screen size
     * @param x
     * @param y
     */
    public void SetScreen(int x, int y)
    {
        screenX = x;
        screenY = y;
    }
    /**
     * Paint all objects in the  
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = gameLayor.size();
        //System.out.println(size); //<-- Proof that object count stays at a low number.
        for (int i = 0; i < size; i++)
        {
            GameObject obj = gameLayor.get(i);
            
            if(destoryOffScreenItems(obj) || !obj.getIsAlive()) //Destroy objects below the screen or if the object is dead.
            {
                obj = null;
                gameLayor.remove(i);
                size = size - 1;
                i--;
                continue;
            }
            
            if (obj.image != null) //Draw the image IF it exsists.
            {
                int diameter = (int)(obj.scale * 2);
                int drawX = (int)(obj.x - obj.scale);
                int drawY = (int)(obj.y - obj.scale);
                g.drawImage(obj.image, drawX, drawY, diameter, diameter, this);
            } 
            else //If no image exsists, then I do this little magic trick called using the gameObjects color class to draw a solid image.
            {
                int diameter = (int)(obj.scale * 2);
                int drawX = (int)(obj.x - obj.scale);
                int drawY = (int)(obj.y - obj.scale);
            
                g.setColor(obj.color);
                g.fillOval(drawX, drawY, diameter, diameter);
            }
            /**
             * Set to true to use to draw debug lines. 
             * 
             * I used this to test if object's boxes were rendering correctly. 
             */
            if (false) 
            { 
                BoxCollidor box = obj.getBox();
                g.setColor(Color.red);
                g.drawRect((int)box.getNegX(), (int)box.getNegY(), 
                (int)(box.getPosX() - box.getNegX()), 
                (int)(box.getPosY() - box.getNegY()));
            }
        }
        // g.drawRect(x, y, diameter, diameter);
    }
    /**
     * Destroy the screen items if they go offscreen unless {@code GameObject obj.getDestroyOffScreen() == true}
     * @param obj gameobject.
     * @return True if it needs to be destroyed, False if it needs to be kept.
     */
    public boolean destoryOffScreenItems(GameObject obj)
    {
        if (obj.getDestroyOffScreen())
        {
            BoxCollidor box = obj.getBox();
            if (box.getPosX() < 0)
            {
                return true;
            }
            if (obj.destoryTopScreen)
            {
                if (box.getPosY() < 0)
                {
                    return true;
                }
            }
            
            if (box.getNegX() > screenX)
            {
                return true;
            }
            if (box.getNegY() > screenY)
            {
                return true;
            }
        }
        return false;
    }
}