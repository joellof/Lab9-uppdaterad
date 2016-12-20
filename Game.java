public interface Game {

    //public enum movements{};

    public void nextTurn();

    public boolean endGame();

    public boolean deadMonster();

    public void towerAttack();

    public int getMonsterHealth();

    public Position getMonsterPosition();

}
