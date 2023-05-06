//**************************  SLL.java  *********************************
//           a generic singly linked list class 
package Classes;

public class SLL<T> {
    protected SLLNode<T> head, tail;

    public SLL() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToHead(T el) {
        head = new SLLNode<T>(el, head);
        if (tail == null)
            tail = head;
    }

    public void addToTail(T el) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(el);
            tail = tail.next;
        } else
            head = tail = new SLLNode<T>(el);
    }

    public T deleteFromHead() { // delete the head and return its info;
        if (isEmpty())
            return null;
        T el = head.info;
        if (head == tail) // if only one node on the list;
            head = tail = null;
        else
            head = head.next;
        return el;
    }

    public T deleteFromTail() { // delete the tail and return its info;
        if (isEmpty())
            return null;
        T el = tail.info;
        if (head == tail) // if only one node in the list;
            head = tail = null;
        else { // if more than one node in the list,
            SLLNode<T> tmp; // find the predecessor of tail;
            for (tmp = head; tmp.next != tail; tmp = tmp.next)
                ;
            tail = tmp; // the predecessor of tail becomes tail;
            tail.next = null;
        }
        return el;
    }

    public void delete(T el) { // delete the node with an element el;
        if (!isEmpty())
            if (head == tail && el.equals(head.info)) // if only one
                head = tail = null; // node on the list;
            else if (el.equals(head.info)) // if more than one node on the list;
                head = head.next; // and el is in the head node;
            else { // if more than one node in the list
                SLLNode<T> pred, tmp;// and el is in a nonhead node;
                for (pred = head, tmp = head.next; tmp != null
                        && !tmp.info.equals(el); pred = pred.next, tmp = tmp.next)
                    ;
                if (tmp != null) { // if el was found;
                    pred.next = tmp.next;
                    if (tmp == tail) // if el is in the last node;
                        tail = pred;
                }
            }
    }

    @Override
    public String toString() {
        if (head == null)
            return "[ ]";
        String str = "[ ";
        SLLNode<T> tmp = head;
        while (tmp != null) {
            str += tmp.info + " ";
            tmp = tmp.next;
        }
        return str + "]";
    }

    public boolean contains(T el) {
        if (head == null)
            return false;
        SLLNode<T> tmp = head;
        while (tmp != null) {
            if (tmp.info.equals(el))
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    public int size() {
        if (head == null)
            return 0;

        int count = 0;
        SLLNode<T> p = head;
        while (p != null) {
            count++;
            p = p.next;
        }

        return count;
    }

    // Please write the methods of Task02 here:
    public void insertBefore(int index, T newElement) throws Exception {
        if (this.isEmpty()) {
            throw new Exception("List is empty");
        } else if (index == this.size()) {
            throw new Exception("Invalid index");
        } else if (index != 0) {
            SLLNode<T> tmp = this.head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            tmp.next = new SLLNode<T>(newElement, tmp.next);
        } else {
            this.head = new SLLNode<T>(newElement, this.head);
        }
    }

    public T delete(int index) throws Exception {
        if (this.isEmpty()) {
            throw new Exception("List is empty");
        } else if (index >= this.size()) {
            throw new Exception("Invalid index");
        } else if (index != 0) {
            SLLNode<T> tmp = this.head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            T element = tmp.next.info;
            if (tmp.next.next == null) {
                tmp.next = null;
            } else
                tmp.next = tmp.next.next;
            return element;
        } else {
            T element = this.head.info;
            this.head = this.head.next;
            return element;
        }
    }

    public void insertAfterSecondOccurence(T element1, T element2) throws Exception {
        if (this.isEmpty()) {
            throw new Exception("List is empty");
        } else {
            int counter = 0;
            SLLNode<T> tmp = this.head;
            for (int i = 0; i < this.size(); i++) {
                if (element2.equals(tmp.info)) {
                    counter++;
                }
                if (counter == 2) {
                    tmp.next = new SLLNode<T>(element1, tmp.next);
                    break;
                }
                tmp = tmp.next;
            }
            if (counter != 2) {
                throw new Exception("No second occurence");
            }
        }
    }
}