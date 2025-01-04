import java.util.*;

class MaxHeap {
    int arr[];
    int heapSize, maxSize;

    MaxHeap(int ms) {
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

        while (i != 0 && arr[parent(i)] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }

    void removeMax() {
        if (heapSize <= 0) {
            System.out.println("Heap does not exist");
        } else if (heapSize == 1) {
            heapSize--;
        } else {
            arr[0] = arr[heapSize - 1];
            heapSize--;
            maxHeapify(0);
        }
    }

    void maxHeapify(int i) {
        int l = lchild(i);
        int r = rchild(i);
        int largest = i;

     
        if (l < heapSize && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxHeapify(largest);
        }
    }

    int getMax() {
        if (heapSize == 0) {
            System.out.println("Heap does not exist");
            return Integer.MIN_VALUE;
        } else {
            return arr[0];
        }
    }

    int curSize() {
        return heapSize;
    }

    public static void main(String args[]) {
        MaxHeap h = new MaxHeap(100);  
        int elements[] = {3, 10, 12, 8, 2, 14};
        
        for (int e : elements) {
            h.insertKey(e);
        }

        System.out.println("The current size of the heap is " + h.curSize());
        System.out.println("The current maximum element is " + h.getMax());

        h.removeMax();
        System.out.println("The current size of the heap is " + h.curSize());

        h.insertKey(15);
        h.insertKey(5);

        System.out.println("The current size of the heap is " + h.curSize());
        System.out.println("The current maximum element is " + h.getMax());
    }
}
