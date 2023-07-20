public class Main {
    public static void main(String[] args) {
        Player p1 = new RealPlayer();
        Player p2 = new VirtualPlayer();

        Match m = new Match();

        m.startMatch(p1, p2);
    }
}