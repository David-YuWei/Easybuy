/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.text.SimpleDateFormat;
import java.util.Map;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author weiyu
 */
public class ObjectMapperInitializer implements InitializingBean {

	private ObjectMapper objectMapper;
	private Map<Class, JsonSerializer> genericSerializers;
	private Map<Class, JsonSerializer> specificSerializers;
	private String dateFormat;

	public ObjectMapperInitializer() {
	}

	public ObjectMapperInitializer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public Map<Class, JsonSerializer> getGenericSerializers() {
		return genericSerializers;
	}

	public void setGenericSerializers(Map<Class, JsonSerializer> genericSerializers) {
		this.genericSerializers = genericSerializers;
	}

	public Map<Class, JsonSerializer> getSpecificSerializers() {
		return specificSerializers;
	}

	public void setSpecificSerializers(Map<Class, JsonSerializer> specificSerializers) {
		this.specificSerializers = specificSerializers;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SerializationConfig sc = objectMapper.getSerializationConfig();
		CustomSerializerFactory sf = new CustomSerializerFactory();
		if (genericSerializers != null) {
			for (Map.Entry<Class, JsonSerializer> entry : genericSerializers.entrySet()) {
				sf.addGenericMapping(entry.getKey(), entry.getValue());
			}
		}
		if (specificSerializers != null) {
			for (Map.Entry<Class, JsonSerializer> entry : specificSerializers.entrySet()) {
				sf.addSpecificMapping(entry.getKey(), entry.getValue());
			}
		}
		objectMapper.setSerializerFactory(sf);
		
		if (dateFormat != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sc.setDateFormat(sdf);
		}
	}
}
