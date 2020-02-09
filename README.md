# Blockchain
This is a simple version of a Blockchain in Java, designed to record and verify transactions. It contains functionality such as mining with Proof-of-Work and verification of transactions with Merkle Trees. 

## Block
Block objects contain: 
  1. index
  2. hash of the previous Block
  3. merkle Root of transactions, calculated at time when Block is created 
  4. nonce (the proof of work)
## Transactions
Transactions are stored in an arrayList. A transaction can only be trusted once a block is mined, after the transaction was added. 

The transaction verifier algorithm works by checking if the merkleRoot calculated from the given transaction is equal to the merkleRoot in the latest Block. 

## Proof-of-Work
The Proof-of-Work algorithm is based off the Bitcoin proof-of-work. Mining a block entails finding an integer (nonce) that, when hashed with the Block results in a set number of leading zeroes, the Block is created. 
