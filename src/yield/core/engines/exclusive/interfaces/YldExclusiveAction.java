package yield.core.engines.exclusive.interfaces;

/**
 * Essa interface é feita para ser usada com uma YldExclusiveEngine.
 */
public abstract interface YldExclusiveAction {

    /**
     * Esse método é chamado pela Exclusive engine quando ela é iniciada.
     */
    public void onAction();

}
