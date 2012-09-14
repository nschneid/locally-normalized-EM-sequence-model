package edu.cmu.cs.lti.ark.ssl.pos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import edu.cmu.cs.lti.ark.ssl.util.BasicFileIO;



public class UnlabeledSentencesReader {
	
	/** whitespace-sparated tokens, one sequence per line */
	public static List<List<String>> readSequences(String path, int numSequences, int maxSequenceLength) {
		List<List<String>> sequences = new ArrayList<List<String>>();
		try {
			BufferedReader reader = BasicFileIO.openFileToRead(path);
			String line = BasicFileIO.getLine(reader);
			int countLines = 0;
			while (line != null) {
				line = line.trim();
				String[] toks = TabSeparatedFileReader.getToks(line);
				if (toks.length > maxSequenceLength) {
					line = BasicFileIO.getLine(reader);
					continue;
				}
				List<String> seq = Arrays.asList(toks);
				sequences.add(seq);
				countLines++;
				if (countLines >= numSequences) {
					break;
				}
				line = BasicFileIO.getLine(reader);
			}
			BasicFileIO.closeFileAlreadyRead(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequences;
	}
	
	// nschneid
	/** tab-separated features, first of which is the token; one token per line, blank line between sequences */
	public static List<List<List<String>>> readSequencesWithFeatures(String path, int numSequences, int maxSequenceLength) {
		List<List<List<String>>> sequences = new ArrayList<List<List<String>>>();
		try {
			BufferedReader reader = BasicFileIO.openFileToRead(path);
			String line;
			int nSeqs = 0;
			List<List<String>> seq = new ArrayList<List<String>>();
			while ((line = BasicFileIO.getLine(reader)) != null) {
				line = line.trim();
				if (line.length()==0) {
					if (seq.size()>0) {
						sequences.add(seq);
						nSeqs++;
						if (nSeqs >= numSequences) {
							break;
						}
						
						seq = new ArrayList<List<String>>();
					}
					continue;
				}
				String[] toks = TabSeparatedFileReader.getToks(line, "\t", false);
				
				List<String> feats = Arrays.asList(toks);
				seq.add(feats);
				if (seq.size() > maxSequenceLength) {
					seq = new ArrayList<List<String>>();
					continue;
				}
			}
			
			if (seq.size()>0) {
				sequences.add(seq);
				nSeqs++;
			}
			
			BasicFileIO.closeFileAlreadyRead(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequences;
	}
	
	// nschneid
		/** loads one token per line, trimming newlines only. blank lines separate sequences. */
		public static List<List<String>> readSequencesOneTokenPerLine(String path, int numSequences, int maxSequenceLength) {
			List<List<String>> sequences = new ArrayList<List<String>>();
			try {
				BufferedReader reader = BasicFileIO.openFileToRead(path);
				String line;
				int nSeqs = 0;
				List<String> seq = new ArrayList<String>();
				while ((line = BasicFileIO.getLine(reader)) != null) {
					if (line.length()==0) {
						if (seq.size()>0) {
							sequences.add(seq);
							nSeqs++;
							seq = new ArrayList<String>();
							
							if (nSeqs >= numSequences) {
								break;
							}
						}
						continue;
					}
					seq.add(line);
					if (seq.size() > maxSequenceLength) {
						seq = new ArrayList<String>();
						continue;
					}
				}
				
				if (seq.size()>0 && seq.size()<=maxSequenceLength) {
					sequences.add(seq);
					nSeqs++;
				}
				
				BasicFileIO.closeFileAlreadyRead(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sequences;
		}
}