package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.EventHandler;

public interface BeforeComputeHandler extends EventHandler {
	
	public void onBeforeCompute(BeforeComputeEvent event);

}
