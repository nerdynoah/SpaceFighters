package BaseGame;

public abstract class Collidor implements Icollision
{
    /**
     * The center x posisition of the object
     */
    protected double centerX;
    /**
     * The center y position of the object
     */
    protected double centerY;
    /**
     * 
     * @return the center X of the box.
     */
    public double getCenterX()
    {
        return centerX;
    }
    /**
     * 
     * @return the center Y of the box.
     */
    public double getCenterY()
    {
        return centerY;
    }
}
