package fr.famivac.gestionnaire.interfaces.web.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author paoesco
 * @param <T> : type de classe
 */
public class LazyFilter<T> implements Predicate<T> {

    private final Map<String, Object> filters;

    public LazyFilter(Map<String, Object> filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(T t) {
        boolean match = true;
        if (filters != null) {
            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    Object filterValue = filters.get(filterProperty);
                    String methodName = "get" + Character.toUpperCase(filterProperty.charAt(0)) + filterProperty.substring(1);
                    String fieldValue = String.valueOf(t.getClass().getMethod(methodName).invoke(t));
                    if (filterValue == null || fieldValue.toLowerCase().contains(filterValue.toString().toLowerCase())) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                    Logger.getLogger(LazyFilter.class.getName()).log(Level.FINE, null, ex);
                    match = false;
                }
            }
        }
        return match;
    }

}
