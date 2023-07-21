package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Q2fPerGrafici entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Q2fPerGrafici
 * @author MyEclipse Persistence Tools
 */

public class Q2fPerGraficiDAO implements IQ2fPerGraficiDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Q2fPerGrafici entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * Q2fPerGraficiDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Q2fPerGrafici entity) {
		EntityManagerHelper.log("saving Q2fPerGrafici instance", Level.INFO,
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
	 * Delete a persistent Q2fPerGrafici entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * Q2fPerGraficiDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Q2fPerGrafici entity) {
		EntityManagerHelper.log("deleting Q2fPerGrafici instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Q2fPerGrafici.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Q2fPerGrafici entity and return it or a copy
	 * of it to the sender. A copy of the Q2fPerGrafici entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = Q2fPerGraficiDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to update
	 * @return Q2fPerGrafici the persisted Q2fPerGrafici entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Q2fPerGrafici update(Q2fPerGrafici entity) {
		EntityManagerHelper.log("updating Q2fPerGrafici instance", Level.INFO,
				null);
		try {
			Q2fPerGrafici result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Q2fPerGrafici findById(Q2fPerGraficiId id) {
		EntityManagerHelper.log(
				"finding Q2fPerGrafici instance with id: " + id, Level.INFO,
				null);
		try {
			Q2fPerGrafici instance = getEntityManager().find(
					Q2fPerGrafici.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Q2fPerGrafici entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Q2fPerGrafici property to query
	 * @param value
	 *            the property value to match
	 * @return List<Q2fPerGrafici> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Q2fPerGrafici> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding Q2fPerGrafici instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Q2fPerGrafici model where model."
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
	 * Find all Q2fPerGrafici entities.
	 * 
	 * @return List<Q2fPerGrafici> all Q2fPerGrafici entities
	 */
	@SuppressWarnings("unchecked")
	public List<Q2fPerGrafici> findAll() {
		EntityManagerHelper.log("finding all Q2fPerGrafici instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Q2fPerGrafici model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}