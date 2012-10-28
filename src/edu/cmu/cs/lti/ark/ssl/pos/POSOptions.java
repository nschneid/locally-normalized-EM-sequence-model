package edu.cmu.cs.lti.ark.ssl.pos;

import argparser.ArgParser;
import argparser.BooleanHolder;
import argparser.DoubleHolder;
import argparser.IntHolder;
import argparser.StringHolder;

public class POSOptions extends ArgParser {
	public StringHolder trainSet = new StringHolder(null);
	public StringHolder unlabeledSet = new StringHolder(null);
	public StringHolder unlabeledFeatureFile = new StringHolder(null);
	public BooleanHolder useUnlabeledData = new BooleanHolder();
	public StringHolder testSet = new StringHolder(null);
	public StringHolder testFeatureFile = new StringHolder(null);
	public StringHolder trainOrTest = new StringHolder(null);	
	public StringHolder modelFile = new StringHolder(null);
	public StringHolder runOutput = new StringHolder(null);
	public IntHolder numLabeledSentences = new IntHolder();
	public IntHolder numUnLabeledSentences = new IntHolder();
	public IntHolder maxSentenceLength = new IntHolder();
	//public IntHolder numLabels;
	public IntHolder iters = new IntHolder();
	public IntHolder printRate = new IntHolder();
	public BooleanHolder useStandardMultinomialMStep = new BooleanHolder();
	public DoubleHolder standardMStepCountSmoothing = new DoubleHolder(0);
	public BooleanHolder useGlobalForLabeledData = new BooleanHolder();
	public DoubleHolder initialWeightsUpper = new DoubleHolder(0.01);
	public DoubleHolder initialWeightsLower = new DoubleHolder(-0.01);
	public DoubleHolder regularizationWeight = new DoubleHolder(0);
	public DoubleHolder regularizationBias = new DoubleHolder(0);
	public BooleanHolder useStandardFeatures = new BooleanHolder();
	public IntHolder lengthNGramSuffixFeature = new IntHolder(3);
	public BooleanHolder useBiasFeature = new BooleanHolder();
	public DoubleHolder biasFeatureBias = new DoubleHolder();
	public DoubleHolder biasFeatureRegularizationWeight = new DoubleHolder();
	public IntHolder randSeedIndex = new IntHolder(-1);
	public StringHolder execPoolDir = new StringHolder(null);
	public BooleanHolder restartTraining = new BooleanHolder();
	public StringHolder restartModelFile = new StringHolder(null);		
	public BooleanHolder useSameSetOfFeatures = new BooleanHolder();
	public BooleanHolder startWithTrainedSupervisedModel = new BooleanHolder();
	public StringHolder trainedSupervisedModel = new StringHolder(null);
	public DoubleHolder gamma = new DoubleHolder();
	public BooleanHolder useOnlyUnlabeledData = new BooleanHolder();
	public StringHolder regParametersModel = new StringHolder(null);	
	public BooleanHolder useTagDictionary = new BooleanHolder();	
	public StringHolder tagDictionaryFile = new StringHolder(null);
	public StringHolder tagDictionaryKeyFields = new StringHolder(null);
	//public StringHolder clusterToTagMappingFile = new StringHolder(null);	
	public BooleanHolder trainHMMDiscriminatively = new BooleanHolder();
	public BooleanHolder useStackedFeatures = new BooleanHolder();
	public StringHolder stackedFile = new StringHolder(null);
	public IntHolder numTags = new IntHolder();	
	public BooleanHolder bio = new BooleanHolder();
	public StringHolder initTransitionsFile = new StringHolder(null);
	public BooleanHolder useDistSim = new BooleanHolder();
	public BooleanHolder useNames = new BooleanHolder();	
	public BooleanHolder useInterpolation = new BooleanHolder();
	public StringHolder fineToCoarseMapFile = new StringHolder(null);
	public StringHolder pathToHelperTransitions = new StringHolder(null);
	public BooleanHolder printPosteriors = new BooleanHolder();
	
