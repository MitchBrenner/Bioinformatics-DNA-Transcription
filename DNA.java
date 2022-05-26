//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 DNA
// Course:   CS 300 Spring 2022
//
// Author:   Mitchell Brenner
// Email:    mkbrenner3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class uses a linked queue to implement DNA transcription
 */
public class DNA {
  protected static String[][] mRNAtoProteinMap =
          {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
                  {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
                  {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
                  {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
                  {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
                  {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
                  {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
                  {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
                  {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
                  {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
                  {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};

  protected LinkedQueue<Character> DNA; //The queue containing the original DNA sequence

  /**
   * Creates the DNA queue from the provided String. Each Node contains a single Character from
   * the sequence.
   *
   * @param sequence a String containing the original DNA sequence
   */
  public DNA (String sequence){

    DNA = new LinkedQueue<>();
    if(sequence == null){
      return;
    }

    for(int i = 0; i < sequence.length(); i++){
      DNA.enqueue(sequence.charAt(i));
    }

  }

  /**
   * Creates and returns a new queue of mRNA characters from the stored DNA.
   * The transcription is done one character at a time, as (A->U, T->A, C->G, G->C).
   *
   * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
   */
  public LinkedQueue<Character> transcribeDNA(){

    LinkedQueue<Character> mRNA = new LinkedQueue<>();

    if(DNA.isEmpty() || DNA == null){
      return mRNA;
    }

    for(int i = 0; i < DNA.size(); i++){
      char c = DNA.dequeue();

      if(c == 'A'){
        mRNA.enqueue('U');
      } else if(c== 'T'){
        mRNA.enqueue('A');
      } else if(c == 'C'){
        mRNA.enqueue('G');
      } else if(c == 'G'){
        mRNA.enqueue('C');
      }
      // enqueue back to end of queue
      DNA.enqueue(c);
    }

    return mRNA;
  }

  /**
   * Creates and returns a new queue of amino acids from a provided queue of mRNA characters.
   * The translation is done three characters at a time, according to the static mRNAtoProtein
   * Map provided above.
   * Translation ends either when you run out of mRNA characters OR when a STOP codon is reached
   * (i.e. the three-character sequence corresponds to the word STOP in the map, rather than a
   * single amino acid character).
   *
   * @param mRNA a queue containing the mRNA sequence corresponding to the stored DNA sequence
   * @return the queue containing the amino acid sequence corresponding to the provided mRNA
   *          sequence
   */
  public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA){

    LinkedQueue<String> aminoAcidSequence = new LinkedQueue<>();

    // loop through LinkedQueue 3 nodes at a time
    if(mRNA.isEmpty() || mRNA == null){
      return aminoAcidSequence;
    }

    int size = mRNA.size();

    for(int i = 0; i < size; i += 3){

      // get strings of three chars from mRNA
      String s = "";
      if(mRNA.size() >= 3){
        for(int j = 0; j < 3; j++){
          // dequeue to get one char at a time
          s += mRNA.dequeue();
        }
        // loop through proteinMap to find match
        for (int k = 0; k < mRNAtoProteinMap.length; k++){
          if(s.equals(mRNAtoProteinMap[k][0])){
            // check to make sure it isn't STOP
            if(mRNAtoProteinMap[k][1].equals("STOP")){
              return aminoAcidSequence;
            }
            // enqueue char if not "STOP"
            aminoAcidSequence.enqueue(mRNAtoProteinMap[k][1]);
          }
        }
      }
    }
    return aminoAcidSequence;
  }

  /**
   * A shortcut method that translates the stored DNA sequence to a queue of amino acids using
   * the other two methods in this class
   *
   * @return the queue containing the amino acid sequence corresponding to the stored DNA
   * sequence, via its mRNA transcription
   */
  public LinkedQueue<String> translateDNA(){
    LinkedQueue<Character> mRNA = this.transcribeDNA();
    return this.mRNATranslate(mRNA);
  }

}
