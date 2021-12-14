package yield.core;

public abstract class ThreadType implements Runnable {

    private boolean interruptOnEnd = true;
    private Thread thread;

    protected String threadName() {
        return "Yield(ThreadType)";
    }

    public ThreadType() {
        thread = new Thread(this);
        thread.setName(threadName());
    }

    public void startThread() {
        thread.start();
    }

    @Override
    public final void run() {
        start();
        end();
        if(interruptOnEnd)
        thread.interrupt();
    }

    public abstract void start();

    public void end() {
        
    }

    public boolean isInterruptOnEnd() {
        return interruptOnEnd;
    }

    public void setInterruptOnEnd(boolean interruptOnEnd) {
        this.interruptOnEnd = interruptOnEnd;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
