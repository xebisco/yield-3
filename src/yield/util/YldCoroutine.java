package yield.util;

import java.util.HashSet;

public class YldCoroutine implements Runnable {

    private Thread thread;

    private boolean stopOnEnd = true;

    private HashSet<YldAction> actions;

    public YldCoroutine() {
        thread = new Thread(this);
        thread.setName("YldCoroutine");
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
        for(YldAction action : actions) {
            action.onAction();
        }
        if(stopOnEnd)
        stop();
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

    public HashSet<YldAction> getActions() {
        return actions;
    }

    public void setActions(HashSet<YldAction> actions) {
        this.actions = actions;
    }

}
