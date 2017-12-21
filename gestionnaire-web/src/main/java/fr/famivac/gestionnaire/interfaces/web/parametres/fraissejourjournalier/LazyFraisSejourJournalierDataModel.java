package fr.famivac.gestionnaire.interfaces.web.parametres.fraissejourjournalier;

import fr.famivac.gestionnaire.administration.parametres.entity.FraisSejourJournalier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyFraisSejourJournalierDataModel extends LazyDataModel<FraisSejourJournalier> {

    private final List<FraisSejourJournalier> datasource;

    public LazyFraisSejourJournalierDataModel(List<FraisSejourJournalier> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public FraisSejourJournalier getRowData(String rowKey) {
        for (FraisSejourJournalier entity : datasource) {
            if (Long.valueOf(rowKey).equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(FraisSejourJournalier bean) {
        return bean.getId();
    }

    @Override
    public List<FraisSejourJournalier> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

    public FraisSejourJournalier getRowData(int rowIndex) {
        if (rowIndex > datasource.size()) {
            throw new IllegalArgumentException();
        }
        return datasource.get(rowIndex);
    }

}
