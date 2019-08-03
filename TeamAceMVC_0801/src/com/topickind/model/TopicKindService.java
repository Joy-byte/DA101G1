package com.topickind.model;

import java.util.List;

import com.product.model.ProductVO;
import com.topic.model.TopicVO;

public class TopicKindService {

	private TopicKindDAO_interface dao;

	public TopicKindService() {
		dao = new TopicKindDAO();
	}

	public TopicKindVO addTopicKind(String topicKind_name) {

		TopicKindVO topicKindVO = new TopicKindVO();

		topicKindVO.setTopicKind_name(topicKind_name);
		dao.insert(topicKindVO);

		return topicKindVO;
	}

	public TopicKindVO updateTopicKind(String topicKind_no, String topicKind_name) {

		 TopicKindVO topicKindVO = new  TopicKindVO();
        
		 topicKindVO.setTopicKind_no(topicKind_no);
		 topicKindVO.setTopicKind_name(topicKind_name);
   		dao.update( topicKindVO);

		return  topicKindVO;
	}

	public void deleteTopicKind(String topicKind_no) {
		dao.delete(topicKind_no);
	}

	public TopicKindVO getOneTopicKind(String topicKind_no) {
		return dao.findByPrimaryKey(topicKind_no);
	}

	public List<TopicKindVO> getAll() {
		return dao.getAll();
	}
}
