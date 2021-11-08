package yield.test;

import java.awt.Color;

import yield.YldGScript;
import yield.core.engines.exclusive.interfaces.YldExclusiveAction;
import yield.util.YldTime;
import yield.util.input.YldMainInputs;
import yieldg.YldSprite;

public class FloatingYieldLogo {

	double x, y, speed = 1;

	int xx = 1, yx = 1, width = 400, height = 400;

	boolean controlling = false;

	YldSprite logoSprite;

	YldGScript logoScript = new YldGScript() {

		@Override
		public String tag() {
			return "logoScript";
		}

		public void start() {
			callExclusive(new YldExclusiveAction() {
				@Override
				public void onAction() {
					logoSprite = new YldSprite("/Yield Presentation.png");
				}
			});
		}

		public void tick() {

			if (YldMainInputs.horizontal != 0 || YldMainInputs.vertical != 0) {
				controlling = true;
			}

			if (!controlling) {
				x += speed * (double) xx;
				y += speed * (double) yx;
			} else {
				x += speed * YldMainInputs.horizontal;
				y += speed * YldMainInputs.vertical;
			}

			if (x < 0) {
				xx = 1;
				x += speed * YldTime.deltaTime();
			}
			if (x > yld.getYldGraphicsEngine().getWWidth() - width) {
				xx = -1;
				x -= speed * YldTime.deltaTime();
			}

			if (y < 0) {
				yx = 1;
				y += speed * YldTime.deltaTime();
			}
			if (y > yld.getYldGraphicsEngine().getWHeight() - height) {
				yx = -1;
				y -= speed * YldTime.deltaTime();
			}

		}

		public void render(java.awt.Graphics2D g) {
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, yld.getYldGraphicsEngine().getWWidth(), yld.getYldGraphicsEngine().getWHeight());
			try {
				logoSprite.draw((int) x, (int) y, width, height, g);
			} catch (Exception e) {
			}

		}

	};

}
