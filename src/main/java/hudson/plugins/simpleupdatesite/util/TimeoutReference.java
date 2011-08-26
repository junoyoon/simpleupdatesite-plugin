/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite.util;

/**
 * Utility class which contains timeouttable reference.
 * 
 * @author JunHo Yoon
 */
public class TimeoutReference<T> {
	private final long period;
	private long lastPutTimestamp;
	private T reference;
	private ReferenceRetriever<T> retriever;

	public TimeoutReference(long period) {
		this.period = period;
	}

	public TimeoutReference(long period, ReferenceRetriever<T> retriever) {
		this.period = period;
		this.retriever = retriever;
	}

	public void put(T reference) {
		this.lastPutTimestamp = System.currentTimeMillis();
		this.reference = reference;
	}

	public void invalidate() {
		this.lastPutTimestamp = 0;
	}

	public T get() {
		long gap = System.currentTimeMillis() - this.lastPutTimestamp;
		if (gap < this.period) {
			return this.reference;
		}
		if (this.retriever != null) {
			put(this.retriever.createReference());
			return this.reference;
		}
		return null;
	}

	public void addReferenceRetriever(ReferenceRetriever<T> retriever) {
		this.retriever = retriever;
	}

	/**
	 * Interface for reference retriever
	 * 
	 * @author JunHo Yoon
	 */
	public interface ReferenceRetriever<T> {
		public T createReference();
	}
}