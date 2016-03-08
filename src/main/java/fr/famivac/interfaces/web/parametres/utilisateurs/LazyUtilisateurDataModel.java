package fr.famivac.interfaces.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.administration.control.RetrieveUtilisateursDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyUtilisateurDataModel extends LazyDataModel<RetrieveUtilisateursDTO> {

    private final List<RetrieveUtilisateursDTO> datasource;

    public LazyUtilisateurDataModel(List<RetrieveUtilisateursDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public RetrieveUtilisateursDTO getRowData(String rowKey) {
        for (RetrieveUtilisateursDTO dto : datasource) {
            if (rowKey.equals(dto.getLogin())) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RetrieveUtilisateursDTO bean) {
        return bean.getLogin();
    }

    @Override
    public List<RetrieveUtilisateursDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

    public RetrieveUtilisateursDTO getRowData(int rowIndex) {
        if (rowIndex > datasource.size()) {
            throw new IllegalArgumentException();
        }
        return datasource.get(rowIndex);
    }

}
