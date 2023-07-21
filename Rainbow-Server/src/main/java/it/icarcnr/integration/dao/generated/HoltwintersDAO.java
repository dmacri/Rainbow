package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Holtwinters entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Holtwinters
 * @author MyEclipse Persistence Tools
 */

public class HoltwintersDAO implements IHoltwintersDAO {
	// property constants
	public static final String SAMPLE_VALUE = "sampleValue";
	public static final String CLEANED_VALUE = "cleanedValue";
	public static final String ESTIMATED_VALUE = "estimatedValue";
	public static final String AT = "at";
	public static final String FT = "ft";
	public static final String ST = "st";
	public static final String CLEAN_SIGMAT = "cleanSigmat";
	public static final String LOWER_BOUND = "lowerBound";
	public static final String UPPER_BOUND = "upperBound";
	public static final String BAND_SIGMAT = "bandSigmat";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Holtwinters entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HoltwintersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Holtwinters entity) {
		EntityManagerHelper
				.log("saving Holtwinters instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Holtwinters entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HoltwintersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Holtwinters entity) {
		EntityManagerHelper.log("deleting Holtwinters instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Holtwinters.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Holtwinters entity and return it or a copy of
	 * it to the sender. A copy of the Holtwinters entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = HoltwintersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to update
	 * @return Holtwinters the persisted Holtwinters entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Holtwinters update(Holtwinters entity) {
		EntityManagerHelper.log("updating Holtwinters instance", Level.INFO,
				null);
		try {
			Holtwinters result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Holtwinters findById(HoltwintersId id) {
		EntityManagerHelper.log("finding Holtwinters instance with id: " + id,
				Level.INFO, null);
		try {
			Holtwinters instance = getEntityManager().find(Holtwinters.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Holtwinters entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Holtwinters property to query
	 * @param value
	 *            the property value to match
	 * @return List<Holtwinters> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Holtwinters> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Holtwinters instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Holtwinters model where model."
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

	public List<Holtwinters> findBySampleValue(Object sampleValue) {
		return findByProperty(SAMPLE_VALUE, sampleValue);
	}

	public List<Holtwinters> findByCleanedValue(Object cleanedValue) {
		return findByProperty(CLEANED_VALUE, cleanedValue);
	}

	public List<Holtwinters> findByEstimatedValue(Object estimatedValue) {
		return findByProperty(ESTIMATED_VALUE, estimatedValue);
	}

	public List<Holtwinters> findByAt(Object at) {
		return findByProperty(AT, at);
	}

	public List<Holtwinters> findByFt(Object ft) {
		return findByProperty(FT, ft);
	}

	public List<Holtwinters> findBySt(Object st) {
		return findByProperty(ST, st);
	}

	public List<Holtwinters> findByCleanSigmat(Object cleanSigmat) {
		return findByProperty(CLEAN_SIGMAT, cleanSigmat);
	}

	public List<Holtwinters> findByLowerBound(Object lowerBound) {
		return findByProperty(LOWER_BOUND, lowerBound);
	}

	public List<Holtwinters> findByUpperBound(Object upperBound) {
		return findByProperty(UPPER_BOUND, upperBound);
	}

	public List<Holtwinters> findByBandSigmat(Object bandSigmat) {
		return findByProperty(BAND_SIGMAT, bandSigmat);
	}

	/**
	 * Find all Holtwinters entities.
	 * 
	 * @return List<Holtwinters> all Holtwinters entities
	 */
	@SuppressWarnings("unchecked")
	public List<Holtwinters> findAll() {
		EntityManagerHelper.log("finding all Holtwinters instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Holtwinters model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}