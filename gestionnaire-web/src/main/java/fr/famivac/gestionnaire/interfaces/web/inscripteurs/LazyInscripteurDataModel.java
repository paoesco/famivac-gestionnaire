package fr.famivac.gestionnaire.interfaces.web.inscripteurs;

import fr.famivac.gestionnaire.enfants.control.RetrieveInscripteursResponseDTO;
import fr.famivac.gestionnaire.interfaces.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Collections;
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
        setRowCount(datasource.size());
        //sort
        if (sortField != null) {
            Collections.sort(datasource, new LazySorter<>(RetrieveInscripteursResponseDTO.class, sortField, sortOrder));
        }
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        return datasource.subList(first, max);
    }

}
