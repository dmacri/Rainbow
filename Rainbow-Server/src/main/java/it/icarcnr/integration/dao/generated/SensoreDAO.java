package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sensore entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Sensore
 * @author MyEclipse Persistence Tools
 */

public class SensoreDAO implements ISensoreDAO {
	// property constants
	public static final String DESCRIPTION = "description";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Sensore entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SensoreDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Sensore entity) {
		EntityManagerHelper.log("saving Sensore instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Sensore entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SensoreDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Sensore entity) {
		EntityManagerHelper.log("deleting Sensore instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Sensore.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Sensore entity and return it or a copy of it
	 * to the sender. A copy of the Sensore entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SensoreDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to update
	 * @return Sensore the persisted Sensore entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Sensore update(Sensore entity) {
		EntityManagerHelper.log("updating Sensore instance", Level.INFO, null);
		try {
			Sensore result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Sensore findById(Integer id) {
		EntityManagerHelper.log("finding Sensore instance with id: " + id,
				Level.INFO, null);
		try {
			Sensore instance = getEntityManager().find(Sensore.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Sensore entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Sensore property to query
	 * @param value
	 *            the property value to match
	 * @return List<Sensore> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Sensore> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Sensore instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Sensore model where model."
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

	public List<Sensore> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/**
	 * Find all Sensore entities.
	 * 
	 * @return List<Sensore> all Sensore entities
	 */
	@SuppressWarnings("unchecked")
	public List<Sensore> findAll() {
		EntityManagerHelper.log("finding all Sensore instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Sensore model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}