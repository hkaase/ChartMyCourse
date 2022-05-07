package chartmycourse.chartmycourse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Objects;

public class Post {
	String author, postContents;
	int replyCount, upvotes;

	ArrayList<Reply> replies = new ArrayList<>();

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

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public void setReplies(ArrayList<Reply> replies) {
		this.replies = replies;
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Post post)) return false;
		return replyCount == post.replyCount && upvotes == post.upvotes && Objects.equals(author, post.author) && Objects.equals(postContents, post.postContents) && Objects.equals(replies, post.replies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, postContents, replyCount, upvotes, replies);
	}
}
