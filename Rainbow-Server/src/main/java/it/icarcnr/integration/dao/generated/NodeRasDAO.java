package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * NodeRas entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.NodeRas
 * @author MyEclipse Persistence Tools
 */

public class NodeRasDAO implements INodeRasDAO {
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved NodeRas entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeRasDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeRas entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodeRas entity) {
		EntityManagerHelper.log("saving NodeRas instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent NodeRas entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NodeRasDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodeRas entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodeRas entity) {
		EntityManagerHelper.log("deleting NodeRas instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(NodeRas.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved NodeRas entity and return it or a copy of it
	 * to the sender. A copy of the NodeRas entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NodeRasDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeRas entity to update
	 * @return NodeRas the persisted NodeRas entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NodeRas update(NodeRas entity) {
		EntityManagerHelper.log("updating NodeRas instance", Level.INFO, null);
		try {
			NodeRas result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public NodeRas findById(Integer id) {
		EntityManagerHelper.log("finding NodeRas instance with id: " + id,
				Level.INFO, null);
		try {
			NodeRas instance = getEntityManager().find(NodeRas.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all NodeRas entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodeRas property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodeRas> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<NodeRas> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding NodeRas instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from NodeRas model where model."
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

	public List<NodeRas> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<NodeRas> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all NodeRas entities.
	 * 
	 * @return List<NodeRas> all NodeRas entities
	 */
	@SuppressWarnings("unchecked")
	public List<NodeRas> findAll() {
		EntityManagerHelper.log("finding all NodeRas instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from NodeRas model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}