package cm.program.base;


public class Start {

    public static final String GAME_VERSION = "0.1 beta #1";
    public static final String GAME_NAME = "CubeStruct";

    public static void main(String[] args) {
        Game game = new Game();

      /* if (args.length > 2) {
            game.doLogin(args[0], args[1], args[2]);
        } else {
            game.dummyLogin();
        }*/

        Thread t = new Thread(game);
        t.start();
    }
}
