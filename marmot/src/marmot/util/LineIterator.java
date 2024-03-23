// Copyright 2013 Thomas Müller
// This file is part of MarMoT, which is licensed under GPLv3.

package marmot.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LineIterator implements Iterator<List<String>> {
	
	private final static String DefaultSeperator_ = "\\s+";
	protected BufferedReader reader_;
	protected String seperator_;
	
	public LineIterator(String filename){
		this(filename, DefaultSeperator_);
	}
	
	public LineIterator(InputStream in) {
		this(in, DefaultSeperator_);
	}
	
	public LineIterator(InputStream in, String seperator) {
		seperator_ = seperator;
		try {
			reader_ = FileUtils.openStream(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public LineIterator(String filename, String seperator) {
		seperator_ = seperator;
		try {
			reader_ = FileUtils.openFile(filename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean hasNext(){
		try {
			return reader_.ready();
		}
		catch (IOException e){
			throw new RuntimeException("IOException: " + e);
		}
	}
	
	public List<String> next(){
		
		if (!hasNext()){
			throw new NoSuchElementException();
		}
		
		try {
			String line = reader_.readLine();
			String[] tokens = line.split(seperator_);
			ArrayList<String> list = new ArrayList<>(tokens.length);
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    list.add(token);
                }
            }
			return list;
		}
		catch (IOException e){
			throw new RuntimeException("IOException: " + e);
		}
	}
	
    public void remove() {
        throw new UnsupportedOperationException();
    }

}