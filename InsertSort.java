
public class InsertSort {

	//   插入排序（递增）假设sort[0...i]为有序，将sort[i+1...size]中遍历值与sort[0...i]中的值比较，插入应该在的位置，并将后面的值后移
	public static int[] sort(int[] sort) {
		int[] sorts = new int[sort.length + 1];
		System.arraycopy(sort, 0, sorts, 1, sort.length);
		int j;
		for (int i = 1; i < sorts.length; i++) {
			// 从0开始，sort[0] = temp，当sort[i] < sort[i-1]才进行比较，否则直接进行下次比较
			if (sorts[i] < sorts[i - 1]) {
				// 将temp = sort[0] = sort[i]
				sorts[0] = sorts[i];
				// 从i - 1处依次向前比较
				j = i - 1;
				// 当j处的值小于temp(sort[0])时，将值依次后移
				while (sorts[0] < sorts[j]) {
					sorts[j + 1] = sorts[j];
					j--;
				}
				// 最后将temp放至该放的位置
				sorts[j + 1] = sorts[0];
			}
		}
		return sorts;
	}

	public static void main(String[] arg) {

		int[] sort = { 32, 23, 90, 18, 1, 3, 43, 42, 532, 34, 22 };
		int[] sorts = InsertSort.sort(sort);
		for (int i : sorts) {
			System.out.println(i);
		}
	}
}
