package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoSensore entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.TipoSensore
 * @author MyEclipse Persistence Tools
 */

public class TipoSensoreDAO implements ITipoSensoreDAO {
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String UNITA_MIS = "unitaMis";
	public static final String MIN = "min";
	public static final String MAX = "max";
	public static final String NAME = "name";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TipoSensore entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoSensoreDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoSensore entity) {
		EntityManagerHelper
				.log("saving TipoSensore instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TipoSensore entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoSensoreDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoSensore entity) {
		EntityManagerHelper.log("deleting TipoSensore instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(TipoSensore.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TipoSensore entity and return it or a copy of
	 * it to the sender. A copy of the TipoSensore entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TipoSensoreDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to update
	 * @return TipoSensore the persisted TipoSensore entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoSensore update(TipoSensore entity) {
		EntityManagerHelper.log("updating TipoSensore instance", Level.INFO,
				null);
		try {
			TipoSensore result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TipoSensore findById(Integer id) {
		EntityManagerHelper.log("finding TipoSensore instance with id: " + id,
				Level.INFO, null);
		try {
			TipoSensore instance = getEntityManager().find(TipoSensore.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TipoSensore entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoSensore property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoSensore> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TipoSensore> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding TipoSensore instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TipoSensore model where model."
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

	public List<TipoSensore> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<TipoSensore> findByUnitaMis(Object unitaMis) {
		return findByProperty(UNITA_MIS, unitaMis);
	}

	public List<TipoSensore> findByMin(Object min) {
		return findByProperty(MIN, min);
	}

	public List<TipoSensore> findByMax(Object max) {
		return findByProperty(MAX, max);
	}

	public List<TipoSensore> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all TipoSensore entities.
	 * 
	 * @return List<TipoSensore> all TipoSensore entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoSensore> findAll() {
		EntityManagerHelper.log("finding all TipoSensore instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from TipoSensore model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}