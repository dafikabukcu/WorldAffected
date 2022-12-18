import java.io.Serializable;

public class Player implements Serializable {

    public String name;
    public String score;

    public Player(String name, String score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name+": "+score;
    }
}
