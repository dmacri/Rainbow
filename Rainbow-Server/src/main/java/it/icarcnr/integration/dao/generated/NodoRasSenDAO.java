package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * NodoRasSen entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.NodoRasSen
 * @author MyEclipse Persistence Tools
 */

public class NodoRasSenDAO implements INodoRasSenDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved NodoRasSen entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodoRasSenDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodoRasSen entity) {
		EntityManagerHelper.log("saving NodoRasSen instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent NodoRasSen entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodoRasSenDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodoRasSen entity) {
		EntityManagerHelper.log("deleting NodoRasSen instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(NodoRasSen.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved NodoRasSen entity and return it or a copy of
	 * it to the sender. A copy of the NodoRasSen entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NodoRasSenDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to update
	 * @return NodoRasSen the persisted NodoRasSen entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NodoRasSen update(NodoRasSen entity) {
		EntityManagerHelper.log("updating NodoRasSen instance", Level.INFO,
				null);
		try {
			NodoRasSen result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public NodoRasSen findById(NodoRasSenId id) {
		EntityManagerHelper.log("finding NodoRasSen instance with id: " + id,
				Level.INFO, null);
		try {
			NodoRasSen instance = getEntityManager().find(NodoRasSen.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all NodoRasSen entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodoRasSen property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodoRasSen> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<NodoRasSen> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding NodoRasSen instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from NodoRasSen model where model."
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
	 * Find all NodoRasSen entities.
	 * 
	 * @return List<NodoRasSen> all NodoRasSen entities
	 */
	@SuppressWarnings("unchecked")
	public List<NodoRasSen> findAll() {
		EntityManagerHelper.log("finding all NodoRasSen instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from NodoRasSen model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}