package it.icarcnr.integration.dao.generated;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractArea entity provides the base persistence definition of the Area
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private String stringId;
	private String icon;
	private String qtip;
	private String thumbnail;
	private String parameters;
	private Set<Action> actions = new HashSet<Action>(0);

	// Constructors

	/** default constructor */
	public AbstractArea() {
	}

	/** minimal constructor */
	public AbstractArea(String name, String description, String stringId,
			String icon) {
		this.name = name;
		this.description = description;
		this.stringId = stringId;
		this.icon = icon;
	}

	/** full constructor */
	public AbstractArea(String name, String description, String stringId,
			String icon, String qtip, String thumbnail, String parameters,
			Set<Action> actions) {
		this.name = name;
		this.description = description;
		this.stringId = stringId;
		this.icon = icon;
		this.qtip = qtip;
		this.thumbnail = thumbnail;
		this.parameters = parameters;
		this.actions = actions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "string_id", nullable = false, length = 45)
	public String getStringId() {
		return this.stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	@Column(name = "icon", nullable = false)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "qtip", length = 45)
	public String getQtip() {
		return this.qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	@Column(name = "thumbnail")
	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Column(name = "parameters")
	public String getParameters() {
		return this.parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Action> getActions() {
		return this.actions;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}

}