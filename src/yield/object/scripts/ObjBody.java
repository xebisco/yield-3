package yield.object.scripts;

import yield.YldBody;

/**
 * NOT FINISHED!
 */
public final class ObjBody extends YldBody {

    public static ObjBody mainObjBody;

    @Override
    public void awake() {
        if (ObjRoom.mainObjRoom == null) {
            ObjRoom.mainObjRoom = new ObjRoom();
        }
        if (mainObjBody == null) {
            mainObjBody = this;
        }
    }

}
