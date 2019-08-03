package com.topickind.model;

import java.util.List;

public interface TopicKindDAO_interface {
	public void insert(TopicKindVO topicKindVO);
    public void update(TopicKindVO topicKindVO);
    public void delete(String topicKind_no);
    public TopicKindVO findByPrimaryKey(String topicKind_no);
      public List<TopicKindVO> getAll();
}
