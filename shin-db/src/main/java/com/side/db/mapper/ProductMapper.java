package com.side.db.mapper;

import com.side.db.dto.ProductDto;
import com.side.db.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	Product toProduct(ProductDto.RegisterRequest request);

	ProductDto.RegisterResponse toRegisterResponse(Product product);

	ProductDto.SimpleInfo toSimpleInfo(Product product);

	List<ProductDto.SimpleInfo> toSimpleInfoList(List<Product> products);

	ProductDto.DetailInfo toDetailInfo(Product product);

}
