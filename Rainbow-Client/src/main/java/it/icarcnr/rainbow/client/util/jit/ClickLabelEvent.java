package it.icarcnr.rainbow.client.util.jit;

import com.google.gwt.event.shared.GwtEvent;

public class ClickLabelEvent extends GwtEvent<ClickLabelHandler> {

	protected ClickLabelEvent(boolean rightclick) {
		this.rightclick = rightclick;
	}

	public static Type getType() {
		return TYPE;
	}

	public static void fire(HasClickLabelHandlers source, boolean rightclick) {
		ClickLabelEvent event = new ClickLabelEvent(rightclick);
		source.fireEvent(event);
	}

	@Override
	protected void dispatch(ClickLabelHandler handler) {
		if (rightclick) {
			handler.onRightClickLabel(this);
		} else {
			handler.onClickLabel(this);
		}
	}

	@Override
	public final Type getAssociatedType() {
		return TYPE;
	}

	private final boolean rightclick;
	private static Type<ClickLabelHandler> TYPE = new Type<ClickLabelHandler>();

}
