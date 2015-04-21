/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 * @author weiyu
 */
public class JSONRawValueSerializer extends JsonSerializer<JSONRawValue> {

	@Override
	public void serialize(JSONRawValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeRawValue(value.getValue());
	}
}
