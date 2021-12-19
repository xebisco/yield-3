package yield.objects;

import yield.YldGame;

public abstract class YldB extends YldLayable implements YldMethods {

    private int frames;

    public void load() {
        YldGame.getHandler().getbHashSet().add(this);
    }

    public void remove() {
        YldGame.getHandler().getbHashSet().remove(this);
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

}

