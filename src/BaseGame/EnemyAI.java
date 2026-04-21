package BaseGame;

/**
 * Enemy AI data
 */
public class EnemyAI 
{
    /**
     * Enemy Object
     */
    private GameObject enemy;
    /**
     * nearbyThreashold
     */
    private double nearbyThreshhold;
    /**
     * Width of the Screen
     */
    private final double WIDTH;
    /**
     * HEIGHT of the screen
     */
    private final double HEIGHT;
    /**
     * Create Enemy
     * @param Enemy Enemy to move around.
     * @param nearbyThreshold Vision
     * @param width Width of the screen.
     */
    public EnemyAI(GameObject enemy, double nearbyThreshold, double width, double height)
    {
        this.enemy = enemy;
        nearbyThreshhold = nearbyThreshold;
        WIDTH = width;
        HEIGHT = height;
    }
    /**
     * Check if object is nearby
     * @param x X pos
     * @param y Y pos
     * @return True/False
     */
    public boolean IsNearby(double x, double y)
    {
        double tempX = enemy.getXPos() - x;
        double tempY = enemy.getYPos() - y;
        double total = Math.abs(tempX) + Math.abs(tempY);
        if (total < nearbyThreshhold){return true;}
        return false;
    }
    /**
     * Check if <i>GameObject</i> is nearby.
     * @param obj Gameobject
     * @return True/False
     */
    public boolean IsNearby(GameObject obj)
    {
        double tempX = enemy.getXPos() - obj.getXPos();
        double tempY = enemy.getYPos() - obj.getYPos();
        double total = Math.abs(tempX) + Math.abs(tempY);
        if (total < nearbyThreshhold){return true;}
        return false;
    }
    /**
     * Move the object away from the ways.
     */
    public void BrainMovement()
    {
        if (enemy.getXPos() >= (WIDTH - enemy.scale -1))
        {
            enemy.push(-enemy.speed, enemy.getKnockY());
        }
        else if (enemy.getXPos() <= (enemy.scale + 1))
        {
            enemy.push(enemy.speed, enemy.getKnockY());
        }
        if (enemy.getYPos() >= (HEIGHT - enemy.scale -1))
        {
            enemy.push(0,-enemy.speed);
        }
        else if (enemy.getYPos() <= (enemy.scale + 1))
        {
            enemy.push(0,enemy.speed);
        }
        enemy.knockX = 0;
        enemy.knockY = 0;
    }
    /**
     * Move closer to a {@code GameObject} based on X/Y cordnation
     * @param obj
     * @param customSpeed
     */
    public void MoveCloserToObject(GameObject obj, double customSpeed)
    {
        if (obj.y > enemy.y)
        {
            enemy.push(0, customSpeed * 1);
        }
        if (obj.y < enemy.y)    
        {
            enemy.push(0, -customSpeed * 1.3);
        }
        if (obj.x > enemy.x)
        {
            enemy.push(customSpeed* 1.25, 0);
        }
        if (obj.x < enemy.x)
        {
            enemy.push(-customSpeed* 1.25, 0);
        }
    }
}
