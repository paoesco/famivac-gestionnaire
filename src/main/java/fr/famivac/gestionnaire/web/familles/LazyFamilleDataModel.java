package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.familles.boundary.FamilleDTO;
import fr.famivac.gestionnaire.web.utils.LazyFilter;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class LazyFamilleDataModel extends LazyDataModel<FamilleDTO> {

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
  public String getRowKey(FamilleDTO bean) {
    return bean.getId().toString();
  }

  @Override
  public List<FamilleDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    var filter = new LazyFilter<>(filterBy.values());
    List<Comparator<FamilleDTO>> comparators =
        sortBy.values().stream()
            .map(o -> new LazySorter<>(FamilleDTO.class, o.getField(), o.getOrder()))
            .collect(Collectors.toList());
    var comparator = ComparatorUtils.chainedComparator(comparators);
    var rowCount = datasource.stream().filter(filter).count();

    setRowCount((int) rowCount);
    return datasource.stream()
        .filter(filter)
        .sorted(comparator)
        .skip(first)
        .limit(pageSize)
        .collect(Collectors.toList());
  }
}
