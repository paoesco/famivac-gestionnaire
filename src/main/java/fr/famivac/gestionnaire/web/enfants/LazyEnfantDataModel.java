package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.domains.enfants.control.EnfantDTO;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyEnfantDataModel extends LazyDataModel<EnfantDTO> {

  private final List<EnfantDTO> datasource;

  public LazyEnfantDataModel(List<EnfantDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public EnfantDTO getRowData(String rowKey) {
    for (EnfantDTO bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public Object getRowKey(EnfantDTO bean) {
    return bean.getId();
  }

  @Override
  public List<EnfantDTO> load(
      int first,
      int pageSize,
      String sortField,
      SortOrder sortOrder,
      Map<String, FilterMeta> filterBy) {
    setRowCount(datasource.size());
    if (sortField != null) {
      Collections.sort(datasource, new LazySorter<>(EnfantDTO.class, sortField, sortOrder));
    }
    int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
    return datasource.subList(first, max);
  }
}
