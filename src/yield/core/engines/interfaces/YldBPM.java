package yield.core.engines.interfaces;

/**
 * Essa interface é feita para ser usada com uma YldBPMEngine.
 */
public interface YldBPM {
	
	/**
     * Esse método é chamado pela BPM engine a cada passo.
     */
	public void tickBPM();

}
