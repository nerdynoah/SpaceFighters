package BaseGame;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Everything which can interact with each other, has a point, is painting apon, is a gameobject. Hold in an array to render multiple at a time.
 */
public class GameObject extends JComponent{
    /**
     * Current X cordnet
     */
    protected double x;
    /**
     * Current Y cordnet
     */
    protected double y;
    /**
     * Knockback of X
     */
    protected double knockX = 0;
    /**
     * Knockback of Y
     */
    protected double knockY = 0;
    /**
     * End knockback at time.
     */
    protected long endKnock = 0;
    /**
     * Are you being pushed by a permanite force?
     */
    protected boolean permPush = false;
    /**
     * The scaling of the object. (how big is it.)
     */
    protected double scale;
    /**
     * The rotation of the object (Doesn't work)
     */
    protected double rotatation;
    /**
     * The color of the object. (Doesn't work)
     */
    protected Color color;
    /**
     * The image to be rendered of the object.
     */
    protected BufferedImage image;
    /**
     * The speed of the object
     */
    public double speed;
    /**
     * Your current HP.
     */
    protected double health;
    /**
     * Max Health. Used in the Heal(double amount) method.
     */
    protected double maxHealth;
    /**
     * Weight of the object. Effects knockback.
     */
    protected double weight; 
    /**
     * How long does knockback last.
     */
    protected long weightTime;
    /**
     * Can the object be destroyed if it goes off the screen?
     */
    private boolean destoryOffScreen = false;
    /**
     * How much damage you deal.
     */
    protected double damage;
    /**
     * Invincibility before you get hit again.
     */
    protected long hitDelay;
    /**
     * Can the object be destroyed at the top of the screen?
     */
    public boolean destoryTopScreen = false;

