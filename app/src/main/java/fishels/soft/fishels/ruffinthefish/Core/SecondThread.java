package fishels.soft.fishels.ruffinthefish.Core;

public class SecondThread extends Thread implements Runnable {

    private GamePanel gamePanel;
    private boolean running;


    public SecondThread(GamePanel gamePanel) {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {

        while (running) {
            this.gamePanel.initFish();

            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean b) {
        this.running = b;
    }
}
