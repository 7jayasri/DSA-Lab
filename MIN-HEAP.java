import java.util.*;

class MinHeap {
    int arr[];
    int heapSize, maxSize;

    MinHeap(int ms) {
        maxSize = ms;
        heapSize = 0;
        arr = new int[maxSize];
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int lchild(int i) {
        return (2 * i + 1);
    }

    int rchild(int i) {
        return (2 * i + 2);
    }

    void insertKey(int x) {
        if (heapSize == maxSize) {
            System.out.println("Overflow: Cannot insert key as heap is full.");
            return;
        }
        int i = heapSize;
        arr[i] = x;
        heapSize++;

        while (i != 0 && arr[parent(i)] > arr[i]) {
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }

    void removeMin() {
        if (heapSize <= 0) {
            System.out.println("Heap does not exist");
        } else if (heapSize == 1) {
            heapSize--;
        } else {
            arr[0] = arr[heapSize - 1];
            heapSize--;
            minHeapify(0);
        }
    }

    void minHeapify(int i) {
        int l = lchild(i);
        int r = rchild(i);
        int smallest = i;

     
        if (l < heapSize && arr[l] < arr[smallest]) {
            smallest = l;
        }

        if (r < heapSize && arr[r] < arr[smallest]) {
            smallest= r;
        }

        if (smallest != i) {
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;
            minHeapify(smallest);
        }
    }

    int getMin() {
        if (heapSize == 0) {
            System.out.println("Heap does not exist");
            return Integer.MAX_VALUE;
        } else {
            return arr[0];
        }
    }

    int curSize() {
        return heapSize;
    }

    public static void main(String args[]) {
        MinHeap h = new MinHeap(100);  
        int elements[] = {3, 10, 12, 8, 2, 14};
        
        for (int e : elements) {
            h.insertKey(e);
        }

        System.out.println("The current size of the heap is " + h.curSize());
        System.out.println("The current maximum element is " + h.getMin());

        h.removeMin();
        System.out.println("The current size of the heap is " + h.curSize());

        h.insertKey(15);
        h.insertKey(5);

        System.out.println("The current size of the heap is " + h.curSize());
        System.out.println("The current maximum element is " + h.getMin());
    }
}
