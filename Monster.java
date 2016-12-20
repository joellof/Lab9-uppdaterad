
public class Monster {
    private int health;
    private final int hit = 10;
    private final Position currentPosition;

    public Monster (){
        this.health = 50;
        Position start = new Position(0,1);
        this.currentPosition = start;
    }
    public void loseHealth(){
        this.health = health - this.hit;
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    public void move (TowerDefenceGame.movements move){

        switch (move) {
            case FRONT:
                currentPosition.moveForward();
                break;
            case UP:
                currentPosition.moveUp();
                break;
            case DOWN:
                currentPosition.moveDown();
                break;
        }

    }

    public int getHealth(){
    	return this.health;
    }

}
