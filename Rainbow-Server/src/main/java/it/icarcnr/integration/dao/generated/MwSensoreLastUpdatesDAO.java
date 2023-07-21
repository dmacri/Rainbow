package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * MwSensoreLastUpdates entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.MwSensoreLastUpdates
 * @author MyEclipse Persistence Tools
 */

public class MwSensoreLastUpdatesDAO implements IMwSensoreLastUpdatesDAO {
	// property constants
	public static final String LAST_VALUE = "lastValue";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved MwSensoreLastUpdates
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MwSensoreLastUpdatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MwSensoreLastUpdates entity) {
		EntityManagerHelper.log("saving MwSensoreLastUpdates instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent MwSensoreLastUpdates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MwSensoreLastUpdatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MwSensoreLastUpdates entity) {
		EntityManagerHelper.log("deleting MwSensoreLastUpdates instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					MwSensoreLastUpdates.class, entity.getSensoreId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved MwSensoreLastUpdates entity and return it or a
	 * copy of it to the sender. A copy of the MwSensoreLastUpdates entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MwSensoreLastUpdatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to update
	 * @return MwSensoreLastUpdates the persisted MwSensoreLastUpdates entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MwSensoreLastUpdates update(MwSensoreLastUpdates entity) {
		EntityManagerHelper.log("updating MwSensoreLastUpdates instance",
				Level.INFO, null);
		try {
			MwSensoreLastUpdates result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public MwSensoreLastUpdates findById(Integer id) {
		EntityManagerHelper.log(
				"finding MwSensoreLastUpdates instance with id: " + id,
				Level.INFO, null);
		try {
			MwSensoreLastUpdates instance = getEntityManager().find(
					MwSensoreLastUpdates.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all MwSensoreLastUpdates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MwSensoreLastUpdates property to query
	 * @param value
	 *            the property value to match
	 * @return List<MwSensoreLastUpdates> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<MwSensoreLastUpdates> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding MwSensoreLastUpdates instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from MwSensoreLastUpdates model where model."
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

	public List<MwSensoreLastUpdates> findByLastValue(Object lastValue) {
		return findByProperty(LAST_VALUE, lastValue);
	}

	/**
	 * Find all MwSensoreLastUpdates entities.
	 * 
	 * @return List<MwSensoreLastUpdates> all MwSensoreLastUpdates entities
	 */
	@SuppressWarnings("unchecked")
	public List<MwSensoreLastUpdates> findAll() {
		EntityManagerHelper.log("finding all MwSensoreLastUpdates instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from MwSensoreLastUpdates model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}