package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * SensoreSoglie entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.SensoreSoglie
 * @author MyEclipse Persistence Tools
 */

public class SensoreSoglieDAO implements ISensoreSoglieDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SensoreSoglie entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SensoreSoglieDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SensoreSoglie entity) {
		EntityManagerHelper.log("saving SensoreSoglie instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SensoreSoglie entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SensoreSoglieDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SensoreSoglie entity) {
		EntityManagerHelper.log("deleting SensoreSoglie instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(SensoreSoglie.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SensoreSoglie entity and return it or a copy
	 * of it to the sender. A copy of the SensoreSoglie entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SensoreSoglieDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to update
	 * @return SensoreSoglie the persisted SensoreSoglie entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SensoreSoglie update(SensoreSoglie entity) {
		EntityManagerHelper.log("updating SensoreSoglie instance", Level.INFO,
				null);
		try {
			SensoreSoglie result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SensoreSoglie findById(Integer id) {
		EntityManagerHelper.log(
				"finding SensoreSoglie instance with id: " + id, Level.INFO,
				null);
		try {
			SensoreSoglie instance = getEntityManager().find(
					SensoreSoglie.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SensoreSoglie entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SensoreSoglie property to query
	 * @param value
	 *            the property value to match
	 * @return List<SensoreSoglie> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SensoreSoglie> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding SensoreSoglie instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from SensoreSoglie model where model."
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

	/**
	 * Find all SensoreSoglie entities.
	 * 
	 * @return List<SensoreSoglie> all SensoreSoglie entities
	 */
	@SuppressWarnings("unchecked")
	public List<SensoreSoglie> findAll() {
		EntityManagerHelper.log("finding all SensoreSoglie instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from SensoreSoglie model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}