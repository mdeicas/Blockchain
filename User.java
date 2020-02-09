public class User{
    //users can add a transaction, mine a block, ask to verify a transaction, ask to validate the chain
    //ask for the the amount of coins each address has  

    public static void mineBlock(){
        Blockchain.createBlock();
    }
    public static void addTransaction(String sender, String recipient, int amount){
        Transaction.addTransaction(sender, recipient, amount);
    }
    public static void verifyTransaction(String sender, String recipient, int amount){
        if(TransactionVerifier.transactionVerifier(sender, recipient, amount)){
            System.out.println("That transaction (" + sender + ", " + recipient + ", " + amount + ") is confirmed under at least 1 block");
        }else{
            System.out.println("That transaction (" + sender + ", " + recipient + ", " + amount + ") is NOT confirmed");
        }
    }

    public static void main(String[] args){
        System.out.println("");
        System.out.println("run!");
        Blockchain blockchain1 = new Blockchain(); 

        System.out.println("The nonce of the block " + Blockchain.chain.get(Blockchain.chain.size() -1 ).index + " is " + Blockchain.chain.get(Blockchain.chain.size() -1 ).nonce);
        addTransaction("a", "Marco", 30 );
        addTransaction("b", "Marco", 30 );
        mineBlock();
        addTransaction("c", "Marco", 30 );
        addTransaction("d", "Marco", 30 );
        addTransaction("f", "Marco", 30 );
        addTransaction("g", "Marco", 30 );
        addTransaction("h", "Marco", 30 );
        addTransaction("z", "Marco", 30 );




        verifyTransaction("e", "Marco", 30);
        mineBlock();
        mineBlock();
        verifyTransaction("c", "Marco", 30);

        mineBlock();
        verifyTransaction("a", "Marco", 30);

    }
}