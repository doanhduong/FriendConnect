package com.it4996.object;

public class Article {
	private String id;
	private String poster;
	private String content;
	private String url_image;
	private String post_date;
	private String update_date;
	private String post_location;
	private int countLike, countComment;

	public Article(String id, String poster, String content) {
		this.id = id;
		this.poster = poster;
		this.content = content;
	}

	public Article() {

	}

	public int getCountLike() {
		return countLike;
	}

	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}

	public int getCountComment() {
		return countComment;
	}

	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getPost_location() {
		return post_location;
	}

	public void setPost_location(String post_location) {
		this.post_location = post_location;
	}

}