	public POSOptions(String[] args) {
		super("SemiSupervisedPOSTagger <options>\n\n" +
              "BLURB.", true);
		
		this.addOption("--trainSet %s #Tagged sentences for training", trainSet);
		this.addOption("--unlabeledSet %s #Unlabeled sentences for SSL (raw text)", unlabeledSet);
		this.addOption("--unlabeledFeatureFile %s #Unlabeled sentences for SSL (features)", unlabeledFeatureFile);
		this.addOption("--useUnlabeledData %v #Whether to use unlabeled data", useUnlabeledData);
		this.addOption("--testSet %s #Sentences for test (tagged tokens)", testSet);
		this.addOption("--testFeatureFile %s #Sentences for test (features and tags)", testFeatureFile);
		this.addOption("--trainOrTest %s #Tagged sentences for test", trainOrTest);
		this.addOption("--modelFile %s #Model file", modelFile);
		this.addOption("--runOutput %s #Run output", runOutput);
		this.addOption("--numLabeledSentences %i #Number of sentences to read from labeled train/test file.", numLabeledSentences);
		this.addOption("--numUnLabeledSentences %i #Number of sentences to read from labeled train/test file.", numUnLabeledSentences);
		this.addOption("--maxSentenceLength %i #Skip over sentences that have length longer than this number.", maxSentenceLength);
	//	this.addOption("--numLabels %i #Number of unsupervised tag clusters to use.", numLabels);
		this.addOption("--iters %i #Number of iterations to run for.", iters);
		this.addOption("--printRate %i #Number of iterations between recording weights to file.", printRate);
		this.addOption("--useStandardMultinomialMStep %v #Forces only full-indicator features, and does standard M-step by normalizing expected counts.", useStandardMultinomialMStep);
		this.addOption("--standardMStepCountSmoothing %f #Add this pseudo-count to expected counts when doing standard M-step.", standardMStepCountSmoothing);
		this.addOption("--useGlobalForLabeledData %v #If true, use locally normalized potentials. Otherwise use globally normalized.", useGlobalForLabeledData);
		this.addOption("--initialWeightsUpper %f #Upper end point of interval from which initial weights are drawn UAR.", initialWeightsUpper);
		this.addOption("--initialWeightsLower %f #Lower end point of interval from which initial weights are drawn UAR.", initialWeightsLower);
		this.addOption("--regularizationWeight %f #Regulariztion term is sum_i[ c*(w_i - b)^2 ] where c is regularization weight and b is regularization bias.", regularizationWeight);
		this.addOption("--regularizationBias %f #Regulariztion term is sum_i[ c*(w_i - b)^2 ] where c is regularization weight and b is regularization bias.", regularizationBias);
		this.addOption("--useStandardFeatures %v #If true, in addition to full-indicator features use the following features: 1) initial capital 2) contains digit 3) contains hyphen 4) 3-gram suffix indicators. Otherwise just use full-indicators.", useStandardFeatures);
		this.addOption("--lengthNGramSuffixFeature %i #n to use in n-gram suffix features.", lengthNGramSuffixFeature);
		this.addOption("--useBiasFeature %v #Use a bias feature that is constant.", useBiasFeature);
		this.addOption("--biasFeatureBias %f #Regularization bias for weight for bias feature.", biasFeatureBias);
		this.addOption("--biasFeatureRegularizationWeight %f #Regularization weight for weight for bias feature.", biasFeatureRegularizationWeight);
		this.addOption("--randSeedIndex %i #Index of random seed to use. (10 possible random seeds are deterministically precomputed.)", randSeedIndex);
		this.addOption("--execPoolDir %s #Prefix to an execution directory", execPoolDir);
		this.addOption("--restartTraining %v #Restart from a given model file", restartTraining);
		this.addOption("--restartModelFile %s #Restart from a given model file", restartModelFile);
		this.addOption("--useSameSetOfFeatures %v #Use same set of features in both labeled and unlabeled data.", useSameSetOfFeatures);
		this.addOption("--startWithTrainedSupervisedModel %v #Start with a trained supervised model.", startWithTrainedSupervisedModel);
		this.addOption("--trainedSupervisedModel %s #Trained supervised model.", trainedSupervisedModel);
		this.addOption("--gamma %f #Weight given to unlabeled data.", gamma);
		this.addOption("--useOnlyUnlabeledData %v #If we want to use unlabeled data alone.", useOnlyUnlabeledData);
		this.addOption("--regParametersModel %s #Use model from which we'd like to read the reg parameters' means", regParametersModel);
		this.addOption("--useTagDictionary %v #Use tag dictionary", useTagDictionary);			
		this.addOption("--tagDictionaryFile %s #Tag dictionary file", tagDictionaryFile);
		this.addOption("--tagDictionaryKeyFields %s #When the input is a feature file and a tagging dictionary is used, comma-separated indices of the tab-separated fields to serve as the key when checking against the dictionary (default: 0)", tagDictionaryKeyFields);
	//	this.addOption("--clusterToTagMappingFile %s #Cluster to tag map file", clusterToTagMappingFile);
		this.addOption("--trainHMMDiscriminatively %v #Train the HMM discriminatively", trainHMMDiscriminatively);
		this.addOption("--useStackedFeatures %v #Use stacked features", useStackedFeatures);
		this.addOption("--stackedFile %s #Stacked file", stackedFile);
		this.addOption("--numTags %i #Number of unsupervised tags", numTags);
		this.addOption("--bio %v #BIO tags; ban incoherent transitions", bio);
		this.addOption("--initTransitionsFile %s #File containing initial transition probabilities", initTransitionsFile);
		this.addOption("--useDistSim %v #Use distributional similarities", useDistSim);
		this.addOption("--useNames %v #Use names", useNames);
		this.addOption("--useInterpolation %v #Use interpolation", useInterpolation);
		this.addOption("--fineToCoarseMapFile %s #Fine to coarse tagmap file", fineToCoarseMapFile);
		this.addOption("--pathToHelperTransitions %s #Comma separated path to helper transitions", pathToHelperTransitions);
		this.addOption("--printPosteriors %v #Print posteriors", printPosteriors);
	}
	
	public String[] parseArgs(String[] args) {	
		this.matchAllArgs(args);
	    return args;
	}
}