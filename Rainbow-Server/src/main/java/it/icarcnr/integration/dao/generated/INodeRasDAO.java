package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for NodeRasDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INodeRasDAO {
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
	 * INodeRasDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodeRas entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodeRas entity);

	/**
	 * Delete a persistent NodeRas entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INodeRasDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodeRas entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodeRas entity);

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
	 * entity = INodeRasDAO.update(entity);
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
	public NodeRas update(NodeRas entity);

	public NodeRas findById(Integer id);

	/**
	 * Find all NodeRas entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodeRas property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodeRas> found by query
	 */
	public List<NodeRas> findByProperty(String propertyName, Object value);

	public List<NodeRas> findByDescription(Object description);

	public List<NodeRas> findByName(Object name);

	/**
	 * Find all NodeRas entities.
	 * 
	 * @return List<NodeRas> all NodeRas entities
	 */
	public List<NodeRas> findAll();
}