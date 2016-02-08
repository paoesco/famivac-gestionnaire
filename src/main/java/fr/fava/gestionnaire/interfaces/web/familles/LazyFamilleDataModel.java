package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.interfaces.web.utils.LazyFilter;
import fr.fava.gestionnaire.interfaces.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
        //filter
        LazyFilter<FamilleDTO> lazyFilter = new LazyFilter<>(filters);
        List<FamilleDTO> data = datasource.stream().filter(lazyFilter).collect(Collectors.toList());

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
