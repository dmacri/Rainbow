package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasMouseOutLabelHandlers extends HasHandlers {
	HandlerRegistration addMouseOutLabelHandler(MouseOutLabelHandler handler);
}
