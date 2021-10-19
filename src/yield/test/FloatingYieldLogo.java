package yield.test;

import java.awt.Color;

import yield.YldGScript;
import yield.util.YldTime;
import yield.util.input.YldInput;
import yield.util.input.YldMainInputs;
import yieldg.YldSprite;

public class FloatingYieldLogo {

	double x, y, speed = 1;

	int xx = 1, yx = 1;

	boolean controlling = false;

	YldSprite logoSprite = new YldSprite("/YieldPresentation.png");

	YldGScript logoScript = new YldGScript() {

		@Override
		public String tag() {
			return "logoScript";
		}

		public void tick() {
			
			if (YldInput.getKeysSet().size() > 0) {
				controlling = true;
			}

			if (!controlling) {
				x += speed * YldTime.deltaTime() * xx;
				y += speed * YldTime.deltaTime() * yx;
			} else {
				x += speed * YldTime.deltaTime() * YldMainInputs.horizontal;
				y += speed * YldTime.deltaTime() * YldMainInputs.vertical;
			}

			if (x < 0) {
				xx = 1;
				x += speed * YldTime.deltaTime();
			}
			if (x > yld.getYldGraphicsEngine().getWWidth() - logoSprite.width) {
				xx = -1;
				x -= speed * YldTime.deltaTime();
			}

			if (y < 0) {
				yx = 1;
				y += speed * YldTime.deltaTime();
			}
			if (y > yld.getYldGraphicsEngine().getWHeight() - logoSprite.height) {
				yx = -1;
				y -= speed * YldTime.deltaTime();
			}

		}

		public void render(java.awt.Graphics2D g) {

			g.setColor(new Color(10, 0, 30));
			g.fillRect(0, 0, yld.getYldGraphicsEngine().getWWidth(), yld.getYldGraphicsEngine().getWHeight());
			g.drawImage(logoSprite.getBufferedImage(), (int) x, (int) y, null);

		}

	};

}
