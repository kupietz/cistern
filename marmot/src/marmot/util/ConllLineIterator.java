// Copyright 2013 Thomas Müller
// This file is part of MarMoT, which is licensed under GPLv3.

package marmot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ConllLineIterator extends LineIterator {

	//private final static String DefaultSeperator_ = "\\s+";
	private final static String DefaultSeperator_ = "\\t";

	public ConllLineIterator(String filename){
		super(filename, DefaultSeperator_);
	}

	public ConllLineIterator(InputStream in) {
		super(in, DefaultSeperator_);
	}

	public List<String> next(){

		if (!hasNext()){
			throw new NoSuchElementException();
		}

		try {
			String line = super.reader_.readLine();
			if (line.length() > 1 && line.charAt(0) == '#') {
				//System.out.println(line);
				ArrayList<String> list = new ArrayList<>(1);
				list.add(line);
				return list;
			}
			String[] tokens = line.split(super.seperator_);
			if(tokens[0].contains(".")) {
				ArrayList<String> list = new ArrayList<>(1);
				String[] idxTok = tokens[0].split(".");
				list.add("$|$" + idxTok[0] + "$|$" + line);
				list.add(line);
				return list;
			} else if(tokens[0].contains("-")) {
				String[] idxTok = tokens[0].split("-");
				ArrayList<String> list = new ArrayList<>(1);
				list.add("|$|" + idxTok[0] + "|$|" + line);
				list.add(line);
				return list;
			}
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
}
