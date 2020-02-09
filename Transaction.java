import java.util.ArrayList;
import java.lang.Math;
public class Transaction{
    public String sender;
    public String recipient;
    public int amount;
    public int transactionID;

    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

  
    //adds a transaction to the transaction arrayList
    public static void addTransaction(String sender, String recipient, int amount){
        Transaction newTransaction = new Transaction();
        newTransaction.sender = sender;
        newTransaction.recipient = recipient;
        newTransaction.amount = amount;
        newTransaction.transactionID = transactions.size();

        transactions.add(newTransaction);
    }
    

    //hashes every transaction and puts it into a new Arraylist, then calls a recursive function to find the merkle root 
    public static String merkleRootGenerator(){

        //hashes every transaction in arraylist
        ArrayList<String> hashedTransactions= new ArrayList<String>();
        for(int i = 0; i<transactions.size(); i++){
            hashedTransactions.add(StringUtil.applysha256(transactions.get(i).sender + transactions.get(i).recipient + transactions.get(i).amount));
        }

        return MerkleRootGenerator.merkleRootGenerator(hashedTransactions);
    }
    
    //returns a String hash of a transaction object 
    public static String hash(Transaction transaction){
        return StringUtil.applysha256(transaction.sender + transaction.recipient + Integer.toString(transaction.amount));
    }


    //displays all transactions 
    public static void transactionDisplayer(){
        if(transactions.size() == 0){
            System.out.println( "No transactions have been submitted");
        }
        
        String currentTransaction;
        System.out.println("Sender--------------Recipient--------------amount");
        for(int i =0; i<transactions.size(); i++){
            System.out.println(transactions.get(i).sender + "--------------" + transactions.get(i).recipient + "--------------" + transactions.get(i).amount);
        }
    }
    
    //prints all transactions involving the given address
    public static void transactionDisplayer(String address){
        int z = 0;
        for(int i = 0; i<transactions.size(); i++){
            if(transactions.get(i).sender.compareToIgnoreCase(address) == 0 || transactions.get(i).recipient.compareToIgnoreCase(address) == 0){
                if(z == 0){
                    System.out.println("Sender--------------Recipient--------------amount");                   
                }
                System.out.println(transactions.get(i).sender + "--------------" + transactions.get(i).recipient + "--------------" + transactions.get(i).amount);
                z++;
            }
        }
    }
}