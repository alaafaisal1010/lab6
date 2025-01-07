import java.util.Scanner;

public class StackExample {




        // Stack Interface
        public interface Stack<E> {
            boolean isEmpty();
            int size();
            E top();
            void push(E e);
            E pop();
            Stack<E> clone(); // Added for cloning
        }

        // ArrayStack Implementation
        public static class ArrayStack<E> implements Stack<E> {
            private E[] data;
            private int topIndex;

            @SuppressWarnings("unchecked")
            public ArrayStack(int capacity) {
                data = (E[]) new Object[capacity];
                topIndex = -1;
            }

            public ArrayStack() {

            }

            @Override
            public boolean isEmpty() {
                return topIndex == -1;
            }

            @Override
            public int size() {
                return topIndex + 1;
            }

            @Override
            public E top() {
                if (isEmpty()) return null;
                return data[topIndex];
            }

            @Override
            public void push(E e) {
                if (topIndex + 1 == data.length) throw new IllegalStateException("Stack is full");
                data[++topIndex] = e;
            }

            @Override
            public E pop() {
                if (isEmpty()) return null;
                E item = data[topIndex];
                data[topIndex--] = null; // Avoid loitering
                return item;
            }

            @Override
            public ArrayStack<E> clone() {
                ArrayStack<E> clone = new ArrayStack<>(data.length);
                for (int i = 0; i <= topIndex; i++) {
                    clone.push(data[i]);
                }
                return clone;
            }
        }

        // LinkedListStack Implementation
        public static class LinkedListStack<E> implements Stack<E> {
            private static class Node<E> {
                E data;
                Node<E> next;
                Node(E data) {
                    this.data = data;
                }
            }

            private Node<E> topNode;

            @Override
            public boolean isEmpty() {
                return topNode == null;
            }

            @Override
            public int size() {
                int count = 0;
                Node<E> current = topNode;
                while (current != null) {
                    count++;
                    current = current.next;
                }
                return count;
            }

            @Override
            public E top() {
                if (isEmpty()) return null;
                return topNode.data;
            }

            @Override
            public void push(E e) {
                Node<E> newNode = new Node<>(e);
                newNode.next = topNode;
                topNode = newNode;
            }

            @Override
            public E pop() {
                if (isEmpty()) return null;
                E item = topNode.data;
                topNode = topNode.next;
                return item;
            }

            @Override
            public LinkedListStack<E> clone() {
                LinkedListStack<E> clone = new LinkedListStack<>();
                Stack<E> tempStack = new ArrayStack<>(size());
                while (!isEmpty()) {
                    tempStack.push(pop());
                }
                while (!tempStack.isEmpty()) {
                    E item = tempStack.pop();
                    push(item);
                    clone.push(item);
                }
                return clone;
            }
        }

        // Transfer Method
        public static <E> void transfer(Stack<E> S, Stack<E> T) {
            Stack<E> tempStack = new ArrayStack<>(S.size());
            while (!S.isEmpty()) {
                tempStack.push(S.pop());
            }
            while (!tempStack.isEmpty()) {
                T.push(tempStack.pop());
            }
        }

        // Recursive Method to Clear Stack
        public static <E> void clear(Stack<E> stack) {
            if (!stack.isEmpty()) {
                stack.pop();
                clear(stack);
            }
        }

        // Evaluate Postfix Expression
        public static int evaluatePostfix(String expression) {
            Stack<Integer> stack = new ArrayStack<>();
            String[] tokens = expression.split(" ");

            for (String token : tokens) {
                if (isNumber(token)) {
                    stack.push(Integer.parseInt(token));
                } else {
                    int b = stack.pop();
                    int a = stack.pop();
                    int result = performOperation(a, b, token);
                    stack.push(result);
                }
            }
            return stack.pop();
        }

        // Helper Methods
        private static boolean isNumber(String token) {
            try {
                Integer.parseInt(token);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private static int performOperation(int a, int b, String operator) {
            switch (operator) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/": return a / b;
                default: throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        // Main Method
        public static void main(String[] args) {
            // Example usage of the stack and postfix evaluation
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter a postfix expression: ");
            String expression = scanner.nextLine();
            int result = evaluatePostfix(expression);

            System.out.println("Result: " + result);

            // Example of stack transfer
            ArrayStack<Integer> stack1 = new ArrayStack<>(5);
            ArrayStack<Integer> stack2 = new ArrayStack<>(5);
            stack1.push(1);
            stack1.push(2);
            stack1.push(3);

            transfer(stack1, stack2);
            System.out.println("Transferred stack top: " + stack2.top());

            // Clear stack example
            clear(stack2);
            System.out.println("Stack empty after clear: " + stack2.isEmpty());

            scanner.close();
        }
    }

