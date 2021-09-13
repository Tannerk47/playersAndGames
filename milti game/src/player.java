public class player implements java.io.Serializable {
    private int wins;
    private String name;
    public player ( String name){
        this.name = name;
        wins =0;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }
}
