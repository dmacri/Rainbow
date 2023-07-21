package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Q1fPerMappa entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Q1fPerMappa
 * @author MyEclipse Persistence Tools
 */

public class Q1fPerMappaDAO implements IQ1fPerMappaDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Q1fPerMappa entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * Q1fPerMappaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Q1fPerMappa entity) {
		EntityManagerHelper
				.log("saving Q1fPerMappa instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Q1fPerMappa entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * Q1fPerMappaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Q1fPerMappa entity) {
		EntityManagerHelper.log("deleting Q1fPerMappa instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Q1fPerMappa.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Q1fPerMappa entity and return it or a copy of
	 * it to the sender. A copy of the Q1fPerMappa entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = Q1fPerMappaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to update
	 * @return Q1fPerMappa the persisted Q1fPerMappa entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Q1fPerMappa update(Q1fPerMappa entity) {
		EntityManagerHelper.log("updating Q1fPerMappa instance", Level.INFO,
				null);
		try {
			Q1fPerMappa result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Q1fPerMappa findById(Q1fPerMappaId id) {
		EntityManagerHelper.log("finding Q1fPerMappa instance with id: " + id,
				Level.INFO, null);
		try {
			Q1fPerMappa instance = getEntityManager().find(Q1fPerMappa.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Q1fPerMappa entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Q1fPerMappa property to query
	 * @param value
	 *            the property value to match
	 * @return List<Q1fPerMappa> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Q1fPerMappa> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Q1fPerMappa instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Q1fPerMappa model where model."
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
	 * Find all Q1fPerMappa entities.
	 * 
	 * @return List<Q1fPerMappa> all Q1fPerMappa entities
	 */
	@SuppressWarnings("unchecked")
	public List<Q1fPerMappa> findAll() {
		EntityManagerHelper.log("finding all Q1fPerMappa instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Q1fPerMappa model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}