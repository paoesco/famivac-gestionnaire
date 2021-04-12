package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.familles.boundary.FamilleDTO;
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
public class LazyFamilleDataModel extends LazyDataModel<FamilleDTO> {

  //    private static final Logger logger = Logger.getLogger(LazyFamilleDataModel.class.getName());

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
  public List<FamilleDTO> load(
      int first,
      int pageSize,
      String sortField,
      SortOrder sortOrder,
      Map<String, FilterMeta> filterBy) {
    var filter = new LazyFilter<>(filterBy.values());

    var rowCount = datasource.stream().filter(filter).count();
    setRowCount((int) rowCount);

    var data =
        datasource.stream()
            .filter(filter)
            .sorted(new LazySorter<>(FamilleDTO.class, sortField, sortOrder))
            .skip(first)
            .limit(pageSize)
            .collect(Collectors.toList());

    return data;
  }
}
