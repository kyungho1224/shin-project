package com.side.api.service;

import com.side.db.constant.RegisterStatus;
import com.side.db.dto.ProductDto;
import com.side.db.entity.Product;
import com.side.db.mapper.ProductMapper;
import com.side.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Transactional(readOnly = true)
	public Page<ProductDto.SimpleInfo> selectAllProducts(Pageable pageable) {
		return productRepository.findAllByRegisterStatusOrderByCreatedAtDesc(RegisterStatus.REGISTERED, pageable)
		  .map(productMapper::toSimpleInfo);
	}

	@Transactional(readOnly = true)
	public ProductDto.DetailInfo selectOneProduct(Long productId) {
		Product validProduct = productRepository.getValidProductOrThrow(productId, RegisterStatus.REGISTERED);
		return productMapper.toDetailInfo(validProduct);
	}

}
