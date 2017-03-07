package service.features;

// This interface helps in calculation of interdependent feature (rest. - DE) etc. and can be
// extended for more than 2 features by defining the types correctly. Please note : that we dont
// intend to make more such interface for > 2. It can be build using this alone.

public interface MultipleEntityFeature<T,U> {
	Double getValue (T object1, U object2);
}
