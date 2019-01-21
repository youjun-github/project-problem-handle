package com.sigmatrix.entity.top;

import java.util.Date;
import javax.persistence.*;

@Table(name = "multistagelevelqrcode")
public class Multistagelevelqrcode {
    @Id
    @Column(name = "pack_content")
    private String packContent;

    @Column(name = "seq_num")
    private String seqNum;

    @Column(name = "current_gyl_product_id")
    private Integer currentGylProductId;

    @Column(name = "new_gyl_product_id")
    private Integer newGylProductId;

    @Column(name = "top_product_id")
    private Integer topProductId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否处理,，1是，0否
     */
    @Column(name = "is_handle")
    private Integer isHandle;

    /**
     * @return pack_content
     */
    public String getPackContent() {
        return packContent;
    }

    /**
     * @param packContent
     */
    public void setPackContent(String packContent) {
        this.packContent = packContent == null ? null : packContent.trim();
    }

    /**
     * @return seq_num
     */
    public String getSeqNum() {
        return seqNum;
    }

    /**
     * @param seqNum
     */
    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum == null ? null : seqNum.trim();
    }

    /**
     * @return current_gyl_product_id
     */
    public Integer getCurrentGylProductId() {
        return currentGylProductId;
    }

    /**
     * @param currentGylProductId
     */
    public void setCurrentGylProductId(Integer currentGylProductId) {
        this.currentGylProductId = currentGylProductId;
    }

    /**
     * @return new_gyl_product_id
     */
    public Integer getNewGylProductId() {
        return newGylProductId;
    }

    /**
     * @param newGylProductId
     */
    public void setNewGylProductId(Integer newGylProductId) {
        this.newGylProductId = newGylProductId;
    }

    /**
     * @return top_product_id
     */
    public Integer getTopProductId() {
        return topProductId;
    }

    /**
     * @param topProductId
     */
    public void setTopProductId(Integer topProductId) {
        this.topProductId = topProductId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否处理,，1是，0否
     *
     * @return is_handle - 是否处理,，1是，0否
     */
    public Integer getIsHandle() {
        return isHandle;
    }

    /**
     * 设置是否处理,，1是，0否
     *
     * @param isHandle 是否处理,，1是，0否
     */
    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }
}