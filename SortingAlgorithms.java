// Name: Vaneeza Shoaib
// Computing ID: ZHW9ZC@virginia.edu
// Homework Name: HW 13- SORTING
// Resources used: geeksforgeeks - Java Program for Merge Sort, geeksforgeeks - Java Program for QuickSort

@SuppressWarnings("unchecked")
public class SortingAlgorithms {

    /*
     * Swaps the elements and indices i and j in list
     * */
    private static <T> void swap(T[] list, int i, int j) {
        if (i < 0 || i >= list.length)
            return;
        if (j < 0 || j >= list.length)
            return;
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    // ####################
    // ## INSERTION SORT ## ----------------------------------------------------------------------
    // ####################
    // ## IMPORTANT: the code we've given you below has a small bug!
    // ##   You will need to look at this code and/or test, and fix the bug.
    // ####################
    /**
     * Usually a slow sorting algorithm. Insertion sort.
     * @param list - An array of items
     */
    public static <T extends Comparable<T>> void insertionSort(T[] list) {
        for (int i = 1; i < list.length; i++) {
            T val = list[i];
            int j = i - 1;
            while (j >= 0 && val.compareTo(list[j]) < 0) {
                list[j+1] = list[j];
                j--;
            }
            list[j+1] = val;
        }
    }

    //=================================================================================


    // ################
    // ## MERGE SORT ## ----------------------------------------------------------------------
    // ################
    /**
     * Fully recursive Merge sort and associated helper method.
     * The second method below provides the portion of the array
     * (i.e., index i to j inclusive) that we want to sort.
     *
     * @param list - An array of items
     */
    public static<T extends Comparable<T>> void mergeSort(T[] list) {
        mergeSort(list, 0, list.length - 1);
    }
    public static<T extends Comparable<T>> void mergeSort(T[] list, int i, int j) {
        if (i < j) {
            // Find the middle point
            int mid = (i + (j - 1)) / 2;
            // Sort first and second halves
            mergeSort(list, i, mid);
            mergeSort(list, mid + 1, j);
            // Merge the sorted halves
            merge(list, i, mid, j);
        }
    }

    /**
     * Merge method for Merge Sort algorithm.
     * Your mergeSort algorithm will call this method as appropriate to do the merging.
     * @param list - An array of items
     * @param i - lower bound index
     * @param mid - middle index
     * @param j - upper bound index
     */
    public static<T extends Comparable<T>> void merge(T[] list, int i, int mid, int j) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - i + 1;
        int n2 = j - mid;

        // Create temp arrays
        T[] mergedList1 = (T[]) new Comparable[n1];
        T[] mergedList2 = (T[]) new Comparable[n2];

        // Copy data to temp arrays
        for (int a = 0; a < n1; a++)
            mergedList1[a] = list[i + a];
        for (int b = 0; b < n2; ++b)
            mergedList2[b] = list[mid + b+ 1];

        // Merge the temp arrays
        // Initial indexes of first and second subarrays
        int a = 0, b = 0;

        // Initial index of merged subarray array
        int k = i;
        while (a < n1 && b < n2) {
            if (mergedList1[a].compareTo(mergedList2[b]) < 0 || mergedList1[a].compareTo(mergedList2[b]) == 0) {
                list[k] = mergedList1[a];
                a++;
            }
            else {
                list[k] = mergedList2[b];
                b++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (a < n1) {
            list[k] = mergedList1[a];
            a++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (b < n2) {
            list[k] = mergedList2[b];
            b++;
            k++;
        }


        //Reminder: when using a generic type, to create a new array to hold items of type T,
        //  you do something like the following:
        //T[] merged = (T[]) new Comparable[j-i+1]; // space for new merged list to hold (sorted) sublist

    }

    //=================================================================================

    // #######################
    // ## HYBRID MERGE SORT ## ----------------------------------------------------------------------
    // #######################
    /**
     * Hybrid recursive Merge sort and associated helper method.
     * The second method below provides the portion of the array
     * (i.e., index i to j inclusive) that we want to sort.
     * For this implementation, when the size of the portion of the array
     * to be sorted is less than 100 items, call insertionSort method to
     * sort that chunk of the array.
     *
     *
     * @param list - An array of items
     */
    public static<T extends Comparable<T>> void mergeSortHybrid(T[] list) {
        mergeSortHybrid(list, 0, list.length - 1);
    }
    public static<T extends Comparable<T>> void mergeSortHybrid(T[] list, int i, int j) {
        if (i < j && (j - i + 1) < 100) { //If the size of the list being considered is less than 100, we simply use an insertion sort
            insertionSort(list);
        }
        else if (i < j) {
            int middle = (i + (j - 1)) / 2;
            mergeSort(list, i, middle);
            mergeSort(list, middle + 1, j);
            merge(list, i, middle, j);
        }
    }

    //=================================================================================

    // ###############
    // ## QUICKSORT ## ----------------------------------------------------------------------
    // ###############
    /**
     * Fully recursive Quicksort and associated helper method.
     * The second method below provides the portion of the array
     * (i.e., index i to j inclusive) that we want to sort.
     * >>> Use any partition scheme that you like.
     *
     * @param list - An array of items
     */
    public static<T extends Comparable<T>> void quickSort(T[] list) {
        quickSort(list, 0, list.length - 1);
    }
    public static<T extends Comparable<T>> void quickSort(T[] list, int i, int j) {
        if (i < j) {
            int partitioningIndex = partition(list, i, j);
            quickSort(list, i, partitioningIndex - 1);
            quickSort(list, partitioningIndex + 1, j);
        }
    }

    /**
     * Partition method for Quicksort - Use any valid partition algorithm that you like.
     * Your quickSort algorithm will call this method as appropriate to do the partitioning.
     * @param list - An array of items
     * @param i - lower bound
     * @param j - upper bound
     */
    public static<T extends Comparable<T>> int partition(T[] list, int i, int j) {
        T pivot = list[j];
        int index = i - 1;
        for (int x = i; x < j; x++) {
            if (list[x].compareTo(pivot) < 0 || list[x].compareTo(pivot) == 0) {
                index++;
                swap(list, index, x);
            }
        }
        swap(list, index + 1, j);
        return index+1;
    }

    //=================================================================================

    // ######################
    // ## HYBRID QUICKSORT ## ----------------------------------------------------------------------
    // ######################
    /**
     * Hybrid Quicksort and associated helper method.
     * The second method below provides the portion of the array
     * (i.e., index i to j inclusive) that we want to sort.
     * >>> Use any partition scheme that you like.
     * For this implementation, when the size of the portion of the array
     * to be sorted is less than 100 items, call insertionSort method to
     * sort that chunk of the array.
     *
     * @param list - An array of items
     */
    public static<T extends Comparable<T>> void quickSortHybrid(T[] list) {
        quickSortHybrid(list, 0, list.length - 1);
    }
    public static<T extends Comparable<T>> void quickSortHybrid(T[] list, int i, int j) {
        if (i < j && (j - i + 1) < 100) { //If the size considered is less than 100, we just use insertion sort
            insertionSort(list);
        }
        else if (i < j) {
            int partitioningIndex = partitionHybrid(list, i, j);
            quickSort(list, i, partitioningIndex - 1);
            quickSort(list, partitioningIndex + 1, j);
        }
    }

    /**
     * Partition method for Quicksort - Use any valid partition algorithm that you like.
     * Your quickSort algorithm will call this method as appropriate to do the partitioning.
     * @param list - An array of items
     * @param i - lower bound
     * @param j - upper bound
     */
    public static<T extends Comparable<T>> int partitionHybrid(T[] list, int i, int j) {
        T pivot = list[j];
        int index = i - 1;
        for (int x = i; x < j; x++) {
            if (list[x].compareTo(pivot) < 0 || list[x].compareTo(pivot) == 0) {
                index++;
                swap(list, index, x);
            }
        }
        swap(list, index + 1, j);
        return index+1;
    }

}