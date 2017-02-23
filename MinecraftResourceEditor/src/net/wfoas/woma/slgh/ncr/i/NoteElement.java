package net.wfoas.woma.slgh.ncr.i;

public class NoteElement {
	String name, author, content;

	public NoteElement(String name, String author, String content) {
		this.name = name;
		this.author = author;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void serialize() {

	}

}
