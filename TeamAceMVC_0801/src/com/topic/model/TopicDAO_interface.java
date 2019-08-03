package com.topic.model;

import java.util.List;

public interface TopicDAO_interface {
	  public void insert(TopicVO topicVO);
	  public void update(TopicVO topicVO);
	  public void delete(String topic_no);
	  public TopicVO findByPrimaryKey(String topic_no);
      public List<TopicVO> getAll();
}
