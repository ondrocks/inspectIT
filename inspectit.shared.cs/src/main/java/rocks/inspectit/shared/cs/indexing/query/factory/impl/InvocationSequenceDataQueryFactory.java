package rocks.inspectit.shared.cs.indexing.query.factory.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.indexing.IIndexQuery;
import rocks.inspectit.shared.cs.indexing.query.factory.AbstractQueryFactory;
import rocks.inspectit.shared.cs.indexing.restriction.impl.IndexQueryRestrictionFactory;

/**
 * Factory for all queries for the {@link InvocationSequenceData}.
 *
 * @author Ivan Senic
 *
 * @param <E>
 */
@Component
public class InvocationSequenceDataQueryFactory<E extends IIndexQuery> extends AbstractQueryFactory<E> {

	/**
	 * Returns query for invocation overview.
	 *
	 * @param platformId
	 *            The ID of the platform/agent.
	 * @param methodId
	 *            The ID of the method.
	 * @param fromDate
	 *            Date include invocation from.
	 * @param toDate
	 *            Date include invocation to.
	 *
	 * @return Returns the query for invocation sequence overview.
	 */
	public E getInvocationSequences(long platformId, long methodId, Date fromDate, Date toDate) {
		E query = getIndexQueryProvider().getIndexQuery();
		query.setPlatformIdent(platformId);
		query.setMethodIdent(methodId);
		ArrayList<Class<?>> searchedClasses = new ArrayList<>();
		searchedClasses.add(InvocationSequenceData.class);
		query.setObjectClasses(searchedClasses);
		if (fromDate != null) {
			query.setFromDate(new Timestamp(fromDate.getTime()));
		}
		if (toDate != null) {
			query.setToDate(new Timestamp(toDate.getTime()));
		}
		return query;
	}

	/**
	 * Returns query for invocation overview.
	 *
	 * @param platformId
	 *            The ID of the platform/agent.
	 * @param fromDate
	 *            Date include invocation from.
	 * @param toDate
	 *            Date include invocation to.
	 * @param minId
	 *            The minimum ID for objects to be returned.
	 *
	 * @return Returns the query for invocation sequence overview.
	 */
	public E getInvocationSequences(long platformId, Date fromDate, Date toDate, long minId) {
		return this.getInvocationSequences(platformId, fromDate, toDate, minId, 0, 0);
	}

	/**
	 * Returns query for invocation overview.
	 *
	 * @param platformId
	 *            Platform ID where to look for the objects. If the zero value is passed, looking
	 *            for the object will be done in all platforms.
	 * @param invocationIdCollection
	 *            Collections of invocations IDs to search.
	 * @param limit
	 *            The limit/size of the list.
	 *
	 * @return Returns the query for invocation sequence overview.
	 */
	public E getInvocationSequences(long platformId, Collection<Long> invocationIdCollection, int limit) {
		E query = getIndexQueryProvider().getIndexQuery();
		query.setPlatformIdent(platformId);
		ArrayList<Class<?>> searchedClasses = new ArrayList<>();
		searchedClasses.add(InvocationSequenceData.class);
		query.setObjectClasses(searchedClasses);
		query.addIndexingRestriction(IndexQueryRestrictionFactory.isInCollection("id", invocationIdCollection));
		return query;
	}

	/**
	 * Returns query for invocation overview.
	 *
	 * @param platformId
	 *            Platform ID where to look for the objects. If the zero value is passed, looking
	 *            for the object will be done in all platforms.
	 * @param startDate
	 *            Date include invocation from.
	 * @param endDate
	 *            Date include invocation to.
	 * @param minId
	 *            The minimum ID for objects to be returned.
	 * @param businessTrxId
	 *            Business transaction ID. If the zero value is passed, looking for the objects will
	 *            be done on all business transactions.
	 * @param applicationId
	 *            Application ID. If the zero value is passed, looking for the objects will be done
	 *            on all applications.
	 * @return Returns the query for invocation sequence overview.
	 */
	public E getInvocationSequences(Long platformId, Date startDate, Date endDate, long minId, int businessTrxId, int applicationId) {
		E query = getIndexQueryProvider().getIndexQuery();
		query.setPlatformIdent(platformId);
		query.setMinId(minId);
		ArrayList<Class<?>> searchedClasses = new ArrayList<Class<?>>();
		searchedClasses.add(InvocationSequenceData.class);
		query.setObjectClasses(searchedClasses);
		if (businessTrxId != 0) {
			query.addIndexingRestriction(IndexQueryRestrictionFactory.equal("businessTransactionId", businessTrxId));
		}
		if (applicationId != 0) {
			query.addIndexingRestriction(IndexQueryRestrictionFactory.equal("applicationId", applicationId));
		}
		if (startDate != null) {
			query.setFromDate(new Timestamp(startDate.getTime()));
		}
		if (endDate != null) {
			query.setToDate(new Timestamp(endDate.getTime()));
		}
		return query;
	}
}
