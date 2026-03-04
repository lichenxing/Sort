
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    /**
     * 对整个数组进行快速排序的便捷方法。
     */
    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 原始区间版快速排序，保持返回值以兼容原有调用方式。
     */
    public static int[] quickSort(int[] array, int low, int high) {
        if (array == null || array.length < 2) {
            return array;
        }
        quickSortInternal(array, low, high);
        return array;
    }

    /**
     * 真实的递归实现，使用三数取中作为枢轴，并对小区间使用插入排序优化。
     */
    private static void quickSortInternal(int[] array, int low, int high) {
        // 小数组使用插入排序，减少递归和交换开销
        final int INSERTION_SORT_THRESHOLD = 10;
        if (high - low + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortInternal(array, low, pivotIndex - 1);
            quickSortInternal(array, pivotIndex + 1, high);
        }
    }

    /**
     * 对区间 [low, high] 使用插入排序。
     */
    private static void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= low && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * 分区函数：使用三数取中选择枢轴，减少近乎有序数据退化为 O(n^2) 的概率。
     */
    private static int partition(int[] array, int low, int high) {
        // 三数取中：low, mid, high
        int mid = low + (high - low) / 2;
        if (array[low] > array[mid]) {
            swap(array, low, mid);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }

        // 现在 array[mid] 是三者中间值，将其作为枢轴，放到 high-1 位置
        swap(array, mid, high - 1);
        int pivot = array[high - 1];

        int i = low;
        int j = high - 1;
        while (true) {
            while (array[++i] < pivot) {
                // 空循环体
            }
            while (array[--j] > pivot) {
                // 空循环体
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        // 将枢轴放回正确位置
        swap(array, i, high - 1);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * 简单场景测试：打印排序结果和是否有序。
     */
    private static void simpleTests() {
        test(new int[]{12, 23, 8, 9, 21, 10, 4, 13});  // 一般无序
        test(new int[]{1, 2, 3, 4, 5});                // 已经有序
        test(new int[]{5, 4, 3, 2, 1});                // 逆序
        test(new int[]{1, 1, 1, 1});                   // 全部相等
        test(new int[]{1});                            // 单元素
        test(new int[]{});                             // 空数组
    }

    private static void test(int[] data) {
        System.out.println("原数组:   " + Arrays.toString(data));
        quickSort(data);
        System.out.println("排序后:   " + Arrays.toString(data));
        System.out.println("是否有序: " + isSorted(data));
        System.out.println("------------");
    }

    private static boolean isSorted(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 与 JDK Arrays.sort 的结果做对比，验证正确性。
     */
    private static void compareWithJdkTests() {
        compareWithJdk(new int[]{12, 23, 8, 9, 21, 10, 4, 13});
        compareWithJdk(new int[]{1, 2, 3, 4, 5});
        compareWithJdk(new int[]{5, 4, 3, 2, 1});
        compareWithJdk(new int[]{1, 1, 1, 1});
        compareWithJdk(new int[]{1});
        compareWithJdk(new int[]{});
    }

    private static void compareWithJdk(int[] data) {
        int[] a = Arrays.copyOf(data, data.length);
        int[] b = Arrays.copyOf(data, data.length);

        quickSort(a);
        Arrays.sort(b);

        boolean same = Arrays.equals(a, b);

        System.out.println("原数组:   " + Arrays.toString(data));
        System.out.println("快排结果: " + Arrays.toString(a));
        System.out.println("JDK排序:  " + Arrays.toString(b));
        System.out.println("结果一致: " + same);
        System.out.println("------------");
    }

    /**
     * 随机数据压力测试：大量随机数组与 JDK 排序对比。
     */
    private static void randomTests(int times, int maxLen, int maxValue) {
        Random random = new Random();
        for (int t = 0; t < times; t++) {
            int len = random.nextInt(maxLen + 1); // 0 ~ maxLen
            int[] data = new int[len];
            for (int i = 0; i < len; i++) {
                data[i] = random.nextInt(maxValue * 2 + 1) - maxValue; // 允许负数
            }

            int[] a = Arrays.copyOf(data, len);
            int[] b = Arrays.copyOf(data, len);

            quickSort(a);
            Arrays.sort(b);

            if (!Arrays.equals(a, b)) {
                System.out.println("发现错误用例: " + Arrays.toString(data));
                System.out.println("快排结果:   " + Arrays.toString(a));
                System.out.println("JDK排序:    " + Arrays.toString(b));
                return;
            }
        }
        System.out.println("随机测试通过，次数: " + times);
    }

    public static void main(String[] args) {
        System.out.println("====== 简单用例测试 ======");
        simpleTests();

        System.out.println("====== 与 JDK 排序对比 ======");
        compareWithJdkTests();

        System.out.println("====== 随机压力测试 ======");
        randomTests(1000, 100, 1000);
    }
}
