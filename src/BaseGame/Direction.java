package BaseGame;

/**
 * The direction a object should move.
 */
public enum Direction 
{
    /**
     * Don't move.
     */
    None(0,0),
    /**
     * Move UP
     */
    Up(0,-1),
    /**
     * Move DOWN
     */
    Down(0,1),
    /**
     * Move LEFT
     */
    Left(-1,0),
    /**
     * Move RIGHT
     */
    Right(1,0),
    /**
     * Move Left and Up, splitting the differnce between the 2 directions.
     */
    UpLeft(-0.5,0.5),
    /**
     * Move Right and Up, splitting the differnce between the 2 directions.
     */
    UpRight(0.5,0.5),
    /**
     * Move Left and Down, splitting the differnce between the 2 directions.
     */
    DownLeft(-0.5,-0.5),
    /**
     * Move Right and Down, splitting the differnce between the 2 directions.
     */
    DownRight(0.5,-0.5);
    /**
     * How much should be applied in the X direction
     */
    protected double x;
    /**
     * How much should be applied in the Y direction.
     */
    protected double y;
    /**
     * Create a direction
     * @param valueX Push left/right or not at all.
     * @param valueY Push up/down or not at all.
     */
    Direction(double valueX, double valueY)
    {
        x = valueX;
        y = valueY;
    }
    /**
     * 
     * @return x power (a value from 0.0 to 1.0)
     */
    public double getX()
    {
        return x;
    }
    /**
     * 
     * @return y power (a value from 0.0 to 1.0)
     */
    public double getY()
    {
        return y;
    }
}
