package xahlicem.runner.graphics;

public class Sprite {

    public final int width, height;
    public int[] pixels;
    protected SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
		width = size;
		height = size;
		this.sheet = sheet;
		pixels = new int[width * height];
		load(x * width, y * height, pixels);
	}

	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		pixels = new int[width * height];
		load(x * width, y * height, pixels);
	}

	public Sprite(int size, int pixelColor) {
		width = size;
		height = size;
		pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = pixelColor;
	}

	public Sprite(int width, int height, int pixelColor) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = pixelColor;
	}

	protected void load(int xPos, int yPos, int[] pixels) {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[x + y * width] = sheet.pixels[(xPos + x) + (yPos + y) * sheet.width];
	}

	protected Sprite rotate(int times) {
		int[] srcPixels = pixels;
		Sprite s = new Sprite(width, height, 0);

		for (int i = 0; i < times; i++) {
			// Create the destination Sprite
			int[] destPixels = new int[srcPixels.length];

			int srcPos = 0; // We can just increment this since the data pack
							// order matches our loop traversal: left to right,
							// top to bottom. (Just like reading a book.)
			for (int srcY = 0; srcY < height; srcY++)
				for (int srcX = 0; srcX < width; srcX++) {
					int destX = ((height - 1) - srcY);
					int destY = srcX;
					destPixels[destX + destY * height] = srcPixels[srcPos++];
				}
			srcPixels = destPixels;
		}

		s.pixels = srcPixels;
		return s;
	}

	protected Sprite rotate() {
		return rotate(1);
	}
}