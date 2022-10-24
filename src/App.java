public class App {
    private static Player player1 = null;
    private static Player player2 = null;
    private static Player player3 = null;
    private static Player player4 = null;

    public App() {
        new Menu();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Player getPlayer3() {
        return player3;
    }

    public static Player getPlayer4() {
        return player4;
    }
}
