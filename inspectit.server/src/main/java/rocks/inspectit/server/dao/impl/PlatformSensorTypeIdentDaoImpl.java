package rocks.inspectit.server.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import rocks.inspectit.server.dao.PlatformSensorTypeIdentDao;
import rocks.inspectit.shared.all.cmr.model.PlatformSensorTypeIdent;

/**
 * The default implementation of the {@link PlatformSensorTypeIdentDao} interface by using the
 * Entity manager.
 *
 * @author Patrice Bouillet
 *
 */
@Repository
public class PlatformSensorTypeIdentDaoImpl extends AbstractJpaDao<PlatformSensorTypeIdent> implements PlatformSensorTypeIdentDao {

	/**
	 * Default constructor.
	 */
	public PlatformSensorTypeIdentDaoImpl() {
		super(PlatformSensorTypeIdent.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(PlatformSensorTypeIdent platformSensorTypeIdent) {
		if (null == platformSensorTypeIdent.getId()) {
			super.create(platformSensorTypeIdent);
		} else {
			super.update(platformSensorTypeIdent);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PlatformSensorTypeIdent> findAll() {
		return getEntityManager().createNamedQuery(PlatformSensorTypeIdent.FIND_ALL, PlatformSensorTypeIdent.class).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> findIdByClassNameAndPlatformId(String fullyQualifiedClassName, long platformId) {
		TypedQuery<Long> query = getEntityManager().createNamedQuery(PlatformSensorTypeIdent.FIND_ID_BY_CLASS_AND_PLATFORM_ID, Long.class);
		query.setParameter("fullyQualifiedClassName", fullyQualifiedClassName);
		query.setParameter("platformIdent", platformId);

		return query.getResultList();
	}

}
