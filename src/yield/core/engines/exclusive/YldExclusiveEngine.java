package yield.core.engines.exclusive;

import yield.core.engines.exclusive.interfaces.YldExclusiveAction;

/**
     * Essa engine é feita para ser usada com assets, como imagens ou audios.
     * @since yield 3.4
     */
    @Deprecated
public class YldExclusiveEngine implements Runnable {

    public static final String EXCLUSIVE_ENGINE_VERSION = "1";

    private Thread thread;

    private boolean stopOnEnd = true;

    private YldExclusiveAction action;

    public YldExclusiveEngine() {
        thread = new Thread(this);
        thread.setName("YieldExclusiveEngine");
    }
    
    /**
     * Esse método é usado para chamar o onAction no YldExclusiveAction dessa engine.
     */
    public void start() {
        thread.start();   
    }

    private void stop() {
        thread.interrupt();
    }

    @Override
    public void run() {
        action.onAction();
        if(stopOnEnd)
        stop();
    }

    public static String getExclusiveEngineVersion() {
        return EXCLUSIVE_ENGINE_VERSION;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isStopOnEnd() {
        return stopOnEnd;
    }

    public void setStopOnEnd(boolean stopOnEnd) {
        this.stopOnEnd = stopOnEnd;
    }

    public YldExclusiveAction getAction() {
        return action;
    }

    public void setAction(YldExclusiveAction action) {
        this.action = action;
    }

    

    

}
