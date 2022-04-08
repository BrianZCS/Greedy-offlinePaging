import java.util.Scanner;

public class Paging2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while (instances-- > 0) {
			// Read all the data
			int[] cache = new int[scan.nextInt()];
			int[] pages = new int[scan.nextInt()];
			//int[] farthest = new int[cache.length];
			for (int i = 0; i < pages.length; ++i) {
				pages[i] = scan.nextInt();
			}
			// Find paging fault
			int pageFault = 0;
			int currentPage = 0;
			outloop: while (currentPage < pages.length) {
				// empty cache
				for (int i = 0; i < cache.length; ++i) {
					if (cache[i] == 0) {
						cache[i] = pages[currentPage];
						currentPage++;
						pageFault++;
						continue outloop;
					} else if (cache[i] == pages[currentPage]) {
						currentPage++;
						continue outloop;
					}
				}
				int maxCacheIndex = 0;
				int maxPageIndex = currentPage + 1;
				for (int j = 0; j < cache.length; ++j) {
					boolean inf = true;
					for (int i = currentPage + 1; i < pages.length; ++i) {
						if (pages[i] == cache[j]) {
							//farthest[j] = i;
							inf = false;
							if(i >= maxPageIndex) {
								maxPageIndex = i;
								maxCacheIndex = j;
							}
							break;
						}//need to consider the case that the element doesn't exist in pages, choose that
					}
					if(inf) {
						maxCacheIndex = j;
						break;
					}
				}
				cache[maxCacheIndex] = pages[currentPage];
				pageFault++;
				currentPage++;
			}
			System.out.println(pageFault);
		}
		scan.close();
	}
}
