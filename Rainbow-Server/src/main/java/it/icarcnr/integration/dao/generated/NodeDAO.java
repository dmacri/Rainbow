package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Node
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Node
 * @author MyEclipse Persistence Tools
 */

public class NodeDAO implements INodeDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Node entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Node entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Node entity) {
		EntityManagerHelper.log("saving Node instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Node entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Node entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Node entity) {
		EntityManagerHelper.log("deleting Node instance", Level.INFO, null);
		try {
			entity = getEntityManager()
					.getReference(Node.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Node entity and return it or a copy of it to
	 * the sender. A copy of the Node entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NodeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Node entity to update
	 * @return Node the persisted Node entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Node update(Node entity) {
		EntityManagerHelper.log("updating Node instance", Level.INFO, null);
		try {
			Node result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Node findById(Integer id) {
		EntityManagerHelper.log("finding Node instance with id: " + id,
				Level.INFO, null);
		try {
			Node instance = getEntityManager().find(Node.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Node entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Node property to query
	 * @param value
	 *            the property value to match
	 * @return List<Node> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Node> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Node instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Node model where model."
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

	public List<Node> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Node> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/**
	 * Find all Node entities.
	 * 
	 * @return List<Node> all Node entities
	 */
	@SuppressWarnings("unchecked")
	public List<Node> findAll() {
		EntityManagerHelper.log("finding all Node instances", Level.INFO, null);
		try {
			final String queryString = "select model from Node model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}