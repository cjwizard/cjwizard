/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author rcreswick
 *
 */
public class WizardSettings {
   private final Stack<IdMapTuple> _pageStack =
      new Stack<IdMapTuple>();

   private final Map<String, IdMapTuple> _oldPageMaps =
      new HashMap<String, IdMapTuple>();
   
   
   public WizardSettings(){
      // start with an empty new page:
      newPage("");
   }
   
   public Set<String> keySet(){
      Set<String> keys = new HashSet<String>();
      
      for (IdMapTuple tuple : _pageStack){
         keys.addAll(tuple.map.keySet());
      }
      return keys;
   }
   
   /**
    * 
    */
   public void rollBack() {
      _pageStack.pop();
   }

   /**
    * @param id
    */
   public void newPage(String id) {
      if (0 != _pageStack.size()){
         // if there was a previous map, then
         // store the previous page map by id:
         _oldPageMaps.put(current().id, current());
      }
      
      // If we've seen this ID before, use it again:
      IdMapTuple curTuple;
      if (_oldPageMaps.containsKey(id)){
         curTuple = _oldPageMaps.get(id);
      }else{
         curTuple = new IdMapTuple(id, new HashMap<String, Object>());
      }
      
      // push the new map:
      _pageStack.push(curTuple);
   }

   public Object put(String key, Object value) {
      return current().map.put(key, value);
   }
   
   public Object get(String key){
      Object value = null;
      
      
      for (int i=_pageStack.size()-1; null == value && i >= 0; i--){
         IdMapTuple tuple = _pageStack.get(i);
         value = tuple.map.get(key);
      }
      return value;
   }
   
   private IdMapTuple current(){
      if (0 == _pageStack.size())
         return null;
      
      return _pageStack.peek();
   }
   
   private class IdMapTuple{
      public final String id;
      public final Map<String, Object> map;
      
      public IdMapTuple(String id, Map<String, Object> map) {
         this.id = id;
         this.map = map;
      }
   }
   
   public String toString(){
      StringBuilder str = new StringBuilder("WizardSettings: ");
      
      for (String key : keySet()){
         str.append("["+key+"="+get(key)+"] ");
      }
      return str.toString();
   }
}
