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
import org.json.JSONArray;

/**
 *
 * @author weiyu
 */
public class JSONArraySerializer extends JsonSerializer<JSONArray> {

	public JSONArraySerializer() {
	}

	@Override
	public void serialize(JSONArray value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeRawValue(value == null ? "null" : value.toString());
	}
}
