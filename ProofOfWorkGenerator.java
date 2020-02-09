public class ProofOfWorkGenerator{

    public static int getPOF(int difficultyTarget, int index, String previousHash, String merkleRoot){      

        //converts difficulty target to a String containing the corresponding number of zeroes 
        String difficulty = "";
        for(int i = 0; i<difficultyTarget;i++){
            difficulty = difficulty + "0";
        }
       
        int nonce = 0;
        
        String leadingDigits = StringUtil.applysha256(Integer.toString(index) + previousHash + merkleRoot + Integer.toString(nonce)).substring(0, difficultyTarget-1);
        while(!leadingDigits.equals(difficulty)){
            nonce++;
            leadingDigits = StringUtil.applysha256(Integer.toString(index) + previousHash + merkleRoot + Integer.toString(nonce)).substring(0, difficultyTarget);
        }
        
        return nonce;
    }
}