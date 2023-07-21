package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Action entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "action", catalog = "rainbow")
public class Action extends AbstractAction implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Action() {
	}

	/** minimal constructor */
	public Action(Area area, String path, String description) {
		super(area, path, description);
	}

	/** full constructor */
	public Action(Area area, Action action, String path, String description,
			Integer idUtilityGroup,
			Set<ActionNetworkFunction> actionNetworkFunctions,
			Set<Action> actions) {
		super(area, action, path, description, idUtilityGroup,
				actionNetworkFunctions, actions);
	}

}
