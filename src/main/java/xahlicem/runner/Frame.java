package xahlicem.runner;

import xahlicem.runner.graphics.Screen;
import xahlicem.runner.helpers.Input;

import javax.swing.JFrame;

public class Frame extends JFrame {
    private static final long serialVersionUID = -5902218571111718023L;

    private Screen screen;
    private Input input;

    public Frame(Screen screen, Input input) {
        this.screen = screen;
        this.input = input;
    }

    public void init() {
        setResizable(false);
        setTitle(App.TITLE);
        add(screen);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        screen.addKeyListener(input);
		screen.addMouseListener(input);
		screen.addMouseWheelListener(input);
		screen.addMouseMotionListener(input);
    }
}