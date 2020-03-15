public class vEBtreePriorityQueue {
    private vEBtreePriorityQueue summary;
    private vEBtreePriorityQueue[] galaxies;
    private int universe;
    private int min;
    private int max;
    private int NIL = -1;

    vEBtreePriorityQueue(int u) {
        universe = u;
        //default values for min and max are NIL to indicate they're empty.
        min = NIL;
        max = NIL;
        if (u <= 2) {
            summary = null;
            galaxies = new vEBtreePriorityQueue[0];
        } else {
            int sqrtU = (int) Math.ceil(Math.sqrt(u));
            summary = new vEBtreePriorityQueue(sqrtU);
            galaxies = new vEBtreePriorityQueue[sqrtU];
            for (int i = 0; i < sqrtU; i++) {
                galaxies[i] = new vEBtreePriorityQueue((int)Math.ceil(Math.sqrt(u)));
            }
        }
    }

    /*  Index:
     *  The formula to finding the index of where a given number belongs is through use of the following:
     *
     *  x = i * sqrt(u) + j
     *
     *  WHERE 0 <= j <= sqrt(u)
     *  i = cluster number.
     *  j = position of x within that cluster.
     *
     *  Example: x = 9
     *
     *  Cluster: 0           |1            |2           | 3
     *  Bits:   [0, 1, 0, 0, | 0, 0, 0, 0, | 0, 1, 1, 0,| 0, 0, 0, 1]	u=16
     *  Index:   0  1  2  3  |  4  5  6  7 | 8  9 10  11| 12 13 14 15
     *			                                ^
     *	                                        9 = 2 * sqrt(u) + 1
     *	                                        9 = 2 * (4) + 1
     *	                                        9 = 8 + 1
     *	                                        9 = 9 ✓
     */

    private int Index(int i, int j) {
        int sqrtU = (int) Math.ceil(Math.sqrt(universe));
        return i * sqrtU + j;
    }

    // High is the galaxy that X belongs to.
    private int high(int x) {
        int sqrtU = (int)Math.ceil((Math.sqrt(universe)));
        return x / sqrtU;
    }

    // low is the position of X within the galaxy.
    private int low(int x) {
        int remainder = (int)Math.ceil(Math.sqrt(universe));
        return x % remainder;
    }

    /*  Extracting Minimum / Maximum from the vEB tree:
     *   Because we store the minimum and maximum in the attributes min and max, two
     *   of the operations are one-liners, taking constant time:
     *
     *   VEB-TREE-MINIMUM.V /
     *      return V:min
     *
     *   VEB-TREE-MAXIMUM.V /
     *      return V:max
     */

    public int Minimum(vEBtreePriorityQueue vEB) {
        return vEB.min;
    }

    public int Maximum(vEBtreePriorityQueue vEB) {
        return vEB.max;
    }

    /*  INSERT:
     *  The most expensive insert is when we have an empty vEB Priority Queue.
     *  To determine if the structure is empty.
     *  1 if V:min == NIL
     *  2   VEB-EMPTY-TREE-INSERT.V; x/
     *  3 else if x < V:min
     *  4   exchange x with V:min
     *  5 if V:u > 2
     *  6   if VEB-TREE-MINIMUM.V:cluster(high.x// == NIL
     *  7       VEB-TREE-INSERT.V:summary; high.x//
     *  8       VEB-EMPTY-TREE-INSERT.V:cluster(high.x/; low.x//
     *  9   else VEB-TREE-INSERT.V:cluster(high.x/; low.x//
     *  10 if x > V:max
     *  11  V:max = x
     *
     *
     *
        Empty Insert(V,X):
	        if v.min = none: v.min = v.max = x;
	            V.max = x;
		    return;
	        if V.cluster(high(x)].min = non e:
	        insert(V-cluster[high(x)], low(x))
         */

    //Helper Function to  Exchange the minimum and X in the case that  X is smaller than the minimum, we need to update it.
    private void Exchange(int min, int x) {
        int temp = min;
        min = x;
        x = temp;
    }

    // INSERT:
    void Insert(vEBtreePriorityQueue vEB, int x) {
        //If there is no current minimum: (in the constructor we defaulted to NIL) we empty insert.
        if (vEB.min == NIL) {
            vEB.min = vEB.max = x;
        } else {
            if (x < vEB.min) {
                Exchange(vEB.min, x);
            }
            if (vEB.universe > 2) {
                if (Minimum(vEB.galaxies[vEB.high(x)]) == NIL) {
                    Insert(vEB.summary, vEB.high(x));
                    vEB.galaxies[vEB.high(x)].min = vEB.low(x);
                    vEB.galaxies[vEB.high(x)].max = vEB.low(x);
                } else {
                    Insert(vEB.galaxies[vEB.high(x)], vEB.low(x));
                }
            }if (x > vEB.max) {
                vEB.max = x;
            }
        }
    }

    /*  DELETE:
     *  The base case is that if there is a vEB tree with a universe of 2, the algorithm must check if the key
     *  exists.
     *  If the key exists, we have to update it's pointer to null which will make it not present.
     *
     *  VEB-TREE-DELETE.V; x/
     *   1 if V:min == V:max
     *   2      V:min = NIL
     *   3      V:max = NIL
     *   4 elseif V:u == 2
     *   5      if x == 0
     *   6          V:min = 1
     *   7      else V:min = 0
     *   8          V:max = V:min
     *   9 else if x == V:min
     *   10     first-cluster = VEB-TREE-MINIMUM.V:summary
     *   11     x = index.first-cluster;
     *              VEB-TREE-MINIMUM.V:cluster(first-cluster
     *   12     V:min = x
     *   13 VEB-TREE-DELETE.V:clusterŒhigh.x/; low.x
     *   14 if VEB-TREE-MINIMUM.V:cluster(high.x) == NIL
     *   15     VEB-TREE-DELETE.V:summary; high.x
     *   16     if x == V:max
     *   17         summary-max D VEB-TREE-MAXIMUM.V:summary
     *   18         if summary-max == NIL
     *   19             V:max = V:min
     *   20         else V:max D index.summary-max;
     *                      VEB-TREE-MAXIMUM.V:cluster(summary-max))
     *   21 elseif x == V:max
     *   22     V:max = index.high.x;
     *              VEB-TREE-MAXIMUM.V:cluster(high.x)
     */

    public void Delete(vEBtreePriorityQueue vEB, int x) {
        //if there is only one key, then the tree's minimum and max will be the same, therefore set to nil and remove it.
        if (vEB.min == vEB.max) {
            vEB.min = NIL;
            vEB.max = NIL;
            //If the tree has more than two keys we can delete it and assign it to another key.
        } else if (vEB.universe == 2) {
            if (x == 0) {
                vEB.min = 1;
            } else {
                vEB.min = 0;
            }
            vEB.max = vEB.min;
        } else {
            //Assign the next biggest key in the tree as the new minimum.
            if (x == vEB.min) {
                //low is the initial cluster.
                int first = Minimum(vEB.summary);
                x = vEB.Index(first, Minimum(vEB.galaxies[first]));
                vEB.min = x;
            }
            //Now that we have assigned the next biggest key as the minimum, we can delete the original minimum.
            Delete(vEB.galaxies[vEB.high(x)], vEB.low(x));
            //if the key is the maximum of the tree:
            if (Minimum(vEB.galaxies[vEB.high(x)]) == NIL) {
                Delete(vEB.summary, vEB.high(x));
                if (x == vEB.max) {
                    int maximum = Maximum(vEB.summary);
                    if (maximum == NIL) {
                        vEB.max = vEB.min;
                    } else {
                        vEB.max = vEB.Index(maximum, Maximum(vEB.galaxies[maximum]));
                    }
                }
                //Find the new biggest number and update the maximum.
            } else if (x == vEB.max) {
                vEB.max = vEB.Index(vEB.high(x), Maximum(vEB.galaxies[vEB.high(x)]));
            }
        }
    }

    //Extract max will get the biggest in the set, then delete it.
    public  int ExtractMax(vEBtreePriorityQueue vEB){
        int max = vEB.max;
        Delete(vEB, max);
        return  max;
    }

    public void IncreaseKey(vEBtreePriorityQueue vEB, int value, int priority){
        if(value < priority){
            System.out.println("Error: new key is smaller than previous key.");
        }else{
            vEB.Delete(vEB,value);
            vEB.Insert(vEB, priority);
        }
    }


}
