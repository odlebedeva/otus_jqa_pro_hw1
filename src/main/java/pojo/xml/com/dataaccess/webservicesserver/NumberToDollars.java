
package pojo.xml.com.dataaccess.webservicesserver;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "dnum" })
@XmlRootElement(name = "NumberToDollars")
public class NumberToDollars {
  @XmlElement(required = true)
  protected BigDecimal dnum;

  public BigDecimal getdnum() {
    return dnum;
  }
  public void setdnum(BigDecimal value) {
    this.dnum = value;
  }
}