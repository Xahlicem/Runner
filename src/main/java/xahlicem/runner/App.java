package xahlicem.runner;

import xahlicem.runner.graphics.Screen;
import xahlicem.runner.helpers.Input;

public final class App implements Runnable {
    private static final double TPS = 60D;
    private static final double NSPT = 1_000_000_000D / TPS;

    public static final String TITLE = "Runner";

    private Thread thread;
    private boolean running;
    private Screen screen;
    private Input input;
    private Frame frame;

    private App() {
        screen = new Screen();
        input = new Input();
        frame = new Frame(screen, input);
    }

    /**
     * Start the game.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Starting!");

        App app = new App();
        app.frame.init();

        app.start();
    }

    public void run() {
        long lastTime = System.nanoTime(), now = 0;
        long timer = System.currentTimeMillis();
        double delta = 0;
        int fps = 0, tps = 0;
        boolean draw = false;

        while (running) {
            now = System.nanoTime();
            delta += (double) (now - lastTime) / NSPT;
            lastTime = now;
            if (delta < 1)
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            while (delta >= 1) {
                tick();
                tps++;
                draw = true;
                delta--;
            }
            if (draw) {
                draw();
                fps++;
                // draw = false;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + "   |   TPS:" + tps + ", FPS:" + fps);
                tps = 0;
                fps = 0;
            }
        }
    }

    public synchronized void start() {
		running = true;

		/*String name = JOptionPane.showInputDialog("Please enter name");
		String ip = "10.1.10.2";
		if (JOptionPane.showConfirmDialog(frame, "Do you want to host?") == 0) {
			server = new Server(this);
			server.start();
		} // else ip = JOptionPane.showInputDialog("Please enter server IP");
		client = new Client(this, ip);
		client.start();

		new PacketLogin(name).writeData(client);
        */
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    private void tick() {
        input.tick();
    }

    private void draw() {

        screen.draw();
    }
}
