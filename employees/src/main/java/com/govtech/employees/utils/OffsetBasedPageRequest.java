/**
 * Author: Wong Jin Pei 
 * This class Offset based page request (paging and sorting) for JPA
 */
package com.govtech.employees.utils;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetBasedPageRequest implements Pageable, Serializable {

	private static final long serialVersionUID = -25822477129613575L;

	private int limit;
	private int offset;
	private final Sort sort;

	/**
	 * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
	 *
	 * @param offset
	 *            zero-based offset.
	 * @param limit
	 *            the size of the elements to be returned.
	 * @param sort
	 *            can be {@literal null}.
	 */
	public OffsetBasedPageRequest(int offset, int limit, Sort sort) {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset index must not be less than zero!");
		}

		if (limit < 1) {
			throw new IllegalArgumentException("Limit must not be less than one!");
		}
		this.limit = limit;
		this.offset = offset;
		this.sort = sort;
	}

	/**
	 * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
	 *
	 * @param offset
	 *            zero-based offset.
	 * @param limit
	 *            the size of the elements to be returned.
	 * @param direction
	 *            the direction of the {@link Sort} to be specified, can be
	 *            {@literal null}.
	 * @param properties
	 *            the properties to sort by, must not be {@literal null} or empty.
	 */
	public OffsetBasedPageRequest(int offset, int limit, Sort.Direction direction, String... properties) {
		this(offset, limit, new Sort(direction, properties));
	}

	/**
	 * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
	 *
	 * @param offset
	 *            zero-based offset.
	 * @param limit
	 *            the size of the elements to be returned.
	 */
	public OffsetBasedPageRequest(int offset, int limit) {
		this(offset, limit, new Sort(Sort.Direction.ASC, "id"));
	}

	@Override
	public int getPageNumber() {
		return offset / limit;
	}

	@Override
	public int getPageSize() {
		return limit;
	}

	@Override
	public long getOffset() {
		return offset;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public Pageable next() {
		return new OffsetBasedPageRequest((int) getOffset() + getPageSize(), getPageSize(), getSort());
	}

	public OffsetBasedPageRequest previous() {
		return hasPrevious() ? new OffsetBasedPageRequest((int) getOffset() - getPageSize(), getPageSize(), getSort())
				: this;
	}

	@Override
	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	@Override
	public Pageable first() {
		return new OffsetBasedPageRequest(0, getPageSize(), getSort());
	}

	@Override
	public boolean hasPrevious() {
		return offset > limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
		result = prime * result + offset;
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OffsetBasedPageRequest other = (OffsetBasedPageRequest) obj;
		if (limit != other.limit)
			return false;
		if (offset != other.offset)
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OffsetBasedPageRequest [limit=" + limit + ", offset=" + offset + ", sort=" + sort + "]";
	}

}