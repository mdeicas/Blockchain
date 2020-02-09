import java.util.ArrayList;
public class MerkleRootGenerator{

    /*
        whenever a new Block is added, new transactions are verified. The merkleTree is rebuilt with those new transactions.
        This occurs in Blockchain.createBlock()
     */
    public static ArrayList<ArrayList> merkleTree = new ArrayList<>();

    //recursive function to generate the Root from an arrayList of hashes
    public static String merkleRootGenerator(ArrayList<String> level1){
        ArrayList<String> level2 = hashArrayList(level1);
        if(level2.size() == 1){
            return level2.get(0);
        }
        return merkleRootGenerator(level2);
    }
    
    //this function takes in an arraylist of strings and outputs an arraylist of strings one level higher in the merkle tree
    public static ArrayList hashArrayList(ArrayList<String> list){
        //addListToMerkleTree(list);
        merkleTree.add(list);

        ArrayList<String> newList = new ArrayList<String>();
        for(int i = 0; i<(list.size() - list.size()%2); i = i+2 ){
            newList.add( StringUtil.applysha256(  list.get(i)+ list.get(i+1) ));
        }
        if(list.size()%2 == 1){
            newList.add(StringUtil.applysha256(list.get(list.size()-1) + list.get(list.size()-1)));
        }

        if(newList.size() == 1){
            merkleTree.add(newList);
        }
       
        //merkleTree.add(newList);
        return newList;
    }

    //just for testing 
    public static void displayMerkleTree(){
        System.out.println("");
        for(int i = 0; i<merkleTree.size(); i++){
            System.out.println("");
            for(int z = 0; z<merkleTree.get(i).size(); z++){
                System.out.print("--node--");
            }
        }
    }

}

