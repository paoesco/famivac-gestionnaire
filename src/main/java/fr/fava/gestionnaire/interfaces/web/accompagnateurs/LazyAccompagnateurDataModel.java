package fr.fava.gestionnaire.interfaces.web.accompagnateurs;

import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.domain.sejour.Accompagnateur;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyAccompagnateurDataModel extends LazyDataModel<Accompagnateur> {

    private final List<Accompagnateur> datasource;

    public LazyAccompagnateurDataModel(List<Accompagnateur> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public Accompagnateur getRowData(String rowKey) {
        for (Accompagnateur bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Accompagnateur bean) {
        return bean.getId();
    }

    @Override
    public List<Accompagnateur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
