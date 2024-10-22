import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] files = {"src/resources/1000.txt", "src/resources/5000.txt", "src/resources/10000.txt"};

        for (String file : files) {
            try {
                int[] array = readNumbersFromFile(file);

                // Bubblesort
                int[] array1 = Arrays.copyOf(array, array.length);
                long startTime = System.nanoTime();
                bubbleSort(array1);
                long endTime = System.nanoTime();
                System.out.println("BubbleSort (" + file + "): " + (endTime - startTime) + " ns");

                // Selectionsort
                int[] array2 = Arrays.copyOf(array, array.length);
                startTime = System.nanoTime();
                selectionSort(array2);
                endTime = System.nanoTime();
                System.out.println("SelectionSort (" + file + "): " + (endTime - startTime) + " ns");

                // Quicksort
                int[] array3 = Arrays.copyOf(array, array.length);
                startTime = System.nanoTime();
                quickSort(array3, 0, array3.length - 1);
                endTime = System.nanoTime();
                System.out.println("QuickSort (" + file + "): " + (endTime - startTime) + " ns");

            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo: " + file + " - " + e.getMessage());
            }
        }
    }

    public static int[] readNumbersFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().mapToInt(Integer::parseInt).toArray();
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
