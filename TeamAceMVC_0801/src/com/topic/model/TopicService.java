package com.topic.model;

import java.util.List;

import com.product.model.ProductVO;

public class TopicService {
	
	private TopicDAO_interface dao;

	public TopicService() {
		dao = new TopicDAO();
	}

	public TopicVO addTopic(String topicKind_no, String topic_name) {

		TopicVO topicVO = new TopicVO();

		topicVO.setTopicKind_no(topicKind_no);
		topicVO.setTopic_name(topic_name);
		dao.insert(topicVO);

		return topicVO;
	}
	
	public TopicVO updateTopic(String topic_no, String topicKind_no, String topic_name){

		TopicVO topicVO = new TopicVO();
        
		topicVO.setTopic_no(topic_no);
		topicVO.setTopicKind_no(topicKind_no);
		topicVO.setTopic_name(topic_name);

		dao.update(topicVO);

		return topicVO;
	}

	public void deleteTopic(String topic_no) {
		dao.delete(topic_no);
	}

	public TopicVO getOneTopic(String topic_no) {
		return dao.findByPrimaryKey(topic_no);
	}

	public List<TopicVO> getAll() {
		return dao.getAll();
	}
}
