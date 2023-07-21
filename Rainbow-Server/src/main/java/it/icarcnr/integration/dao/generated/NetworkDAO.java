package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Network entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Network
 * @author MyEclipse Persistence Tools
 */

public class NetworkDAO implements INetworkDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Network entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NetworkDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Network entity) {
		EntityManagerHelper.log("saving Network instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Network entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NetworkDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Network entity) {
		EntityManagerHelper.log("deleting Network instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Network.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Network entity and return it or a copy of it
	 * to the sender. A copy of the Network entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NetworkDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to update
	 * @return Network the persisted Network entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Network update(Network entity) {
		EntityManagerHelper.log("updating Network instance", Level.INFO, null);
		try {
			Network result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Network findById(Integer id) {
		EntityManagerHelper.log("finding Network instance with id: " + id,
				Level.INFO, null);
		try {
			Network instance = getEntityManager().find(Network.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Network entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Network property to query
	 * @param value
	 *            the property value to match
	 * @return List<Network> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Network> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Network instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Network model where model."
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

	public List<Network> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Network> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/**
	 * Find all Network entities.
	 * 
	 * @return List<Network> all Network entities
	 */
	@SuppressWarnings("unchecked")
	public List<Network> findAll() {
		EntityManagerHelper.log("finding all Network instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Network model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}