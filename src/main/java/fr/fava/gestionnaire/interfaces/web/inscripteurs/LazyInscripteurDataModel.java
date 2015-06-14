package fr.fava.gestionnaire.interfaces.web.inscripteurs;

import fr.fava.gestionnaire.application.enfant.RetrieveInscripteursResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyInscripteurDataModel extends LazyDataModel<RetrieveInscripteursResponseDTO> {

    private final List<RetrieveInscripteursResponseDTO> datasource;

    public LazyInscripteurDataModel(List<RetrieveInscripteursResponseDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public RetrieveInscripteursResponseDTO getRowData(String rowKey) {
        for (RetrieveInscripteursResponseDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RetrieveInscripteursResponseDTO bean) {
        return bean.getId();
    }

    @Override
    public List<RetrieveInscripteursResponseDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
