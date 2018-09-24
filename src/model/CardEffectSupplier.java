package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import utilities.CircularListImpl;
import utilities.Parse;

public class CardEffectSupplier {

	private static CardEffectSupplier SINGLETONSUPPLIER;
	private CircularListImpl<ConcrateConsequences> unexpected;
	private CircularListImpl<ConcrateConsequences> probability;
	private static boolean done = false;
	
	private CardEffectSupplier(List<String> probability, List<String> unexpected) {
		this.unexpected = new CircularListImpl<>(unexpected.stream().map(Parse.PARSING_CONSEQUENCES::apply).collect(Collectors.toList()));
		this.probability = new CircularListImpl<>(probability.stream().map(Parse.PARSING_CONSEQUENCES::apply).collect(Collectors.toList()));
		
		Collections.shuffle(unexpected);
		Collections.shuffle(probability);
	}
	
	private static void check(boolean condition) {
		if(condition) {
			throw new UnsupportedOperationException();
		}
	}
	
	public static CardEffectSupplier get(){
		check(SINGLETONSUPPLIER == null || !done);
		
		return SINGLETONSUPPLIER;
	}
	
	public static CardEffectSupplier get(List<String> probabilityList, List<String> unexpectedList){
		check(done);
		if(SINGLETONSUPPLIER == null) {
			SINGLETONSUPPLIER = new CardEffectSupplier(probabilityList, unexpectedList);
			done = true;
		}
		
		return SINGLETONSUPPLIER;
	}
	
	public ConcrateConsequences getNextProbability(){
		this.probability.shift();
		return this.probability.getHead();
	}
	
	public ConcrateConsequences getNextUnexpected() {
		this.unexpected.shift();
		return this.unexpected.getHead();
	}
}
