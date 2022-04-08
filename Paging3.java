import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Paging3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while (instances-- > 0) {
			// Read all the data
			int[] cache = new int[scan.nextInt()];
			int pagesize = scan.nextInt();
			ArrayList<Integer> distict = new ArrayList<>();
			ArrayList<ArrayList<Integer>> index = new ArrayList<>();
			ArrayList<Integer> pages = new ArrayList<>();
			ArrayList<Iterator<Integer>> iterator = new ArrayList<>();
			for (int i = 0; i < pagesize; ++i) {
				int element = scan.nextInt();
				pages.add(element);
				int contain = distict.indexOf(element);
				if(contain==-1) {
					distict.add(element);
					ArrayList<Integer> indecie = new ArrayList<>();
					indecie.add(i);
					index.add(indecie);
					}
				else {
					index.get(contain).add(i);
				}			
			}
			for(int i = 0; i<distict.size();++i) {
				iterator.add(index.get(i).iterator());
			}
			// Find paging fault
			int pageFault = 0;
			int currentPage = 0;
			outloop: while (currentPage < pagesize) {
				// empty cache
				int maxCacheIndex = -1;
				int maxPageIndex = -1;
				for (int i = 0; i < cache.length; ++i) {
					if (cache[i] == 0) {
						cache[i] = pages.get(currentPage);
						currentPage++;
						pageFault++;
						continue outloop;
					} else if (cache[i] == pages.get(currentPage)) {
						currentPage++;
						continue outloop;
					}
					Iterator<Integer> iter = iterator.get(distict.indexOf(cache[i]));
					Integer temp;
					if(!iter.hasNext()) {
						maxPageIndex = Integer.MAX_VALUE;
						maxCacheIndex = i;
					}
					else if((temp=iter.next())>maxPageIndex) {
						maxPageIndex = temp;
						maxCacheIndex = i;
					}
				}
				cache[maxCacheIndex] = pages.get(currentPage);
				pageFault++;
				currentPage++;
			}

			System.out.println(pageFault);
		}
		scan.close();
	}
}
