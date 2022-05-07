package chartmycourse.chartmycourse;

import java.util.Objects;

public class Reply {

    String author, postContents;
    int upvotes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply reply)) return false;
        return upvotes == reply.upvotes && Objects.equals(author, reply.author) && Objects.equals(postContents, reply.postContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, postContents, upvotes);
    }
}
