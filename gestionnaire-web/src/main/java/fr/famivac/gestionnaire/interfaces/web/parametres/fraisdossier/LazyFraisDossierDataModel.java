package fr.famivac.gestionnaire.interfaces.web.parametres.fraisdossier;

import fr.famivac.gestionnaire.administration.parametres.entity.FraisDossier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyFraisDossierDataModel extends LazyDataModel<FraisDossier> {

    private final List<FraisDossier> datasource;

    public LazyFraisDossierDataModel(List<FraisDossier> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public FraisDossier getRowData(String rowKey) {
        for (FraisDossier entity : datasource) {
            if (Long.valueOf(rowKey).equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(FraisDossier bean) {
        return bean.getId();
    }

    @Override
    public List<FraisDossier> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

    public FraisDossier getRowData(int rowIndex) {
        if (rowIndex > datasource.size()) {
            throw new IllegalArgumentException();
        }
        return datasource.get(rowIndex);
    }

}
