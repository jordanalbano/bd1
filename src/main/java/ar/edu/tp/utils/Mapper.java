package ar.edu.tp.utils;

import ar.edu.tp.model.Sale;
import ar.edu.tp.model.SaleDto;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    SaleDto convert(Sale sale);
}
