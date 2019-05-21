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
 * 日志实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "SYSTEM_LOG")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class SystemLog implements Serializable {

	/**
	 * 主键，GUID
	 * SYSTEM_LOG.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 类型 1系统日志 2操作日志
	 * SYSTEM_LOG.FLAG
	 */
	@Column(name = "FLAG")
	private String flag;

	/**
	 * 日志内容
	 * SYSTEM_LOG.MESSAGE
	 */
	@Column(name = "MESSAGE")
	private String message;

	/**
	 * 录入人代码
	 * SYSTEM_LOG.INPUT_USER
	 */
	@Column(name = "INPUT_USER")
	private String inputUser;

	/**
	 * 录入时间
	 * SYSTEM_LOG.INPUT_TIME
	 */
	@Column(name = "INPUT_TIME")
	private Date inputTime;

	/**
	 * 业务单据号
	 * SYSTEM_LOG.SUBJECT_NO
	 */
	@Column(name = "SUBJECT_NO")
	private String subjectNo;

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

	private static final long serialVersionUID = 1L;
}
