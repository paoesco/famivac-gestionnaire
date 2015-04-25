package fr.fava.gestionnaire.interfaces.sejours.web;

import fr.fava.gestionnaire.application.famille.RetrieveFamillesResponseDTO;
import fr.fava.gestionnaire.application.sejours.RetrieveSejoursDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazySejourDataModel extends LazyDataModel<RetrieveSejoursDTO> {

    private final List<RetrieveSejoursDTO> datasource;

    public LazySejourDataModel(List<RetrieveSejoursDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public RetrieveSejoursDTO getRowData(String rowKey) {
        for (RetrieveSejoursDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RetrieveSejoursDTO bean) {
        return bean.getId();
    }

    @Override
    public List<RetrieveSejoursDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
