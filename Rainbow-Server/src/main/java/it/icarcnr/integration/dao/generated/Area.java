package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area", catalog = "rainbow")
public class Area extends AbstractArea implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String name, String description, String stringId, String icon) {
		super(name, description, stringId, icon);
	}

	/** full constructor */
	public Area(String name, String description, String stringId, String icon,
			String qtip, String thumbnail, String parameters,
			Set<Action> actions) {
		super(name, description, stringId, icon, qtip, thumbnail, parameters,
				actions);
	}

}
