package fr.fava.gestionnaire.interfaces.web.enfants;

import fr.fava.gestionnaire.application.enfant.EnfantDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyEnfantDataModel extends LazyDataModel<EnfantDTO> {

    private final List<EnfantDTO> datasource;

    public LazyEnfantDataModel(List<EnfantDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public EnfantDTO getRowData(String rowKey) {
        for (EnfantDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(EnfantDTO bean) {
        return bean.getId();
    }

    @Override
    public List<EnfantDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
