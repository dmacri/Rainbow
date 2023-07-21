package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoSoglie entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.TipoSoglie
 * @author MyEclipse Persistence Tools
 */

public class TipoSoglieDAO implements ITipoSoglieDAO {
	// property constants
	public static final String DESCRIZIONE = "descrizione";
	public static final String LOWER_BOUND = "lowerBound";
	public static final String UPPER_BOUND = "upperBound";
	public static final String LIVELLO = "livello";
	public static final String PATH_ICON = "pathIcon";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TipoSoglie entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoSoglieDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoSoglie entity) {
		EntityManagerHelper.log("saving TipoSoglie instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TipoSoglie entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TipoSoglieDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoSoglie entity) {
		EntityManagerHelper.log("deleting TipoSoglie instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(TipoSoglie.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TipoSoglie entity and return it or a copy of
	 * it to the sender. A copy of the TipoSoglie entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TipoSoglieDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to update
	 * @return TipoSoglie the persisted TipoSoglie entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoSoglie update(TipoSoglie entity) {
		EntityManagerHelper.log("updating TipoSoglie instance", Level.INFO,
				null);
		try {
			TipoSoglie result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TipoSoglie findById(Integer id) {
		EntityManagerHelper.log("finding TipoSoglie instance with id: " + id,
				Level.INFO, null);
		try {
			TipoSoglie instance = getEntityManager().find(TipoSoglie.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TipoSoglie entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoSoglie property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoSoglie> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TipoSoglie> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding TipoSoglie instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TipoSoglie model where model."
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

	public List<TipoSoglie> findByDescrizione(Object descrizione) {
		return findByProperty(DESCRIZIONE, descrizione);
	}

	public List<TipoSoglie> findByLowerBound(Object lowerBound) {
		return findByProperty(LOWER_BOUND, lowerBound);
	}

	public List<TipoSoglie> findByUpperBound(Object upperBound) {
		return findByProperty(UPPER_BOUND, upperBound);
	}

	public List<TipoSoglie> findByLivello(Object livello) {
		return findByProperty(LIVELLO, livello);
	}

	public List<TipoSoglie> findByPathIcon(Object pathIcon) {
		return findByProperty(PATH_ICON, pathIcon);
	}

	/**
	 * Find all TipoSoglie entities.
	 * 
	 * @return List<TipoSoglie> all TipoSoglie entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoSoglie> findAll() {
		EntityManagerHelper.log("finding all TipoSoglie instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from TipoSoglie model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}