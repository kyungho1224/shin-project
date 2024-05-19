package com.side.db.mapper;

import com.side.db.dto.ProductDto;
import com.side.db.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.IGNORE,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

	Product toProduct(ProductDto.RegisterRequest request);

	ProductDto.RegisterResponse toRegisterResponse(Product product);

	ProductDto.SimpleInfo toSimpleInfo(Product product);

	List<ProductDto.SimpleInfo> toSimpleInfoList(List<Product> products);

	ProductDto.DetailInfo toDetailInfo(Product product);

}
