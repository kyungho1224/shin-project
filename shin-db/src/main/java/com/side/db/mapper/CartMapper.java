package com.side.db.mapper;

import com.side.db.dto.CartDto;
import com.side.db.entity.Cart;
import com.side.db.entity.CartItem;
import com.side.db.entity.Customer;
import com.side.db.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {

	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

	Cart createCart(Customer customer);

	CartItem addCart(Product product, int quantity);

	CartItem getOneCartItem(Long cartItemId);

	Cart getCartList(Customer customer);

	List<CartDto.List> getAllCartItem(List<CartItem> cartItemList);

}
