package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.interfaces.web.utils.LazySorter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyFamilleDataModel extends LazyDataModel<FamilleDTO> {

    private static final Logger logger = Logger.getLogger(LazyFamilleDataModel.class.getName());

    private final List<FamilleDTO> datasource;

    public LazyFamilleDataModel(List<FamilleDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public FamilleDTO getRowData(String rowKey) {
        for (FamilleDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(FamilleDTO bean) {
        return bean.getId();
    }

    @Override
    public List<FamilleDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<FamilleDTO> data = new ArrayList<>();

        //filter
        for (FamilleDTO famille : datasource) {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String methodName = "get" + Character.toUpperCase(filterProperty.charAt(0)) + filterProperty.substring(1);
                        String fieldValue = String.valueOf(famille.getClass().getMethod(methodName).invoke(famille));
                        if (filterValue == null || fieldValue.toLowerCase().startsWith(filterValue.toString().toLowerCase())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(LazyFamilleDataModel.class.getName()).log(Level.FINE, null, ex);
                        match = false;
                    } catch (NoSuchMethodException | InvocationTargetException ex) {
                        Logger.getLogger(LazyFamilleDataModel.class.getName()).log(Level.FINE, null, ex);
                        match = false;
                    }
                }
            }
            if (match) {
                data.add(famille);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter<>(FamilleDTO.class, sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

}
