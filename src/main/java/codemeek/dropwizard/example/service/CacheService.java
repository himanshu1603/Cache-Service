package codemeek.dropwizard.example.service;

public class CacheService implements GACache {

  private Heap cacheHeap;


  public CacheService() {
    cacheHeap = new Heap(5);
  }

  @Override
  public void setValue(String key, String value) {
    cacheHeap.setValueInCache(key, value);
  }

  @Override
  public String getValue(String key) {
    return cacheHeap.getValueFromCache(key);
  }

  @Override
  public String remove(String key) {
    return null;
  }

  @Override
  public void clearCache() {

  }


}
