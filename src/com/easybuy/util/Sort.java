/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.util;

import java.io.Serializable;

/**
 * used as parameter when doing database query
 * @author weiyu
 */
public class Sort implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private boolean asc;

	public Sort() {
		this.asc = true;
	}

	public Sort(String name) {
		this.name = name;
		this.asc = true;
	}

	public Sort(String name, boolean asc) {
		this.name = name;
		this.asc = asc;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Sort other = (Sort) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		if (this.asc != other.asc) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 37 * hash + (this.asc ? 1 : 0);
		return hash;
	}

	@Override
	public String toString() {
		return "Sort{" + "name=" + name + ", asc=" + asc + '}';
	}
}
