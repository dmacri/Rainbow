package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Utility entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Utility
 * @author MyEclipse Persistence Tools
 */

public class UtilityDAO implements IUtilityDAO {
	// property constants
	public static final String SCRIPT = "script";
	public static final String DESCRIPTION = "description";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Utility entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Utility entity) {
		EntityManagerHelper.log("saving Utility instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Utility entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Utility entity) {
		EntityManagerHelper.log("deleting Utility instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Utility.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Utility entity and return it or a copy of it
	 * to the sender. A copy of the Utility entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UtilityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to update
	 * @return Utility the persisted Utility entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Utility update(Utility entity) {
		EntityManagerHelper.log("updating Utility instance", Level.INFO, null);
		try {
			Utility result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Utility findById(Integer id) {
		EntityManagerHelper.log("finding Utility instance with id: " + id,
				Level.INFO, null);
		try {
			Utility instance = getEntityManager().find(Utility.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Utility entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Utility property to query
	 * @param value
	 *            the property value to match
	 * @return List<Utility> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Utility> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Utility instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Utility model where model."
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

	public List<Utility> findByScript(Object script) {
		return findByProperty(SCRIPT, script);
	}

	public List<Utility> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/**
	 * Find all Utility entities.
	 * 
	 * @return List<Utility> all Utility entities
	 */
	@SuppressWarnings("unchecked")
	public List<Utility> findAll() {
		EntityManagerHelper.log("finding all Utility instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Utility model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}