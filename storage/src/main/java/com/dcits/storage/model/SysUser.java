package com.dcits.storage.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "SYS_USER")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class SysUser implements Serializable {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "creator")
	private String creator;

	@Column(name = "is_deleted")
	private String isDeleted;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "updater")
	private String updater;

	@Column(name = "version")
	private Integer version;

	@Column(name = "email")
	private String email;

	@Column(name = "is_enable")
	private String isEnable;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "role_id")
	private String roleId;

	@Column(name = "comp_code")
	private String compCode;

	@Column(name = "comp_name")
	private String compName;

	private static final long serialVersionUID = 1L;
}
