package service.features;

// This interface helps in calculation of interdependent feature (rest. - DE) etc. and can be
// extended for more than 2 features by defining the types correctly. Please note : that we dont
// intend to make more such interface for > 2. It can be build using this alone.

import org.jblas.DoubleMatrix;

import java.util.List;

public interface MultipleEntityFeature<T,U> {
	DoubleMatrix getValue (T object1, List<U> object2);
}
