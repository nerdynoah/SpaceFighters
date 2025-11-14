package BaseGame;

public class BoxCollidor
{
    /**
     * RIGHT
     */
    protected double posX;
    /**
     * TOP 
     */
    protected double posY;
    /**
     * LEFT
     */
    protected double negX;
    /**
     * DOWN
     */
    protected double negY;
    /**
     * The center x posisition of the object
     */
    protected double centerX;
    /**
     * The center y position of the object
     */
    protected double centerY;
    /**
     * Create a box
     * @param x xPos (Center)
     * @param y yPos (Center)
     * @param scale The scale of the object.
     */
    public BoxCollidor(double x, double y, double scale)
    {
        posX = x + scale;
        negX = x - scale;
        posY = y + scale;
        negY = y - scale;
        centerX = x;
        centerY = y;
    }
    /**
     * Returns if the gameobject is colliding with the box.
     * @param other Gameobject
     * @return True if colliding, false if not colliding.
     */
    public boolean getIsColliding(GameObject other)
    {
        return getIsColliding(new BoxCollidor(other.x, other.y, other.scale));
    }
    /**
     * Get if this box is colliding with other box.
     * @param other a BoxCollidor.
     * @return If X and Y are overlapping, return true.
     */
    public boolean getIsColliding(BoxCollidor other)
    {
        boolean overlapX = (this.negX <= other.posX) && (this.posX >= other.negX);
        boolean overlapY = (this.negY <= other.posY) && (this.posY >= other.negY);
        return overlapX && overlapY;
    }
    /**
     * Get if the box is colliding with a point.
     * @param x X value
     * @param y Y value
     * @return If X and Y are overlapping, return true;
     */
    public boolean getIsColliding(int x, int y)
    {
        boolean overlapX = (this.negX <= x) && (this.posX >= x);
        boolean overlapY = (this.negY <= y) && (this.posY >= y);
        return overlapX && overlapY;
    }
    /**
     * @deprecated Does not accuratly return the correct directions in calcuations. 
     * @param other Box collidior
     * @return {@code Directions}
     */
    @Deprecated
    public Direction getIsCollidingDirection(BoxCollidor other)
    {
        if (getIsColliding(other))
        {
            if (this.centerY > other.centerY && this.posX >= other.negX)
            {
                return Direction.UpLeft;
            }
            if (this.centerY < other.centerY && this.posX >= other.negX)
            {
                return Direction.DownLeft;
            }
            if (this.centerY > other.centerY && this.negX <= other.posX)
            {
                return Direction.UpRight;
            }
            if (this.centerY < other.centerY && this.negX <= other.posX)
            {
                return Direction.DownRight;
            }
        }
        return Direction.Down;
    }
    /**
     * Get if the gameobject is above or below this collidor.
     * @param other
     * @return {@code Direction}
     */
    public Direction getIsCollidingDirectionY(GameObject other)
    {
        if (getIsColliding(other))
        {
            if (this.centerY > other.getBox().centerY)
            {
                return Direction.Up;
            }
            return Direction.Down;
        }
        return Direction.Down;
    }
    /**
     * Get if the gameobject is above or below this collidor.
     * @param other BoxCollidor.
     * @return {@code Direction}, UP or DOWN
     */
    public Direction getIsCollidingDirectionY(BoxCollidor other)
    {
        if (getIsColliding(other))
        {
            if (this.centerY > other.centerY)
            {
                return Direction.Up;
            }
            return Direction.Down;
        }
        return Direction.Down;
    }
    /**
     * Get if the gameobject is on the left or right side of an object. If it is directly between the 2, returns down.
     * @param other GameObject
     * @return {@code Direction} LEFT, RIGHT, or DOWN
     */
    public Direction getIsCollidingDirectionX(GameObject other)
    {
        if (getIsColliding(other))
        {
            if (this.centerX > other.getBox().centerX)
            {
                return Direction.Left;
            }
            return Direction.Right;
        }
        return Direction.Down;
    }
    /**
     * Get if the box is on the left or right side of an object. If it is directly between the 2, returns down.
     * @param other boxCollidor
     * @return {@code Direction} LEFT, RIGHT, or DOWN
     */
    public Direction getIsCollidingDirectionX(BoxCollidor other)
    {
        if (getIsColliding(other))
        {
            if (this.centerX > other.centerX)
            {
                return Direction.Left;
            }
            return Direction.Right;
        }
        return Direction.Down;
    }
    /**
     * 
     * @return the box's negative X 
     */
    public double getNegX()
    {
        return negX;
    }
    /**
     * 
     * @return the box's positive X
     */
    public double getPosX()
    {
        return posX;
    }
    /**
     * 
     * @return the box's negative Y 
     */
    public double getNegY()
    {
        return negY;
    }
    /**
     * 
     * @return the box's positive Y 
     */
    public double getPosY()
    {
        return posY;
    }
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
     * Print the bounds of the box. (DEBUG ONLY)
     */
    public void printBounds() 
    {
        System.out.printf("Box bounds: X[%.2f, %.2f] Y[%.2f, %.2f]%n", negX, posX, negY, posY);
    }
}
