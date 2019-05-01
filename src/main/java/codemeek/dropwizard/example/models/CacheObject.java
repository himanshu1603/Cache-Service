package codemeek.dropwizard.example.models;

public class CacheObject {
    private String key;
    private String value;
    private int count;
    
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CacheObject [key=" + key + ", value=" + value + ", count=" + count + "]";
	}
	

    
}
