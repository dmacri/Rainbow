package it.icarcnr.integration.dao.generated;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Criteria entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Criteria
 * @author MyEclipse Persistence Tools
 */

public class CriteriaDAO implements ICriteriaDAO {
	// property constants
	public static final String VALUE = "value";
	public static final String MONDAY = "monday";
	public static final String TUESDAY = "tuesday";
	public static final String WEDNESDAY = "wednesday";
	public static final String THURSDAY = "thursday";
	public static final String FRIDAY = "friday";
	public static final String SATURDAY = "saturday";
	public static final String SUNDAY = "sunday";
	public static final String VALUE_CHECK_MAJOR = "valueCheckMajor";
	public static final String DESCRIPTION = "description";
	public static final String STATUS = "status";
	public static final String TYPE_CHECK = "typeCheck";
	public static final String VALUE_CHECK_CRITICAL = "valueCheckCritical";
	public static final String ENABLED = "enabled";
	public static final String VALUE_CHECK_SECONDARY_MAJOR = "valueCheckSecondaryMajor";
	public static final String VALUE_CHECK_SECONDARY_CRITICAL = "valueCheckSecondaryCritical";
	public static final String SUSPENDED = "suspended";
	public static final String CALCULATION = "calculation";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Criteria entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CriteriaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Criteria entity) {
		EntityManagerHelper.log("saving Criteria instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Criteria entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CriteriaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Criteria entity) {
		EntityManagerHelper.log("deleting Criteria instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Criteria.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Criteria entity and return it or a copy of it
	 * to the sender. A copy of the Criteria entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CriteriaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to update
	 * @return Criteria the persisted Criteria entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Criteria update(Criteria entity) {
		EntityManagerHelper.log("updating Criteria instance", Level.INFO, null);
		try {
			Criteria result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Criteria findById(Integer id) {
		EntityManagerHelper.log("finding Criteria instance with id: " + id,
				Level.INFO, null);
		try {
			Criteria instance = getEntityManager().find(Criteria.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Criteria entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Criteria property to query
	 * @param value
	 *            the property value to match
	 * @return List<Criteria> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Criteria> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Criteria instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Criteria model where model."
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

	public List<Criteria> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List<Criteria> findByMonday(Object monday) {
		return findByProperty(MONDAY, monday);
	}

	public List<Criteria> findByTuesday(Object tuesday) {
		return findByProperty(TUESDAY, tuesday);
	}

	public List<Criteria> findByWednesday(Object wednesday) {
		return findByProperty(WEDNESDAY, wednesday);
	}

	public List<Criteria> findByThursday(Object thursday) {
		return findByProperty(THURSDAY, thursday);
	}

	public List<Criteria> findByFriday(Object friday) {
		return findByProperty(FRIDAY, friday);
	}

	public List<Criteria> findBySaturday(Object saturday) {
		return findByProperty(SATURDAY, saturday);
	}

	public List<Criteria> findBySunday(Object sunday) {
		return findByProperty(SUNDAY, sunday);
	}

	public List<Criteria> findByValueCheckMajor(Object valueCheckMajor) {
		return findByProperty(VALUE_CHECK_MAJOR, valueCheckMajor);
	}

	public List<Criteria> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Criteria> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Criteria> findByTypeCheck(Object typeCheck) {
		return findByProperty(TYPE_CHECK, typeCheck);
	}

	public List<Criteria> findByValueCheckCritical(Object valueCheckCritical) {
		return findByProperty(VALUE_CHECK_CRITICAL, valueCheckCritical);
	}

	public List<Criteria> findByEnabled(Object enabled) {
		return findByProperty(ENABLED, enabled);
	}

	public List<Criteria> findByValueCheckSecondaryMajor(
			Object valueCheckSecondaryMajor) {
		return findByProperty(VALUE_CHECK_SECONDARY_MAJOR,
				valueCheckSecondaryMajor);
	}

	public List<Criteria> findByValueCheckSecondaryCritical(
			Object valueCheckSecondaryCritical) {
		return findByProperty(VALUE_CHECK_SECONDARY_CRITICAL,
				valueCheckSecondaryCritical);
	}

	public List<Criteria> findBySuspended(Object suspended) {
		return findByProperty(SUSPENDED, suspended);
	}

	public List<Criteria> findByCalculation(Object calculation) {
		return findByProperty(CALCULATION, calculation);
	}

	/**
	 * Find all Criteria entities.
	 * 
	 * @return List<Criteria> all Criteria entities
	 */
	@SuppressWarnings("unchecked")
	public List<Criteria> findAll() {
		EntityManagerHelper.log("finding all Criteria instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Criteria model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}