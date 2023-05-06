package Classes;

public class SLLNode<T> {
    protected T info;
    protected SLLNode<T> next;

    public SLLNode() {
        this(null, null);
    }

    public SLLNode(T el) {
        this(el, null);
    }

    public SLLNode(T el, SLLNode<T> ptr) {
        info = el;
        next = ptr;
    }
}