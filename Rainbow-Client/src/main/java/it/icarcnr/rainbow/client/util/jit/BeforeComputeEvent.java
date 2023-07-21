package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.GwtEvent;

public class BeforeComputeEvent extends GwtEvent<BeforeComputeHandler> {

	public static Type getType() {
		return TYPE;
	}

	public static void fire(HasBeforeComputeHandlers source) {
		BeforeComputeEvent event = new BeforeComputeEvent();
		source.fireEvent(event);
	}

	@Override
	protected void dispatch(BeforeComputeHandler handler) {
		handler.onBeforeCompute(this);
	}

	@Override
	public final Type getAssociatedType() {
		return TYPE;
	}

	private static Type<BeforeComputeHandler> TYPE = new Type<BeforeComputeHandler>();

}
