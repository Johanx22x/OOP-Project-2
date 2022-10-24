public class App {
    private static Player player1 = null;
    private static Player player2 = null;
    private static Player player3 = null;
    private static Player player4 = null;

    public App() {
        new Menu();
    }

    public static getPlayer1() {
        return player1;
    }

    public static getPlayer2() {
        return player2;
    }

    public static getPlayer3() {
        return player3;
    }

    public static getPlayer4() {
        return player4;
    }
}
