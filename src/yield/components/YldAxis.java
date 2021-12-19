package yield.components;

import java.awt.*;
import java.util.Objects;

public class YldAxis extends YldComponent {

    public final YldAxisPosition position = new YldAxisPosition();
    public final YldAxisRotation rotation = new YldAxisRotation();
    public final YldAxisScale scale = new YldAxisScale(128, 128);

    public static class YldAxisPosition extends YldAxisBasic {

        public void translate(Vector vector) {
            setX(getX() + vector.getX());
            setY(getY() + vector.getY());
        }

        public void teleport(Vector to) {
            setX(to.getX());
            setY(to.getY());
        }

    }

    public static class YldAxisScale extends YldAxisBasic {
        public YldAxisScale(float x, float y) {
            setX(x);
            setY(y);
        }

        public void setImageScale(Image image) {
            setX(image.getWidth(null));
            setY(image.getHeight(null));
        }

    }

    public static class YldAxisRotation {
        private float angle = 0f;

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }

    public static class YldAxisBasic {
        private float x, y;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            YldAxisBasic that = (YldAxisBasic) o;
            return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static class Vector extends YldAxisBasic {
        public Vector(float x, float y) {
            setX(x);
            setY(y);
        }
    }


}
