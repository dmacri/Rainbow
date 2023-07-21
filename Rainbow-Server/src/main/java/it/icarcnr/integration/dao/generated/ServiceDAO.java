package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Service entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Service
 * @author MyEclipse Persistence Tools
 */

public class ServiceDAO implements IServiceDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String FILE = "file";
	public static final String VALUE = "value";
	public static final String SAMPLING_PERIOD = "samplingPeriod";
	public static final String LOG = "log";
	public static final String ID_UTILITY = "idUtility";
	public static final String START_MINUTE = "startMinute";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Service entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServiceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Service entity) {
		EntityManagerHelper.log("saving Service instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Service entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServiceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Service entity) {
		EntityManagerHelper.log("deleting Service instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Service.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Service entity and return it or a copy of it
	 * to the sender. A copy of the Service entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ServiceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to update
	 * @return Service the persisted Service entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Service update(Service entity) {
		EntityManagerHelper.log("updating Service instance", Level.INFO, null);
		try {
			Service result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Service findById(Integer id) {
		EntityManagerHelper.log("finding Service instance with id: " + id,
				Level.INFO, null);
		try {
			Service instance = getEntityManager().find(Service.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Service entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Service property to query
	 * @param value
	 *            the property value to match
	 * @return List<Service> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Service> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Service instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Service model where model."
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

	public List<Service> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Service> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Service> findByFile(Object file) {
		return findByProperty(FILE, file);
	}

	public List<Service> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List<Service> findBySamplingPeriod(Object samplingPeriod) {
		return findByProperty(SAMPLING_PERIOD, samplingPeriod);
	}

	public List<Service> findByLog(Object log) {
		return findByProperty(LOG, log);
	}

	public List<Service> findByIdUtility(Object idUtility) {
		return findByProperty(ID_UTILITY, idUtility);
	}

	public List<Service> findByStartMinute(Object startMinute) {
		return findByProperty(START_MINUTE, startMinute);
	}

	/**
	 * Find all Service entities.
	 * 
	 * @return List<Service> all Service entities
	 */
	@SuppressWarnings("unchecked")
	public List<Service> findAll() {
		EntityManagerHelper.log("finding all Service instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Service model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}