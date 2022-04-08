import java.util.Scanner;
import java.util.ArrayList;

public class Pagingorigin {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while (instances-- > 0) {
			ArrayList<Integer> pages = new ArrayList<Integer>();
			// Read all the data
			int cacheSize = scan.nextInt();
			ArrayList<Integer> cache = new ArrayList<Integer>();
			int page = scan.nextInt();
			while (page-- > 0) {
				pages.add(scan.nextInt());
			}
			// Find paging fault
			int pageFault = 0;
			int currentPage = 0;

			while (currentPage < pages.size()) {
				// page faults
				if (!cache.contains(pages.get(currentPage))) {
					// empty cache
					if (cache.size() < cacheSize) {
						cache.add(pages.get(currentPage));
					} else {
						// find farthest
						int farthest = -1;
						int value = cache.get(0);
						for (int i = 0; i < cacheSize; ++i) {
							if (pages.indexOf(cache.get(i)) == -1) {
								value = cache.get(i);
								break;
							} else if (pages.indexOf(cache.get(i)) > farthest) {
								farthest = pages.indexOf(cache.get(i));
								value = cache.get(i);
							}
						}
						cache.set(cache.indexOf(value), pages.get(currentPage));
					}
					pageFault++;
				}
				pages.set(currentPage, 0);
				currentPage++;
			}
			System.out.println(pageFault);
		}
		scan.close();
	}

}
