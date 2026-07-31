package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "accontId", target = "accountId")
    @Mapping(source = "accountNur", target = "accountNumber")
    ProductDto toDto(Product product);

    @Mapping(source = "accountId", target = "accontId")
    @Mapping(source = "accountNumber", target = "accountNur")
    Product toEntity(ProductDto dto);
}
