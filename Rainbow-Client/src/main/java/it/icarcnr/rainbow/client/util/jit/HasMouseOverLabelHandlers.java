package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasMouseOverLabelHandlers extends HasHandlers {
	HandlerRegistration addMouseOverLabelHandler(MouseOverLabelHandler handler);
}
