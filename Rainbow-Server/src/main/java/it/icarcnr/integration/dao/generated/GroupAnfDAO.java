package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * GroupAnf entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.GroupAnf
 * @author MyEclipse Persistence Tools
 */

public class GroupAnfDAO implements IGroupAnfDAO {
	// property constants
	public static final String READ = "read";
	public static final String WRITE = "write";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved GroupAnf entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GroupAnfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GroupAnf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GroupAnf entity) {
		EntityManagerHelper.log("saving GroupAnf instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent GroupAnf entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GroupAnfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GroupAnf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GroupAnf entity) {
		EntityManagerHelper.log("deleting GroupAnf instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(GroupAnf.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved GroupAnf entity and return it or a copy of it
	 * to the sender. A copy of the GroupAnf entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = GroupAnfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GroupAnf entity to update
	 * @return GroupAnf the persisted GroupAnf entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public GroupAnf update(GroupAnf entity) {
		EntityManagerHelper.log("updating GroupAnf instance", Level.INFO, null);
		try {
			GroupAnf result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public GroupAnf findById(Integer id) {
		EntityManagerHelper.log("finding GroupAnf instance with id: " + id,
				Level.INFO, null);
		try {
			GroupAnf instance = getEntityManager().find(GroupAnf.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all GroupAnf entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the GroupAnf property to query
	 * @param value
	 *            the property value to match
	 * @return List<GroupAnf> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<GroupAnf> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding GroupAnf instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from GroupAnf model where model."
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

	public List<GroupAnf> findByRead(Object read) {
		return findByProperty(READ, read);
	}

	public List<GroupAnf> findByWrite(Object write) {
		return findByProperty(WRITE, write);
	}

	/**
	 * Find all GroupAnf entities.
	 * 
	 * @return List<GroupAnf> all GroupAnf entities
	 */
	@SuppressWarnings("unchecked")
	public List<GroupAnf> findAll() {
		EntityManagerHelper.log("finding all GroupAnf instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from GroupAnf model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}