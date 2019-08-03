package com.question.model;

public class JedisQuestionVO implements java.io.Serializable{
	private String qusetion;
	private String qusetionOption1;
	private String qusetionOption2;
	private String qusetionOption3;
	private String answer;
	
	public JedisQuestionVO() {
		super();
	}


	
	public JedisQuestionVO(String qusetion, String qusetionOption1, String qusetionOption2, String qusetionOption3,
			String answer) {
		super();
		this.qusetion = qusetion;
		this.qusetionOption1 = qusetionOption1;
		this.qusetionOption2 = qusetionOption2;
		this.qusetionOption3 = qusetionOption3;
		this.answer = answer;
	}



	public String getQusetion() {
		return qusetion;
	}
	public void setQusetion(String qusetion) {
		this.qusetion = qusetion;
	}
	public String getQusetionOption1() {
		return qusetionOption1;
	}
	public void setQusetionOption1(String qusetionOption1) {
		this.qusetionOption1 = qusetionOption1;
	}
	public String getQusetionOption2() {
		return qusetionOption2;
	}
	public void setQusetionOption2(String qusetionOption2) {
		this.qusetionOption2 = qusetionOption2;
	}
	public String getQusetionOption3() {
		return qusetionOption3;
	}
	public void setQusetionOption3(String qusetionOption3) {
		this.qusetionOption3 = qusetionOption3;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

	
}
