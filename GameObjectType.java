package SI;

public enum GameObjectType {

    DEFENDER,
    INVADER,
    BARRIER,
    SCORE,
    HEART,
    BOMB,
    SHOT;

    public GameObjectType getDefender(){
        return DEFENDER;
    }

    public GameObjectType getInvader(){
        return INVADER;
    }

    public GameObjectType getBarrier(){
        return BARRIER;
    }

}
