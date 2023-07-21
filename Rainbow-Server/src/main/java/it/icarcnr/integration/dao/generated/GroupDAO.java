package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Group
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Group
 * @author MyEclipse Persistence Tools
 */

public class GroupDAO implements IGroupDAO {
	// property constants
	public static final String NAME = "name";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Group entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GroupDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Group entity) {
		EntityManagerHelper.log("saving Group instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Group entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GroupDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Group entity) {
		EntityManagerHelper.log("deleting Group instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Group.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Group entity and return it or a copy of it to
	 * the sender. A copy of the Group entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = GroupDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to update
	 * @return Group the persisted Group entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Group update(Group entity) {
		EntityManagerHelper.log("updating Group instance", Level.INFO, null);
		try {
			Group result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Group findById(Integer id) {
		EntityManagerHelper.log("finding Group instance with id: " + id,
				Level.INFO, null);
		try {
			Group instance = getEntityManager().find(Group.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Group entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Group property to query
	 * @param value
	 *            the property value to match
	 * @return List<Group> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Group> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Group instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Group model where model."
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

	public List<Group> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all Group entities.
	 * 
	 * @return List<Group> all Group entities
	 */
	@SuppressWarnings("unchecked")
	public List<Group> findAll() {
		EntityManagerHelper
				.log("finding all Group instances", Level.INFO, null);
		try {
			final String queryString = "select model from Group model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}