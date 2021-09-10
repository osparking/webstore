package com.ezen.webstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.webstore.domain.Product;
import com.ezen.webstore.service.ProductService;

@Controller
@RequestMapping("market")
//@formatter:of
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 신상품 추가 폼을 반환한다.
	 * @param model 빈을 연결해주는 기본 자료
	 * @return 폼 LVN(논리뷰명칭)
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(
			@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}

	/**
	 * 신상품 정보를 FBBean(Form Backing Bean)을 통해서 받아와서 저장한다.
	 * @param newProduct 폼 배킹 빈
	 * @param result 폼에 들어온 자료를 평가한 결과
	 * @return 방향전환(redirect) LVN
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") 
			Product newProduct, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("엮어오려는 허용되지 않은 항목 : " + 
			StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}

	@RequestMapping("/update/stock")
	public String updateAllStock() {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, 
			@PathVariable("category") String category) {
		model.addAttribute("products", 
				productService.getProductsByCategory(category));

		return "products";
	}

	@RequestMapping("/products/laptop")
	public String laptop(Model model) {
		model.addAttribute("products", productService.getAllProducts("laptop"));
		return "products";
	}

	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filter) {
		model.addAttribute("products", productService.getProductsByFilter(filter));

		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(Model model, @RequestParam("id") String productId) {
		model.addAttribute("product", productService.getProductById(productId));

		return "product";
	}
}
