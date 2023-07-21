package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operation entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Operation
 * @author MyEclipse Persistence Tools
 */

public class OperationDAO implements IOperationDAO {
	// property constants
	public static final String NAME = "name";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Operation entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OperationDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Operation entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Operation entity) {
		EntityManagerHelper.log("saving Operation instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Operation entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OperationDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Operation entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Operation entity) {
		EntityManagerHelper
				.log("deleting Operation instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Operation.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Operation entity and return it or a copy of it
	 * to the sender. A copy of the Operation entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = OperationDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Operation entity to update
	 * @return Operation the persisted Operation entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Operation update(Operation entity) {
		EntityManagerHelper
				.log("updating Operation instance", Level.INFO, null);
		try {
			Operation result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Operation findById(Integer id) {
		EntityManagerHelper.log("finding Operation instance with id: " + id,
				Level.INFO, null);
		try {
			Operation instance = getEntityManager().find(Operation.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Operation entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Operation property to query
	 * @param value
	 *            the property value to match
	 * @return List<Operation> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Operation> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Operation instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Operation model where model."
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

	public List<Operation> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all Operation entities.
	 * 
	 * @return List<Operation> all Operation entities
	 */
	@SuppressWarnings("unchecked")
	public List<Operation> findAll() {
		EntityManagerHelper.log("finding all Operation instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Operation model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}