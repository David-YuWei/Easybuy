/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

/**
 *
 * @author weiyu
 */
@Service("common:cacheService")
public class CacheService {

	@Resource(name = "common:ehcache")
	private Ehcache ehcache;

	public CacheService() {
	}

	public boolean add(String key, Object value) throws Exception {
		try{
			Element element = new Element(key, value);
			ehcache.put(element);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}


	public boolean delete(String key) throws Exception {
		ehcache.remove(key);
		return true;
	}

	public Object get(String key) throws Exception {
		Element element = ehcache.get(key);
		if (element != null) {
			return element.getObjectValue();
		}
		else{
			return null;
		}
		
	}

}
