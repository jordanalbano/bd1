package ar.edu.tp.utils;

import ar.edu.tp.model.Sale;
import ar.edu.tp.model.SaleDto;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    SaleDto convert(Sale sale);
    @Mapping(target = "client.creditCards", ignore = true)
    @Mapping(target = "shoppingCart.client.creditCards", ignore = true)
    Sale convert(SaleDto saleDto);
}
