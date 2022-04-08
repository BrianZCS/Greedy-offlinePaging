import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Paging {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while (instances-- > 0) {
			// Read all the data
			int cachesize = scan.nextInt();
			ArrayList<Integer> cache = new ArrayList<>();
			int pagesize = scan.nextInt();
			ArrayList<Integer> distict = new ArrayList<>();
			ArrayList<ArrayList<Integer>> index = new ArrayList<>();
			ArrayList<Integer> pages = new ArrayList<>();
			PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
			for (int i = 0; i < pagesize; ++i) {
				int element = scan.nextInt();
				pages.add(element);
				int contain = distict.indexOf(element);
				if (contain == -1) {
					distict.add(element);
					ArrayList<Integer> indecie = new ArrayList<>();
					indecie.add(i);
					index.add(indecie);
				} else {
					index.get(contain).add(i);
				}
			}
			// Find paging fault
			int pageFault = 0;
			int currentPage = 0;
			ArrayList<Integer> maxCache = new ArrayList<>();
			outloop: while (currentPage < pagesize) {
				// empty cache
				if (cache.contains(pages.get(currentPage))) {
					int temp = distict.indexOf(pages.get(currentPage));
					if(index.get(temp).size()==1) {
						maxCache.add(cache.indexOf(distict.get(temp)));
					}
					else {
					//pQueue.remove(currentPage);
					pQueue.add(index.get(temp).remove(1));}
					currentPage++;
					continue outloop;
				}
				else if (cache.size() < cachesize) {
					int temp = distict.indexOf(pages.get(currentPage));
					cache.add(pages.get(currentPage));
					if(index.get(temp).size()==1) {
						maxCache.add(cache.indexOf(distict.get(temp)));
					}
					else {
					//pQueue.remove(currentPage);
					pQueue.add(index.get(temp).remove(1));}
					currentPage++;
					pageFault++;
					continue outloop;
				} 
			//bug: if we have two elements set maxCache, our algo will not remove that.
				if(maxCache.size()!=0) {
					cache.set(maxCache.remove(0), pages.get(currentPage));
				}
				else {
				cache.set(cache.indexOf(pages.get(pQueue.poll())), pages.get(currentPage));
				}
				int temp = distict.indexOf(pages.get(currentPage));
				if(index.get(temp).size()==1) {
					maxCache.add(cache.indexOf(distict.get(temp)));
				}
				else {
				pQueue.add(index.get(temp).remove(1));}
				pageFault++;
				currentPage++;
			}

			System.out.println(pageFault);
		}
		scan.close();
	}
}
