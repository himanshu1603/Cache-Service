package codemeek.dropwizard.example.service;

import java.util.*;

import org.slf4j.*;

import codemeek.dropwizard.example.models.CacheObject;

public class Heap {
  private static Logger log = LoggerFactory.getLogger(Heap.class);
  List<CacheObject> list;
  int capacity;
  int curSize;

  public Heap(int capacity) {
    this.capacity = capacity;
    list = new ArrayList<>();
  }


  public void swap(int i, int j) {
    CacheObject obj = list.get(i);
    list.add(i, list.get(j));
    list.add(j, obj);
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
      swap(i, parent(i));
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
  
  
// This return key of object which we need to delete in overflow condition
  private String deleteMin() {
    if ( curSize <= 0 )
      return null;
    if ( curSize == 1 ) {
      curSize--;
      return list.get(0).getKey();
    }

    String root = list.get(0).getKey();
    list.add(0, list.get(curSize - 1));
    curSize--;
    minHeapify(0);

    return root;

  }


  // This will update the given object with given index
  public void increaseKey(CacheObject obj, int index) {
    list.set(index, obj);
    minHeapify(index);
  }


  public int getIndexFromObject(CacheObject obj) {
    return list.indexOf(obj);
  }

  public void setObjectAtIndex(int i, CacheObject obj) {
    list.set(i, obj);
    minHeapify(i);
  }

  public String insertKey(CacheObject obj) {
    String key = "";
    if ( curSize == capacity ) {
      log.info("overflow condition.........");
      key = deleteMin();
    }
    
    log.info("size is" + curSize);
    curSize++;
    int i = curSize - 1;
    list.add(i, obj);

    while (i != 0 && list.get(parent(i)).getCount() > list.get(i).getCount()) {
    	swap(i, parent(i));
//      CacheObject swp = list.get(i);
//      list.add(i, list.get(parent(i)));
//      list.add(parent(i), swp);
      
      i = parent(i);
    }
    return key;


  }


}