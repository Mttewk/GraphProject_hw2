public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public E removeAt(int index) {
        checkIndex(index);
        E old = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return old;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void grow() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}