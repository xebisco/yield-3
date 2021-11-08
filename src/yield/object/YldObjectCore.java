package yield.object;

import java.util.ArrayList;
import java.util.List;

import yield.YldScript;

/**
 * NOT FINISHED
 */
public class YldObjectCore {

    private List<YldObject> objects;

    public static YldScript objectCoreScript = new YldScript() {
        public String tag() {
            return "ObjectCoreScript";
        }


    };

    public YldObjectCore() {
        objects = new ArrayList<>();
    }

    public List<YldObject> getObjects() {
        return objects;
    }

    public void setObjects(List<YldObject> objects) {
        this.objects = objects;
    }

    public static YldScript getObjectCoreScript() {
        return objectCoreScript;
    }

    public static void setObjectCoreScript(YldScript objectCoreScript) {
        YldObjectCore.objectCoreScript = objectCoreScript;
    }
    
    

}
