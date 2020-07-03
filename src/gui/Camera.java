package gui;

public class Camera {
	private static int x, y;
	
	public static void setPosition(int x, int y){
		Camera.setX(x);
		Camera.setY(y);
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Camera.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Camera.y = y;
	}
}
