package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.application.famille.FamilleDTO;
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
public class LazyFamilleDataModel extends LazyDataModel<FamilleDTO> {

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
        setRowCount(datasource.size());
        //sort
        if (sortField != null) {
            Collections.sort(datasource, new LazySorter<>(FamilleDTO.class, sortField, sortOrder));
        }
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        return datasource.subList(first, max);
    }

}
