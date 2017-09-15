package pl.sikorski.kamil.springapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "System")
public class System {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint NOT NULL")
	private long id;
	@Column(name = "name", columnDefinition = "character varying(50) UNIQUE")
	private String name;
	@Column(name = "description", columnDefinition = "character varying(500)")
	private String description;
	@Column(name = "support_group", columnDefinition = "character varying(50) UNIQUE")
	private String supportGroup;

	// Used for tests
	// @OneToMany(mappedBy = "system")
	// private List<SystemContract> systemContracts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupportGroup() {
		return supportGroup;
	}

	public void setSupportGroup(String supportGroup) {
		this.supportGroup = supportGroup;
	}

//	public List<SystemContract> getSystemContracts() {
//		return systemContracts;
//	}
//
//	public void setSystemContracts(List<SystemContract> systemContracts) {
//		this.systemContracts = systemContracts;
//	}

}
