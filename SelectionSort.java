
public class SelectionSort {

	// 直接选择排序，将待排序序列分为两组，sort[0...i-1]为已排序，sort[i..size]为未排序，在sort[i...size]中取最小数sort[k]放至sort[0...i-1]的i处
	public static int[] sort(int[] sort) {
		int i, j, k, size = sort.length, temp;
		for (i = 0; i < size; i++) {
			// 初始sort[k] = sort[0]
			k = i;
			for (j = i + 1; j < size; j++) {
				// 从k + 1处向后遍历找出最小值，当找到最小值，交换k处和j处的值
				if (sort[k] > sort[j]) {
					k = j;
				}
				// 交换k处和j处的值，遍历结束后，将i + 1处的值作为循环的初始值（即为排序的sort[0]）
				if (k != i) {
					temp = sort[i];
					sort[i] = sort[k];
					sort[k] = temp;
				}
			}
		}

		return sort;
	}

	public static void main(String[] arg) {
		int[] sort = { 232, 31, 1, 0, 43, 41, 32, 4, 676 };
		int[] sorts = SelectionSort.sort(sort);
		for (int i : sorts) {
			System.out.println(i);
		}
	}

}
