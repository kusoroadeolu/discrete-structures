package functions;

import settheory.sets.DomainSet;
import settheory.sets.QuerySet;

import java.util.*;
import java.util.function.Function;

public class FunctionChecker<T, R> {
    private final Function<T, R> function;
    private DomainSet<T> domainSet; //Domain of the function. You can set a predicate for this domain, hereby restricting the values allowed in this set
    private QuerySet<R> codomain;
    private final Map<T, R> mapInputsToImages;

    public FunctionChecker(Function<T, R> function, DomainSet<T> domainSet, QuerySet<R> codomain){
        validateDomain(domainSet);
        this.function = function;
        this.domainSet = domainSet;
        this.codomain = codomain;
        this.mapInputsToImages = new HashMap<>(domainSet.size());
    }

    //To ensure users can't instantiate without a function
    private FunctionChecker(){
        this.function = null;
        this.mapInputsToImages = null;
    }

    /**
     * Calculates the range of the domain of this function
     * @return A map of images mapping each input to it's image
     * */
    public Map<T, R> getMapInputsToImages(){
        for(T t : this.domainSet.toList()){
            final R res = function.apply(t);
            this.mapInputsToImages.put(t, res);
        }

        return this.mapInputsToImages;
    }

    /**
     * Calculates the range of the domain of this function
     * @return A set of the images in this function
     * */
    public Set<R> getRange(){
        this.validateInputToImagesMap();
        return new HashSet<>(this.mapInputsToImages.values());
    }

    /**
     * Checks if this function is surjective(meaning each element in the codomain exists in the range)
     * @return true or false
     * */
    public boolean isSurjective(){
        this.validateInputToImagesMap();
        return this.codomain.isSubsetOf(new QuerySet<>(this.mapInputsToImages.values())); //Check if the range is a subset of the codomain
    }

    /**
     * Checks if this function is injective(meaning each element in the range is unique to each element in the domain)
     * @return true or false
     * */
    public boolean isInjective(){
        Set<R> images = new HashSet<>(this.mapInputsToImages.values());
        return images.size() == this.domainSet.size();
    }

    /**
     * Checks if this function is both injective and bijective
     * @return true or false
     * */
    public boolean isBijective(){
        return this.isInjective() && this.isSurjective();
    }

    /**
     * Finds the set of the inputs in the domain that map to a given image
     * @return A set of inputs which map to the given image
     * */
    public Set<T> inverseImage(R image){
        this.validateInputToImagesMap();
        Set<T> inputs = new HashSet<>();
        for (T t : this.domainSet.toList()){
            R r = this.function.apply(t);

            if(Objects.equals(image, r)){
                inputs.add(t);
            }

        }

        return inputs;
    }



    private void validateDomain(DomainSet<T> domainSet){
        if(domainSet.isEmpty()){
            throw new IllegalArgumentException("Domain cannot be empty");
        }

        this.domainSet = domainSet;
    }


    //Simple helper function to calculate map all the images if the map is empty
    private void validateInputToImagesMap(){
        if(this.mapInputsToImages.isEmpty()) {
            getMapInputsToImages();
        }
    }



}
