package fr.fava.gestionnaire.interfaces.web.communes;

import fr.fava.gestionnaire.domain.common.Commune;
import fr.fava.gestionnaire.domain.utils.AlphanumComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyCommuneDataModel extends LazyDataModel<Commune> {

    private final List<Commune> datasource;

    private Comparator alphanumComparator;

    public LazyCommuneDataModel(List<Commune> datasource) {
        alphanumComparator = new AlphanumComparator();
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public Commune getRowData(String rowKey) {
        for (Commune bean : datasource) {
            if (rowKey.equals(bean.getCode())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Commune bean) {
        return bean.getCode();
    }

    @Override
    public List<Commune> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        datasource.sort((Commune o1, Commune o2) -> {
            return alphanumComparator.compare(o1.getCode(), o2.getCode());
        });
        return datasource.subList(first, max);
    }

    public boolean contains(Commune bean) {
        return datasource.contains(bean);
    }

}
