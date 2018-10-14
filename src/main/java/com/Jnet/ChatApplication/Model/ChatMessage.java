package com.Jnet.ChatApplication.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "chat_message")
public class ChatMessage {

    @Id
    private String id;

    private String content;
    private String author;
    private Date createDate;

	public ChatMessage(String content, String author, Date createDate) {
        this.content = content;
        this.author = author;
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }


    public String getAuthor() {
        return author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ChatMessage copy(){

    	ChatMessageBuilder copy = new ChatMessageBuilder();

    	copy.setAuthor(this.author)
			.setContent(this.content)
    		.setDate(this.createDate);

    	return copy.createChatMessage();
	}

    @Override
    public String toString() {

        return String.format("ChatMessage[id=%s, content= %s, author= %s, createDate= %s]",
                id, content, author, createDate.toString());
    }

    public String getDivFormattedStringFor(){

    	return String.format("<div class=\"container message\">\n" +
							"<p>%s: %s</p>\n" +
							"</div>", author.toString(), content.toString());
    }

	public static class ChatMessageBuilder {

		private String author;
		private String content;
		private Date date = new Date();

		public ChatMessageBuilder setContent(String content) {

			this.content = content;
			return this;
		}

		public ChatMessageBuilder setAuthor(String author) {

			this.author = author;
			return this;
		}

		public ChatMessageBuilder setDate(Date date) {

			this.date = date;
			return this;
		}

		public ChatMessage createChatMessage() {

			if (null == date)
				return new ChatMessage(content, author, new Date());
			else
				return new ChatMessage(content, author, date);
		}
	}
}
