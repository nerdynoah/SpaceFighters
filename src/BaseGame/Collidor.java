package BaseGame;
public abstract class Collidor implements ICollision
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
    /**
     * Get is colliding based on exact positions
     * @param x
     * @param y
     * @return
     */
    public boolean getIsColliding(double x, double y)
    {
        if (centerX == x && centerY == y)
        {
            return true;
        }
        return false;
    }
    /**
     * Get is colliding based on exact posisiotning
     */
    public boolean getIsColliding(GameObject other)
    {
        if (other.getYPos() == centerX && other.getYPos() == centerY)
        {
            return true;
        }
        return false;
    }
}
