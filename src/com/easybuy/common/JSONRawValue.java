/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

/**
 *
 * @author weiyu
 */
public class JSONRawValue {

	private String value;

	public JSONRawValue() {
	}

	public JSONRawValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value == null ? "null" : value.toString();
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value == null ? "null" : value.toString();
	}
}
