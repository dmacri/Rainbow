package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.EventHandler;

public interface ClickLabelHandler extends EventHandler {
	
	public void onClickLabel(ClickLabelEvent event);
	
	public void onRightClickLabel(ClickLabelEvent event);

}
