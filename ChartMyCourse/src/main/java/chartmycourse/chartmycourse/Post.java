package chartmycourse.chartmycourse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

public class Post {
	String author, postContents;
	
	//not yet implemented
	/*
	static public JTable replies;
	static {
		replies.setModel(new DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Review Author", "CRN", "Course", "Professor", "Review Score/10", "View Review"
	            }
	        ) {
	            Class[] types = new Class [] {
	                String.class, String.class, String.class, String.class, Integer.class, Object.class
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	        });
	}
	*/
	int replyCount, upvotes;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}

	public int getReplies() {
		return replyCount;
	}

	public void setReplies(int replies) {
		this.replyCount = replies;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Post post)) return false;
		return replyCount == post.replyCount && upvotes == post.upvotes && Objects.equals(author, post.author) && Objects.equals(postContents, post.postContents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, postContents, replyCount, upvotes);
	}
}
