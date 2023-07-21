package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Type
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Type
 * @author MyEclipse Persistence Tools
 */

public class TypeDAO implements ITypeDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Type entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Type entity) {
		EntityManagerHelper.log("saving Type instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Type entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Type entity) {
		EntityManagerHelper.log("deleting Type instance", Level.INFO, null);
		try {
			entity = getEntityManager()
					.getReference(Type.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Type entity and return it or a copy of it to
	 * the sender. A copy of the Type entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to update
	 * @return Type the persisted Type entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Type update(Type entity) {
		EntityManagerHelper.log("updating Type instance", Level.INFO, null);
		try {
			Type result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Type findById(Integer id) {
		EntityManagerHelper.log("finding Type instance with id: " + id,
				Level.INFO, null);
		try {
			Type instance = getEntityManager().find(Type.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Type entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Type property to query
	 * @param value
	 *            the property value to match
	 * @return List<Type> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Type> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Type instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Type model where model."
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

	public List<Type> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Type> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/**
	 * Find all Type entities.
	 * 
	 * @return List<Type> all Type entities
	 */
	@SuppressWarnings("unchecked")
	public List<Type> findAll() {
		EntityManagerHelper.log("finding all Type instances", Level.INFO, null);
		try {
			final String queryString = "select model from Type model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}