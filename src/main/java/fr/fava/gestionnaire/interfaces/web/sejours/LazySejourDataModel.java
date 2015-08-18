package fr.fava.gestionnaire.interfaces.web.sejours;

import fr.fava.gestionnaire.application.sejour.SejourDTO;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazySejourDataModel extends LazyDataModel<SejourDTO> {

    private final List<SejourDTO> datasource;

    public LazySejourDataModel(List<SejourDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public SejourDTO getRowData(String rowKey) {
        for (SejourDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(SejourDTO bean) {
        return bean.getId();
    }

    @Override
    public List<SejourDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setRowCount(datasource.size());
        //sort
        if (sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        return datasource.subList(first, max);
    }

    class LazySorter implements Comparator<SejourDTO> {

        private final String sortField;

        private final SortOrder sortOrder;

        public LazySorter(String sortField, SortOrder sortOrder) {
            this.sortField = sortField;
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(SejourDTO s1, SejourDTO s2) {
            try {
                String methodName = "get" + Character.toUpperCase(this.sortField.charAt(0)) + this.sortField.substring(1);
                Object value1 = SejourDTO.class.getMethod(methodName).invoke(s1);
                Object value2 = SejourDTO.class.getMethod(methodName).invoke(s2);
                int value = ((Comparable) value1).compareTo(value2);
                return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.getLogger(LazySejourDataModel.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

}
