package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * UtilityAnf entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.UtilityAnf
 * @author MyEclipse Persistence Tools
 */

public class UtilityAnfDAO implements IUtilityAnfDAO {
	// property constants
	public static final String MAX_PROCESSES = "maxProcesses";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved UtilityAnf entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityAnfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityAnf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UtilityAnf entity) {
		EntityManagerHelper.log("saving UtilityAnf instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UtilityAnf entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityAnfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityAnf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UtilityAnf entity) {
		EntityManagerHelper.log("deleting UtilityAnf instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(UtilityAnf.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UtilityAnf entity and return it or a copy of
	 * it to the sender. A copy of the UtilityAnf entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UtilityAnfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityAnf entity to update
	 * @return UtilityAnf the persisted UtilityAnf entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UtilityAnf update(UtilityAnf entity) {
		EntityManagerHelper.log("updating UtilityAnf instance", Level.INFO,
				null);
		try {
			UtilityAnf result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UtilityAnf findById(Integer id) {
		EntityManagerHelper.log("finding UtilityAnf instance with id: " + id,
				Level.INFO, null);
		try {
			UtilityAnf instance = getEntityManager().find(UtilityAnf.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UtilityAnf entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UtilityAnf property to query
	 * @param value
	 *            the property value to match
	 * @return List<UtilityAnf> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UtilityAnf> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding UtilityAnf instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UtilityAnf model where model."
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

	public List<UtilityAnf> findByMaxProcesses(Object maxProcesses) {
		return findByProperty(MAX_PROCESSES, maxProcesses);
	}

	/**
	 * Find all UtilityAnf entities.
	 * 
	 * @return List<UtilityAnf> all UtilityAnf entities
	 */
	@SuppressWarnings("unchecked")
	public List<UtilityAnf> findAll() {
		EntityManagerHelper.log("finding all UtilityAnf instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from UtilityAnf model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}