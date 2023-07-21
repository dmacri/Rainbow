package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cronjob entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Cronjob
 * @author MyEclipse Persistence Tools
 */

public class CronjobDAO implements ICronjobDAO {
	// property constants
	public static final String NAMEJOB = "namejob";
	public static final String NAMETRIGGER = "nametrigger";
	public static final String OUTPUT = "output";
	public static final String COMMAND = "command";
	public static final String ID_USER = "idUser";
	public static final String STATUS = "status";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Cronjob entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CronjobDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cronjob entity) {
		EntityManagerHelper.log("saving Cronjob instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cronjob entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CronjobDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cronjob entity) {
		EntityManagerHelper.log("deleting Cronjob instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Cronjob.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cronjob entity and return it or a copy of it
	 * to the sender. A copy of the Cronjob entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CronjobDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to update
	 * @return Cronjob the persisted Cronjob entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cronjob update(Cronjob entity) {
		EntityManagerHelper.log("updating Cronjob instance", Level.INFO, null);
		try {
			Cronjob result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cronjob findById(Integer id) {
		EntityManagerHelper.log("finding Cronjob instance with id: " + id,
				Level.INFO, null);
		try {
			Cronjob instance = getEntityManager().find(Cronjob.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cronjob entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cronjob property to query
	 * @param value
	 *            the property value to match
	 * @return List<Cronjob> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cronjob> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Cronjob instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cronjob model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Cronjob> findByNamejob(Object namejob) {
		return findByProperty(NAMEJOB, namejob);
	}

	public List<Cronjob> findByNametrigger(Object nametrigger) {
		return findByProperty(NAMETRIGGER, nametrigger);
	}

	public List<Cronjob> findByOutput(Object output) {
		return findByProperty(OUTPUT, output);
	}

	public List<Cronjob> findByCommand(Object command) {
		return findByProperty(COMMAND, command);
	}

	public List<Cronjob> findByIdUser(Object idUser) {
		return findByProperty(ID_USER, idUser);
	}

	public List<Cronjob> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/**
	 * Find all Cronjob entities.
	 * 
	 * @return List<Cronjob> all Cronjob entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cronjob> findAll() {
		EntityManagerHelper.log("finding all Cronjob instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Cronjob model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}