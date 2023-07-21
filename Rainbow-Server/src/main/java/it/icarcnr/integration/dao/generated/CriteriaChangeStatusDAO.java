package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CriteriaChangeStatus entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.CriteriaChangeStatus
 * @author MyEclipse Persistence Tools
 */

public class CriteriaChangeStatusDAO implements ICriteriaChangeStatusDAO {
	// property constants
	public static final String STATUS_FROM = "statusFrom";
	public static final String STATUS_TO = "statusTo";
	public static final String VALUE_AFTER = "valueAfter";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CriteriaChangeStatus
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CriteriaChangeStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CriteriaChangeStatus entity) {
		EntityManagerHelper.log("saving CriteriaChangeStatus instance",
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
	 * Delete a persistent CriteriaChangeStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CriteriaChangeStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CriteriaChangeStatus entity) {
		EntityManagerHelper.log("deleting CriteriaChangeStatus instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					CriteriaChangeStatus.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CriteriaChangeStatus entity and return it or a
	 * copy of it to the sender. A copy of the CriteriaChangeStatus entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CriteriaChangeStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to update
	 * @return CriteriaChangeStatus the persisted CriteriaChangeStatus entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CriteriaChangeStatus update(CriteriaChangeStatus entity) {
		EntityManagerHelper.log("updating CriteriaChangeStatus instance",
				Level.INFO, null);
		try {
			CriteriaChangeStatus result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CriteriaChangeStatus findById(Integer id) {
		EntityManagerHelper.log(
				"finding CriteriaChangeStatus instance with id: " + id,
				Level.INFO, null);
		try {
			CriteriaChangeStatus instance = getEntityManager().find(
					CriteriaChangeStatus.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CriteriaChangeStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CriteriaChangeStatus property to query
	 * @param value
	 *            the property value to match
	 * @return List<CriteriaChangeStatus> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CriteriaChangeStatus> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding CriteriaChangeStatus instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CriteriaChangeStatus model where model."
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

	public List<CriteriaChangeStatus> findByStatusFrom(Object statusFrom) {
		return findByProperty(STATUS_FROM, statusFrom);
	}

	public List<CriteriaChangeStatus> findByStatusTo(Object statusTo) {
		return findByProperty(STATUS_TO, statusTo);
	}

	public List<CriteriaChangeStatus> findByValueAfter(Object valueAfter) {
		return findByProperty(VALUE_AFTER, valueAfter);
	}

	/**
	 * Find all CriteriaChangeStatus entities.
	 * 
	 * @return List<CriteriaChangeStatus> all CriteriaChangeStatus entities
	 */
	@SuppressWarnings("unchecked")
	public List<CriteriaChangeStatus> findAll() {
		EntityManagerHelper.log("finding all CriteriaChangeStatus instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CriteriaChangeStatus model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}