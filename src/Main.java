import frames.GameCenter;
import frames.Login;

import register.Register;

/**
 * Main class of the program, here the program starts.
 *
 * @author Johan Rodriguez, Karina Urbina, Maria Jose Solis
 * @version 1.0
 */
public class Main {
    // Main method of the program
    public static void main(String[] args) {
        Login.readUsers();
        Login login = new Login();

        while (!login.getLoginSuccess()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new GameCenter(login.getUser(), Register.readRecords());
    }
}
