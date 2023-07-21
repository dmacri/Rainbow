package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * UtilityGroup entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.UtilityGroup
 * @author MyEclipse Persistence Tools
 */

public class UtilityGroupDAO implements IUtilityGroupDAO {
	// property constants
	public static final String MAX_PROCESSES = "maxProcesses";
	public static final String NAME = "name";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved UtilityGroup entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityGroupDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityGroup entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UtilityGroup entity) {
		EntityManagerHelper.log("saving UtilityGroup instance", Level.INFO,
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
	 * Delete a persistent UtilityGroup entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UtilityGroupDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityGroup entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UtilityGroup entity) {
		EntityManagerHelper.log("deleting UtilityGroup instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(UtilityGroup.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UtilityGroup entity and return it or a copy of
	 * it to the sender. A copy of the UtilityGroup entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UtilityGroupDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UtilityGroup entity to update
	 * @return UtilityGroup the persisted UtilityGroup entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UtilityGroup update(UtilityGroup entity) {
		EntityManagerHelper.log("updating UtilityGroup instance", Level.INFO,
				null);
		try {
			UtilityGroup result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UtilityGroup findById(Integer id) {
		EntityManagerHelper.log("finding UtilityGroup instance with id: " + id,
				Level.INFO, null);
		try {
			UtilityGroup instance = getEntityManager().find(UtilityGroup.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UtilityGroup entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UtilityGroup property to query
	 * @param value
	 *            the property value to match
	 * @return List<UtilityGroup> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UtilityGroup> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding UtilityGroup instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UtilityGroup model where model."
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

	public List<UtilityGroup> findByMaxProcesses(Object maxProcesses) {
		return findByProperty(MAX_PROCESSES, maxProcesses);
	}

	public List<UtilityGroup> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all UtilityGroup entities.
	 * 
	 * @return List<UtilityGroup> all UtilityGroup entities
	 */
	@SuppressWarnings("unchecked")
	public List<UtilityGroup> findAll() {
		EntityManagerHelper.log("finding all UtilityGroup instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from UtilityGroup model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}