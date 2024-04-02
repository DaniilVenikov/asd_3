public class DynArray<T>
{
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz)
    {
        clazz = clz;

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity)
    {
        if (array == null) {
            array = (T[]) Array.newInstance(clazz, new_capacity);
        } else {
            T[] newArray = (T[]) Array.newInstance(clazz, new_capacity);
            System.arraycopy(array, 0, newArray, 0, Math.min(count, new_capacity));
            array = newArray;
        }
        capacity = new_capacity;
    }

    public T getItem(int index)
    {
        checkIndex(index);
        return array[index];
    }

    public void append(T itm)
    {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index)
    {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (count == capacity) {
            makeArray(capacity * 2);
        }

        if (index == count) {
            array[count] = itm;
        } else {
            System.arraycopy(array, index, array, index + 1, count - index);
            array[index] = itm;
        }
        count++;
    }

    public void remove(int index)
    {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, count - index - 1);
        count--;

        if (count < capacity / 2) {
            makeArray(Math.max((int)(capacity/1.5), 16));
        }
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}

