
public class Position {

    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveForward(){
        this.x = x + 1;
    }
   
    public void moveUp(){
    	this.y = y + 1;
    }
    
    public void moveDown(){
        this.y = y - 1;
    }
    
    public int getXPosition (){
        return this.x;
    }

    public int getYPosition (){
        return this.y;
    }

    @Override
    public String toString(){
    	return "(" + this.x + "," + this.y + ")";
    }
   
}
