package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Content")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "content_id")
	private long id;
	protected String title;
	protected String text;
	protected String markdown;
	@JsonIgnore
	protected Instant creationDate;
	protected Instant releaseDate;
	protected Instant lastEditDate;
	protected boolean hasBeenEdited;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	protected UserProfile profile;
	public Content()
	{
	}

	public Content(String title, String text, String markdown, UserProfile profile)
	{
		super();
		this.title = title;
		this.text = text;
		this.markdown = markdown;
		this.creationDate = Instant.now();
		this.hasBeenEdited = false;
		this.profile = profile;
	}

	private void updateLastEditDate()
	{
		if (hasBeenEdited)
			lastEditDate = Instant.now();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
		updateLastEditDate();
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		updateLastEditDate();
	}

	public String getMarkdown()
	{
		return markdown;
	}

	public void setMarkdown(String markdown)
	{
		this.markdown = markdown;
		updateLastEditDate();
	}

	public Instant getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(Instant releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public Instant getLastEditDate()
	{
		return lastEditDate;
	}

	public void setLastEditDate(Instant lastEditDate)
	{
		this.lastEditDate = lastEditDate;
	}

	public boolean isHasBeenEdited()
	{
		return hasBeenEdited;
	}

	public void setHasBeenEdited(boolean hasBeenEdited)
	{
		this.hasBeenEdited = hasBeenEdited;
	}

	public long getId()
	{
		return id;
	}

	public Instant getCreationDate()
	{
		return creationDate;
	}

	public UserProfile getProfile()
	{
		return profile;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (creationDate == null)
		{
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Content [id=" + id + ", title=" + title + ", text=" + text + ", releaseDate=" + releaseDate + ", lastEditDate=" + lastEditDate
				+ ", hasBeenEdited=" + hasBeenEdited + "]";
	}
	
	

}
