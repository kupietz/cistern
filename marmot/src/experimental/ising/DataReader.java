package experimental.ising;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class DataReader {
	
	
	protected int numVariables;
	protected Map<String,Integer> tag2Integer;
	protected Map<Integer,String> integer2Tag;
	protected Map<String,Set<String>> word2Tags;
	protected Map<String,Set<Pair<String,String>>> word2LemmaTag;
	
	
	protected List<Datum> data;
	protected Set<Pair<Integer, Integer>> pairs;
	protected List<String> tagNames;
	
	protected List<Pair<Integer, Integer>> pairsLst;
	
	
	
	public DataReader() {
		this.numVariables = 0;
		this.tag2Integer = new HashMap<>();
		this.integer2Tag = new HashMap<>();
		
		this.word2Tags = new HashMap<>();
		this.word2LemmaTag = new HashMap<>();

		this.data = new ArrayList<>();
		this.pairs = new HashSet<>();
		this.tagNames = new ArrayList<>();
		
	}
}
