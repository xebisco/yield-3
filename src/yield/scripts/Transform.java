package yield.scripts;

import yield.main.Script;

public class Transform extends Script {

	public Transform transform;
	public Scale scale;
	public Position position;

	@Override
	public String tag() {
		return "Transform";
	}

	public Transform() {
		transform = this;
		scale = new Scale();
		position = new Position();
	}

	public void scale(int width, int height) {
		scale.setWidth(scale.getWidth() + width);
		scale.setHeight(scale.getHeight() + height);
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public class Position {
		private double x, y;

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}
	}

	public class Scale {
		private int width = 128, height = 128;

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}

}
