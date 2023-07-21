package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for CalculationParameterDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICalculationParameterDAO {
	/**
	 * Perform an initial save of a previously unsaved CalculationParameter
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICalculationParameterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CalculationParameter entity);

	/**
	 * Delete a persistent CalculationParameter entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICalculationParameterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CalculationParameter entity);

	/**
	 * Persist a previously saved CalculationParameter entity and return it or a
	 * copy of it to the sender. A copy of the CalculationParameter entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICalculationParameterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CalculationParameter entity to update
	 * @return CalculationParameter the persisted CalculationParameter entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CalculationParameter update(CalculationParameter entity);

	public CalculationParameter findById(Integer id);

	/**
	 * Find all CalculationParameter entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CalculationParameter property to query
	 * @param value
	 *            the property value to match
	 * @return List<CalculationParameter> found by query
	 */
	public List<CalculationParameter> findByProperty(String propertyName,
			Object value);

	public List<CalculationParameter> findByName(Object name);

	public List<CalculationParameter> findByValue(Object value);

	public List<CalculationParameter> findByType(Object type);

	/**
	 * Find all CalculationParameter entities.
	 * 
	 * @return List<CalculationParameter> all CalculationParameter entities
	 */
	public List<CalculationParameter> findAll();
}