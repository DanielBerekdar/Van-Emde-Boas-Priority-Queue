public class BinaryHeapPriorityQueue{
    private int [] items;
    private int size;
    private int maxSize;

    BinaryHeapPriorityQueue(int n){
        this.maxSize = n;
        this.size = 0;
        this.items = new int[this.maxSize++];
    }


    /* INSERT
        MAX-HEAP-INSERT(A, key)
     1 A.heap-size = A:heap-size + 1
     2 A[A:heap-size] = -Infinity
     3 HEAP-INCREASE-KEY.A; A:heap-size; key/
     */

    public void Insert(int key){
        size++;
        items[size] = Integer.MIN_VALUE;
        IncreaseKey(size-1, key);
    }

    private void swap(int item1, int item2){
        int temp = item1;
        item1 = item2;
        item2 = temp;
    }
    /*      INCREASE KEY
            IncreaseKey(A, i, key)
            if key < a[i]
                error"new key is smaller than current key"
              a[i] = key
              while(i > 1 and A[PARENT(i) < A[i]
                    exchange A[i] with A[Parent(i)]
                    i = parent(i)
     */

    private void IncreaseKey(int i, int key){
        if (key < items[i]) {
            System.out.println("Error new key is smaller than current key");
        }
        while (i > 0 && items[Parent(i)] < key) {
            items[i] = items[Parent(i)];
            i = Parent(i);
        }
        items[i] = key;
    }

    /*
        HEAP-EXTRACT-MAX(A)
        1 if A:heap-size < 1
        2 error “heap underflow”
        3 max = AŒ1
        4 AŒ1 = AŒA:heap-size
        5 A:heap-size = A:heap-size - 1
        6 MAX-HEAPIFY.A; 1)
        7 return max
     */

    public int ExtractMax(){
        int max = items[0];
        items[0] = items[size - 1];
        size--;
        MaxHeapify(items,0);
        return max;
    }

    private int Parent(int index){
        return index / 2;
    }
    public int getSize(){
        return size;
    }

    private void MaxHeapify(int[] items, int i) {
        int left = 2*i+1;
        int right = 2*i+2;
        int largest =i;
        if (left < items.length && items[left]>items[largest])
            largest = left;
        if (right < items.length && items[right]>items[largest])
            largest = right;
        if (largest != i) {
            int temp = items[i];
            items[i] = items[largest];
            items[largest] = temp;
            MaxHeapify(items, largest);
        }
    }

}