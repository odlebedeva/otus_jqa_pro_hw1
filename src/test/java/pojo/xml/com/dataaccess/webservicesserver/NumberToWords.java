package pojo.xml.com.dataaccess.webservicesserver;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ubiNum" })
@XmlRootElement(name = "NumberToWords")
public class NumberToWords {
  @XmlElement(required = true)
  @XmlSchemaType(name = "unsignedLong")
  protected BigInteger ubiNum;
}