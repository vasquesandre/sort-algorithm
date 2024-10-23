import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] files = {"src/resources/1000.txt", "src/resources/5000.txt", "src/resources/10000.txt"};
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("Escolha o arquivo de números:");
            System.out.println("1. 1000 números");
            System.out.println("2. 5000 números");
            System.out.println("3. 10000 números");
            int fileChoice = scanner.nextInt();

            if (fileChoice < 1 || fileChoice > 3) {
                System.out.println("Escolha inválida. Programa encerrado.");
                break;
            }

            String selectedFile = files[fileChoice - 1];

            System.out.println("Escolha o algoritmo de ordenação:");
            System.out.println("1. BubbleSort");
            System.out.println("2. SelectionSort");
            System.out.println("3. QuickSort");
            int algoChoice = scanner.nextInt();

            if (algoChoice < 1 || algoChoice > 3) {
                System.out.println("Escolha inválida. Programa encerrado.");
                break;
            }

            try {
                int[] array = readNumbersFromFile(selectedFile);

                long startTime = System.nanoTime();
                int[] sortedArray = null;
                switch (algoChoice) {
                    case 1:
                        sortedArray = bubbleSort(array);
                        System.out.println("BubbleSort executado.");
                        break;
                    case 2:
                        sortedArray = selectionSort(array);
                        System.out.println("SelectionSort executado.");
                        break;
                    case 3:
                        sortedArray = quickSort(array, 0, array.length - 1);
                        System.out.println("QuickSort executado.");
                        break;
                }
                long endTime = System.nanoTime();

                long durationInMs = (endTime - startTime) / 1_000_000;
                long durationInNs = (endTime - startTime);

                System.out.println("Tempo de execução: " + durationInMs + " ms / " + durationInNs + " ns");

                System.out.println("Array ordenado: " + Arrays.toString(sortedArray));

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + selectedFile + " - " + e.getMessage());
            }

            System.out.println("1. Repetir processo");
            System.out.println("2. Encerrar processo");
            int continueChoice = scanner.nextInt();

            if (continueChoice == 2) {
                keepRunning = false;
            }
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    public static int[] readNumbersFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().mapToInt(Integer::parseInt).toArray();
    }

    public static int[] bubbleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        int n = sortedArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    public static int[] selectionSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        int n = sortedArray.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = sortedArray[minIndex];
            sortedArray[minIndex] = sortedArray[i];
            sortedArray[i] = temp;
        }
        return sortedArray;
    }

    public static int[] quickSort(int[] array, int low, int high) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        quickSortRecursive(sortedArray, low, high);
        return sortedArray;
    }

    private static void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortRecursive(array, low, pi - 1);
            quickSortRecursive(array, pi + 1, high);
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
