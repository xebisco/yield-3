package yield.core.loop;

import yield.core.ThreadType;

public class YldLoop extends ThreadType {

    private YldLoopable loopable;
    private boolean stop;
    private double fps = 60;

    @Override
    protected String threadName() {
        return "Yield(Loop)";
    }

    public YldLoop(YldLoopable loopable) {
        super();
        this.loopable = loopable;
    }

    @Override
    public void start() {
        loopable.tickStart();

        long initialTime = System.nanoTime();
        double deltaF = 0;

        while (!stop) {
            final double timeF = 1000000000 / fps;
            long currentTime = System.nanoTime();
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaF >= 1) {
                loopable.tick();
                deltaF--;
            }
        }
    }

    @Override
    public void end() {
        loopable.tickEnd();
    }

    public YldLoopable getLoopable() {
        return loopable;
    }

    public void setLoopable(YldLoopable loopable) {
        this.loopable = loopable;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }
}
