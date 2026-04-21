package BaseGame;


public interface ICollision 
{
    /**
     * Returns if the gameobject is colliding
     * @param other Gameobject
     * @return True if colliding, false if not colliding.
     */
    public boolean getIsColliding(GameObject other);
    /**
     * Returns if the gameobject is colliding via x/y
     * @param x
     * @param y
     * @return
     */
    public boolean getIsColliding(int x, int y);
    
}
