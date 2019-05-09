package codemeek.dropwizard.example.service;

import java.util.*;

import org.slf4j.*;

import codemeek.dropwizard.example.models.CacheObject;

public class Heap {
  private static Logger log = LoggerFactory.getLogger(Heap.class);
  List<CacheObject> list;
  
  // to store key and object index
  private Map<String, Integer> cacheObjectMap;
  int capacity;
  int curSize;

  public Heap(int capacity) {
    this.capacity = capacity;
    list = new ArrayList<>();
    cacheObjectMap = new HashMap<>();
  }


  public void swap(int i, int j) {
    CacheObject obj = list.get(i);
    list.set(i, list.get(j));
    list.set(j, obj);
  }

  public void minHeapify(int i) {
    int l = left(i);
    int r = right(i);
    int smallest = i;
    if ( l < curSize && list.get(l).getCount() < list.get(i).getCount() )
      smallest = l;
    if ( r < curSize && list.get(r).getCount() < list.get(smallest).getCount() )
      smallest = r;
    if ( smallest != i ) {
      //swapping index also in map
      cacheObjectMap.put(list.get(smallest).getKey(), i);
      cacheObjectMap.put(list.get(i).getKey(), smallest);
      swap(i, smallest);
      minHeapify(smallest);
    }

  }

  private int parent(int i) {
    return (i - 1) / 2;
  }

  private int left(int i) {
    return (2 * i + 1);
  }

  private int right(int i) {
    return (2 * i + 2);
  }



  private void increaseKey(CacheObject obj, int index) {
    list.set(index, obj);
    minHeapify(index);
  }

  public String getValueFromCache(String key) {
    if ( !cacheObjectMap.containsKey(key) ) {
      return "Key does not exist!";
    }
    int index = cacheObjectMap.get(key);
    CacheObject obj = list.get(index);
    log.info("get object from cache is "+obj);
    obj.setCount(obj.getCount() + 1);
    increaseKey(obj, index);
    return obj.getValue();
  }

  public void setValueInCache(String key, String value) {
    if ( cacheObjectMap.containsKey(key) ) {
      int index = cacheObjectMap.get(key);
      CacheObject obj = list.get(index);
      obj.setValue(value);
      obj.setCount(obj.getCount() + 1);
      increaseKey(obj, index);
      log.info("object is already present " + obj);
    } else {
      if ( list.size() == capacity ) {
        log.info("removing object in full cache case" + list.get(0));
        cacheObjectMap.remove(list.get(0).getKey());
        list.remove(0);
        list.add(0, list.get(list.size()-1));
        list.remove(list.size() -1);
        minHeapify(0);
      }
      CacheObject obj = new CacheObject();
      obj.setCount(1);
      obj.setKey(key);
      obj.setValue(value);
      list.add(obj);
      int curIndex = list.size()-1;
      cacheObjectMap.put(key, curIndex);

      while (curIndex != 0 && list.get(parent(curIndex)).getCount() > list.get(curIndex).getCount()) {
        cacheObjectMap.put(list.get(curIndex).getKey(), parent(curIndex));
        cacheObjectMap.put(list.get(parent(curIndex)).getKey(), curIndex);
        swap(curIndex, parent(curIndex));
        curIndex = parent(curIndex);
      }
      log.info("inserted object is " + obj);

    }

  }



}
