package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Interface for CronjobDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICronjobDAO {
	/**
	 * Perform an initial save of a previously unsaved Cronjob entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICronjobDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cronjob entity);

	/**
	 * Delete a persistent Cronjob entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICronjobDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cronjob entity);

	/**
	 * Persist a previously saved Cronjob entity and return it or a copy of it
	 * to the sender. A copy of the Cronjob entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICronjobDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cronjob entity to update
	 * @return Cronjob the persisted Cronjob entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cronjob update(Cronjob entity);

	public Cronjob findById(Integer id);

	/**
	 * Find all Cronjob entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cronjob property to query
	 * @param value
	 *            the property value to match
	 * @return List<Cronjob> found by query
	 */
	public List<Cronjob> findByProperty(String propertyName, Object value);

	public List<Cronjob> findByNamejob(Object namejob);

	public List<Cronjob> findByNametrigger(Object nametrigger);

	public List<Cronjob> findByOutput(Object output);

	public List<Cronjob> findByCommand(Object command);

	public List<Cronjob> findByIdUser(Object idUser);

	public List<Cronjob> findByStatus(Object status);

	/**
	 * Find all Cronjob entities.
	 * 
	 * @return List<Cronjob> all Cronjob entities
	 */
	public List<Cronjob> findAll();
}