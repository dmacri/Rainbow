package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Release entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Release
 * @author MyEclipse Persistence Tools
 */

public class ReleaseDAO implements IReleaseDAO {
	// property constants
	public static final String VERSION = "version";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Release entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ReleaseDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Release entity) {
		EntityManagerHelper.log("saving Release instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Release entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ReleaseDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Release entity) {
		EntityManagerHelper.log("deleting Release instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Release.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Release entity and return it or a copy of it
	 * to the sender. A copy of the Release entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ReleaseDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to update
	 * @return Release the persisted Release entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Release update(Release entity) {
		EntityManagerHelper.log("updating Release instance", Level.INFO, null);
		try {
			Release result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Release findById(Integer id) {
		EntityManagerHelper.log("finding Release instance with id: " + id,
				Level.INFO, null);
		try {
			Release instance = getEntityManager().find(Release.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Release entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Release property to query
	 * @param value
	 *            the property value to match
	 * @return List<Release> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Release> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Release instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Release model where model."
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

	public List<Release> findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/**
	 * Find all Release entities.
	 * 
	 * @return List<Release> all Release entities
	 */
	@SuppressWarnings("unchecked")
	public List<Release> findAll() {
		EntityManagerHelper.log("finding all Release instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Release model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}