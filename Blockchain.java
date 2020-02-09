import java.util.ArrayList;
public class Blockchain{
        
    //this arrayList contains the Blockchain
    public static ArrayList<Block> chain = new ArrayList<Block>();

    //creates genesis Block 
    public Block genesisBlock; 
    public Blockchain(){
        genesisBlock = new Block(0, Block.genesisBlockPreviousHash, Block.genesisBlockMerkleRoot, ProofOfWorkGenerator.getPOF(difficultyTarget, 0, Block.genesisBlockPreviousHash, Block.genesisBlockMerkleRoot));
        chain.add(genesisBlock);
    }

    //difficulty target, must be at least 1
    private static final int difficultyTarget = 5;

    //mines a block by obtaining a proof of work, and then adds it to the chain
    public static void createBlock(){
        MerkleRootGenerator.merkleTree.clear();

        int index = chain.size();
        String previousHash = Block.hash(chain.get(chain.size() -1 ));
        String merkleRoot = Transaction.merkleRootGenerator();
        long nonce  = ProofOfWorkGenerator.getPOF(difficultyTarget, index, previousHash, merkleRoot);
        
        //Block newBlock = new Block();
        chain.add(new Block(index, previousHash, merkleRoot, nonce));
         
    }

    private static void createFirstRowOfMerkleTree(){
        /*these lines add the hashed list of transactions to the merkleTree. This step has to occur here becuase this way only
        the transactions that are added are the ones that are encoded into the block
        */
        //hashes every transaction in arraylist
        
        MerkleRootGenerator.merkleTree.clear(); 

        ArrayList<String> hashedTransactions= new ArrayList<String>();
        for(int i = 0; i<Transaction.transactions.size(); i++){
            hashedTransactions.add(StringUtil.applysha256(Transaction.transactions.get(i).sender + Transaction.transactions.get(i).recipient + Transaction.transactions.get(i).amount));
        }
       
        MerkleRootGenerator.merkleTree.add(hashedTransactions); 
    }

    public static void validateChain(){
        //checks that hash(block n) = previousHash of block n+1
        // and that the proof of work is valid for each block--getPOF
        //loop for entire chain
    }

}