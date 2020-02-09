
public class Block{
    /**
        attributes of a block: 
            index
            previous hash
            merkle root of all transactions
            nonce
            difficulty target
            final genesis block nonce
            final genesis block previous hash
    **/

    public int index; 
    public String previousHash;
    public String merkleRoot;
    public long nonce;

    //Block constructor
    public Block(int index, String previousHash, String merkleRoot, long nonce){
        this.index = index;
        this.previousHash = previousHash;
        this.merkleRoot = merkleRoot;
        this.nonce = nonce;
    }

    //these are the hard-coded attributes of the genesis Block
    public static final String genesisBlockPreviousHash = "1";
    public static final String genesisBlockMerkleRoot = "0";

    //returns a String hash of a Block object
    //hashes index, previousHash, merkleRoot, and nonce
    public static String hash(Block block){
        return StringUtil.applysha256(Integer.toString(block.index) + block.previousHash + block.merkleRoot + block.nonce);
    }
    
    //returns a String hash of an String, for testing purposes
    public static String hash(String input){
        return StringUtil.applysha256(input);
    }
}