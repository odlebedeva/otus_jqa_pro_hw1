
package pojo.xml.com.dataaccess.webservicesserver;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "dNum" })
@XmlRootElement(name = "NumberToDollars")
public class NumberToDollars {
  @XmlElement(required = true)
  protected BigDecimal dNum;

  public BigDecimal getdNum() {
    return dNum;
  }
  public void setdNum(BigDecimal value) {
    this.dNum = value;
  }
}