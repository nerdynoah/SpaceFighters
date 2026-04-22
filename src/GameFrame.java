import javax.imageio.ImageIO;
import javax.swing.*;

import BaseGame.AllGameObjects;
import BaseGame.BoxCollidor;
import BaseGame.Direction;
import BaseGame.GameObject;
import BaseGame.EnemyAI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener
{
    /**
     * A Jpanel to render stuff.
     */
    private JPanel root = new JPanel();
    /**
     * Spaceship img
     */
    private BufferedImage mainSPace = null;
    /**
     * Enemy spaceship
     */
    private BufferedImage enemySpace = null;
    /**
     * Astriods
     */
    private BufferedImage obstical = null;
    /**
     * Bullets.
     */
    private BufferedImage bulletIMG = null;
    private BufferedImage staredIMG = null;
    private BufferedImage evilastriodIMG = null;
    private BufferedImage boost = null;
    private BufferedImage BounceIMG = null;
    /**
     * Used to calculate deltaTime
     */
    private long last_time = 0;
    /**
     * Time between frames in millis
     */
    private long deltaTime;
    /**
     * Initial astriod summoning delay
     */
    private long astriodSummonDelay = 1550;
    /**
     * The delay value which updates to {@code Time.time + astriodSummonDelay}
     */
    private long AstriodDelay = 3000;
    /**
     * Delay between shots.
     */
    private final long BULLETSUMMONDELAY = 191;
    /**
     * Bullet delays
     */
    private long bulletDelay = 0;
    /**
     * Random system.
     */
    private Random random = new Random();
    /**
     * The spaceship gameobject.
     */
    private GameObject spaceShip;
    /**
     * The base astriod gameobject to be copied by other astriods.
     */
    private GameObject astriod;
    /**
     * Super evil Astriod, which acts like a boss.
     */
    private GameObject evilAstriod;
    /**
     * The base bullet gameobject to be copied by other bullets.
     */
    private GameObject bullet;
    /**
     * The base star, which acts like a astriod but destorys astriods and heals the player.
     */ 
    private GameObject star;
    /**
     * Gives ANYTHING it passes a boost;
     */
    private GameObject booster;
    /**
     * A Bossfight.
     */
    private GameObject EvilBoss;
    /**
     * Bouncing ball
     */
    private GameObject BoucingBall;
    /**
     * Pathing system for boss;
     */
    private EnemyAI EvilBossAI;
    /**
     * An arraylist of <h1>EVERY RENDERED OBJECT</h1>
     */
    private AllGameObjects allGameObjects = new AllGameObjects();
    /**
     * An arraylist of <h3>Every astriod in play</h3> 
     */ 
    private ArrayList<GameObject> astriods = new ArrayList<>();
    /**
     * An arraylist of <h4>Every bullet in game</h4>
     */
    private ArrayList<GameObject> bullets = new ArrayList<>();
    /**
     * Can you move foward
     */
    private boolean foward;
    /**
     * Can you move backward
     */
    private boolean backword;
    /**
     * Can you move left
     */
    private boolean left;
    /**
     * Can you move right
     */
    private boolean right;
    /**
     * Time before you can get hit again. {@Code HitDelay = basehitDelay + Time.time}
     */
    private long HitDelay = 0;
    /**
     * The amount of time until you get hit again. Leave relativly small to create a funny bounce effect.
     */
    private long baseHitDelay = 150;
    /**
     * How long you survived for.
     */
    private long speedRun = 0;
    /**
     * How many points you gained. Currently: <br></br>
     * <ul>
     *  <li>Each Second Survived</li>
     *  <li>Destorying Objects</li>
     * </ul>
     */
    private long points = 0;
    /**
     * 
     * @return How longed you survived.
     */
    public long getSpeedRun()
    {
        return System.currentTimeMillis() - speedRun;
    }
    /**
     * 
     * @return Your score
     */
    public long getScore()
    {
        return Math.max((points + System.currentTimeMillis() - speedRun)/100,0);
    }
    /**
     * Is the game over?
     */
    private boolean isGameOver = false;
    /**
     * 
     * @return true if the game is over.
     */
    public boolean getGameOver()
    {
        return isGameOver;
    }
    /**
     * The width of the program
     */
    private final int WIDTH = 1300;
    /**
     * The height of the program
     */
    private final int HEIGHT = 900;
    /**
     * The starting HP of the ship.
     */
    private final int SHIPSTARTINGHP = 440;
    private final int EVILBOSSHP = 1000;
    private long speedboostTime = 0;
    private long evilShootDelay = 0;
    private final long EVILSHOTDELAY = 580;
    private double speedBoost = 1;
    private int mode = 0;
    /**
     * The red progress bar at the bottom of the stage.
     */
    private JProgressBar bar = new JProgressBar(0, SHIPSTARTINGHP);
    private JProgressBar evilbar = new JProgressBar(0,EVILBOSSHP);

    /**
     * Music to play in the background
     */
    private PlaySong music = new PlaySong();
    /**
     * Good shooting effect
     * Whoosh effect: Sound Effect by <a href="https://pixabay.com/users/universfield-28281460/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=192899">Universfield</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=192899">Pixabay</a>
     * Sound Effect by <a href="https://pixabay.com/users/media_man_uk-53122485/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=432285">Media_Man_UK</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=432285">Pixabay</a>
     */
    private PlaySong effects = new PlaySong(); 
    /**
     * Used to play the astriod effects
     */
    private PlaySong hitSoundCH2 = new PlaySong();
    /**
     * Evil shooter
     * Sound Effect by <a href="https://pixabay.com/users/u_62htdrvg4y-50821090/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=359196">u_62htdrvg4y</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=359196">Pixabay</a>
     */
    private PlaySong evilShotEffect = new PlaySong();
    /**
     * Good hitsound
     * Sound Effect by <a href="https://pixabay.com/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=14611">freesound_community</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=14611">Pixabay</a>
     */
    private PlaySong hitSound = new PlaySong();
    /**
     * Coin sound: <a href="https://pixabay.com/users/u_vdwj1c20kz-50311970/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=342335">u_vdwj1c20kz</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=342335">Pixabay</a>
     * Hurt sound: <a href="https://pixabay.com/users/freesound_community-46691455/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=103405">freesound_community</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=103405">Pixabay</a>
     */
    private PlaySong hurtSound = new PlaySong();
    /**
     * Setup the game data.
     * @throws IOException File was prob moved or deleted.
     */
    public GameFrame() throws IOException
    {
        //Setup time and screen setup for the allGameObjects object.
        random.setSeed(System.nanoTime());
        speedRun = System.currentTimeMillis();
        last_time = System.currentTimeMillis();
        allGameObjects.SetScreen(WIDTH, HEIGHT);
        points = 0;
        //Setup images.
        mainSPace = ImageIO.read(new File("MainCharacter\\SpaceFigherCapSmall.png"));
        obstical = ImageIO.read(new File("LOOKOUT\\Astroid2.png"));
        bulletIMG = ImageIO.read(new File("Bullet\\Bullet.png"));
        staredIMG = ImageIO.read(new File("LOOKOUT\\Star.png"));
        evilastriodIMG = ImageIO.read(new File("LOOKOUT\\Astroid.png"));
        boost = ImageIO.read(new File("LOOKOUT/Time.png"));
        enemySpace = ImageIO.read(new File("LOOKOUT\\Enemy.png"));
        BounceIMG = ImageIO.read(new File("LOOKOUT\\Bouse.png"));
        
        //Setup gameobjects.
        spaceShip = new GameObject(WIDTH/2, 600, 25, mainSPace);
        spaceShip.setName("Player Spaceship");
        spaceShip.SetupStats(SHIPSTARTINGHP,SHIPSTARTINGHP,0.44f,10,3,false);
        EvilBoss = new GameObject(600,-100,100,enemySpace);
        EvilBoss.setName("EvilSpaceBoss");
        EvilBoss.SetupStats(EVILBOSSHP,EVILBOSSHP,0.485f,5,300);

        astriod = new GameObject(600,-100,100,obstical);
        astriod.setName("Astriod");
        astriod.SetupStats(15, 15, 0.1f, 10, 1000,false);
        BoucingBall = new GameObject(600,-100,100,BounceIMG);
        BoucingBall.setName("Bouncy");
        BoucingBall.SetupStats(15, 15, 0.1f, 10, 1000,false);
        evilAstriod = new GameObject(600,-100,100,evilastriodIMG);
        evilAstriod.setName("Evil Astriod");
        evilAstriod.SetupStats(15, 15, 0.1f, 20, 1000,false);
        star = new GameObject(600,-100,100,staredIMG);
        star.setName("Star");
        star.SetupStats(1, 1, 0.1f, 10, 1000,false);
        booster = new GameObject(600,-100,100,boost);
        booster.setName("Booster");
        booster.SetupStats(3, 3, 0.1f, 10, 1000,false);
        //Setup background some more.
        bar.setForeground(Color.GREEN);
        bar.setValue(SHIPSTARTINGHP);
        evilbar.setForeground(Color.RED);
        evilbar.setValue(0);
        //Create bullet
        bullet = new GameObject(-100,-100,16,bulletIMG);
        bullet.setName("Bullet");
        bullet.destoryTopScreen = true;
        //Setup canvus stuff.
        allGameObjects.gameLayor.add(spaceShip);
        this.root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        this.root.add(evilbar);
        this.root.add(allGameObjects);
        this.root.add(bar);
        this.setContentPane(root);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        //Main loop
        animate();
        //Rest of the window.
        this.setTitle("Space Fighters");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        speedRun = System.currentTimeMillis(); //Start speedrun
        music.playWAV("Music/Casual.wav", true);
    }
    /**
     * End the game. Set the speedrun to the correct time, and set the game to be over.
     */
    public void endGame()
    {
        music.stop();
        isGameOver = true;
    }
    /**
     * Get is the spaceship dead.
     * @return If the spaceship is dead, return true.
     */
    public boolean getSpaceShipDead()
    {
        //System.out.println(spaceShip.getHealth()); //This method was used for debbugging some issues with health not returning if you were alive or dead accuratly.
        return !spaceShip.getIsAlive();
    }
    /**
     * Animate everything being used on the screen.
     * @remarks Note to me in the future, Do not use nanoTime for multiplayer games due to it being unsafe. Different JVM's will have different outputs of nanaTime. see <a href="https://stackoverflow.com/questions/351565/system-currenttimemillis-vs-system-nanotime">Stack Overflow System Nanotime</a> for more details
     * @param last_time first anaimation loop.
     */
    public void animate()
    {
        Timer t = new Timer(10, (e) -> {
            //Deltatime calculations
            long time = System.currentTimeMillis();
            deltaTime = (time - last_time);
            last_time = time;
            
            move(); //Update movement of the spaceship
            spaceShip.update(time,deltaTime); //Update the visual of the spaceship during knockback from collision from astriods.
            //Summon astriod if the proper amount of time has passed.
            if (mode == 0 || mode == 3)
            {
                if (time > AstriodDelay)
                {
                AstriodDelay = Math.max(time + astriodSummonDelay-(summonAstriod() * 20),25); //Delay on summoning the next astriod
                //Decrease the delay to make the game more diffucult.
                if (astriodSummonDelay < 270)
                {
                    astriodSummonDelay = Math.max(astriodSummonDelay - 2, 110);
                }
                else if (astriodSummonDelay < 400)
                {
                    astriodSummonDelay = Math.max(astriodSummonDelay - 15, 140);
                }
                else if (astriodSummonDelay < 600)
                {
                    astriodSummonDelay = Math.max(astriodSummonDelay - 50, 100);
                }
                else if (astriodSummonDelay < 1000)
                {
                    astriodSummonDelay = Math.max(astriodSummonDelay - 120, 100);
                }
                else
                {
                    astriodSummonDelay = Math.max(astriodSummonDelay - 200, 100);
                }
                System.out.println("Delay for next spawn: " + (AstriodDelay - time) + "Points: " + this.getScore());
                }
                evilbar.setValue((int)this.getScore() * 5);
            }
            else if (mode == 2)
            {
                if (time > AstriodDelay)
                {
                    AstriodDelay = time + 600 - summonAstriod() * 20;
                    System.out.println("Delay for next spawn: " + (AstriodDelay - time) + "Points: " + this.getScore());
                }
                EvilBossAI.BrainMovement();
                if (System.currentTimeMillis() > evilShootDelay)
                {
                    shootEvilBullet();
                    evilShootDelay = System.currentTimeMillis() + EVILSHOTDELAY;
                }
                EvilBossAI.MoveCloserToObject(spaceShip, 0.112f * deltaTime);
                for (int i = 0; i < astriods.size(); i++)
                {
                    if ((astriods.get(i).getName().equals("Star") || astriods.get(i).getName().equals("Booster")) && astriods.get(i).getYPos() > 0 && astriods.get(i).getYPos() < HEIGHT && astriods.get(i).getXPos() < WIDTH &&astriods.get(i).getXPos() > 0)
                    {
                        EvilBossAI.MoveCloserToObject(astriods.get(i), 0.275 * deltaTime);
                        System.out.println("FOUND STAR");
                        break;
                    }
                    if (astriods.get(i).getName().equals("Evil Astriod"))
                    {
                        if (EvilBossAI.IsNearby(astriods.get(i)))
                        {
                            if (EvilBoss.getXPos() <= WIDTH / 2)
                            {
                                EvilBoss.push(0.67 * deltaTime, 0.05 * deltaTime);
                            }
                            if (EvilBoss.getXPos() >= WIDTH / 2)
                            {
                                EvilBoss.push(-0.67 * deltaTime, 0.05 * deltaTime);
                            }
                        }
                    }
                }
                
                evilbar.setValue((int)EvilBoss.getHealth());
            }
            isColliding(); //Check if anything is colliding
            if (this.getScore() > 200 && mode < 1)
            {
                mode = 1;
                SummonBoss();
            }
            this.allGameObjects.repaint(); //Update the script
            if (!EvilBoss.getIsAlive() && mode < 3) 
            {
                music.stop();
                music.playWAV("Music\\ToVictory.wav", true);
                AstriodDelay = 2000;
                mode = 3;
            }
            if (getSpaceShipDead()) //If the spaceship is dead, end the game.
            {
                ((Timer)e.getSource()).stop();
                endGame();
                allGameObjects.removeAll(); //Delete all objects.
                return; 
            }
        });
        t.restart();
        t.start();
        
    }
    
    /**
     * Summon a astriod, return delay beteween summining astriods.<br></br>
     * Astriods will be random from size, damage, spawn, weight, and speed. Health is = to size,
     * @return 150/size
     */
    public long summonAstriod()
    {
        double size = 10 + (random.nextFloat()*160); //Size
        float rngValue = random.nextFloat();
        if (mode == 2)
        {
            rngValue = rngValue + 0.082f;
        }
        if (rngValue > 0.95)
        {
            GameObject obj = new GameObject(star,false,Math.max(size/2,20));
            obj.SetPosition(random.nextInt(WIDTH), -size); //Random summoning position
            //Setup stats
            double speed = Math.max(0.6 + (random.nextFloat()*0.6) - size/200,0.4);
            double damage = 25 + (random.nextFloat()*10) + size/9;
            double weight = Math.max(((damage/4) + speed + (size/8))/16,0)+0.5;
            obj.SetupStats(size/10,size/10,speed,(weight),(long)(16*deltaTime),damage,true);
            if (obj.getXPos() < WIDTH/2)
            {
                obj.pushVelocity(1 * random.nextFloat(),speed); //Permanilty push these objects downward.
            }
            else
            {
                obj.pushVelocity(-1 * random.nextFloat(), speed);
            }
            
            astriods.add(obj);
            allGameObjects.gameLayor.add(obj); //Add to this array to make it possible to paint.
            System.out.println(weight);
        }
        else if (rngValue > 0.93)
        {
            size = size * 2.8;
            GameObject obj = new GameObject(evilAstriod,false,size+20); //Copy
            obj.SetPosition(WIDTH/2, -size); //Random summoning position
            //Setup stats
            double speed = Math.max(Math.abs(0.2 + (random.nextFloat()*(size/100)) - (size-100)/100)/10,0.2);
            double damage = 10 + (random.nextFloat()*20) + size/3;
            double health = 140 + size/10 + random.nextFloat()*70;
            double weight = Math.max(((damage*2) + speed + (size))/12,0)+ health * 0.02;
            obj.SetupStats(health,health,speed,(weight),(long)(16*deltaTime),damage,true);
            obj.pushVelocity(0,speed); //Permanilty push these objects downward.
            astriods.add(obj);
            allGameObjects.gameLayor.add(obj); //Add to this array to make it possible to paint.
            System.out.println(weight);
        }
        else if (rngValue > 0.865)
        {
            size = size + 40;
            GameObject obj = new GameObject(booster,false,size);
            obj.SetPosition(random.nextInt(WIDTH), -size); //Random summoning position
            //Setup stats
            double speed = Math.max(0.95 + (random.nextFloat()*0.35) - size/120,0.5);
            double damage = 3500 + (random.nextFloat()*2000) + size*10;
            double weight = 1.051791 + (random.nextFloat()*0.101)+size/300;
            obj.SetupStats(size/2,size/2,speed,(weight),(long)(16*deltaTime),damage,true);
            if (obj.getXPos() < WIDTH/2)
            {
                obj.pushVelocity(0.5 * random.nextFloat(),speed); //Permanilty push these objects downward.
            }
            else
            {
                obj.pushVelocity(-0.5 * random.nextFloat(), speed);
            }
            astriods.add(obj);
            allGameObjects.gameLayor.add(obj); //Add to this array to make it possible to paint.
            System.out.println(weight);
        }
        else
        {
            GameObject obj = new GameObject(astriod,false,size); //Copy
            obj.SetPosition(random.nextInt(WIDTH), -size); //Random summoning position
            //Setup stats
            double speed = Math.max(0.82 + (random.nextFloat()*0.35) - size/180,0.4);
            double damage = 10 + (random.nextFloat()*10) + size/9;
            double weight = Math.max(((damage/4) + speed/2 + (size/8))/16,0)+0.5;
            obj.SetupStats(size * 0.8f,size * 0.8f,speed,(weight),(long)(16*deltaTime),damage,true);
            obj.pushVelocity(0,speed); //Permanilty push these objects downward.
            astriods.add(obj); 
            allGameObjects.gameLayor.add(obj); //Add to this array to make it possible to paint.
            System.out.println(weight);
        }
        
        return (long)(150/size); //Delay
    }
    /**
     * Check for the following:
     * <ol>
     *  <li> Update bullet location </li>
     *  <li> If any of the astriods are dead, delete them </li>
     *  <li> Update astriod poisions </li>
     *  <li> Check if spaceship is colliding with astriods </li>
     *  <li> Check if any bullets are colliding with the astriods </li>
     *  <li> Check if any astriods are colliding with astriods.
     */
    public void isColliding()
    {
        BoxCollidor box = spaceShip.getBox();
        for (int i = 0; i < bullets.size(); i++) //Update bullet location, O(n) with n being a value between 0-6.
        {
            bullets.get(i).update(deltaTime);    
        }
        for (int i = 0; i < astriods.size(); i++) //Update astriod info, O(n) with n being a value from 0-50.
        {
            if (astriods.get(i) == null || !astriods.get(i).getIsAlive()) //If any of the astriods are dead, delete them
            {
                astriods.remove(i);
                i--;
                continue;
            }
            astriods.get(i).update(deltaTime); //Update astriod poisions
            if (box.getIsColliding(astriods.get(i))&& System.currentTimeMillis() > HitDelay) //Check if spaceship is colliding with astriods
            { //If yes, then damage, knockback, and give a hitdelay to the spaceShip.
                if (astriods.get(i).getName().equals("Star"))
                {
                    hurtSound.stop();
                    spaceShip.heal(astriods.get(i).getDamage());
                    HitDelay = baseHitDelay * 2 + System.currentTimeMillis(); //Update hit delay
                    bar.setValue((int)spaceShip.getHealth()); //Update visuals.
                    hurtSound.playWAV("Music\\Effects\\u_vdwj1c20kz-coin-collision-sound-342335.wav", false);
                }
                else if (astriods.get(i).getName().equals("Booster")) //If you touch a booster, you will get 70% of its effects.
                {
                    speedboostTime = (long)astriods.get(i).getDamage() + System.currentTimeMillis();
                    speedBoost = astriods.get(i).getWeight()*0.7;
                    System.out.println("Applied speed boost to player: " + speedBoost + " for :" + (speedboostTime/1000 - System.currentTimeMillis())+"s");
                }
                else
                {
                    hurtSound.stop();
                    spaceShip.damage(astriods.get(i).getDamage()); //Damage
                    spaceShip.push(astriods.get(i).getWeight() * astriods.get(i).GetKnockX(), astriods.get(i).getWeight() * astriods.get(i).getKnockY(), astriods.get(i).getWeightTime() + System.currentTimeMillis()); //Knock
                    System.out.println(spaceShip.getName() + " was hit! HP: " + spaceShip.getHealth());
                    HitDelay = baseHitDelay + System.currentTimeMillis(); //Update hit delay
                    bar.setValue((int)spaceShip.getHealth()); //Update visuals.
                    hurtSound.playWAV("Music\\Effects\\freesound_community-fast-collision-reverb-14611.wav", false);
                }
                
            }
            for (int j = 0; j < bullets.size(); j++) 
            {
                if (astriods.get(i).getBox().getIsColliding(bullets.get(j))) //Check if any bullets have collided with the astriods
                { //If yes, damage and knockback the astriods based on weight.
                    hitSound.stop();
                    astriods.get(i).damage(bullets.get(j).getDamage()); //Damage the astriod
                    hitSound.playWAV("Music\\Effects\\freesound_community-fast-collision-reverb-14611.wav", false);
                    if (!astriods.get(i).getIsAlive()) //Check if its alive, if not, give points based on size.
                    {
                        hitSoundCH2.stop();
                        points = (points + ((long)astriods.get(i).getScale())*10);
                        if (astriods.get(i).getName().equals("Star")) //Heal the player with a 10% boost to the amount.
                        {
                            hitSoundCH2.playWAV("Music\\Effects\\u_vdwj1c20kz-coin-collision-sound-342335.wav", false);
                            spaceShip.heal(astriods.get(i).getDamage()*1.1);
                            bar.setValue((int)spaceShip.getHealth()); //Update visuals.
                        }
                        if(astriods.get(i).getName().equalsIgnoreCase("Booster")) //If you destroy a booster, give full boost.
                        {
                            hitSoundCH2.playWAV("Music/Effects/universfield-high-speed-02-192899.wav", false);
                            speedboostTime = (long)astriods.get(i).getDamage() + System.currentTimeMillis();
                            speedBoost = astriods.get(i).getWeight();
                            System.out.println("Applied speed boost to player: " + speedBoost + " for :" + (speedboostTime/1000 - System.currentTimeMillis())+"s");
                        }
                    }
                    astriods.get(i).push(0, Math.min(bullets.get(j).getWeight()*-4 + astriods.get(i).getWeight(),0)); //Give knockback
                    bullets.get(j).SetPosition(-100, -100); //Set the bullets off to the side to be deleted.
                }
            }
            for (int j = 0; j < astriods.size();j++) //Check if any astriods have collided with the astriods
            { 
                if (!astriods.get(i).equals(astriods.get(j)) && astriods.get(i).getBox().getIsColliding(astriods.get(j)))
                {   //If yes, knockback the astriods based on weight.
                    BoxCollidor evilBox = astriods.get(i).getBox();
                    Direction dir = evilBox.getIsCollidingDirectionX(astriods.get(j));
                    astriods.get(i).pushVelocity(-1*dir.getX() * (0.0006*Math.max(astriods.get(j).getWeight() - astriods.get(i).getWeight(), 0)+ 0.0004)* deltaTime,0); //Knocking them left/right only
                    astriods.get(j).pushVelocity(dir.getX() * (0.0006*Math.max(astriods.get(i).getWeight() - astriods.get(j).getWeight(), 0) + 0.0004)* deltaTime,0); //Knocking them left/right only
                    if (astriods.get(j).getName().equals("Star") && !astriods.get(i).getName().equals("Evil Astriod") && !astriods.get(i).getName().equals("EvilSpaceBoss"))
                    {
                        astriods.get(i).damage(astriods.get(j).getDamage());
                    }
                    else if (astriods.get(j).getName().equals("Star") && astriods.get(i).getName().equals("EvilSpaceBoss"))
                    {
                        astriods.get(i).heal(astriods.get(j).getDamage()*1.2);
                        astriods.get(j).damage(10000);
                    }
                    else if (astriods.get(j).getName().equals("EvilSpaceBoss") && !astriods.get(i).getName().equals("Booster") && astriods.get(i).getName().equals("Bouncy"))
                    {
                        astriods.get(j).damage(astriods.get(j).getDamage()*1.15);
                    }
                    if (astriods.get(i).getName().equals("Booster"))
                    {
                        astriods.get(j).pushVelocity(0,(0.001*Math.max(astriods.get(i).getWeight() - astriods.get(j).getWeight(), 0) + 0.0001)* deltaTime);
                    }
                    if (astriods.get(j).getName().equals("Booster"))
                    {
                        astriods.get(i).pushVelocity(0,(0.001*Math.max(astriods.get(j).getWeight() - astriods.get(i).getWeight(), 0) + 0.0001)* deltaTime);
                    }
                }
            }
        }
    }
    /**
     * 
     * @return The amount of bullets currently being rendered in game.
     */
    public int getBulletCount()
    {
        int name = 0;
        for (int i = 0; i < allGameObjects.gameLayor.size(); i++) //Go through every object in the game.
        {
            GameObject obj = allGameObjects.gameLayor.get(i);
            if ("Bullet".equals(obj.getName())) //Finds bullets by name cause I couldn't get the damned bullet array to work.
            {
                if (obj.getBox().getNegY() < 0)
                {
                    obj = null; //Set the object to null in order to "delete" it from memory (I think)
                    allGameObjects.gameLayor.remove(i); //Remove the item.
                }
                else
                {
                    name++; //Next object.
                }
            }
        }
        return name;
    }
    /**
     * Move the ship
     */
    public void move()
    {
        double deltaX = 0;
        double deltaY = 0;
        //You won't even notice the speed differneces due to the astriods falling around you. Pretty neat optical illision.
        if (foward)
        {
            deltaY -= 1 * spaceShip.speed * 0.9f; //Move 10% slower upward so your not running headfirst into the astriods heading TOWARDS YOU.
        }
        if (backword)
        {
            deltaY += 1 * spaceShip.speed * 1.06f; //Move 6% faster downwards so you can catch up to astriods falling by you.
        }
        if (right)
        {
            deltaX += 1 * spaceShip.speed * 1.15; //Move left/right faster cause base is too slow
        }
        if (left)
        {
            deltaX -= 1 * spaceShip.speed * 1.15; //Move left/right faster cause base is too slow
        }
        if (System.currentTimeMillis() < speedboostTime) //Apply speed boost if needed.
        {
            deltaX = deltaX * speedBoost;
            deltaY = deltaY * speedBoost;
        }
        spaceShip.push(deltaX * deltaTime, deltaY * deltaTime); //Push in the appropirate direction.
        //Manually move the spaceship away from the walls.
        if (spaceShip.getBox().getPosX() > WIDTH)
        {
            spaceShip.SetPosition(WIDTH - spaceShip.getScale() - 1, spaceShip.getYPos());
        }
        if (spaceShip.getBox().getNegX() < 0)
        {
            spaceShip.SetPosition(0 + spaceShip.getScale() + 1, spaceShip.getYPos());
        }
        if (spaceShip.getBox().getPosY() > HEIGHT - spaceShip.getScale()*2)
        {
            spaceShip.SetPosition(spaceShip.getXPos(), HEIGHT - spaceShip.getScale() * 3);
        }
        if (spaceShip.getBox().getNegY() < 0)
        {
            spaceShip.SetPosition(spaceShip.getXPos(), 0 + spaceShip.getScale() + 1);
        }
        
    }
    /**
     * Shoot bullets when commanded to. Summons 2 at a time at the ship's left and right positive corners.
     */
    public void shootBullet()
    {
        effects.stop();
        effects.playWAV("Music\\Effects\\media_man_uk-lazer-gun-432285.wav", false);
        double size = 6 + (random.nextFloat()*16); //Size
        //2 Bullets
        GameObject lobj = new GameObject(bullet,true,size);
        GameObject robj = new GameObject(bullet, true, size);
        //Left and right of the ship
        robj.SetPosition(spaceShip.getBox().getPosX(), spaceShip.getYPos() - size);
        lobj.SetPosition(spaceShip.getBox().getNegX(), spaceShip.getYPos() - size);

        double speed = Math.max(0.5 + (random.nextFloat()*1.3) - size/40,0.4); //Speed of the bullets.
        double damage = 4.5 + (random.nextFloat()*2) + size*0.25; //Damage of the bullets.
        double weight = (long)((damage/10) + speed*2 + (size/3)); //Knockback/Weight of the bullets.
        System.out.println("Bullet wieght: " + weight);
        System.out.println("Speed of next Bullets: " + speed); 
        //Setup stats and speed.
        lobj.SetupStats(size,size,speed,(weight),(long)(10 * deltaTime),damage,true);
        robj.SetupStats(size,size,speed,(weight),(long)(10 * deltaTime),damage,true);
        lobj.setPermVelocity(0,-speed);
        robj.setPermVelocity(0,-speed);
        
        bullets.add(lobj);
        bullets.add(robj);  
        allGameObjects.gameLayor.add(robj); //Add to drawing array.
        allGameObjects.gameLayor.add(lobj); //Add to drawing array.
    }
    public void shootEvilBullet()
    {
        evilShotEffect.stop();
        evilShotEffect.playWAV("Music\\Effects\\u_62htdrvg4y-gun-shot-359196.wav", false);
        double size = 6 + (random.nextFloat()*40); //Size
        //2 Bullets
        GameObject lobj = new GameObject(BoucingBall,true,size);
        GameObject robj = new GameObject(BoucingBall, true, size);
        //Left and right of the ship
        robj.SetPosition(EvilBoss.getBox().getPosX() + size + 1, EvilBoss.getBox().getPosY() + size + 1);
        lobj.SetPosition(EvilBoss.getBox().getNegX() - size + 1, EvilBoss.getBox().getPosY() + size + 1);

        double speed = Math.max(0.25 + (random.nextFloat()*1.25) - size/40,0.2); //Speed of the bullets.
        double damage = (random.nextFloat()*(EvilBoss.getDamage()*0.55)) + size*0.25; //Damage of the bullets.
        double weight = (long)((damage/60) + speed*1.2 + (size/4)); //Knockback/Weight of the bullets.
        System.out.println("Bullet wieght: " + weight);
        System.out.println("Speed of next Bullets: " + speed); 
        //Setup stats and speed.
        lobj.SetupStats(size/2,size/2,speed,(weight),100,damage,true);
        robj.SetupStats(size/2,size/2,speed,(weight),100,damage,true);
        lobj.setPermVelocity(0,speed);
        robj.setPermVelocity(0,speed);
        astriods.add(lobj);
        astriods.add(robj);  
        allGameObjects.gameLayor.add(robj); //Add to drawing array.
        allGameObjects.gameLayor.add(lobj); //Add to drawing array.
    }
    @Override
    public void keyPressed(KeyEvent e) 
    {
        double extra = 0;
        if (System.currentTimeMillis() < speedboostTime) //Apply speed boost if needed.
        {
            extra = 2;
        }
        if (e.getKeyCode() == Controls.foward)
        {
            foward = true;    //Move foward
        }
        if (e.getKeyCode() == Controls.backword)
        {
            backword = true; //Move Backward
        }
        if (e.getKeyCode() == Controls.left)
        {
            left = true; //Move Left
        }
        if (e.getKeyCode() == Controls.right)
        {
            right = true; //Move Right
        }
        if ((e.getKeyCode() == Controls.primary || e.getKeyCode() == Controls.secondary) && (getBulletCount() < 8 + extra && System.currentTimeMillis() > bulletDelay))
        {
            bulletDelay = BULLETSUMMONDELAY + System.currentTimeMillis(); //Delay before the next shot.
            shootBullet();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }
    @Override
    public void keyReleased(KeyEvent e) 
    {
        //Used to ensure releasing the key stops the spaceship.
        if (e.getKeyCode() == Controls.foward)
        {
            foward = false;    //Stop foward
        }
        if (e.getKeyCode() == Controls.backword)
        {
            backword = false; //Stop backward
        }
        if (e.getKeyCode() == Controls.left)
        {
            left = false; //Stop left
        }
        if (e.getKeyCode() == Controls.right)
        {
            right = false; //Stop right
        }
    }
    /**
     * Use to summon the boss;
     */
    private void SummonBoss()
    {
        music.stop();
        //https://www.youtube.com/watch?v=5o9ftHHIyY4&list=PLBWCE0NqaWBqkSYpgSJwRBkKAcJNoFW16&index=189 <-- CREDIT TO MUSIC
        music.playWAV("Music\\Mario & Luigi Brothership Drowned In Despair Glohm Boss Battle Theme (by @hi_im_shade445) - MysteryMa'am (128k).wav", true);
        int SIZE = 30;
        EvilBoss = new GameObject(EvilBoss,false,SIZE);
        EvilBoss.SetupStats(EVILBOSSHP, EVILBOSSHP, 3f, 35, 400,20,false);
        EvilBossAI = new EnemyAI(EvilBoss, 720, WIDTH, HEIGHT);
        
        EvilBoss.SetPosition(WIDTH/2, SIZE);
        astriods.add(EvilBoss);
        allGameObjects.gameLayor.add(EvilBoss);
        mode = 2;
        evilbar.setValue(EVILBOSSHP);
        EvilBoss.push(30, 0);
    }
    
}