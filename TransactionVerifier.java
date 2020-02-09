public class TransactionVerifier{

    /*
    * this class contains the algorithm to verify a transaction. It checks to see if the merkle Root contained
    * in the latest block is equal to the merkleRoot calculated from the given transaction.
    * */
    
    public static boolean transactionVerifier(String sender, String recipient, int amount){
        
        //checks case of there being no blocks, or just the genesis block in the chain
        if(Blockchain.chain.size() == 0 || Blockchain.chain.size() == 1 ){
            return false;
        }
        
        rowIndex = 0;
        //number of rows of MerkLeTree, including the base layer but not including the root.
        rowActual = (int) Math.ceil(Math.log(MerkleRootGenerator.merkleTree.get(0).size())/Math.log(2));

        int queryTransactionID = findTransactionID(sender, recipient, amount);
        // occurs if transaction does not exist
        if(queryTransactionID == -1){
            return false;
        }
        
        /*
        * findTransactionID searches the transactions arraylist and not merkleTree because the merkleTree
        * only contains hashes.
        * So this checks the case that the query transaction is not in the merkleTree but is in the transactions arraylist
        */
        if(queryTransactionID>=MerkleRootGenerator.merkleTree.get(0).size()){
            return false;
        }
        // this works because the index of the transaction is the same in the transactions arraylist as in the merkletree
        String queryNode = (String) MerkleRootGenerator.merkleTree.get(0).get(queryTransactionID);
        if(rowActual == 0){
            if( StringUtil.applysha256(queryNode + queryNode).equals(Blockchain.chain.get(Blockchain.chain.size() - 1).merkleRoot)){
                return true;
            }
            else {
                return false; 
            }
        }else{
            if(GetMerkleRootFromLeafNode(queryNode, queryTransactionID).equals(Blockchain.chain.get(Blockchain.chain.size() - 1).merkleRoot) ){
                return true;
            }else{
                return false;
            }
        }
    }

    //searches the transactions arraylist for the transactionID
    // returns transaction ID, or -1 if transaction does not exist
    private static int findTransactionID(String sender, String recipient, int amount){
        //search through the transaction arraylist to find the transaction id
        int queryTransactionID = 0;
        for(int i = 0; i<Transaction.transactions.size(); i++){
            if(Transaction.transactions.get(i).sender.equals(sender) && Transaction.transactions.get(i).recipient.equals(recipient) && Transaction.transactions.get(i).amount==amount){
                queryTransactionID = i;
                i = Transaction.transactions.size() + 1; // this is to exit the loop
            }else{
                queryTransactionID = -1;
            }
        }
        return queryTransactionID;
    }
    
    
    /*
    * The following methods calculate the merkle Root from a leaf node using arithmetic.
    */
    private static String GetMerkleRootFromLeafNode(String node, int n){
        if(rowIndex==rowActual){
            return GetMerkleRootFromLeafNode(node);
        }
        
        if(getParity(n)){
            return GetMerkleRootFromLeafNode(StringUtil.applysha256(node + getPartnerNode(n)), getParentIndex(n));
        }else{
            return GetMerkleRootFromLeafNode(StringUtil.applysha256(getPartnerNode(n) + node), getParentIndex(n));
        }
    }
    
    private static String GetMerkleRootFromLeafNode(String node){
        return node;
    }
    private static int getParentIndex(int n){
        int parent = 0;
        
        if(n%2 == 0){
            parent = n/2;
        }
        if(n%2==1){
            parent = (n-1)/2;
        } 
        return parent;
    }

    private static int rowIndex = 0;
    //private static int z = MerkleRootGenerator.merkleTree.get(0).size();
    private static int rowActual = 0; //number of rows of MerkLeTree, including the base layer but not including the root. 
    
    private static String getPartnerNode(int n){
        int partner =0;
        if(n%2 == 0){
            partner = n+1;
        }
        if(n%2==1){
            partner = n-1; 
        }
        
        String partnerNode;
        //this if statement is for the case when the node needs to be hashed against itself because it has no partner node 
        if(MerkleRootGenerator.merkleTree.get(rowIndex).size() == partner){
            partnerNode = (String) MerkleRootGenerator.merkleTree.get(rowIndex).get(n);
        }else{
            partnerNode = (String) MerkleRootGenerator.merkleTree.get(rowIndex).get(partner);
        }
        rowIndex++;
        return partnerNode;
    }
    
    //returns TRUE if even, FALSE if odd
    private static boolean getParity(int n){
       boolean parity = false;
        if(n%2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
  
}
