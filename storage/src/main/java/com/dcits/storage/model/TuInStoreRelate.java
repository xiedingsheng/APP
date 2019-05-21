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
 * 入库单关联实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_IN_STORE_RELATE")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuInStoreRelate implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_IN_STORE_RELATE.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 入库单号
	 * TU_IN_STORE_RELATE.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 条码编号
	 * TU_IN_STORE_RELATE.BAR_CODE
	 */
	@Column(name = "BAR_CODE")
	private String barCode;

	/**
	 * 库位号
	 * TU_IN_STORE_RELATE.LOCATION_NO
	 */
	@Column(name = "LOCATION_NO")
	private String locationNo;

	/**
	 * 托盘长度（米）
	 * TU_IN_STORE_RELATE.LENGTH
	 */
	@Column(name = "LENGTH")
	private Double length;

	/**
	 * 托盘宽度（米）
	 * TU_IN_STORE_RELATE.WIDTH
	 */
	@Column(name = "WIDTH")
	private Double width;

	/**
	 * 托盘高度（米）
	 * TU_IN_STORE_RELATE.HEIGHT
	 */
	@Column(name = "HEIGHT")
	private Double height;

	/**
	 * 托盘体积（立方米）
	 * TU_IN_STORE_RELATE.VOLUME
	 */
	@Column(name = "VOLUME")
	private Double volume;

	/**
	 * 托盘重量（千克）
	 * TU_IN_STORE_RELATE.WEIGHT
	 */
	@Column(name = "WEIGHT")
	private Double weight;

	/**
	 * 关联状态 2入库关联 3入库确认 4
	 * TU_IN_STORE_RELATE.RELATE_STATUS
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

	private static final long serialVersionUID = 1L;
}
