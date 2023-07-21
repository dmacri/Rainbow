package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Valori entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Valori
 * @author MyEclipse Persistence Tools
 */

public class ValoriDAO implements IValoriDAO {
	// property constants
	public static final String VALUE = "value";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Valori entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ValoriDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Valori entity) {
		EntityManagerHelper.log("saving Valori instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Valori entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ValoriDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Valori entity) {
		EntityManagerHelper.log("deleting Valori instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Valori.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Valori entity and return it or a copy of it to
	 * the sender. A copy of the Valori entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ValoriDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to update
	 * @return Valori the persisted Valori entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Valori update(Valori entity) {
		EntityManagerHelper.log("updating Valori instance", Level.INFO, null);
		try {
			Valori result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Valori findById(ValoriId id) {
		EntityManagerHelper.log("finding Valori instance with id: " + id,
				Level.INFO, null);
		try {
			Valori instance = getEntityManager().find(Valori.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Valori entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Valori property to query
	 * @param value
	 *            the property value to match
	 * @return List<Valori> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Valori> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Valori instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Valori model where model."
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

	public List<Valori> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	/**
	 * Find all Valori entities.
	 * 
	 * @return List<Valori> all Valori entities
	 */
	@SuppressWarnings("unchecked")
	public List<Valori> findAll() {
		EntityManagerHelper.log("finding all Valori instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Valori model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}