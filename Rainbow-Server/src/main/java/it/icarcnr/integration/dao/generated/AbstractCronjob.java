package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractCronjob entity provides the base persistence definition of the
 * Cronjob entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCronjob implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cronjob cronjob;
	private Utility utility;
	private String namejob;
	private String nametrigger;
	private Timestamp date;
	private String output;
	private String command;
	private Integer idUser;
	private Timestamp endExecution;
	private String status;
	private Set<Cronjob> cronjobs = new HashSet<Cronjob>(0);

	// Constructors

	/** default constructor */
	public AbstractCronjob() {
	}

	/** minimal constructor */
	public AbstractCronjob(Utility utility, String namejob, String nametrigger,
			Timestamp date, String output, String command, Integer idUser,
			String status) {
		this.utility = utility;
		this.namejob = namejob;
		this.nametrigger = nametrigger;
		this.date = date;
		this.output = output;
		this.command = command;
		this.idUser = idUser;
		this.status = status;
	}

	/** full constructor */
	public AbstractCronjob(Cronjob cronjob, Utility utility, String namejob,
			String nametrigger, Timestamp date, String output, String command,
			Integer idUser, Timestamp endExecution, String status,
			Set<Cronjob> cronjobs) {
		this.cronjob = cronjob;
		this.utility = utility;
		this.namejob = namejob;
		this.nametrigger = nametrigger;
		this.date = date;
		this.output = output;
		this.command = command;
		this.idUser = idUser;
		this.endExecution = endExecution;
		this.status = status;
		this.cronjobs = cronjobs;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Cronjob")
	public Cronjob getCronjob() {
		return this.cronjob;
	}

	public void setCronjob(Cronjob cronjob) {
		this.cronjob = cronjob;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Utility", nullable = false)
	public Utility getUtility() {
		return this.utility;
	}

	public void setUtility(Utility utility) {
		this.utility = utility;
	}

	@Column(name = "namejob", unique = true, nullable = false, length = 45)
	public String getNamejob() {
		return this.namejob;
	}

	public void setNamejob(String namejob) {
		this.namejob = namejob;
	}

	@Column(name = "nametrigger", nullable = false, length = 45)
	public String getNametrigger() {
		return this.nametrigger;
	}

	public void setNametrigger(String nametrigger) {
		this.nametrigger = nametrigger;
	}

	@Column(name = "date", nullable = false, length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Column(name = "output", nullable = false)
	public String getOutput() {
		return this.output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Column(name = "command", nullable = false)
	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Column(name = "ID_User", nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Column(name = "end_execution", length = 19)
	public Timestamp getEndExecution() {
		return this.endExecution;
	}

	public void setEndExecution(Timestamp endExecution) {
		this.endExecution = endExecution;
	}

	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cronjob")
	public Set<Cronjob> getCronjobs() {
		return this.cronjobs;
	}

	public void setCronjobs(Set<Cronjob> cronjobs) {
		this.cronjobs = cronjobs;
	}

}