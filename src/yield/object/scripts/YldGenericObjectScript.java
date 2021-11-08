package yield.object.scripts;

import java.util.HashSet;

import yield.YldScript;
import yield.object.YldCode;

/**
 * NOT FINISHED!
 */
public class YldGenericObjectScript extends YldScript {

    private HashSet<YldCode> codes;

    @Override
    public String tag() {
        return "GenericObjectScript";
    }

    @Override
    public void awake() {
        codes = new HashSet<>();
    }

    public HashSet<YldCode> getCodes() {
        return codes;
    }

    public void setCodes(HashSet<YldCode> codes) {
        this.codes = codes;
    }

}
