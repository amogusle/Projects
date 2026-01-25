public class Map<KeyType,ValueType>{
    
    private SeparateChainingHashTable<MapEntry<KeyType,ValueType>> elements;

    public Map() {
        elements= new SeparateChainingHashTable<>();
    }

    public void put(KeyType key, ValueType val) {
        MapEntry<KeyType, ValueType> map= new MapEntry<>(key, val);
        
        elements.insert(map);
    }

    public ValueType get(KeyType key) {
        MapEntry<KeyType, ValueType> map= elements.get(new MapEntry<>(key, null));
        
        if (map != null) {
            return map.getVal();
        } 
        
        return null;
    }

    public boolean isEmpty( ) {
        return elements.isEmpty();
    }

    public void makeEmpty( ) {
        elements.makeEmpty();
    }

    private static class MapEntry<KeyType,ValueType> {
        private KeyType key;
        private ValueType value;

        public MapEntry(KeyType key, ValueType value) {
            this.key= key;
            this.value= value;
        }

        public KeyType getKey() {
            return key;
        }

        public ValueType getVal() {
            return value;
        }

        public void setVal(ValueType value) {
            this.value = value;
        }
    } // end class MapEntry
} // end class Map