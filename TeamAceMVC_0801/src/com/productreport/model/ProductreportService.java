package com.productreport.model;

import java.util.List;

public class ProductreportService {
	private ProductreportDAO_interface dao;

	public ProductreportService() {
		dao = new ProductreportDAO();
	}

	public ProductreportVO addProductreport(String product_no, String  reportcon_no, String  reportcon_status) {

		ProductreportVO productreportVO = new ProductreportVO();

		productreportVO.setProduct_no(product_no);
		productreportVO.setReportcon_no(reportcon_no);
		productreportVO.setReportcon_status(reportcon_status);
		dao.insert(productreportVO);

		return productreportVO;
	}

	public ProductreportVO updateProductreport(String productreport_no, String product_no, String  reportcon_no, String  reportcon_status) {

		ProductreportVO productreportVO = new ProductreportVO();
		
		productreportVO.setProductreport_no(productreport_no);
		productreportVO.setProduct_no(product_no);
		productreportVO.setReportcon_no(reportcon_no);
		productreportVO.setReportcon_status(reportcon_status);
		dao.update(productreportVO);

		return productreportVO;
	}

	public void deleteProductreport(String productreport_no) {
		dao.delete(productreport_no);
	}

	public ProductreportVO getOneProductreport(String productreport_no) {
		return dao.findByPrimaryKey(productreport_no);
	}

	public List<ProductreportVO> getAll() {
		return dao.getAll();
	}
	
	
	// 更改用單一檢舉編號更改審核狀態並ProductreportVO傳回
	public ProductreportVO updateProductreportStatus(String productreport_no, String product_no, String  reportcon_status) {

		ProductreportVO productreportVO = new ProductreportVO();
		
		productreportVO.setProductreport_no(productreport_no);
		productreportVO.setProduct_no(product_no);
		productreportVO.setReportcon_status(reportcon_status);
		dao.updateProductreportStatus(productreportVO);

		return productreportVO;
	}
}
