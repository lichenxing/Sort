
public class QuickSort {

	public static int[] quickSort(int[] sort, int low, int hight) {
		int position;
		if (low < hight) {
			position = point(sort, low, hight);
			quickSort(sort, low, position - 1);
			quickSort(sort, position + 1, hight);
		}
		return sort;
	}

	private static int point(int[] sort, int low, int hight) {
		int key = sort[low];
		while (low < hight) { // 从两端向中间扫描，直至low == hight
			// 首先自右向左移动，当遇到小于key的值得时候跳出循环
			while (low < hight && sort[hight] >= key) {
				hight--;
			}
			// 当遇到小于key的值时，交换值
			if (low < hight) {
				sort[low++] = sort[hight];
			}
			// 自左向右移动，当遇到大于key的值得时候跳出循环
			while (low < hight && sort[low] <= key) {
				low++;
			}
			// 当遇到大于key的值时，交换值
			if (low < hight) {
				sort[hight--] = sort[low];
			}
		}
		sort[low] = key;
		return low;
	}

	public static void main(String[] arg) {
		int[] sort = { 12, 23, 8, 9, 21, 10, 4, 13 };
		int sorts[] = QuickSort.quickSort(sort, 0, sort.length - 1);
		for (int i : sorts) {
			System.out.println(i);
		}
	}
}
