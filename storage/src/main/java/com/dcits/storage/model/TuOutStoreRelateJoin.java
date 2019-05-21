package com.dcits.storage.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 出库单关联实体类（多表关联）
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_OUT_STORE_RELATE")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuOutStoreRelateJoin implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_OUT_STORE_RELATE.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 出库单号
	 * TU_OUT_STORE_RELATE.OUT_STORE_NO
	 */
	@Column(name = "OUT_STORE_NO")
	private String outStoreNo;

	/**
	 * 对应入库单号
	 * TU_OUT_STORE_RELATE.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 对应提单号
	 * TU_OUT_STORE_RELATE.BILL_NO
	 */
	@Column(name = "BILL_NO")
	private String billNo;

	/**
	 * 条码编号
	 * TU_OUT_STORE_RELATE.BAR_CODE
	 */
	@Column(name = "BAR_CODE")
	private String barCode;

	/**
	 * 出库关联状态 1待出库 2出库关联
	 * TU_OUT_STORE_RELATE.RELATE_STATUS
	 */
	@Column(name = "RELATE_STATUS")
	private String relateStatus;

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

	/**
	 * 出库关联状态名称
	 */
	private String relateStatusName;

	private static final long serialVersionUID = 1L;
}
