package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(String merchant_no, String product_name, String product_status, Integer product_pr,
			String product_typ, String product_pn, String product_ps, byte[] product_img, Integer product_quan) {

		ProductVO productVO = new ProductVO();

		productVO.setMerchant_no(merchant_no);
		productVO.setProduct_name(product_name);
		productVO.setProduct_status(product_status);
		productVO.setProduct_pr(product_pr);
		productVO.setProduct_typ(product_typ);
		productVO.setProduct_pn(product_pn);
		productVO.setProduct_ps(product_ps);
		productVO.setProduct_img(product_img);
		productVO.setProduct_quan(product_quan);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(String product_no, String merchant_no, String product_name, String product_status,
			Integer product_pr, String product_typ, String product_pn, String product_ps, byte[] product_img,
			Integer product_quan) {

		ProductVO productVO = new ProductVO();

		productVO.setProduct_no(product_no);
		productVO.setMerchant_no(merchant_no);
		productVO.setProduct_name(product_name);
		productVO.setProduct_status(product_status);
		productVO.setProduct_pr(product_pr);
		productVO.setProduct_typ(product_typ);
		productVO.setProduct_pn(product_pn);
		productVO.setProduct_ps(product_ps);
		productVO.setProduct_img(product_img);
		productVO.setProduct_quan(product_quan);

		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(String product_no) {
		dao.delete(product_no);
	}

	public ProductVO getOneProduct(String product_no) {
		return dao.findByPrimaryKey(product_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	// 查詢單一廠商有關的商品
	public List<ProductVO> findByMerchantNo(String merchant_no) {
		return dao.findByMerchantNo(merchant_no);
	}

	// 修改單一商品狀態
	public ProductVO updateProductStatus(String product_no, String product_status) {

		ProductVO productVO = new ProductVO();

		productVO.setProduct_no(product_no);

		productVO.setProduct_status(product_status);

		dao.updateProductStatus(productVO);

		return productVO;
	}

	// 查詢單一狀態有關商品
	public List<ProductVO> getOneStatusOfAll(String product_status) {
		return dao.getOneStatusOfAll(product_status);
	}

	// 查詢單一狀態有關商品
	public List<ProductVO> getOneStatusOfAll(String product_status, String product_pn) {
		return dao.getOneStatusOfAll(product_status, product_pn);
	}
	
	
	public ProductVO updateProduct(ProductVO productVO) {
		dao.update(productVO);

		return productVO;
			
	}
	
	//修改單一商品廣告狀態
		public ProductVO updateProductOfPN(String product_no, String product_pn) {

			ProductVO productVO = new ProductVO();
			        
			productVO.setProduct_no(product_no);

			productVO.setProduct_status(product_pn);

				    
			dao.updateProductOfPN(productVO);

			return productVO;
		}

}
