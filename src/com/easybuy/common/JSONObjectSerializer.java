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
import org.json.JSONObject;

/**
 *
 * @author weiyu
 */
public class JSONObjectSerializer extends JsonSerializer<JSONObject> {

	public JSONObjectSerializer() {
	}

	@Override
	public void serialize(JSONObject value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeRawValue(value == null ? "null" : value.toString());
	}
}
