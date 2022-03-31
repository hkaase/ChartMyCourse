package chartmycourse.chartmycourse;

import java.util.Objects;


public class review {
    public String author;
    public String CRN;
    public String course;
    public String professor;
    int rating;
    String reviewBody;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCRN() {
		return CRN;
	}
	public void setCRN(String cRN) {
		CRN = cRN;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewBody() {
		return reviewBody;
	}
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
	@Override
	public int hashCode() {
		return Objects.hash(CRN, author, course, professor, rating, reviewBody);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		review other = (review) obj;
		return Objects.equals(CRN, other.CRN) && Objects.equals(author, other.author)
				&& Objects.equals(course, other.course) && Objects.equals(professor, other.professor)
				&& rating == other.rating && Objects.equals(reviewBody, other.reviewBody);
	}
    
    
}
