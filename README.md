# Greedy-offlinePaging-Furthest in the future

The input will start with an positive integer, giving the number of instances that follow. For each
instance, the first line will be a positive integer, giving the number of pages in the cache. The second
line of the instance will be a positive integer giving the number of page requests. The third and final
line of each instance will be space delimited positive integers which will be the request sequence.

For each instance, your program should output the number of page faults achieved by furthest in the
future paging assuming the cache is initially empty at the start of processing the page request sequence.

# Different versions

Pagingorigin is a valid greedy algorithm with small running speed.

Paging3 is the best version of the algorithm because it implements the priority queue and improve the complixity a lot. 

Paging and Paging2 are several stages I developed Paging3. In each of the stage, I found some bugs and fixed them.
