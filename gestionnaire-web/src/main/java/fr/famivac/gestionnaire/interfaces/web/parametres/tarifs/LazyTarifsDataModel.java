package fr.famivac.gestionnaire.interfaces.web.parametres.tarifs;

import fr.famivac.gestionnaire.administration.parametres.entity.Tarif;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyTarifsDataModel extends LazyDataModel<Tarif> {

    private final List<Tarif> datasource;

    public LazyTarifsDataModel(List<Tarif> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public Tarif getRowData(String rowKey) {
        for (Tarif entity : datasource) {
            if (Long.valueOf(rowKey).equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Tarif bean) {
        return bean.getId();
    }

    @Override
    public List<Tarif> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

    public Tarif getRowData(int rowIndex) {
        if (rowIndex > datasource.size()) {
            throw new IllegalArgumentException();
        }
        return datasource.get(rowIndex);
    }

}
