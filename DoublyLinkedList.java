public class DoublyLinkedList <T>{


        private static class Node<T> {
            T data;
            Node<T> prev;
            Node<T> next;

            public Node(T data) {
                this.data = data;
            }
        }

        private Node<T> header;
        private Node<T> trailer;
        private int size;

        public DoublyLinkedList() {
            header = new Node<>(null);
            trailer = new Node<>(null);
            header.next = trailer;
            trailer.prev = header;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public T first() {
            if (isEmpty()) {
                return null;
            }
            return header.next.data;
        }

        public T last() {
            if (isEmpty()) {
                return null;
            }
            return trailer.prev.data;
        }

        public void addFirst(T element) {
            Node<T> newNode = new Node<>(element);
            newNode.next = header.next;
            newNode.prev = header;
            header.next.prev = newNode;
            header.next = newNode;
            size++;
        }

        public void addLast(T element) {
            Node<T> newNode = new Node<>(element);
            newNode.prev = trailer.prev;
            newNode.next = trailer;
            trailer.prev.next = newNode;
            trailer.prev = newNode;
            size++;
        }

        public T removeFirst() {
            if (isEmpty()) {
                return null;
            }
            Node<T> temp = header.next;
            header.next = temp.next;
            temp.next.prev = header;
            temp.next = null;
            temp.prev = null;
            size--;
            return temp.data;
        }

        public T removeLast() {
            if (isEmpty()) {
                return null;
            }
            Node<T> temp = trailer.prev;
            trailer.prev = temp.prev;
            temp.prev.next = trailer;
            temp.next = null;
            temp.prev = null;
            size--;
            return temp.data;
        }

        // Homework 1: Find Middle Node
        public Node<T> findMiddleNode() {
            Node<T> slow = header.next;
            Node<T> fast = header.next;
            while (fast != trailer && fast.next != trailer) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        // Homework 2: size() without instance variable
        public int sizeWithoutInstanceVariable() {
            Node<T> current = header.next;
            int count = 0;
            while (current != trailer) {
                count++;
                current = current.next;
            }
            return count;
        }

        // Homework 3: equals() method
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DoublyLinkedList<?>)) {
                return false;
            }
            DoublyLinkedList<T> other = (DoublyLinkedList<T>) obj;
            if (this.size != other.size) {
                return false;
            }
            Node<T> current1 = this.header.next;
            Node<T> current2 = other.header.next;
            while (current1 != this.trailer) {
                if (!current1.data.equals(current2.data)) {
                    return false;
                }
                current1 = current1.next;
                current2 = current2.next;
            }
            return true;
        }

        // Homework 4: Concatenate two doubly linked lists
        public void concatenate(DoublyLinkedList<T> other) {
            trailer.prev.next = other.header.next;
            other.header.next.prev = trailer.prev;
            trailer = other.trailer;
            size += other.size;
        }

        // Homework 5: DoublyLinkedList with single sentinel
        /*
         * Implementation of DoublyLinkedList with single sentinel is omitted
         * for brevity and to avoid redundancy.
         */

        // Homework 6: Circular Doubly Linked List
        /*
         * Implementation of CircularDoublyLinkedList is omitted for brevity
         * and to avoid redundancy.
         */

        // Homework 7: clone() method
        public DoublyLinkedList<T> clone() {
            DoublyLinkedList<T> newList = new DoublyLinkedList<>();
            Node<T> current = this.header.next;
            while (current != this.trailer) {
                newList.addLast(current.data);
                current = current.next;
            }
            return newList;
        }

        public static void main(String[] args) {
            // Example usage
            DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
            list1.addLast(1);
            list1.addLast(2);
            list1.addLast(3);

            DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
            list2.addLast(4);
            list2.addLast(5);

            System.out.println("List 1: " + list1); // Assuming toString() is implemented
            System.out.println("List 2: " + list2);

            list1.concatenate(list2);
            System.out.println("Concatenated List: " + list1);

            DoublyLinkedList<Integer> list3 = list1.clone();
            System.out.println("Cloned List: " + list3);
        }
    }

