package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cronjob entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cronjob", catalog = "rainbow")
public class Cronjob extends AbstractCronjob implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Cronjob() {
	}

	/** minimal constructor */
	public Cronjob(Utility utility, String namejob, String nametrigger,
			Timestamp date, String output, String command, Integer idUser,
			String status) {
		super(utility, namejob, nametrigger, date, output, command, idUser,
				status);
	}

	/** full constructor */
	public Cronjob(Cronjob cronjob, Utility utility, String namejob,
			String nametrigger, Timestamp date, String output, String command,
			Integer idUser, Timestamp endExecution, String status,
			Set<Cronjob> cronjobs) {
		super(cronjob, utility, namejob, nametrigger, date, output, command,
				idUser, endExecution, status, cronjobs);
	}

}
