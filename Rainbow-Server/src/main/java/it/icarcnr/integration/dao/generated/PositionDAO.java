package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Position entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Position
 * @author MyEclipse Persistence Tools
 */

public class PositionDAO implements IPositionDAO {
	// property constants
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String INDIRIZZO = "indirizzo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Position entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PositionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Position entity) {
		EntityManagerHelper.log("saving Position instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Position entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PositionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Position entity) {
		EntityManagerHelper.log("deleting Position instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Position.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Position entity and return it or a copy of it
	 * to the sender. A copy of the Position entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PositionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to update
	 * @return Position the persisted Position entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Position update(Position entity) {
		EntityManagerHelper.log("updating Position instance", Level.INFO, null);
		try {
			Position result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Position findById(Integer id) {
		EntityManagerHelper.log("finding Position instance with id: " + id,
				Level.INFO, null);
		try {
			Position instance = getEntityManager().find(Position.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Position entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Position property to query
	 * @param value
	 *            the property value to match
	 * @return List<Position> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Position> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Position instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Position model where model."
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

	public List<Position> findByLat(Object lat) {
		return findByProperty(LAT, lat);
	}

	public List<Position> findByLng(Object lng) {
		return findByProperty(LNG, lng);
	}

	public List<Position> findByIndirizzo(Object indirizzo) {
		return findByProperty(INDIRIZZO, indirizzo);
	}

	/**
	 * Find all Position entities.
	 * 
	 * @return List<Position> all Position entities
	 */
	@SuppressWarnings("unchecked")
	public List<Position> findAll() {
		EntityManagerHelper.log("finding all Position instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Position model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}