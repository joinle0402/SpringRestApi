package com.johnsmith.springrestapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
	@NotBlank(message = "Product name is required!")
	@Size(min = 3, max = 255)
	private String name;

	@Min(value = 1_000_000L, message = "Product price must be greater than or equal to 1000000 and less than or equal to 10000000")
	@Max(value = 10_000_000L, message = "Product price must be greater than or equal to 1000000 and less than or equal to 10000000")
	private Long price;
}
