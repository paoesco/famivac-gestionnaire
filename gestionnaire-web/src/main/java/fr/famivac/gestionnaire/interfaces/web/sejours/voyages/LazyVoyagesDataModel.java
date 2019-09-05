package fr.famivac.gestionnaire.interfaces.web.sejours.voyages;

import fr.famivac.gestionnaire.interfaces.web.utils.LazyFilter;
import fr.famivac.gestionnaire.interfaces.web.utils.LazySorter;
import fr.famivac.gestionnaire.sejours.control.VoyageDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyVoyagesDataModel extends LazyDataModel<VoyageDTO> {

	private static final long serialVersionUID = 6160793949461059501L;
	
	private final List<VoyageDTO> datasource;

    public LazyVoyagesDataModel(List<VoyageDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public VoyageDTO getRowData(String rowKey) {
        for (VoyageDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getVoyageId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(VoyageDTO bean) {
        return bean.getVoyageId();
    }

    @Override
    public List<VoyageDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        //filter
        LazyFilter<VoyageDTO> lazyFilter = new LazyFilter<>(filters);
        List<VoyageDTO> data = datasource.stream().filter(lazyFilter).collect(Collectors.toList());

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter<>(VoyageDTO.class, sortField, sortOrder));
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
