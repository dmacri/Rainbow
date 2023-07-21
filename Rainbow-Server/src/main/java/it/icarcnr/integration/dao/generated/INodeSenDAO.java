package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for NodeSenDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INodeSenDAO {
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
	 * INodeSenDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeSen entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodeSen entity);

	/**
	 * Delete a persistent NodeSen entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INodeSenDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodeSen entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodeSen entity);

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
	 * entity = INodeSenDAO.update(entity);
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
	public NodeSen update(NodeSen entity);

	public NodeSen findById(Integer id);

	/**
	 * Find all NodeSen entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodeSen property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodeSen> found by query
	 */
	public List<NodeSen> findByProperty(String propertyName, Object value);

	public List<NodeSen> findByDescription(Object description);

	public List<NodeSen> findByName(Object name);

	public List<NodeSen> findBySerial(Object serial);

	/**
	 * Find all NodeSen entities.
	 * 
	 * @return List<NodeSen> all NodeSen entities
	 */
	public List<NodeSen> findAll();
}