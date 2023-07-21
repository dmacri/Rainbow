package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Area
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.Area
 * @author MyEclipse Persistence Tools
 */

public class AreaDAO implements IAreaDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String STRING_ID = "stringId";
	public static final String ICON = "icon";
	public static final String QTIP = "qtip";
	public static final String THUMBNAIL = "thumbnail";
	public static final String PARAMETERS = "parameters";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Area entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AreaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Area entity) {
		EntityManagerHelper.log("saving Area instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Area entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AreaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Area entity) {
		EntityManagerHelper.log("deleting Area instance", Level.INFO, null);
		try {
			entity = getEntityManager()
					.getReference(Area.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Area entity and return it or a copy of it to
	 * the sender. A copy of the Area entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AreaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to update
	 * @return Area the persisted Area entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Area update(Area entity) {
		EntityManagerHelper.log("updating Area instance", Level.INFO, null);
		try {
			Area result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Area findById(Integer id) {
		EntityManagerHelper.log("finding Area instance with id: " + id,
				Level.INFO, null);
		try {
			Area instance = getEntityManager().find(Area.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Area entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Area property to query
	 * @param value
	 *            the property value to match
	 * @return List<Area> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Area> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Area instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Area model where model."
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

	public List<Area> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Area> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Area> findByStringId(Object stringId) {
		return findByProperty(STRING_ID, stringId);
	}

	public List<Area> findByIcon(Object icon) {
		return findByProperty(ICON, icon);
	}

	public List<Area> findByQtip(Object qtip) {
		return findByProperty(QTIP, qtip);
	}

	public List<Area> findByThumbnail(Object thumbnail) {
		return findByProperty(THUMBNAIL, thumbnail);
	}

	public List<Area> findByParameters(Object parameters) {
		return findByProperty(PARAMETERS, parameters);
	}

	/**
	 * Find all Area entities.
	 * 
	 * @return List<Area> all Area entities
	 */
	@SuppressWarnings("unchecked")
	public List<Area> findAll() {
		EntityManagerHelper.log("finding all Area instances", Level.INFO, null);
		try {
			final String queryString = "select model from Area model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}