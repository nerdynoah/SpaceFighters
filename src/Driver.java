import javax.swing.JOptionPane;

public class Driver {
    public static void main(String[] args) throws Exception 
    {
        //INSTRUCTIONS TO FOLLOW (for instructor and new players)
        JOptionPane.showMessageDialog(null,"Arrow keys to move, Z to shoot. Survive as long as you can" +
        "\n\nObjects to look out for:\nAstriods: Deal damage based on size.\nStars: Purple stars which fly down, destorying all objects it collides with execpt for the spaceship. Instead, it will heal your spaceship if you collide with it.\nGreen Boosters: Increases the speed of the spaceship if collided with, will shove astriods downwards that it collides with.\nEvil Astriods: Huge Astriods with a giant tank of Health. Ignore stars, and worst of all, deal tons of damage." +
        "\n\nScoring:\n1s = 10 points\nDestruction of any objects give points based on its size", "Controls", JOptionPane.YES_OPTION);
        while (true)
        {
            GameFrame game = new GameFrame();
            while (!game.getGameOver()) {
                Thread.sleep(200); // Check every 200ms if the game is over in order to decrease resources needed for this check.
            }
            //Show score
            int response2 = JOptionPane.showConfirmDialog(null,"Your time is: " + game.getSpeedRun()/1000 + "s\nYour score is: " + game.getScore() + "\n\nRestart game?", "You died!", JOptionPane.YES_NO_OPTION);
            game.dispose(); //Delete game window
            if (response2 == JOptionPane.NO_OPTION) //If no, end script. Else just restart the loop.
            {
                break;
            }
        }
    }
}
