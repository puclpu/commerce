package com.sparta.commerce.product.controller;

import com.sparta.commerce.product.dto.ProductInfoDto;
import com.sparta.commerce.product.dto.ProductOptionInfoDto;
import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.service.ProductService;
import com.sparta.commerce.product.type.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Page<ProductSummaryDto>> getProducts(
      @RequestParam(value = "sortBy", required = false, defaultValue = "id")String sortBy,
      @RequestParam(value = "page")int page,
      @RequestParam(value = "size")int size,
      @RequestParam(value = "isAsc", required = false, defaultValue = "false")boolean isAsc,
      @RequestParam(value = "category", required = false) Category category) {
    Page<ProductSummaryDto> productSummaryDtoPage = productService.getProducts(sortBy, page-1, size, isAsc, category);
    return ResponseEntity.status(HttpStatus.OK).body(productSummaryDtoPage);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductInfoDto> getProduct(@PathVariable Long productId) {
    ProductInfoDto responseDto = productService.getProduct(productId);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @GetMapping("/{productId}/option")
  public ResponseEntity<List<ProductOptionInfoDto>> getProductOptions(@PathVariable Long productId) {
    List<ProductOptionInfoDto> productOptionInfoDtoList = productService.getProductOptions(productId);
    return ResponseEntity.status(HttpStatus.OK).body(productOptionInfoDtoList);
  }
}
