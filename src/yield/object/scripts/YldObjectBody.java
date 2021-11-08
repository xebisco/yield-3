package yield.object.scripts;

import yield.YldBody;

/**
 * NOT FINISHED!
 */
public final class YldObjectBody extends YldBody {

    public static YldObjectBody mainObjBody;

    @Override
    public void awake() {
        if (mainObjBody == null) {
            mainObjBody = this;
        }
    }

}
