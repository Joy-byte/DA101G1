package com.game_detail.model;

import java.util.List;

public interface Game_detailDAO_interface {
	    public void insert(Game_detailVO game_detailVO);
	    public void update(Game_detailVO game_detailVO);
	    public void delete(String gameroom_no ,String mem_no);
	    public Game_detailVO findByPrimaryKey(String gameroom_no ,String mem_no);
        public List<Game_detailVO> getAll();
}
