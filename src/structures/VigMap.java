package structures;

import java.util.Map;
import java.util.Objects;

/**
 * HashMap implementation
 * @author shaun.viguerie
 *
 * @param <K>
 * @param <V>
 */
public class VigMap<K, V> {

	private double loadFactor = 0.75;

	private Entry<K, V>[] table;

	private int elemCount = 1 << 4;
	private int size = 0;
	public static class Entry<K, V> {
	
		int hash;
		final K key;
		V value;
		Entry<K, V> next;

		// constructors, getters, setters below
		protected Entry(int hash, K k, V v, Entry<K, V> next) {
			this.hash = hash;
			this.key = k;
			this.value = v;
			this.next = next;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			if (value == null)
				throw new NullPointerException();

			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
		
		public void setNext(Entry<K,V> entry) {
			next = entry;
		}
		
		public boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;

			return key.equals(e.getKey()) && value.equals(e.getValue());
		}

		public int hashCode() {
			return (Objects.hashCode(key) ^ Objects.hashCode(value));
		}

		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}

	/**
	 * 
	 * 
	 * Insert your super-mega-hash-function below :)
	 */
	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	public void put(K key, V value) {
		
		if (size > table.length * loadFactor) { //  || size >= elemCount ) {
			resize();
		}
		
		int index = hash(key.hashCode()) & table.length - 1;
		
		if (table[index] == null) {
			table[index] = new Entry<K, V>(index, key, value, null);
			size++;
		}	
		else {
			Entry<K, V> cur = table[index];
			while (true) {
				if (cur.getKey().equals(key)) {
					cur.setValue(value);  // if both KEYS are equal -- overwrite prev value!
					break;
				}
				if (cur.next == null)
					break;
				cur = cur.next;
			}
			cur.setNext(new Entry<K, V>(index, key, value, null));
			 size++;
		}
	}

	public V get(K key) {
		int index = hash(key.hashCode()) & table.length -1;
		if (table[index] == null)
			return null;
		else {
			Entry<K, V> cur = table[index];
			while (true) {
				if (cur.getKey().equals(key)) {
					return (V) cur.getValue();
				}
				if (cur.next == null)
					break;
				cur = cur.next;
			}
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void resize() {
		System.out.println("resizing!");
		int newSize = (int) (table.length * 2); // 1.5);
		
		Entry<K, V>[] newTable = new Entry[newSize];

		for (int i = 0; i < table.length; i++) {			
			//newTable[i] = table[i];
			
			Entry<K, V> temp = table[i];
			if(temp != null) {
				while(temp != null) {
					Entry<K, V> next = temp.next;
					int newIndex = hash(temp.getKey().hashCode()) & newTable.length - 1;
					temp.next = newTable[newIndex];
					newTable[newIndex] = temp;
					temp = next;
				}
			} else {
				newTable[i] = table[i];
			}
		}
		elemCount = (int)( newTable.length * loadFactor);
		table = newTable;
	}
	
	
	@SuppressWarnings("unchecked")
	public VigMap() {
	     table = new Entry[elemCount]; 
	}

}
