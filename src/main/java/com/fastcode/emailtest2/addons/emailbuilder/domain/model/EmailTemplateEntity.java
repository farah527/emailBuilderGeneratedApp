package com.fastcode.emailtest2.addons.emailbuilder.domain.model;

import com.fastcode.emailtest2.domain.core.abstractentity.AbstractEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Email")
@Getter @Setter
public class EmailTemplateEntity  {
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	@Column(name = "TemplateName", nullable = false ,length = 256)
	private String templateName;
	
	@Basic
	@Column(name = "Category", nullable = true ,length = 256)
	private String category;
	
	@Basic
	@Column(name = "ContentHtml", nullable = true, length = 32768)
	private String contentHtml;
	
	@Basic
	@Column(name = "ContentJson", nullable = true, length = 32768)	
	private String contentJson;
	
	@Basic
	@Column(name = "To_", nullable = false,length = 256)	
	@Email
	private String to;
	
	@Basic
	@Column(name = "Cc", nullable = true ,length = 256)
	@Email
	private String cc;

	@Basic
	@Column(name = "Bcc", nullable = true ,length = 256)
	//@Email
	private String bcc;

	@Basic
	@Column(name = "Subject", nullable = true ,length = 256)
	private String subject;

	@Basic
	@Column(name = "active", nullable = true)
	private Boolean active;

	@Basic
	@Column(name = "attachmentpath", nullable = true, length =256)
	private String attachmentpath;
	
	@Basic
	@Column(name = "description", nullable = true, length =512)
	private String description;
	
	public EmailTemplateEntity(Long emailTemplateId) {
		this.id=emailTemplateId;
	}
    public EmailTemplateEntity() {
	}
	
}

