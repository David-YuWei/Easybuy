/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.io.Writer;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

/**
 * Spring-MVC {@link View} that renders JSON content by serializing the model for the current request using <a
 * href="http://jackson.codehaus.org/">Jackson's</a> {@link ObjectMapper}.
 *
 * <p>By default, the entire contents of the model map (with the exception of framework-specific classes) will be
 * encoded as JSON. For cases where the contents of the map need to be filtered, users may specify a specific set of
 * model attributes to encode via the {@link #setRenderedAttributes(Set) renderedAttributes} property.
 *
 * @author Jeremy Grelle
 * @author Arjen Poutsma
 * @see org.springframework.http.converter.json.MappingJacksonHttpMessageConverter
 * @since 3.0
 */
public class JacksonJsonView extends AbstractView {

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	private ObjectMapper objectMapper = new ObjectMapper();
	private SerializationConfig serializationConfig;
	private boolean prefixJson = false;
	private String modelKey;
	private boolean disableCaching = false;
	private boolean contentTypeWithCharset = true;
	private boolean _appendContentTypeCharset = false;

	/**
	 * Construct a new {@code JacksonJsonView}, setting the content type to {@code application/json}.
	 */
	public JacksonJsonView() {
		super.setContentType(DEFAULT_CONTENT_TYPE);
	}

	/**
	 * Sets the {@code ObjectMapper} for this view. If not set, a default {@link ObjectMapper#ObjectMapper() ObjectMapper}
	 * is used.
	 *
	 * <p>Setting a custom-configured {@code ObjectMapper} is one way to take further control of the JSON serialization
	 * process. For example, an extended {@link SerializerFactory} can be configured that provides custom serializers for
	 * specific types. The other option for refining the serialization process is to use Jackson's provided annotations on
	 * the types to be serialized, in which case a custom-configured ObjectMapper is unnecessary.
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
		this.objectMapper = objectMapper;
	}

	/**
	 * Indicates whether the JSON output by this view should be prefixed with "{@code {} &&}". Default is false.
	 *
	 * <p> Prefixing the JSON string in this manner is used to help prevent JSON Hijacking. The prefix renders the string
	 * syntactically invalid as a script so that it cannot be hijacked. This prefix does not affect the evaluation of JSON,
	 * but if JSON validation is performed on the string, the prefix would need to be ignored.
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}

	public String getModelKey() {
		return modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	/**
	 * Disables caching of the generated JSON.
	 *
	 * <p>Default is {@code true}, which will prevent the client from caching the generated JSON.
	 */
	public void setDisableCaching(boolean disableCaching) {
		this.disableCaching = disableCaching;
	}

	public void setSerializationConfig(SerializationConfig serializationConfig) {
		this.serializationConfig = serializationConfig;
	}

	public void setContentTypeWithCharset(boolean contentTypeWithCharset) {
		this.contentTypeWithCharset = contentTypeWithCharset;
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
		if (contentTypeWithCharset && !contentType.contains(";")) {
			_appendContentTypeCharset = true;
		}
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		if (contentTypeWithCharset && _appendContentTypeCharset) {
			response.setContentType(getContentType() + "; charset=" + response.getCharacterEncoding());
		} else {
			response.setContentType(getContentType());
		}
		if (disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Object value = filterModel(model);
		Writer writer = response.getWriter();
		JsonGenerator generator = objectMapper.getJsonFactory().createJsonGenerator(writer);
		if (prefixJson) {
			generator.writeRaw("{} && ");
		}
		if (serializationConfig == null) {
			objectMapper.writeValue(generator, value);
		} else {
			objectMapper.writeValue(generator, value, serializationConfig);
		}
	}

	/**
	 * Filters out undesired attributes from the given model. The return value can be either another {@link Map}, or a
	 * single value object.
	 *
	 * <p>Default implementation removes {@link BindingResult} instances and entries not included in the {@link
	 * #setRenderedAttributes(Set) renderedAttributes} property.
	 *
	 * @param model the model, as passed on to {@link #renderMergedOutputModel}
	 * @return the object to be rendered
	 */
	protected Object filterModel(Map<String, Object> model) {
		if (modelKey == null) {
			return model;
		} else {
			return model.get(modelKey);
		}
//		Map<String, Object> result = new HashMap<String, Object>(model.size());
//		Set<String> renderedAttributes =
//				!CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
//		for (Map.Entry<String, Object> entry : model.entrySet()) {
//			if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
//				result.put(entry.getKey(), entry.getValue());
//			}
//		}
//		return result;
	}
}
