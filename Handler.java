package SI;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    ArrayList<GameObject> gameObjects = new ArrayList<>();


    public Handler () {}

    public void update() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject g1 = gameObjects.get(i);
            g1.update();
            for (int j = 0; j < gameObjects.size(); j++) {
                GameObject g2 = gameObjects.get(j);
                checkCollisions(g1, g2);
            }
        }
    }

    public void checkCollisions(GameObject g1, GameObject g2){
        if(g1 != null && g2 != null) {
            if (g1.rect.intersects(g2.rect)) {
                collision(g1, g2);
            }
        }
    }

    public void collision(GameObject g1, GameObject g2){

        GameObjectType g1type = g1.getObjectType();
        GameObjectType g2type = g2.getObjectType();

        /* * * * * * * * Barrier - Bomb * * * * * * */
        if(isBarrier(g1type) && isBomb(g2type)){
            collisionBarrierBomb(g1,g2);
        }
        /* if(isBarrier(g2type) && isBomb(g1type)){
            collisionBarrierBomb(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

        /* * * * * * * * Barrier - Shot * * * * * * */
        if(isBarrier(g1type) && isShot(g2type)){
            collisionBarrierShot(g1,g2);
        }
        /* if(isBarrier(g2type) && isShot(g1type)){
            collisionBarrierShot(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

        /* * * * * * Bomb - Defender * * * * * * */
        if(isBomb(g1type) && isDefender(g2type)){
            collisionBombDefender(g1,g2);
        }
        /*if(isBarrier(g2type) && isDefender(g1type)){
            collisionBombDefender(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

        /* * * * * * Defender - Invader * * * * * * */
        if(isDefender(g1type) && isInvader(g2type)){
            collisionDefenderInvader(g1,g2);
        }
        /* if(isBarrier(g2type) && isDefender(g1type)){
            collisionBombDefender(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

        /* * * * * * Defender - Invader * * * * * * */
        if(isDefender(g1type) && isInvader(g2type)){
            collisionDefenderInvader(g1,g2);
        }
        /* if(isBarrier(g2type) && isDefender(g1type)){
            collisionBombDefender(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

        /* * * * * * * Invader - Shot * * * * * * * */
        if(isInvader(g1type) && isShot(g2type)){
            collisionInvaderShot(g1,g2);
        }
        /* if(isBarrier(g2type) && isDefender(g1type)){
            collisionBombDefender(g2,g1);
        } */
        /* * * * * * * * * * * * * * * * * * * * * */

    }

    public void collisionBarrierBomb(GameObject barrier, GameObject bomb){
        barrier.terminate();
        gameObjects.remove(barrier);
        bomb.terminate();
        gameObjects.remove(bomb);
    }

    public void collisionBarrierShot(GameObject barrier, GameObject shot){
        shot.terminate();
        gameObjects.remove(shot);
    }

    public void collisionBombDefender(GameObject bomb, GameObject defender){
        System.out.println("Bomb Defender");
        bomb.terminate();
        gameObjects.remove(bomb);
        defender.terminate();
        if(defender.lives == 0){
            gameObjects.remove(defender);
        }
    }

    public void collisionDefenderInvader(GameObject defender, GameObject invader){
        defender.terminate();
        invader.terminate();
        gameObjects.remove(invader);
    }

    public void collisionInvaderShot(GameObject invader, GameObject shot){
        invader.terminate();
        gameObjects.remove(invader);
        shot.terminate();
        gameObjects.remove(shot);
    }

    public boolean isBarrier(GameObjectType gameObject){
        if(gameObject == GameObjectType.BARRIER) { return true; }
        return false;
    }

    public boolean isBomb(GameObjectType gameObject){
        if(gameObject == GameObjectType.BOMB) { return true; }
        return false;
    }

    public boolean isDefender(GameObjectType gameObject){
        if(gameObject == GameObjectType.DEFENDER) { return true; }
        return false;
    }

    public boolean isInvader(GameObjectType gameObject){
        if(gameObject == GameObjectType.INVADER) { return true; }
        return false;
    }

    public boolean isShot(GameObjectType gameObject){
        if(gameObject == GameObjectType.SHOT) { return true; }
        return false;
    }

    public void paint(Graphics g){
        for(int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).paint(g);
        }
    }

    public void addObject(GameObject g){ gameObjects.add(g); }
    public void removeObject(GameObject g){ gameObjects.remove(g); }

}
