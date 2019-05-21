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
 * 入库单关联记录实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_IN_STORE_RELATE_RECORD")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuInStoreRelateRecord implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_IN_STORE_RELATE_RECORD.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 入库单号
	 * TU_IN_STORE_RELATE_RECORD.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 条码编号
	 * TU_IN_STORE_RELATE_RECORD.BAR_CODE
	 */
	@Column(name = "BAR_CODE")
	private String barCode;

	/**
	 * 库位号
	 * TU_IN_STORE_RELATE_RECORD.LOCATION_NO
	 */
	@Column(name = "LOCATION_NO")
	private String locationNo;

	/**
	 * 操作人代码
	 * TU_IN_STORE_RELATE_RECORD.RELATION_USER
	 */
	@Column(name = "RELATION_USER")
	private String relationUser;

	/**
	 * 操作时间
	 * TU_IN_STORE_RELATE_RECORD.RELATION_TIME
	 */
	@Column(name = "RELATION_TIME")
	private Date relationTime;

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
