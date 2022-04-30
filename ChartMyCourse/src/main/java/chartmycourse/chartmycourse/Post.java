package chartmycourse.chartmycourse;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
}
