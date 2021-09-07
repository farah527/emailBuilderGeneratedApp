package com.fastcode.emailtest2.addons.emailbuilder.domain.model;

import com.fastcode.emailtest2.domain.core.abstractentity.AbstractEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "EmailVariable")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class EmailVariableEntity {
	
	@Id
	@EqualsAndHashCode.Include()
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic
	@Column(name = "PropertyName", nullable = false ,length = 50)
    private String propertyName;
    
    @Basic
	@Column(name = "PropertyType", nullable = false ,length = 50)
    private String propertyType;
    
    @Basic
	@Column(name = "DefaultValue", nullable = true ,length = 100)
    private String defaultValue;
    
    @Basic
	@Column(name = "MergeType", nullable = true ,length = 50)
    private String mergeType;
    

	public EmailVariableEntity(String propertyName, String propertyType, String defaultValue,String mergeTye) {
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.defaultValue = defaultValue;
		this.mergeType=mergeTye;
	}
	
	public EmailVariableEntity(Long id2) {
		this.id=id2;
	}


}
