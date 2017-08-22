/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  public int hashsize ;
  protected int entryNumber =0;
  protected int collision =0;
  protected SList[] hashlist;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    while (!isPrime(sizeEstimate)){
      sizeEstimate++;
    }
    hashlist = new SList[sizeEstimate];
    hashsize = sizeEstimate;

  }

  public boolean isPrime(int n){
    for(int i = 2; i<n ;i++){
      if (n%i == 0){
        return false;
      }
    }
    return true;
  }
  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    hashsize = 101;
    hashlist = new SList[hashsize];
    
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    int a=3;
    int b=5;
    int p=131;
    int compressed_value = ((a * code + b) % p) % hashlist.length;

    if (compressed_value < 0) {
      compressed_value = compressed_value + hashlist.length;
    }
    return compressed_value;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entryNumber;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return entryNumber==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry item = new Entry();
    item.key = key;
    item.value = value;

    int compressed_value = compFunction(item.key.hashCode());
    if (hashlist[compressed_value]==null){
      hashlist[compressed_value] = new SList();
      hashlist[compressed_value].insertFront(item);
    }else{
      hashlist[compressed_value].insertFront(item);
      collision++;
    }
    entryNumber++;

    return item;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int compressed_value = compFunction(key.hashCode());
    SList hash = hashlist[compressed_value];
    try{
      ListNode cur = hash.front();
      while (cur.isValidNode()){
        if(((Entry)cur.item()).key().equals(key)){
          return ((Entry)cur.item());
        }
        cur = cur.next();
      }
    } catch(InvalidNodeException e){
      System.out.println(e);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    SList hash = hashlist[compFunction(key.hashCode())];
    ListNode cur = hash.front();
    try{

         while (cur.isValidNode()){
          if (((Entry) cur.item()).key().equals(key)) {
            Entry entry = (Entry) cur.item();
            cur.remove();
            entryNumber--;
            return entry;
          } else {
            cur = cur.next();
          }
        }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }

    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    hashlist = new SList[hashlist.length];
  }

}
