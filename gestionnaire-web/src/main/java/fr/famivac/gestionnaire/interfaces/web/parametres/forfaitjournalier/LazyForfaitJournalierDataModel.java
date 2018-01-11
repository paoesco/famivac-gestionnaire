package fr.famivac.gestionnaire.interfaces.web.parametres.forfaitjournalier;

import fr.famivac.gestionnaire.administration.parametres.entity.ForfaitJournalier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyForfaitJournalierDataModel extends LazyDataModel<ForfaitJournalier> {

    private final List<ForfaitJournalier> datasource;

    public LazyForfaitJournalierDataModel(List<ForfaitJournalier> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public ForfaitJournalier getRowData(String rowKey) {
        for (ForfaitJournalier entity : datasource) {
            if (Long.valueOf(rowKey).equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(ForfaitJournalier bean) {
        return bean.getId();
    }

    @Override
    public List<ForfaitJournalier> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

    public ForfaitJournalier getRowData(int rowIndex) {
        if (rowIndex > datasource.size()) {
            throw new IllegalArgumentException();
        }
        return datasource.get(rowIndex);
    }

}
