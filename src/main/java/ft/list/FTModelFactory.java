/*
 * A Factory for FTListModels
 */
package ft.list;

import ft.list.models.*;

/**
 *
 * @author Manuel Agostinetto
 */
public class FTModelFactory {
    
    private FTModelFactory() { }
    /**
     * Creates a FTSimpleListModel that can manage Strings.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages String objects
     */
    public static FTSimpleListModel<String> createSimpleStringModel(boolean set) {
        return new FTSimpleGenericModel<String>( set );
    }
    
    /**
     * Creates a FTSimpleListModel that can manage Integers.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages Integer objects
     */
    public static FTSimpleListModel<Integer> createSimpleIntegerModel(boolean set) {
        return new FTSimpleGenericModel<Integer>( set );
    }
    
    /**
     * Creates a FTSimpleListModel that can manage Doubles.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages Double objects
     */
    public static FTSimpleListModel<Double> createSimpleDoubleModel(boolean set) {
        return new FTSimpleGenericModel<Double>( set );
    }
    
    /**
     * Creates a FTSimpleListModel that can manage Longs.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages Long objects
     */
    public static FTSimpleListModel<Long> createSimpleLongModel(boolean set) {
        return new FTSimpleGenericModel<Long>( set );
    }
    
    /**
     * Creates a FTSimpleListModel that can manage Floats.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages Float objects
     */
    public static FTSimpleListModel<Float> createSimpleFloatModel(boolean set) {
        return new FTSimpleGenericModel<Float>( set );
    }
    
    /**
     * Creates a FTTableListModel that can manage arrays of String.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param headers array of String to use as the table header
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages arrays of String
     */
    public static FTTableListModel<String[]> createTableStringModel(String[] headers, boolean set) {
        return new FTTableGenericModel<String>(headers, set);
    }

    /**
     * Creates a FTTableListModel that can manage arrays of Integer.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param headers array of String to use as the table header
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages arrays of Integer
     */
    public static FTTableListModel<Integer[]> createTableIntegerModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Integer>(headers, set);
    }

    /**
     * Creates a FTTableListModel that can manage arrays of Double.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param headers array of String to use as the table header
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages arrays of Double
     */
    public static FTTableListModel<Double[]> createTableDoubleModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Double>(headers, set);
    }

    /**
     * Creates a FTTableListModel that can manage arrays of Long.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param headers array of String to use as the table header
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages arrays of Long
     */
    public static FTTableListModel<Long[]> createTableLongModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Long>(headers, set);
    }

    /**
     * Creates a FTTableListModel that can manage arrays of Float.
     * It can be created as a model that act as a Set, so it can not accept
     * duplicate values.
     * 
     * @param headers array of String to use as the table header
     * @param set if <code>true</code> the generated model act as a Set
     * @return an instance of FTSimpleListModel that manages arrays of Float
     */
    public static FTTableListModel<Float[]> createTableFloatModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Float>(headers, set);
    }
}
