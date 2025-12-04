package BaseGame;

public interface Icollision 
{
    /**
     * Returns if the gameobject is colliding with the box.
     * @param other Gameobject
     * @return True if colliding, false if not colliding.
     */
    public boolean getIsColliding(GameObject other);
    /**
     * Get if the box is colliding with a point.
     * @param x X value
     * @param y Y value
     * @return If X and Y are overlapping, return true;
     */
    public boolean getIsColliding(int x, int y);
}
