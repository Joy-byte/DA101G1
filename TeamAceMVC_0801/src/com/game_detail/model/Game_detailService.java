package com.game_detail.model;

import java.util.List;





public class Game_detailService {

	private Game_detailDAO_interface dao;
	public Game_detailService() {
		dao = new Game_detailDAO();
	}

	public Game_detailVO addGad( String gameroom_no, String mem_no,
			String lens_status, String topic_no, Integer room_num) {

		Game_detailVO game_detailVO = new Game_detailVO();
		game_detailVO.setGameroom_no(gameroom_no);
		game_detailVO.setMem_no(mem_no);
		game_detailVO.setLens_status(lens_status);
		game_detailVO.setTopic_no(topic_no);
		game_detailVO.setRoom_num(room_num);
		dao.insert(game_detailVO);

		return game_detailVO;
	}
	
	
	public Game_detailVO updateGad(String gameroom_no, String mem_no,
			String lens_status, String topic_no, Integer room_num) {
		Game_detailVO game_detailVO = new Game_detailVO();
		game_detailVO.setGameroom_no(gameroom_no);
		game_detailVO.setMem_no(mem_no);
		game_detailVO.setLens_status(lens_status);
		game_detailVO.setTopic_no(topic_no);
		game_detailVO.setRoom_num(room_num);
		dao.update(game_detailVO);
		return game_detailVO;
	}
	

	public void deleteGad(String gameroom_no,String mem_no) {
		dao.delete(gameroom_no,mem_no);
	}
	
	
	public Game_detailVO getOneGad(String gameroom_no,String mem_no) {
		return dao.findByPrimaryKey(gameroom_no,mem_no);
	}
	
	public List<Game_detailVO> getAll() {
		return dao.getAll();
	}
}
