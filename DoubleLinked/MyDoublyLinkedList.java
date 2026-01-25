/**

    Partially stubbed implementation of a singly linked list, 
    prepped to begin transformation to a doubly linked list
    @author Dr. Christopher Summa, University of New Orleans
    @version 1.2.0

*/

public class MyDoublyLinkedList<AnyType>  {

    private Node first;                    
    private Node last;                       
    private int size;


    public MyDoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }
    
    public MyIterator iterator() {
        return new MyIterator();
    }

    // THIS WILL NEED TO BE MODIFIED FOR DOUBLY LINKED LIST
    public void add(AnyType element) {

        Node nodeToAdd = new Node(element);

        if (first == null) {
            first = nodeToAdd;
            last = nodeToAdd;
        }
        else {
            while(last != null) {
                last.getNextNode();
            }                
            last.setNextNode(nodeToAdd);;
            nodeToAdd.itsPrior= last;
        }
        size++;
    }

    // THIS WILL NEED TO BE MODIFIED FOR A DOUBLY LINKED LIST
    public void add(AnyType element, int index) {
        int counter = 0;
        Node nodeToInsert = new Node(element);
        Node current = first;
        while (current != null ) {
            if (counter == index - 1 )
                break;
            current = current.getNextNode();
            counter++;
        }
        nodeToInsert.setNextNode(current.getNextNode());
        current.setNextNode(nodeToInsert);
        size++;
    }

    public AnyType get(int index) {
        int counter = 0;
        Node current = first;
        while (current != null ) {
            if (counter == index)
                break;
            current = current.getNextNode();
            counter++;
        }
        return current.getData();
    }

    // TO BE IMPLEMENTED

    // returns the element at a particular index after the node is removed
    public AnyType remove(int index) {
        Node current = first;
        for (int i= 0; i < index; i++) {
            current= current.getNextNode();
        }
        Node nodetoreturn= current;
        return nodetoreturn.getData();
    }


    // returns true if element is in the list, false otherwise
    public boolean contains(AnyType element) {
        Node current = first;
        while (current.getNextNode() != null ) {
            current = current.getNextNode();
            if (current.getData() == element) {
                return true;
            }
        }
        return false;
    }

    // returns the index of the element if it is in the list, -1 if not found
    public int indexOf(AnyType element) {
        int counter = 0;
        Node current = first;
        while (current != element ) {
            counter++;
            current = current.getNextNode();
            if (current== null) {
                return -1;
            }
        }
        return counter;
    }

    // returns an Iterator at the location of the element if it is in the list
    // if the element is not found returns the null reference 
    public MyIterator iteratorAt(AnyType element) {
        MyIterator point= iterator();
        if (first == element) {
            return point;
        }  
        else {
            while (point.hasNext()) {
                Node current= new Node(point.next());
                    if (current.getData() == element) {
                        return point;
                    }
            }
            return null;
        }
    }

    // destructively overwrites the element in the Node that
    // represents a particular index in the linked list
    public void set(int index, AnyType element) {
        Node nodeToInsert = new Node(element);
        Node current = first;
        for (int i= 1; i < index; i++) {
            current= current.getNextNode();
        }
        current.setNextNode(nodeToInsert);
    }


    public String toString() {
        String returnVal = "";
        Node current = first;
        if (size != 0 ) {
            while (current != null ) {
                returnVal += current.toString();
                returnVal += "\n";
                current = current.getNextNode();
            }
        }
        return returnVal;
    }  // end toString

    private class Node {
    
        private AnyType element;
        private Node itsNext;
        private Node itsPrior;
    
        public Node(AnyType element) {
            this.itsNext = null;
            this.itsPrior = null;
            this.element = element;
        }
    
    
        public AnyType getData() {
            return this.element;
        }
    
        public Node getNextNode() {
            return itsNext;
        }

        // TO BE IMPLEMENTED

        public Node getPriorNode() {
            return itsPrior;
        }

    
        public void setNextNode(Node next) {
            itsNext = next;
        }

        // TO BE IMPLEMENTED

        public void setPriorNode(Node prior) {
            itsPrior= prior;
        }

    
        public String toString() {
            return element.toString();
        }
    
    }  // end of Node

    // NOTE:  update this next inner class to be a MyListIterator 
    //        since it will now be able to move in both directions
    // SEE:  https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
    //       for how the ListIterator should behave

    // member class MyIterator
    public class MyIterator {
    
        private Node myCurrentNode;
    
        public MyIterator() {
            myCurrentNode =  MyDoublyLinkedList.this.first;
        }
    
        // return true if there is a "next" element, otherwise returns false
        public boolean hasNext() {
            if (myCurrentNode != null)
                return true;
            return false;
        }
    
        // TO BE IMPLEMENTED
 
        // return true if there is a "prior" element, otherwise returns false
        public boolean hasPrior() {
            if(myCurrentNode.itsPrior == null) {
                return false;
            }
            else {
                return true;
            }
        }

    
        // returns the "next" element and
        // moves the Iterator forward by one node
        public AnyType next() {
            AnyType returnValue = myCurrentNode.getData();
            myCurrentNode = myCurrentNode.getNextNode();
            return (AnyType)returnValue;
        }
    
        // TO BE IMPLEMENTED

        // returns the "prior" element and
        // moves the Iterator backward by one node
        public AnyType prior() {
            AnyType returnValue = myCurrentNode.getData();
            myCurrentNode = myCurrentNode.getPriorNode();
            return (AnyType)returnValue;
        }
    
        // Sets this iterator to point to the last Node in the list
        public void setToEnd() {
            while (hasNext()) {
                next();
            }
        }

    } // end class MyIterator
} // end class MyLinkedList

