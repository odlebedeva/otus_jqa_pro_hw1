package pojo.xml.com.dataaccess.webservicesserver;

import lombok.Data;
import javax.xml.bind.annotation.*;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"numberToWordsResult"})
@XmlRootElement(name="NumberToWordsResponse")
public class NumberToWordsResponse {
  @XmlElement(name="NumberToWordsResult", required=true)
  protected String numberToWordsResult;
}