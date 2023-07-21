package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Request entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Request
 * @author MyEclipse Persistence Tools
 */

public class RequestDAO implements IRequestDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String DESCRIPTION_EXTENDED = "descriptionExtended";
	public static final String PERCENTAGE_GAP_MAJOR = "percentageGapMajor";
	public static final String PERCENTAGE_GAP_CRITICAL = "percentageGapCritical";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Request entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Request entity) {
		EntityManagerHelper.log("saving Request instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Request entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Request entity) {
		EntityManagerHelper.log("deleting Request instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Request.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Request entity and return it or a copy of it
	 * to the sender. A copy of the Request entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RequestDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to update
	 * @return Request the persisted Request entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Request update(Request entity) {
		EntityManagerHelper.log("updating Request instance", Level.INFO, null);
		try {
			Request result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Request findById(Integer id) {
		EntityManagerHelper.log("finding Request instance with id: " + id,
				Level.INFO, null);
		try {
			Request instance = getEntityManager().find(Request.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Request entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Request property to query
	 * @param value
	 *            the property value to match
	 * @return List<Request> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Request> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Request instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Request model where model."
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

	public List<Request> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Request> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Request> findByDescriptionExtended(Object descriptionExtended) {
		return findByProperty(DESCRIPTION_EXTENDED, descriptionExtended);
	}

	public List<Request> findByPercentageGapMajor(Object percentageGapMajor) {
		return findByProperty(PERCENTAGE_GAP_MAJOR, percentageGapMajor);
	}

	public List<Request> findByPercentageGapCritical(
			Object percentageGapCritical) {
		return findByProperty(PERCENTAGE_GAP_CRITICAL, percentageGapCritical);
	}

	/**
	 * Find all Request entities.
	 * 
	 * @return List<Request> all Request entities
	 */
	@SuppressWarnings("unchecked")
	public List<Request> findAll() {
		EntityManagerHelper.log("finding all Request instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Request model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}