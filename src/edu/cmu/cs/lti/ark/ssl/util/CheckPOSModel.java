package edu.cmu.cs.lti.ark.ssl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.lti.ark.ssl.pos.POSModel;

public class CheckPOSModel {
	public static void main(String[] args) {
		String modelFile = 
			"/usr2/dipanjan/experiments/SSL/UnsupervisedPOS/data/tbmodels/"+args[0]+"-fhmm-reg.en.de.cs.it.int.1.0.model";
		System.out.println("Modelfile:" + modelFile);
		POSModel model = (POSModel) BasicFileIO.readSerializedObject(modelFile);
		ArrayList<String> indexToPOS;
		ArrayList<String> indexToWord;
		ArrayList<String> indexToFeature;
		Map<String, Integer> posToIndex;
		Map<String, Integer> wordToIndex;
		Map<String, Integer> featureToIndex;
		ArrayList<Integer> featureIndexCounts;
		featureIndexCounts = model.getFeatureIndexCounts();
		featureToIndex = model.getFeatureToIndex();
		indexToFeature = model.getIndexToFeature();
		indexToPOS = model.getIndexToPOS();
		posToIndex = model.getPosToIndex();
		double[] weights = model.getWeights();
		
		ArrayList<String> feats = new ArrayList<String>();
		ArrayList<Double> wts = new ArrayList<Double>();
		Set<Integer> uniqueIds = new HashSet<Integer>();
		Set<Integer> uniqueLabels = new HashSet<Integer>();
		for (String feat: indexToFeature) {
			if (feat.startsWith("iind")) {
				String[] toks = feat.split("\\|");
				int id = new Integer(toks[1]);
				int label = new Integer(toks[2]);
				uniqueIds.add(id);
				uniqueLabels.add(label);
			}
		}
		System.out.println("Size of unique ids:" + uniqueIds.size());
		System.out.println("Size of unique labels:" + uniqueLabels.size());
		
		Integer[] idArray = new Integer[uniqueIds.size()];
		uniqueIds.toArray(idArray);
		Integer[] labArray = new Integer[uniqueLabels.size()];
		uniqueLabels.toArray(labArray);
		Arrays.sort(labArray);
		double[][] params = new double[idArray.length][labArray.length];
		for (int l = 0; l < labArray.length; ++l) {
			double sum = 0.0;
			System.out.println("\n\nLabel:"+labArray[l]);
			for (int h = 0; h < idArray.length; ++h) {
				String feat = "iind|"+idArray[h] + "|"+labArray[l];
				if (!featureToIndex.containsKey(feat)) {
					System.out.println("Problem with:" + feat + " Not present.");
					System.exit(-1);
				}
				int index = featureToIndex.get(feat);
				double wt = Math.exp(weights[index]);
				sum += wt;
			}
			for (int h = 0; h < idArray.length; ++h) {
				String feat = "iind|"+idArray[h] + "|"+labArray[l];
				int index = featureToIndex.get(feat);
				double wt = Math.exp(weights[index]);
				wt /= sum;
				System.out.println("Weight for id: " + h + " = " + wt);
			}
		}
	}
}