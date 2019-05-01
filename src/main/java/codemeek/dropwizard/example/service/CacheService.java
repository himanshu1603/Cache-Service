package codemeek.dropwizard.example.service;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import codemeek.dropwizard.example.models.CacheObject;

public class CacheService implements GACache{
	
	private static final String DATA_SOURCE = "dummy_data.json";

    private Map<String, CacheObject> cache;

    public CacheService() {
            cache = new HashMap<>();

    }

//    private void initData() throws IOException {
//        URL url = Resources.getResource(DATA_SOURCE);
//        String json = Resources.toString(url, Charsets.UTF_8);
//        ObjectMapper mapper = new ObjectMapper();
//        CollectionType type = mapper
//                .getTypeFactory()
//                .constructCollectionType((Class<? extends Collection>) Map.class, CacheObject.class);
//        cache = mapper.readValue(json, type);
//    }




	@Override
	public void setValue(String key, String value) {
		CacheObject obj = new CacheObject();
		obj.setKey(key);
		obj.setValue(value);
		int objectCount = obj.getCount();
		obj.setCount(objectCount+1);
		
		// To clear space if count is more than 5
		updateEntryInCache();
		cache.put(key, obj);
		
	}

	@Override
	public String getValue(String key) {
		if (!cache.containsKey(key)) {
			return "Key does not exist";
		}
		CacheObject obj = cache.get(key);
		int objectCount = obj.getCount();
		obj.setCount(objectCount+1);
		return obj.getValue();
		
	}

	@Override
	public String remove(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
		
	}
	
	private void updateEntryInCache() {
		if (cache.size() < 5)
		{
			return ;
		}
		int minCount = Integer.MAX_VALUE;
		String minKey = "";
		for (Map.Entry<String,CacheObject> entry : cache.entrySet())  
		{
			CacheObject object = entry.getValue();
			if (minCount>object.getCount())
			{
				minCount = object.getCount();
				minKey = object.getKey();
			}
		}
		
		cache.remove(minKey);
	}

}
