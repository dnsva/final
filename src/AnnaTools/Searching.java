package AnnaTools;

public class Searching {

    /* BINARY SEARCH (the list, the item to search for) */
    public static int binSearch ( int[] list, int item){
        int bottom = 0; //lower bound of searching
        int top = list.length - 1; //upper bound of searching
        int middle; //current search candidate
        boolean found = false; //flag for early stop
        int location = -1; //location of item, -1 for failure

        while (bottom <= top && !found){ //while not finished sorting OR not early stop
            middle = (bottom + top)/2; // integer division, auto-truncate
            if (list[middle] == item){ //if found
                location = middle; //update to new pos
                found = true; //flag
            }
            else if (list[middle] < item){ //shift to the right of the item
                bottom = middle + 1;
            }
            else{ //shift to the left of the item
                top = middle - 1;
            }
        }
        return location; //return pos, -1 if not found
    }

    //-----------------------------------------------------------------------

    /* SEQUENTIAL SEARCH (the list, the item to search for) <- BASIC SEARCH */
    public static int seqSearch ( int[] list, int item){
        int location = -1; //stays -1 if item not found
        boolean found = false; //flag for early break
        for (int i = 0; i < list.length && !found; i++){ //for each item
            if (list[i] == item) { //if found
                location = i; //set answer to location
                found = true;
            }
        }
        return location; //return pos, -1 if not found
    }
}
