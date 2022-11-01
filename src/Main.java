import frame.GameCenter;
import login.LoginFrame;

import java.util.HashMap;

/**
 * Main class of the program, here the program starts.
 *
 * @author Johan Rodriguez, Karina Urbina, Maria Jose Solis
 * @version 1.0
 */
public class Main {
    // Main method of the program
    public static void main(String[] args) {
        LoginFrame.users = LoginFrame.readUsers();
        LoginFrame login = new LoginFrame();

        while (!login.getLoginSuccess()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new GameCenter(login.getUsername());
    }
}
