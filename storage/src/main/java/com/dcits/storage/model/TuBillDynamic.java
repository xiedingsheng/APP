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
 * 提单物流实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_BILL_DYNAMIC")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuBillDynamic implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_BILL_DYNAMIC.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 入库单号
	 * TU_BILL_DYNAMIC.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 提单号
	 * TU_BILL_DYNAMIC.BILL_NO
	 */
	@Column(name = "BILL_NO")
	private String billNo;

	/**
	 * 操作人代码
	 * TU_BILL_DYNAMIC.OPERATION_USER
	 */
	@Column(name = "OPERATION_USER")
	private String operationUser;

	/**
	 * 操作时间
	 * TU_BILL_DYNAMIC.OPERATION_TIME
	 */
	@Column(name = "OPERATION_TIME")
	private Date operationTime;

	/**
	 * 动态描述
	 * TU_BILL_DYNAMIC.REMARK
	 */
	@Column(name = "REMARK")
	private String remark;

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
