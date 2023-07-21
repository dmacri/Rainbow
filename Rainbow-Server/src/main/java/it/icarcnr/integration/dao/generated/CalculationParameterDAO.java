package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CalculationParameter entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.CalculationParameter
 * @author MyEclipse Persistence Tools
 */

public class CalculationParameterDAO implements ICalculationParameterDAO {
	// property constants
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String TYPE = "type";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CalculationParameter
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CalculationParameterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CalculationParameter entity) {
		EntityManagerHelper.log("saving CalculationParameter instance",
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
	 * Delete a persistent CalculationParameter entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CalculationParameterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CalculationParameter entity) {
		EntityManagerHelper.log("deleting CalculationParameter instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					CalculationParameter.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CalculationParameter entity and return it or a
	 * copy of it to the sender. A copy of the CalculationParameter entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CalculationParameterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to update
	 * @return CalculationParameter the persisted CalculationParameter entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CalculationParameter update(CalculationParameter entity) {
		EntityManagerHelper.log("updating CalculationParameter instance",
				Level.INFO, null);
		try {
			CalculationParameter result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CalculationParameter findById(Integer id) {
		EntityManagerHelper.log(
				"finding CalculationParameter instance with id: " + id,
				Level.INFO, null);
		try {
			CalculationParameter instance = getEntityManager().find(
					CalculationParameter.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CalculationParameter entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CalculationParameter property to query
	 * @param value
	 *            the property value to match
	 * @return List<CalculationParameter> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CalculationParameter> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding CalculationParameter instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CalculationParameter model where model."
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

	public List<CalculationParameter> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<CalculationParameter> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List<CalculationParameter> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	/**
	 * Find all CalculationParameter entities.
	 * 
	 * @return List<CalculationParameter> all CalculationParameter entities
	 */
	@SuppressWarnings("unchecked")
	public List<CalculationParameter> findAll() {
		EntityManagerHelper.log("finding all CalculationParameter instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CalculationParameter model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}