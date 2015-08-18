package fr.fava.gestionnaire.interfaces.web.sejours;

import fr.fava.gestionnaire.application.sejour.SejourDTO;
import fr.fava.gestionnaire.interfaces.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
            Collections.sort(datasource, new LazySorter<>(SejourDTO.class, sortField, sortOrder));
        }
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        return datasource.subList(first, max);
    }

}
