package fr.famivac.gestionnaire.interfaces.web.sejours.voyages;

import fr.famivac.gestionnaire.interfaces.web.utils.LazySorter;
import fr.famivac.gestionnaire.sejours.control.VoyageDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyVoyagesDataModel extends LazyDataModel<VoyageDTO> {

    private final List<VoyageDTO> datasource;

    public LazyVoyagesDataModel(List<VoyageDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public VoyageDTO getRowData(String rowKey) {
        for (VoyageDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getSejourId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(VoyageDTO bean) {
        return bean.getSejourId();
    }

    @Override
    public List<VoyageDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setRowCount(datasource.size());
        //sort
        if (sortField != null) {
            Collections.sort(datasource, new LazySorter<>(VoyageDTO.class, sortField, sortOrder));
        }
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        return datasource.subList(first, max);
    }

}
