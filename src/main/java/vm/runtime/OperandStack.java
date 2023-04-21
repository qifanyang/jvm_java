package vm.runtime;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class OperandStack {

    private Stack<Object> stack;
    private int maxSize;

    public OperandStack(int size) {
        stack = new Stack<>();
        maxSize = size;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Iterator<Object> iterator() {
        return stack.iterator();
    }

    public Object pop() {
        return stack.pop();
    }

    public void push(Object o) {
        stack.push(o);
    }

    public Object peek() {
        return stack.peek();
    }

    public void dup() {
        Object top = stack.peek();
        push(top);
    }

}
