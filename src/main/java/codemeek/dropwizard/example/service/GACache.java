package codemeek.dropwizard.example.service;

/**
 * Created by jitendragangwar on 5/8/17.
 */
public interface GACache {
    // set a value in cache with given (key value) pair
    // if already exists then overrides previous value
    void setValue(String key,String value);
    // return corresponding value for this key
    // if no value then return null
    String getValue(String key);
    // remove key from cache if present if not present then do nothing
    String remove(String key);
    // remove all entries from cache
    void clearCache();
}
