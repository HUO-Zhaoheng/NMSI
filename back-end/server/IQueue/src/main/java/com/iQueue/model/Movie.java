package com.iQueue.model;

/* ����������Ϣ */
public class Movie {
	
	public String Ticket;  // ʣ��Ʊ
	public String MovieName;  // ����
	public void setMovieName(String name) {
		this.MovieName = name;
	}
	public String getMovieName() {
		return MovieName;
	}
	
	public void setTicket(String ticket) {
		this.Ticket = ticket;
	}
	
	public String getTicket() {
		return Ticket;
	}
}
