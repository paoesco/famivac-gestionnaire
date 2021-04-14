package fr.famivac.gestionnaire.web.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.FilterMeta;

public class LazyFilter<T> implements Predicate<T> {

  private final Collection<FilterMeta> filters;

  public LazyFilter(Collection<FilterMeta> filters) {
    this.filters = filters;
  }

  @Override
  public boolean test(T t) {
    if (Objects.isNull(filters)) {
      return true;
    }
    boolean match = true;

    // We need to check for each filter that the object is matching the criteria
    for (FilterMeta filterMeta : filters) {
      try {
        if (Objects.isNull(filterMeta.getFilterValue())) {
          match = true;
          continue;
        }

        // Creates getter
        String filterProperty = filterMeta.getField();
        String getterMethodName =
            "get" + Character.toUpperCase(filterProperty.charAt(0)) + filterProperty.substring(1);
        // Get property value in object to test
        String propertyValue =
            String.valueOf(t.getClass().getMethod(getterMethodName).invoke(t))
                .toLowerCase(Locale.ROOT);
        // Get filter value passed as a param
        String filterValue = filterMeta.getFilterValue().toString().toLowerCase(Locale.ROOT);
        // Match
        match = propertyValue.contains(filterValue);
      } catch (SecurityException
          | IllegalArgumentException
          | IllegalAccessException
          | NoSuchMethodException
          | InvocationTargetException ex) {
        Logger.getLogger(LazyFilter.class.getName()).log(Level.FINE, null, ex);
        match = false;
      }
    }
    return match;
  }
}
