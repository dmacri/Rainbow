package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * NodeSen entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.NodeSen
 * @author MyEclipse Persistence Tools
 */

public class NodeSenDAO implements INodeSenDAO {
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";
	public static final String SERIAL = "serial";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved NodeSen entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeSenDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeSen entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodeSen entity) {
		EntityManagerHelper.log("saving NodeSen instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent NodeSen entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeSenDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodeSen entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodeSen entity) {
		EntityManagerHelper.log("deleting NodeSen instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(NodeSen.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved NodeSen entity and return it or a copy of it
	 * to the sender. A copy of the NodeSen entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NodeSenDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeSen entity to update
	 * @return NodeSen the persisted NodeSen entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NodeSen update(NodeSen entity) {
		EntityManagerHelper.log("updating NodeSen instance", Level.INFO, null);
		try {
			NodeSen result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public NodeSen findById(Integer id) {
		EntityManagerHelper.log("finding NodeSen instance with id: " + id,
				Level.INFO, null);
		try {
			NodeSen instance = getEntityManager().find(NodeSen.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all NodeSen entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodeSen property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodeSen> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<NodeSen> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding NodeSen instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from NodeSen model where model."
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

	public List<NodeSen> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<NodeSen> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<NodeSen> findBySerial(Object serial) {
		return findByProperty(SERIAL, serial);
	}

	/**
	 * Find all NodeSen entities.
	 * 
	 * @return List<NodeSen> all NodeSen entities
	 */
	@SuppressWarnings("unchecked")
	public List<NodeSen> findAll() {
		EntityManagerHelper.log("finding all NodeSen instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from NodeSen model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}