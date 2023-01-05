package lab13;
import java.util.*;
public class WordFisherTester {
	
	public static void main(String[] args) {
		
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		System.out.println("word count: " + alice.getWordCount());
		System.out.println("unique word count: " + alice.getNumUniqueWords());
		System.out.println("frequency of handkerchief: " + alice.getFrequency("handkerchief"));
		alice.pruneVocabulary();
		System.out.println("word count after pruning: " + alice.getWordCount());
		
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");
		moby.pruneVocabulary();
		ArrayList<String> answer = moby.getTopWords(10);
		for(int i = 0; i < answer.size(); i++) {
			System.out.println("word: " + answer.get(i));
		}
		answer = moby.commonPopularWords(20, alice);
		for(int i = 0; i < answer.size(); i++) {
			System.out.println("new word: " + answer.get(i));
		}
		//System.out.println("frequency of handkerchief: " + moby.getFrequency("handkerchief"));
		
	}
}
