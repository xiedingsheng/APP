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
 * 入库单实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_IN_STORE")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuInStore implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_IN_STORE.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 入库单号，系统自动生成
	 * TU_IN_STORE.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 提单号
	 * TU_IN_STORE.BILL_NO
	 */
	@Column(name = "BILL_NO")
	private String billNo;

	/**
	 * 客户单位编号
	 * TU_IN_STORE.OWNER_CODE
	 */
	@Column(name = "OWNER_CODE")
	private String ownerCode;

	/**
	 * 客户单位名称
	 * TU_IN_STORE.OWNER_NAME
	 */
	@Column(name = "OWNER_NAME")
	private String ownerName;

	/**
	 * 出口口岸代码
	 * TU_IN_STORE.IE_PORT
	 */
	@Column(name = "IE_PORT")
	private String iePort;

	/**
	 * 目的港代码
	 * TU_IN_STORE.DESTINATION_PORT
	 */
	@Column(name = "DESTINATION_PORT")
	private String destinationPort;

	/**
	 * 货物性质 1可压 2不可压
	 * TU_IN_STORE.GOODS_TYPE
	 */
	@Column(name = "GOODS_TYPE")
	private String goodsType;

	/**
	 * 件数
	 * TU_IN_STORE.PACK_NO
	 */
	@Column(name = "PACK_NO")
	private Integer packNo;

	/**
	 * 重量
	 * TU_IN_STORE.WEIGHT
	 */
	@Column(name = "WEIGHT")
	private Double weight;

	/**
	 * 体积
	 * TU_IN_STORE.VOLUME
	 */
	@Column(name = "VOLUME")
	private Double volume;

	/**
	 * 清点件数
	 * TU_IN_STORE.CONFIRM_PACK_NO
	 */
	@Column(name = "CONFIRM_PACK_NO")
	private Integer confirmPackNo;

	/**
	 * 清点重量
	 * TU_IN_STORE.CONFIRM_WEIGHT
	 */
	@Column(name = "CONFIRM_WEIGHT")
	private Double confirmWeight;

	/**
	 * 清点体积
	 * TU_IN_STORE.CONFIRM_VOLUME
	 */
	@Column(name = "CONFIRM_VOLUME")
	private Double confirmVolume;

	/**
	 * 状态 0预录入 1接单申报 2入库关
	 * TU_IN_STORE.STATUS
	 */
	@Column(name = "STATUS")
	private String status;

	/**
	 * 录入人代码
	 * TU_IN_STORE.INPUT_USER
	 */
	@Column(name = "INPUT_USER")
	private String inputUser;

	/**
	 * 录入时间
	 * TU_IN_STORE.INPUT_TIME
	 */
	@Column(name = "INPUT_TIME")
	private Date inputTime;

	/**
	 * 接单申报人代码
	 * TU_IN_STORE.APPLY_USER
	 */
	@Column(name = "APPLY_USER")
	private String applyUser;

	/**
	 * 接单申报时间
	 * TU_IN_STORE.APPLY_TIME
	 */
	@Column(name = "APPLY_TIME")
	private Date applyTime;

	/**
	 * 入库关联人代码
	 * TU_IN_STORE.RELATION_USER
	 */
	@Column(name = "RELATION_USER")
	private String relationUser;

	/**
	 * 入库关联时间
	 * TU_IN_STORE.RELATION_TIME
	 */
	@Column(name = "RELATION_TIME")
	private Date relationTime;

	/**
	 * 入库确认人代码
	 * TU_IN_STORE.CONFIRM_USER
	 */
	@Column(name = "CONFIRM_USER")
	private String confirmUser;

	/**
	 * 入库确认时间
	 * TU_IN_STORE.CONFIRM_TIME
	 */
	@Column(name = "CONFIRM_TIME")
	private Date confirmTime;

	/**
	 * 作废人代码
	 * TU_IN_STORE.INVALID_USER
	 */
	@Column(name = "INVALID_USER")
	private String invalidUser;

	/**
	 * 作废时间
	 * TU_IN_STORE.INVALID_TIME
	 */
	@Column(name = "INVALID_TIME")
	private Date invalidTime;

	/**
	 * 备注
	 * TU_IN_STORE.REMARK
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 录入单位代码
	 * TU_IN_STORE.INPUT_COMP_CODE
	 */
	@Column(name = "INPUT_COMP_CODE")
	private String inputCompCode;

	/**
	 * 录入单位名称
	 * TU_IN_STORE.INPUT_COMP_NAME
	 */
	@Column(name = "INPUT_COMP_NAME")
	private String inputCompName;

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
