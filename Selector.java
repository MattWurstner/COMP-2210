import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Matt Wurstner (msw0049@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  01.27.2020
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }

   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param c    The collection where we will find min.
    * @param comp The comparator that defines T.
    * @throws IllegalArgumentException Defined in assignment instructions.
    * @throws NoSuchElementException Defined in assignment instructions.
    * @return     The minimum value within c.
    */
   public static <T> T min(Collection<T> c, Comparator<T> comp) {
   // Check input for null.
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
   // Check if input is empty.
      if (c.isEmpty()) {
        throw new NoSuchElementException();
      }
      // Generalize the min method.
      Iterator<T> itr = c.iterator();
      T min = itr.next();
      while (itr.hasNext()) {
        // Hold the value for the time being.
        T hold = itr.next();
        if (comp.compare(hold, min) < 0) {
          min = hold;
        }
      }
      return min;
   }
  
   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param c    The collection where we will find max.
    * @param comp Comparator that defines T.
    * @throws IllegalArgumentException Defined in assignment instructions.
    * @throws NoSuchElementException Defined in assignment instructions.
    * @return     The maximum value within c.
    */
   public static <T> T max(Collection<T> c, Comparator<T> comp) {
      // Check input for null.
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      // Check input for empty.
      if (c.isEmpty()) {
        throw new NoSuchElementException();
      }

      Iterator<T> itr = c.iterator();
      T max = itr.next();
      while (itr.hasNext()) {
        T hold = itr.next();
        if (comp.compare(hold, max) > 0) {
          max = hold;
        }
      }
      return max;
   }

   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param coll    Collection from which the kth minimum is selected
    * @param k       k-selection value
    * @param comp    Comparator that defines the total order of T
    * @param <T>     General data type
    * @return        kth minimum value in c
    * @throws IllegalArgumentException
    * @throws NoSuchElementException
    *
    */
   public static <T> T kmin(Collection<T> c, int k, Comparator<T> comp) {
      
      //Check collectionfor null or empty, comp is null.
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      //Check if collection is empty.
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }

      // Create Arraylist of collection and sort.
      List<T> copyList = new ArrayList(c);
      java.util.Collections.sort(copyList, comp);
      
      // Check if k < 0 or k > array length.
      if (k > copyList.size() || k <= 0) {
         throw new NoSuchElementException();
      }

      // Count the number of unique values, remove duplicates.
      int dupCount = 0;
      int uniTot = 0;
      int origTot = copyList.size();

      for (int i = 0; i < copyList.size() - 1; i++) {
         while (copyList.size() > 1 && i < copyList.size() - 1 && copyList.get(i) == copyList.get(i + 1)) {
            copyList.remove(i);
            dupCount++;
         }
      }
      
      uniTot = origTot - dupCount;
      
      // k is greater than the number of unique values.
      if (k > uniTot) {
         throw new NoSuchElementException();
      }
      
      return copyList.get(k - 1);
   }

   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    *
    *
    */
   public static <T> T kmax(Collection<T> c, int k, Comparator<T> comp) {
      
      //Check collectionfor null or empty, comp is null.
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      //Check if collection is empty.
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }

      // Create Arraylist of collection and sort.
      List<T> copyList = new ArrayList(c);
      java.util.Collections.sort(copyList, comp);
      
      // Check if k < 0 or k > array length.
      if (k > copyList.size() || k <= 0) {
         throw new NoSuchElementException();
      }

      // Count the number of unique values, remove duplicates.
      int dupCount = 0;
      int uniTot = 0;
      int origTot = copyList.size();

      for (int i = 0;i < copyList.size();i++) {
         while (copyList.size() > 1 && i < copyList.size() - 1 && copyList.get(i) == copyList.get(i + 1)) {
            copyList.remove(i);
            dupCount++;
         }
      }
      
      uniTot = origTot - dupCount;
      
      // k is greater than the number of unique values.
      if (k > uniTot) {
         throw new NoSuchElementException();
      }
      
      return copyList.get(uniTot - k);
   }

   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static <T> Collection<T> range(Collection<T> c, T low, T high,
Comparator<T> comp) {
      // Check for null.
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      // Checks if collection is empty.
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      // Make arraylist of original and arraylist for the range
      // Set j for number of qualifying values
      List<T> copyList = new ArrayList(c);
      List<T> range = new ArrayList(c);
      int j = 0;
      
      /* New copy so that the values within range listed are first in the array*/
      for (int i = 0; i < copyList.size(); i++) {
         if ((comp.compare(copyList.get(i), low) >= 0)
            && (comp.compare(copyList.get(i), high) <= 0)) {
            range.set(j, copyList.get(i));
            j++;
         }
      }
      
      // No values in the arraylist fall within the range.
      if (j == 0) {
         throw new NoSuchElementException();
      }
      
      // Delete extra values of range.
      for (int i = range.size() - 1; i > j - 1; i--) {
         range.remove(i);
      }
      
      return range;
   }

   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static <T> T ceiling(Collection<T> c, T key, Comparator<T> comp) {
   
      // a is null or has a length of 0
      if (c == null || comp == null) {
         throw new IllegalArgumentException(); 
      }
      
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T ceiling = null;
      Iterator<T> itr = c.iterator();
      boolean found = false; //Tells if first possible ceiling found
      while (itr.hasNext()) {
         T temp = itr.next();
         //if haven't found first ceiling, just compare to key.
         if (!found && comp.compare(temp, key) >= 0) {
            ceiling = temp;
            found = true;
         }
         //if found first ceiling, compare to key and current ceiling value
         else if (comp.compare(temp, key) >= 0 && comp.compare(temp, ceiling) <= 0) {
            ceiling = temp;
         }
      }
      if (!found) {
         throw new NoSuchElementException();
      }
      return ceiling;
   }
   
   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static <T> T floor(Collection<T> c, T key, Comparator<T> comp) {
      // a is null or has a length of 0
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      // Minimum value
      Iterator<T> itr = c.iterator();
      T floor = itr.next();
      if (itr.hasNext()) {
      
         for (T element: c) {
            if (comp.compare(element, floor) < 0) {
               floor = element;
            }
         }
      
      }
      
      
      int change = 0;
      
      // Lowest value equal to or above the key.
      for (T element: c) {
         if ((comp.compare(element, key) <= 0)
            && (comp.compare(element, floor) >= 0)) {
            floor = element;
            change++;
         }
      }
      
      // No qualifying value for ceiling.
      if (change == 0) {
         throw new NoSuchElementException();
      }
      
      return floor;
   }
}