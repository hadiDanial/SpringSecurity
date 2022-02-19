package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Images")
@Inheritance(strategy = InheritanceType.JOINED)
public class DBImage
{
	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private long id;
	
	@Lob
	private byte[] content;
	
	private String name;
	private Instant uploadDate;
	public DBImage()
	{
	}
	public DBImage(long id, byte[] content, String name, Instant uploadDate)
	{
		super();
		this.id = id;
		this.content = content;
		this.name = name;
		this.uploadDate = uploadDate;
	}
	public byte[] getContent()
	{
		return content;
	}
	public void setContent(byte[] content)
	{
		this.content = content;
	}
	public long getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Instant getUploadDate()
	{
		return uploadDate;
	}
}
