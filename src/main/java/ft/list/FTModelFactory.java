/*
 * A Factory for FTListModels
 */
package ft.list;

import ft.list.models.*;

/**
 *
 * @author LeleFT
 */
public class FTModelFactory {
    
    private FTModelFactory() { }
    
    public static FTSimpleListModel<String> createSimpleStringModel(boolean set) {
        return new FTSimpleGenericModel<String>( set );
    }
    
    public static FTSimpleListModel<Integer> createSimpleIntegerModel(boolean set) {
        return new FTSimpleGenericModel<Integer>( set );
    }
    
    public static FTSimpleListModel<Double> createSimpleDoubleModel(boolean set) {
        return new FTSimpleGenericModel<Double>( set );
    }
    
    public static FTSimpleListModel<Long> createSimpleLongModel(boolean set) {
        return new FTSimpleGenericModel<Long>( set );
    }
    
    public static FTSimpleListModel<Float> createSimpleFloatModel(boolean set) {
        return new FTSimpleGenericModel<Float>( set );
    }
    
    public static FTTableListModel<String[]> createTableStringModel(String[] headers, boolean set) {
        return new FTTableGenericModel<String>(headers, set);
    }

    public static FTTableListModel<Integer[]> createTableIntegerModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Integer>(headers, set);
    }

    public static FTTableListModel<Double[]> createTableDoubleModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Double>(headers, set);
    }

    public static FTTableListModel<Long[]> createTableLongModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Long>(headers, set);
    }

    public static FTTableListModel<Float[]> createTableFloatModel(String[] headers, boolean set) {
        return new FTTableGenericModel<Float>(headers, set);
    }
}