    /**
     * @return Get if the object can be destroyed if it goes of screen.
     */
    public boolean getDestroyOffScreen()
    {
        return destoryOffScreen;
    }
    /**
     * Empty Constructor
     */
    public GameObject()
    {

    }
    /**
     * Copy Constructor
     * @param obj Obj to copy
     */
    public GameObject(GameObject obj)
    {
        this.x = obj.x;
        this.y = obj.y;
        this.scale = obj.scale;
        this.rotatation = obj.rotatation;
        this.color = obj.color;
        this.image = obj.image;
        this.speed = obj.speed;
        this.health = obj.health;
        this.maxHealth = obj.maxHealth;
        this.weight = obj.weight;
        this.weightTime = obj.weightTime;
        this.destoryOffScreen = obj.destoryOffScreen;
        this.knockY = obj.knockY;
        this.knockX = obj.knockX;
        this.endKnock = obj.endKnock;
        setName(obj.getName());
    }
    /**
     * Copy constructor with the ability to switch if it can or cannot be destoryed when the object goes off screen
     * @param obj gameobject to copy
     * @param destoryOffScreen Should the object be destroyed when it goes offscreen?
     */
    public GameObject(GameObject obj, boolean destoryOffScreen)
    {
        this.x = obj.x;
        this.y = obj.y;
        this.scale = obj.scale;
        this.rotatation = obj.rotatation;
        this.color = obj.color;
        this.image = obj.image;
        this.speed = obj.speed;
        this.health = obj.health;
        this.maxHealth = obj.maxHealth;
        this.weight = obj.weight;
        this.weightTime = obj.weightTime;
        this.destoryOffScreen = destoryOffScreen;
        this.knockY = obj.knockY;
        this.knockX = obj.knockX;
        this.endKnock = obj.endKnock;
        this.hitDelay = obj.hitDelay;
        setName(obj.getName());
    }
    /**
     * Copy constructor with the ability to switch if it can or cannot be destoryed when the object goes off screen. Also allows you rescale the object
     * @param obj gameobject to copy
     * @param destoryOffScreen Should the object be destroyed when it goes offscreen?
     * @param reScale Size of the object
     */
    public GameObject(GameObject obj, boolean destoryOffScreen, double reScale)
    {
        this.x = obj.x;
        this.y = obj.y;
        this.scale = reScale;
        this.rotatation = obj.rotatation;
        this.color = obj.color;
        this.image = obj.image;
        this.speed = obj.speed;
        this.health = obj.health;
        this.maxHealth = obj.maxHealth;
        this.weight = obj.weight;
        this.weightTime = obj.weightTime;
        this.destoryOffScreen = destoryOffScreen;
        this.knockY = obj.knockY;
        this.knockX = obj.knockX;
        this.endKnock = obj.endKnock;
        this.hitDelay = obj.hitDelay;
        setName(obj.getName());
    }
    /**
     * Create a gameobject with its location.
     * @param x
     * @param y
     * @param scale size
     */
    public GameObject(double x, double y, double scale)
    {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    /**
     * Create a gameobject with its location and Color
     * @param x
     * @param y
     * @param scale size
     * @param color (Doesn't work)
     */
    public GameObject(double x, double y, double scale, Color color)
    {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
    }
    /**
     * Create a gameObject with its location and image
     * @param x
     * @param y
     * @param scale size
     * @param image image.
     */
    public GameObject(double x, double y, double scale, BufferedImage image)
    {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.image = image;
        this.color = new Color(255,255,255);
    }
    /**
     * Create a gameobejct with its location, image, and color
     * @param x
     * @param y
     * @param scale size
     * @param image the image to be displayed
     * @param color (Doesn't work)
     */
    public GameObject(double x, double y, double scale, BufferedImage image, Color color)
    {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.image = image;
        this.color = color;
    }
    /**
     * Set the position of the object
     * @param x x
     * @param y y
     */
    public void SetPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Setup the stats of the object
     * @param health How much health should it have, 0 = death.
     * @param maxHealth Max HP
     * @param speed How fast is the object.
     * @param weight The weight of the object
     * @param weightTime The weigth time of the object.
     */
    public void SetupStats(double health, double maxHealth, double speed, double weight, long weightTime)
    {
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.weight = weight;
        this.weightTime = weightTime;
    }
    /**
     * Setup the stats of the object, and weighter it should be destoryed off screen
     * @param health How much health should it have, 0 = death.
     * @param maxHealth Max HP
     * @param speed How fast is the object.
     * @param weight The weight of the object
     * @param weightTime The weigth time of the object.
     * @param destroyOffScreen weighter to destroy it off screen.
     */
    public void SetupStats(double health, double maxHealth, double speed, double weight, long weightTime, boolean destoryOffScreen)
    {
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.weight = weight;
        this.weightTime = weightTime;
        this.destoryOffScreen = destoryOffScreen;
    }
    /**
     * Setup the stats of the object
     * @param health How much health should it have, 0 = death.
     * @param maxHealth Max HP
     * @param speed How fast is the object.
     * @param weight The weight of the object
     * @param weightTime The weigth time of the object.
     * @param damage The damage you deal.
     */
    public void SetupStats(double health, double maxHealth, double speed, double weight, long weightTime, double damage)
    {
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.weight = weight;
        this.weightTime = weightTime;
        this.damage = damage;
    }
    /**
     * Setup the stats of the object, and weighter it should be destoryed off screen
     * @param health How much health should it have, 0 = death.
     * @param maxHealth Max HP
     * @param speed How fast is the object.
     * @param weight The weight of the object
     * @param weightTime The weigth time of the object.
     * @param destroyOffScreen weighter to destroy it off screen.
     * @param damage The damage you deal.
     */
    public void SetupStats(double health, double maxHealth, double speed, double weight, long weightTime, double damage, boolean destoryOffScreen)
    {
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.weight = weight;
        this.weightTime = weightTime;
        this.destoryOffScreen = destoryOffScreen;
        this.damage = damage;
    }
    /**
     * How long till you can be hit again
     * @param delay delay
     */
    public void setHitDelay(long delay)
    {
        hitDelay = delay;
    }
    /**
     * Get how long till you can be hit again.
     * @return
     */
    public long getHitDelay()
    {
        return hitDelay;
    }
    /**
     * Update the posistion of the gameobject based on knockback and deltaTime. 
     * @param time The current time
     * @param deltaTime time between frames in milis.
     */
    public void update(long time, long deltaTime)
    {
        if (time > endKnock && !permPush)
        {
            knockX = 0;
            knockY = 0;
        }
        push(knockX * deltaTime,knockY * deltaTime);
    }
    /**
     * Update the posistion of the gameobject based on knockback and deltaTime. Ignoring any timing systems.
     * @param deltaTime time between frames in milis.
     */
    public void update(long deltaTime)
    {
        push(knockX * deltaTime,knockY * deltaTime);
    }
    /**
     * 
     * @return Color
     */
    public Color getColor()
    {
        return color;
    }
    /**
     * 
     * @return x
     */
    public double getXPos()
    {
        return this.x;
    }
    /**
     * 
     * @return y
     */
    public double getYPos()
    {
        return this.y;
    }
    /**
     * 
     * @return scale
     */
    public double getScale()
    {
        return scale;
    }
    /**
     * Get rotation. Directions from 0 to 360;
     * @return
     */
    public double getRotation()
    {
        return rotatation;
    }
    /**
     * Push the object an amount of pixals.
     * @param x
     * @param y
     */
    public void push(double x, double y)
    {
        this.x += x;
        this.y += y;  
    }
    /**
     * Knockback the object for a period of time.
     * @param x 
     * @param y 
     */
    public void push(double x, double y, long time)
    {
        knockX += x;
        knockY += y;
        endKnock = time; 
    }
    /**
     * Permanilty push a object. This method will add to the values {@code knockX += x} and {@code knockY += y}
     * @param x 
     * @param y 
     */
    public void pushVelocity(double x, double y)
    {
        knockX += x;
        knockY += y;
        permPush = true;
    }
    /**
     * Sets the velocity to the values of X and Y
     * @param x 
     * @param y
     */
    public void setPermVelocity(double x, double y)
    {
        knockX = x;
        knockY = y;
        permPush = true;
    }
    /**
     * Get the current knockY value.
     * @return
     */
    public double getKnockY()
    {
        return knockY;
    }
    /**
     * Get your box collidor
     * @return a new BoxCollidor using the gameobjects size and posisitioning. <br></br>
     * {@code new BoxCollidor(x,y,scale)}
     */
    public BoxCollidor getBox()
    {
        return new BoxCollidor(x,y,scale);
    }
    /**
     * The paint component.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawRect(x, y, diameter, diameter);
    }
    /**
     *  
     * @return if your health is greater than 0, return true.
     */
    public boolean getIsAlive()
    {
        return health > 0;
    }
    /**
     * 
     * @return The gameobjects current health
     */
    public double getHealth()
    {
        return health;
    }
    /**
     * Deal damage to the gameObject.
     * @param amount The amount of damage.
     * @return If the player is alive.
     * @remarks Will need to fix the color system for health to damage.
     */
    public boolean damage(double amount)
    {
        health -= Math.max(amount,0);
        color = new Color(1.0f,Math.max(Math.min((float)(health/maxHealth),1),0),Math.max(Math.min((float)(health/maxHealth),1),0),1.0f);
        image.setRGB(0,0,color.getRGB());
        return getIsAlive();
    }
    /**
     * Heals the player up to their maxHealth.
     * @param amount The amount to heal
     */
    public void heal(double amount)
    {
        health += Math.max(amount, 0);
        health = Math.min(health, maxHealth);
    }
    /**
     * 
     * @return damage
     */
    public double getDamage()
    {
        return damage;
    }
    /**
     * 
     * @return weight
     */
    public double getWeight()
    {
        return weight;
    }
    /**
     * 
     * @return weightTime
     */
    public long getWeightTime()
    {
        return weightTime;
    }
    /**
     * Shows some basic data of the gameobject.
     */
    @Override
    public String toString()
    {
        return "Name: " + getName() + " scale: " + scale + ", x: " + x + ", y: " + y + " speed: " + speed + " health: " + health; 
    }
    /**
     * Shows some basic data of the gameobject and adjusts speed to deltaTime.
     * @param deltaTime
     * @return
     */
    public String toString(long deltaTime)
    {
        return "Name: " + getName() + " scale: " + scale + ", x: " + x + ", y: " + y + " speed: " + speed * deltaTime + " health: " + health; 
    }
}
