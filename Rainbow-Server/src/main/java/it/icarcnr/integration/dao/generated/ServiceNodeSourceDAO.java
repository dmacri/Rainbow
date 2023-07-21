package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceNodeSource entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.ServiceNodeSource
 * @author MyEclipse Persistence Tools
 */

public class ServiceNodeSourceDAO implements IServiceNodeSourceDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ServiceNodeSource entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServiceNodeSourceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceNodeSource entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ServiceNodeSource entity) {
		EntityManagerHelper.log("saving ServiceNodeSource instance",
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
	 * Delete a persistent ServiceNodeSource entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ServiceNodeSourceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceNodeSource entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ServiceNodeSource entity) {
		EntityManagerHelper.log("deleting ServiceNodeSource instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ServiceNodeSource.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ServiceNodeSource entity and return it or a
	 * copy of it to the sender. A copy of the ServiceNodeSource entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ServiceNodeSourceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceNodeSource entity to update
	 * @return ServiceNodeSource the persisted ServiceNodeSource entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ServiceNodeSource update(ServiceNodeSource entity) {
		EntityManagerHelper.log("updating ServiceNodeSource instance",
				Level.INFO, null);
		try {
			ServiceNodeSource result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ServiceNodeSource findById(Integer id) {
		EntityManagerHelper.log("finding ServiceNodeSource instance with id: "
				+ id, Level.INFO, null);
		try {
			ServiceNodeSource instance = getEntityManager().find(
					ServiceNodeSource.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ServiceNodeSource entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ServiceNodeSource property to query
	 * @param value
	 *            the property value to match
	 * @return List<ServiceNodeSource> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceNodeSource> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding ServiceNodeSource instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ServiceNodeSource model where model."
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
	 * Find all ServiceNodeSource entities.
	 * 
	 * @return List<ServiceNodeSource> all ServiceNodeSource entities
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceNodeSource> findAll() {
		EntityManagerHelper.log("finding all ServiceNodeSource instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from ServiceNodeSource model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}