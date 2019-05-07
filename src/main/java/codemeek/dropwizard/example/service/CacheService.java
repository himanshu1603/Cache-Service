package codemeek.dropwizard.example.service;

import java.util.*;

import org.slf4j.*;

import codemeek.dropwizard.example.models.CacheObject;

public class CacheService implements GACache {
  private static Logger log = LoggerFactory.getLogger(CacheService.class);


  private Heap cacheHeap;
  private Map<String, CacheObject> cacheObjectMap;

  public CacheService() {
    cacheHeap = new Heap(5);
    cacheObjectMap = new HashMap<>();

  }

  @Override
  public void setValue(String key, String value) {
    if ( cacheObjectMap.containsKey(key) ) {
      CacheObject obj = cacheObjectMap.get(key);
      log.info("obj..." + obj);
      int index = cacheHeap.getIndexFromObject(obj);
      log.info("index..." + index);
      if ( index != -1 ) {
        obj.setValue(value);
        obj.setCount(obj.getCount() + 1);
        cacheHeap.setObjectAtIndex(index, obj);
      }
    } else {
      //New object is coming
      CacheObject obj = new CacheObject();
      obj.setCount(0);
      obj.setKey(key);
      obj.setValue(value);
      cacheObjectMap.put(key, obj);
      //In overflow condition deleteKey is not empty, so delete this object from map also.
      String deleteKey = cacheHeap.insertKey(obj);
      if ( !deleteKey.isEmpty() ) {
        log.info("deleteKey..." + deleteKey);
        cacheObjectMap.remove(deleteKey);

      }

    }
  }

  @Override
  public String getValue(String key) {
    if ( !cacheObjectMap.containsKey(key) ) {
      return "Key does not exist";
    }
    CacheObject obj = cacheObjectMap.get(key);
    int heapIndex = cacheHeap.getIndexFromObject(obj);
    int objectCount = obj.getCount();
    obj.setCount(objectCount + 1);
    cacheObjectMap.put(key, obj);
    cacheHeap.increaseKey(obj, heapIndex);
    return obj.getValue();

  }

  @Override
  public String remove(String key) {
    return null;
  }

  @Override
  public void clearCache() {

  }


}