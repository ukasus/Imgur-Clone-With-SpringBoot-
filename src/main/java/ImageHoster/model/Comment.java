package ImageHoster.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'images'. Hence the table named 'images' will be created in the database with all the columns mapped to all the attributes in 'Image' class
@Table(name = "comments")
public class Comment {

  //@Id annotation specifies that the corresponding attribute is a primary key
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column annotation specifies that the attribute will be mapped to the column in the database.
  //Here the column name is explicitly mentioned as 'id'
  @Column(name = "id")
  private Integer id;

  @Column(name = "text")
  private String text;

  // Text is a Postgres specific column type that allows you to save
  // text based data that will be longer than 256 characters
  // this is a base64 encoded version of the image



 

  @Column(name = "date")
  private Date createdDate;

  //The 'images' table is mapped to 'users' table with Many:One mapping
  //One image can have only one user (owner) but one user can have multiple images
  //FetchType is EAGER
  @ManyToOne(fetch = FetchType.EAGER)
  //Below annotation indicates that the name of the column in 'images' table referring the primary key in 'users' table will be 'user_id'
  @JoinColumn(name = "user_id")
  private User user;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "image_id")
  private Image image;
  
  

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}

  //The attribute contains a list of all the tags of an image
  //Note that no column will be generated for this attribute in the database instead a new table will be created
  //Since the mapping is Many to Many, a new table will be generated containing the two columns both referencing to the primary key of both the tables ('images', 'tags')
 

}
