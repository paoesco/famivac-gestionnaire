package fr.famivac.gestionnaire.web.sejours.voyages;

import fr.famivac.gestionnaire.domains.sejours.control.VoyageDTO;
import fr.famivac.gestionnaire.web.utils.LazyFilter;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/** @author paoesco */
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
  public List<VoyageDTO> load(
      int first,
      int pageSize,
      String sortField,
      SortOrder sortOrder,
      Map<String, FilterMeta> filters) {
    var filter = new LazyFilter<>(filters.values());

    var rowCount = datasource.stream().filter(filter).count();
    setRowCount((int) rowCount);

    var data =
        datasource.stream()
            .filter(filter)
            .sorted(new LazySorter<>(VoyageDTO.class, sortField, sortOrder))
            .skip(first)
            .limit(pageSize)
            .collect(Collectors.toList());

    return data;
  }
}
