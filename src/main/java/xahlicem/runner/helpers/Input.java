package xahlicem.runner.helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

public class Input implements KeyListener, MouseInputListener, MouseWheelListener {
	public static final int KEY_UP = 	0b00000001;
	public static final int KEY_DOWN =	0b00000010;
	public static final int KEY_LEFT = 	0b00000100;
	public static final int KEY_RIGHT =	0b00001000;
	public static final int KEY_SHIFT =	0b00010000;
	public static final int KEY_PRESS = 0b00100000;
	public static final int KEY_E = 	0b01000000;
	public static final int KEY_ESC = 	0b10000000;

	private int wheelPos = 0;
	private boolean wheelMoved = false;
	private int keys = 0;
	private int[] point = new int[2];

	public boolean isKeyPressed(int keyCode) {
		return (keys & keyCode) == keyCode;
	}

	public int[] getPoint() {
		return point;
	}
	
	public int getWheel() {
		return wheelPos;
	}
	
	public void tick() {
		if (!wheelMoved) wheelPos = 0; 
		wheelMoved = false;
	}

	private void tick(int key, boolean pressed) {
		int bit = 0;
		switch (key) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				bit = KEY_UP;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				bit = KEY_DOWN;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				bit = KEY_LEFT;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				bit = KEY_RIGHT;
				break;
			case KeyEvent.VK_SHIFT:
				bit = KEY_SHIFT;
				break;
			case KeyEvent.VK_ESCAPE:
				bit = KEY_ESC;
				break;
			case KeyEvent.VK_E:
				bit = KEY_E;
				break;
			default:
				return;
		}
		if (pressed) keys |= bit;
		else keys &= ~bit;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		tick(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		tick(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		keys |= KEY_PRESS;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		keys &= ~KEY_PRESS;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		point[0] = e.getX();
		point[1] = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		point[0] = e.getX();
		point[1] = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelPos -= e.getWheelRotation();
		wheelMoved = true;
	}
}