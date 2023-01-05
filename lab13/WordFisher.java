package lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import javax.imageio.stream.FileCacheImageInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
public class WordFisher {

	// Please note these variables. they are the state of the object.
	public HashMap<String, Integer> vocabulary;
	public List<String> stopwords; // User ArrayList for initialization
	private String inputTextFile;
	private String stopwordsFile;

	WordFisher(String inputTextFile, String stopwordsFile) {
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;

		buildVocabulary();
		getStopwords();
	}
	public void showHashMap() {
		for(String i : vocabulary.keySet()) {
			System.out.println(i);
		}
	}
	public void buildVocabulary() {
		vocabulary = new HashMap<String, Integer>();

		// TODO: load in each word from inputTextFile into the vocabulary.
		// By the end of this method, vocabulary should map each word to the number of
		// times it occurs in inputTextFile.
		// Therefore, as you iterate over words, increase the value that the word maps
		// to in vocabulary by 1.
		// If it's not in the vocabulary, then add it with an occurrence of 1.
		// Use getStopwords as an example of reading from files.
		try {
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));
			reader = reader.replaceAll("[^a-zA-Z0-9 ]","");
			reader = reader.toLowerCase();
			String[] allWords = reader.split("\\s+");
			int count = 0;
			while(count < allWords.length) {
				if(!vocabulary.containsKey(allWords[count])) {
					vocabulary.put(allWords[count], 1);
				}else {
					int value = vocabulary.get(allWords[count]);
					vocabulary.replace(allWords[count], value+1);
				}
				count++;
			}
			vocabulary.remove("");
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void getStopwords() {
		stopwords = new ArrayList<String>();
		String word;

		try {
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
			while ((word = input.readLine()) != null) {
				stopwords.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWordCount() {
		// TODO: Return the total number of words in inputTextFile.
		// This can be calculated using vocabulary.
		int wordCount = 0;
		for(Integer i : vocabulary.values()) {
			wordCount += i;
		}
		return wordCount;
	}

	public int getNumUniqueWords() {
		// TODO: Return the number of unique words.
		// This should be the same as the number of keys in vocabulary.
		return vocabulary.size();
	}

	public int getFrequency(String word) {
		if(vocabulary.containsKey(word)){
			// TODO: Return the number of times word occurs. 
			// (Should be one simple line of code.)
			// Think about what vocabulary stores.
			return vocabulary.get(word);
		}
		
		return -1;
	}

	public void pruneVocabulary() {
		// TODO: remove stopwords from the vocabulary.
		for(int i = 0; i < stopwords.size(); i++) {
			if(vocabulary.containsKey(stopwords.get(i))) {
				vocabulary.remove(stopwords.get(i));
			}
		}
	}

	public ArrayList<String> getTopWords(int n) {
		ArrayList<String> topWords = new ArrayList<String>();
		
		// TODO: get the top n words.

		class WordNode{
			private String word;
			private int frequency;
			public WordNode(String word, int frequency) {
				this.word = word;
				this.frequency = frequency;
			}
			public int getFrequency() {
				return this.frequency;
			}
			public String getWord() {
				return this.word;
			}
		}
		class WordNodeComparator implements Comparator<WordNode>{
			public int compare(WordNode one, WordNode two) {
				return two.getFrequency() - one.getFrequency();
			}
		}
		PriorityQueue<WordNode> pq = new PriorityQueue<WordNode>(getNumUniqueWords(), new WordNodeComparator());
		for(String s : vocabulary.keySet()) {
			WordNode node = new WordNode(s,vocabulary.get(s));
			pq.add(node);
		}
		for(int i = 0; i < n; i++) {
			topWords.add(pq.poll().getWord());
		}
		return topWords;
	}
	public ArrayList<String> commonPopularWords(int n, WordFisher other) {
		ArrayList<String> commonPopWords = new ArrayList<String>();
		
		// TODO: get the common popular words.
		ArrayList<String> firstCommon = getTopWords(n);
		ArrayList<String> secondCommon = other.getTopWords(n);
		for(int i = 0; i < firstCommon.size(); i++) {
			for(int j = 0; j < secondCommon.size(); j++) {
				if(firstCommon.get(i).equals(secondCommon.get(j))) {
					commonPopWords.add(firstCommon.get(i));
				}
			}
		}
		return commonPopWords;
	}

}
